package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;


// this class ifor test

public class RegisterSuccessDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setTitle( "Welcome" );
        builder.setMessage( "Register Success" );
        builder.setPositiveButton( "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        } );
        return builder.create();
    }
}
