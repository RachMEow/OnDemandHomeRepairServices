package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterActivityTest {
    @Rule
    public ActivityTestRule<RegisterActivity> rActivityTestRule= new ActivityTestRule<RegisterActivity>(RegisterActivity.class);
    private RegisterActivity rActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        rActivity=rActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkUserName() throws Exception{
        assertNotNull(rActivity.findViewById(R.id.textViewUsername));
        text= rActivity.findViewById(R.id.editTextUsername);
        text.setText("username1");
        String name= text.getText().toString();
        assertNotEquals("username",name);
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception{
        assertNotNull(rActivity.findViewById(R.id.textViewPassword));
        text = rActivity.findViewById(R.id.editTextPassword);
        text.setText("password1");
        String password = text.getText().toString();
        assertNotEquals("password", password);
    }

    @Test
    @UiThreadTest
    public void checkFirstName() throws Exception{
        assertNotNull(rActivity.findViewById(R.id.textViewFirstName));
        text = rActivity.findViewById(R.id.editTextFirstName);
        text.setText("firstName1");
        String firstName = text.getText().toString();
        assertNotEquals("firstName", firstName);
    }

    @Test
    @UiThreadTest
    public void checkLastName() throws Exception{
        assertNotNull(rActivity.findViewById(R.id.textViewLastName));
        text = rActivity.findViewById(R.id.editTextLastName);
        text.setText("lastName1");
        String lastName = text.getText().toString();
        assertNotEquals("lastName", lastName);
    }

    @Test
    @UiThreadTest
    public void checkRole() throws Exception{

    }

}