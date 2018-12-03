package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class HomeOwnerSearchServiceProviderActivityTest {
    @Rule
    public ActivityTestRule<HomeOwnerSearchServiceProvider> mActivityTestRule= new ActivityTestRule<HomeOwnerSearchServiceProvider>(HomeOwnerSearchServiceProvider.class);
    private HomeOwnerSearchServiceProvider mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkSearchServiceName() throws  Exception{
        assertNotNull(mActivity.findViewById(R.id.textServiceName));
        text = mActivity.findViewById(R.id.editTextServiceName);
        text.setText("Painting");
        String servicename = text.getText().toString();
        assertNotEquals("ServiceName",servicename);
    }

    @Test
    @UiThreadTest
    public void checkTimeFrom() throws  Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewTimeFrom));
        text = mActivity.findViewById(R.id.editTextTimeFrom);
        text.setText("9");
        String timefrom = text.getText().toString();
        assertNotEquals("TimeFrom",timefrom);
    }

    @Test
    @UiThreadTest
    public void checkTimeTo() throws  Exception{
        assertNotNull(mActivity.findViewById(R.id.textViewTimeTo));
        text = mActivity.findViewById(R.id.editTextTimeTo);
        text.setText("12");
        String timeto = text.getText().toString();
        assertNotEquals("TimeTo",timeto);
    }

}
