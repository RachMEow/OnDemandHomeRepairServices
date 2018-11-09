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
    public void checkFirstName() throws Exception{
//        assertNotNull(rActivity.findViewById(R.id.textView1));
//        text= rActivity.findViewById(R.id.username);
//        text.setText("user1");
//        String name= text.getText().toString();
//        assertNotEquals("user",name);
    }
}