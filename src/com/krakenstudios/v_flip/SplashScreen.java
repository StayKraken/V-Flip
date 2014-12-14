package com.krakenstudios.v_flip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
public class SplashScreen extends Activity {
	Animation
		fadeIn,
		fadeOut;
	AnimationSet
		animation1,
		animation2;
	Button
		play,
		help;
	ImageView
		image;
	TextView
		title,
		text,
		loading,
		version;
    
    private static int TIME_OUT = 1000;
 
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        image = (ImageView)findViewById(R.id.imgLogo);
        play = (Button)findViewById(R.id.play);
        help = (Button)findViewById(R.id.help);
        title = (TextView)findViewById(R.id.app_name);
        text = (TextView)findViewById(R.id.credits);
        loading = (TextView)findViewById(R.id.loading);
        version = (TextView)findViewById(R.id.version);
        
        loading.setAlpha(0);
        
        play.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
        help.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
        title.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
        text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
        loading.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
        version.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
        
        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); 
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);
        
        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(TIME_OUT);
        fadeOut.setFillAfter(true);
        
        animation1 = new AnimationSet(false); 
        animation1.addAnimation(fadeIn);
        animation2 = new AnimationSet(false); 
        animation2.addAnimation(fadeOut);
        
        image.setAnimation(animation2);
        play.setAnimation(animation1);
        help.setAnimation(animation1);
    	title.setAnimation(animation1);
    	
    	image.startAnimation(animation2);
    	        
        new Handler().postDelayed(new Runnable() {
 
            // Showing splash screen with a timer. 
            //@SuppressWarnings("deprecation")
			@Override
            public void run() {
				image.setAlpha(0);
				title.setAlpha(1);
            	title.startAnimation(animation1);
				play.setAlpha(1);
            	play.startAnimation(animation1);
            	help.setAlpha(1);
            	help.startAnimation(animation1);
            	
                // This method will be executed once the timer is over
                // Start your app main activity
                //Intent i = new Intent(SplashScreen.this, MainActivity.class);
                //startActivity(i);
 
                // close this activity
                //finish();
            }
        }, TIME_OUT);
    }
    
    @Override
    protected void onResume() {
    	this.overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
    	super.onResume();
        loading.setAlpha(0);
    }
    
    public void playClick(View view){
    	loading.setAlpha(1);
    	
    	Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
        //finish();
    }
    
    public void helpClick(View view){
    	try{
    	Intent i = new Intent(SplashScreen.this, ScreenSlideActivity.class);
        startActivity(i);
    	}catch(Exception e){
    		text.setText(e.toString());
    	}
    }
}