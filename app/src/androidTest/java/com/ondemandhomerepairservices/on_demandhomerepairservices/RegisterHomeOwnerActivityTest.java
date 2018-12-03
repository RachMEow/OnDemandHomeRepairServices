package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RegisterHomeOwnerActivityTest {

    @Rule
    public ActivityTestRule<RegisterHomeOwnerActivity> mActivityTestRule= new ActivityTestRule<RegisterHomeOwnerActivity>(RegisterHomeOwnerActivity.class);
    private RegisterHomeOwnerActivity mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkUsername() throws  Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewUsername));
        text = mActivity.findViewById(R.id.editTextUsername);
        text.setText("Michelle2018");
        String username = text.getText().toString();
        assertNotEquals("username",username);
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewPassword));
        text = mActivity.findViewById(R.id.editTextPassword);
        text.setText("Michelle204");
        String password = text.getText().toString();
        assertNotEquals("password", password);
    }

    @Test
    @UiThreadTest
    public void checkFirstName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewFirstName));
        text = mActivity.findViewById(R.id.editTextFirstName);
        text.setText("Michelle");
        String firstName = text.getText().toString();
        assertNotEquals("firstName", firstName);
    }

    @Test
    @UiThreadTest
    public void checkLastName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewLastName));
        text = mActivity.findViewById(R.id.editTextLastName);
        text.setText("Lee");
        String lastName = text.getText().toString();
        assertNotEquals("lastName", lastName);
    }

    @Test
    @UiThreadTest
    public void checkAddress() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewAddress));
        text = mActivity.findViewById(R.id.editTextAddress);
        text.setText("170 Lees");
        String address = text.getText().toString();
        assertNotEquals("address", address);
    }

    @Test
    @UiThreadTest
    public void checkPostalCode() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewPostalCode));
        text = mActivity.findViewById(R.id.editTextPostalCode);
        text.setText("Lee");
        String postalcode = text.getText().toString();
        assertNotEquals("PostalCode", postalcode);
    }




}
