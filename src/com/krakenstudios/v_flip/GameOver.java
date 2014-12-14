package com.krakenstudios.v_flip;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends Activity {
	Button
		newGame,
		mainMenu;
	TextView
		label,
		score;
	int
		x;
	String
		message,
		pattern = "###,###.###";
	DecimalFormat
		decimalFormat = new DecimalFormat(pattern);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
		setContentView(R.layout.activity_game_over);

		message = getIntent().getStringExtra("activity_message");
		x = getIntent().getIntExtra("score", 0);
		
		newGame = (Button)findViewById(R.id.newGame);
		mainMenu = (Button)findViewById(R.id.mainMenu);
		
		label = (TextView)findViewById(R.id.textView1);
		label.setText(message);
		
		score = (TextView)findViewById(R.id.textView2);
		score.setText(String.valueOf(decimalFormat.format(x)));
		
		newGame.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		mainMenu.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		label.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		score.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
	}
	
	public void newGame(View view){
		final Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void mainMenu(View v) {
    	finish();
    }
}