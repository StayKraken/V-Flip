package com.krakenstudios.v_flip;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HelpScreenFour extends Fragment {
	
    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    public static HelpScreenFour  create(int pageNumber) {
    	HelpScreenFour  fragment = new HelpScreenFour();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HelpScreenFour() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.help_screen_four, container, false);

        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }
}
