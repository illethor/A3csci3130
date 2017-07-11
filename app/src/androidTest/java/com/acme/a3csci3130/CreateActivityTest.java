package com.acme.a3csci3130;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class CreateActivityTest {
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCreateContact() throws Exception{
        // Define test credentials
        String name = "Test Biz Name";
        String businessNumber = "111222333";
        String primaryBusiness = "Distributor";
        String address = "123 Test Court";
        String province = "NS";

        onView(withId(R.id.submitButton)).perform(click());
        SystemClock.sleep(1500);
        // Enter test credentials and make sure to close keyboard to avoid any view errors
        onView(withId(R.id.name2)).perform(typeText(name)).perform(closeSoftKeyboard());
        onView(withId(R.id.type)).perform(typeText(primaryBusiness)).perform(closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText(address)).perform(closeSoftKeyboard());
        onView(withId(R.id.number)).perform(typeText(businessNumber)).perform(closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText(province)).perform(closeSoftKeyboard());
        ListView listView = (ListView) mActivityRule.getActivity().findViewById(R.id.listView);
        //get number of elements in list
        int count = listView.getCount();
        onView(withId(R.id.submitButton)).perform(click());
        // wait
        SystemClock.sleep(1500);
        // assert the list count has gone up by one
        assertEquals(listView.getCount(), count + 1);
    }

}