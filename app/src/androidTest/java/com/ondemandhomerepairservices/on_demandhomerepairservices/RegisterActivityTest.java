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
        assertNotEquals("username2",name);
    }
}