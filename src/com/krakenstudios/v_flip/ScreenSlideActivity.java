/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.krakenstudios.v_flip;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

/**
 * Demonstrates a "screen-slide" animation using a {@link ViewPager}. Because {@link ViewPager}
 * automatically plays such an animation when calling {@link ViewPager#setCurrentItem(int)}, there
 * isn't any animation-specific code in this sample.
 *
 * <p>This sample shows a "next" button that advances the user to the next step in a wizard,
 * animating the current screen out (to the left) and the next screen in (from the right). The
 * reverse animation is played when the user presses the "previous" button.</p>
 *
 * @see ScreenSlidePageFragment
 */
public class ScreenSlideActivity extends FragmentActivity {
	public Toast
		toast;
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }


    /**
     * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment} objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	try{
	        	switch(position) {
		        	case 0: //toast = Toast.makeText(getApplicationContext(), "case 0", Toast.LENGTH_SHORT);
	        			//toast.show();
	        			return HelpScreenOne.create(position);
		        	case 1: //toast = Toast.makeText(getApplicationContext(), "case 1", Toast.LENGTH_SHORT);
        				//toast.show();
        				return HelpScreenTwo.create(position);
		        	case 2: //toast = Toast.makeText(getApplicationContext(), "case 2", Toast.LENGTH_SHORT);
	        			//toast.show();
	        			return HelpScreenThree.create(position);
		        	case 3: //toast = Toast.makeText(getApplicationContext(), "case 3", Toast.LENGTH_SHORT);
        				//toast.show();
	        			return HelpScreenFour.create(position);
		        	case 4: //toast = Toast.makeText(getApplicationContext(), "case 4", Toast.LENGTH_SHORT);
        				//toast.show();
        				return HelpScreenFive.create(position);
		        	default: //toast = Toast.makeText(getApplicationContext(), "case default", Toast.LENGTH_SHORT);
        				//toast.show();
        				return ScreenSlidePageFragment.create(position);
	        	}
        	}catch(Exception e){
        		toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
        		toast.show();
        		return ScreenSlidePageFragment.create(position);
        	}
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
