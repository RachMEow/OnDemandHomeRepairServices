package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class HomeOwnerRatingServiceActivityTest {

    @Rule
    public ActivityTestRule<HomeOwnerRatingService> mActivityTestRule= new ActivityTestRule<HomeOwnerRatingService>(HomeOwnerRatingService.class);
    private HomeOwnerRatingService mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkComment() throws  Exception{
        assertNotNull(mActivity.findViewById(R.id.textComment));
        text = mActivity.findViewById(R.id.editTextComment);
        text.setText("Good");
        String comment = text.getText().toString();
        assertNotEquals("Comment",comment);
    }
}
