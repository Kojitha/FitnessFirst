package com.example.home;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddCaloriesTest {

    @Rule
    public ActivityTestRule<AddCalories> CActivityTestRule = new ActivityTestRule<AddCalories>(AddCalories.class);
    private AddCalories aCalories = null;

    @Before
    public void setUp() throws Exception {
        aCalories = CActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = aCalories.findViewById(R.id.textView5);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        aCalories = null;
    }
}