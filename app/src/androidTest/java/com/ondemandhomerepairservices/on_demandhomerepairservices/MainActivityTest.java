package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule= new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception{
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkUserName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewUsername));
        text = mActivity.findViewById(R.id.editTextUsername);
        text.setText("username1");
        String username = text.getText().toString();
        assertNotEquals("username", username);
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewPassword));
        text = mActivity.findViewById(R.id.editTextPassword);
        text.setText("password1");
        String password = text.getText().toString();
        assertNotEquals("password", password);
    }
}
