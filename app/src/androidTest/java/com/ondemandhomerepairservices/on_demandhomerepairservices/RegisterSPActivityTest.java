package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RegisterSPActivityTest {

    @Rule
    public ActivityTestRule<RegisterServiceProviderActivity> mActivityTestRule= new ActivityTestRule<RegisterServiceProviderActivity>(RegisterServiceProviderActivity.class);
    private RegisterServiceProviderActivity mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewPassword));
        text = mActivity.findViewById(R.id.editTextPassword);
        text.setText("789321_yin");
        String password = text.getText().toString();
        assertNotEquals("password", password);
    }


    @Test
    @UiThreadTest
    public void checkUsername() throws  Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewUsername));
        text = mActivity.findViewById(R.id.editTextUsername);
        text.setText("Sikeyin_2018");
        String username = text.getText().toString();
        assertNotEquals("username",username);
    }


}
