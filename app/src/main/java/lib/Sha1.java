package lib;

import java.io.UnsupportedEncodingException;

public class Sha1 {
    // Output format options
    public static final int OUT_HEX = 0;
    public static final int OUT_HEXW = 1;

    /**
     * Generates SHA-1 hash of string.
     *
     * Uses hash method with the OUT_HEX (no spaces) output format.
     *
     * @param msgIn String to be hashed.
     * @return Hash of msg as hex character string.
     * @throws UnsupportedEncodingException
     */
    public static String hash(String msgIn) throws UnsupportedEncodingException {
        return hash(msgIn, OUT_HEX);
    }

    /**
     * Generates SHA-1 hash of string.
     *
     * @param msgIn     String to be hashed.
     * @param outFormat Output format: OUT_HEX (0) for string of contiguous hex
     *                  bytes; OUT_HEXW (1) for grouping hex bytes into groups of (4
     *                  byte / 8 character) words.
     * @return Hash of msg as hex character string.
     * @throws UnsupportedEncodingException
     */

    public static String hash(String msgIn, int outFormat) throws UnsupportedEncodingException {

        // default is to convert string to UTF-8, as SHA only deals with byte-streams
        byte[] oldMsg = utf8Encode(msgIn);

        // constants [§4.2.1]
        int[] K = { 0x5a827999, 0x6ed9eba1, 0x8f1bbcdc, 0xca62c1d6 };

        // initial hash value [§5.3.1]
        int[] H = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476, 0xc3d2e1f0 };

        // PREPROCESSING [§6.1.1]
        byte[] msg = new byte[oldMsg.length + 1];
        for (int i = 0; i < oldMsg.length; i++) {
            msg[i] = oldMsg[i];
        }
        msg[oldMsg.length] = (byte) 0x80; // add trailing '1' bit (+ 0's padding) to string [§5.1.1]

        // convert string msg into 512-bit/16-integer blocks arrays of ints [§5.2.1]
        int l = msg.length / 4 + 2; // length (in 32-bit integers) of msg + ‘1’ + appended length
        int N = (int) Math.ceil(l / 16.0); // number of 16-integer-blocks required to hold 'l' ints
        int[][] M = new int[N][16];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 16; j++) { // encode 4 chars per integer, big-endian encoding
                M[i][j] = (getCharCode(msg, i * 64 + j * 4 + 0) << 24) | (getCharCode(msg, i * 64 + j * 4 + 1) << 16)
                        | (getCharCode(msg, i * 64 + j * 4 + 2) << 8) | (getCharCode(msg, i * 64 + j * 4 + 3) << 0);
            } // note running off the end of msg is ok 'cos bitwise ops on NaN return 0
        }

        // add length (in bits) into final pair of 32-bit integers (big-endian) [§5.1.1]
        // note: most significant word would be (len-1)*8 >>> 32, but since JS converts
        // bitwise-op args to 32 bits, we need to simulate this by arithmetic operators
        M[N - 1][14] = (int) Math.floor(((msg.length - 1) * 8) / Math.pow(2, 32));
        M[N - 1][15] = ((msg.length - 1) * 8) & 0xffffffff;

        // HASH COMPUTATION [§6.1.2]
        for (int i = 0; i < N; i++) {
            int[] W = new int[80];

            // 1 - prepare message schedule 'W'
            for (int t = 0; t < 16; t++) {
                W[t] = M[i][t];
            }
            for (int t = 16; t < 80; t++) {
                W[t] = ROTL(W[t - 3] ^ W[t - 8] ^ W[t - 14] ^ W[t - 16], 1);
            }

            // 2 - initialise five working variables a, b, c, d, e with previous hash value
            int a = H[0];
            int b = H[1];
            int c = H[2];
            int d = H[3];
            int e = H[4];

            // // 3 - main loop (use JavaScript '>>> 0' to emulate UInt32 variables)
            for (int t = 0; t < 80; t++) {
                int s = (int) Math.floor(t / 20.0); // seq for blocks of 'f' functions and 'K' constants
                int T = (ROTL(a, 5) + f(s, b, c, d) + e + K[s] + W[t]) >>> 0;
                e = d;
                d = c;
                c = ROTL(b, 30) >>> 0;
                b = a;
                a = T;
            }

            // 4 - compute the new intermediate hash value (note 'addition modulo 2^32' –
            // JavaScript '>>> 0' coerces to unsigned UInt32 which achieves modulo 2^32
            // addition)
            H[0] = (H[0] + a) >>> 0;
            H[1] = (H[1] + b) >>> 0;
            H[2] = (H[2] + c) >>> 0;
            H[3] = (H[3] + d) >>> 0;
            H[4] = (H[4] + e) >>> 0;
        }

        // concatenate H0..H4, with separator if required
        String separator = outFormat == OUT_HEXW ? " " : "";

        // convert H0..H4 to hex strings (with leading zeros)
        String output = "";
        for (int h = 0; h < H.length - 1; h++) {
            String strValue = "00000000" + Integer.toHexString(H[h]);
            output += strValue.substring(strValue.length() - 8) + separator;
        }
        String strValue = "00000000" + Integer.toHexString(H[H.length - 1]);
        output += strValue.substring(strValue.length() - 8);

        return output;
    }

    /**
     * Returns the integer code of a char in a byte array.
     *
     * @param str   byte array of characters.
     * @param index position of the character desired.
     * @return integer code of such character.
     * @throws UnsupportedEncodingException
     */
    public static int getCharCode(byte[] str, int index) throws UnsupportedEncodingException {
        int value = 0;

        if (index < str.length) {
            value = Math.abs((int) str[index]);
        }
        return value;

    }

    /**
     * Converts a string into an array of bytes, using UTF-8 standard.
     *
     * @param str string to be converted
     * @return array of bytes
     * @throws UnsupportedEncodingException
     */
    public static byte[] utf8Encode(String str) throws UnsupportedEncodingException {
        byte[] result = str.getBytes("UTF-8");
        return result;
    }

    /**
     * Function 'f' [§4.1.1].
     */
    public static int f(int s, int x, int y, int z) {
        int output;
        switch (s) {
            // Ch()
            case 0:
                output = (x & y) ^ (~x & z);
                break;

            // Parity()
            case 1:
                output = x ^ y ^ z;
                break;

            // Maj()
            case 2:
                output = (x & y) ^ (x & z) ^ (y & z);
                break;

            // Parity()
            case 3:
                output = x ^ y ^ z;
                break;
            default:
                throw (new RuntimeException("Unexpected case."));
        }

        return output;
    }

    /**
     * Rotates left (circular left shift) value x by n positions [§3.2.5].
     *
     * @param x value to rotate
     * @param n positions.
     * @return rotates int.
     */
    public static int ROTL(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }
}