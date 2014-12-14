package com.krakenstudios.v_flip;

import java.text.DecimalFormat;
import android.app.Activity;
import android.graphics.Typeface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

// MainActivity
// Handles gameplay and score calculation
// for the application V-Flip.
public class MainActivity extends Activity {
	DecimalFormat									// String Formatting
		decimalFormat;
	double													// Random number
		z;
	ImageButton										// Game tiles
		buttonA1, buttonA2, buttonA3, buttonA4, buttonA5,
		buttonB1, buttonB2, buttonB3, buttonB4, buttonB5,
		buttonC1, buttonC2, buttonC3, buttonC4, buttonC5,
		buttonD1, buttonD2, buttonD3, buttonD4, buttonD5,
		buttonE1, buttonE2, buttonE3, buttonE4, buttonE5,
		hiddenReset;
	int[][]														// Boards for  values, player moves, and game.
		countboard = new int[10][2],
		checkboard = new int[5][5],
		gameboard = new int[5][5];
	int															// Respective tallying and score keeping
		totalScore = 1,
		totalZero = 0,
		totalOne = 0,
		totalTwo = 0,
		totalThree = 0,
		points = 1;
	String														//  Formatting pattern
		pattern = "###,###.###";
	TextView												// TextViews from XML.
		score,
		sum,
		vol;
		
	// onCreate
	// Decimal Format is set to the specified pattern.
	// Each individual button is mapped to its corresponding
	// XML widget. The score field is also mapped, and set to
	// a font that will be used throughout the app.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Transitions defined in XML files.
		this.overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
		setContentView(R.layout.activity_main);
		
		decimalFormat = new DecimalFormat(pattern);
		fillGame();
		buttonA1 = (ImageButton)findViewById(R.id.imageButtonA1);
		buttonA2 = (ImageButton)findViewById(R.id.imageButtonA2);
		buttonA3 = (ImageButton)findViewById(R.id.imageButtonA3);
		buttonA4 = (ImageButton)findViewById(R.id.imageButtonA4);
		buttonA5 = (ImageButton)findViewById(R.id.imageButtonA5);
		buttonB1 = (ImageButton)findViewById(R.id.imageButtonB1);
		buttonB2 = (ImageButton)findViewById(R.id.imageButtonB2);
		buttonB3 = (ImageButton)findViewById(R.id.imageButtonB3);
		buttonB4 = (ImageButton)findViewById(R.id.imageButtonB4);
		buttonB5 = (ImageButton)findViewById(R.id.imageButtonB5);
		buttonC1 = (ImageButton)findViewById(R.id.imageButtonC1);
		buttonC2 = (ImageButton)findViewById(R.id.imageButtonC2);
		buttonC3 = (ImageButton)findViewById(R.id.imageButtonC3);
		buttonC4 = (ImageButton)findViewById(R.id.imageButtonC4);
		buttonC5 = (ImageButton)findViewById(R.id.imageButtonC5);
		buttonD1 = (ImageButton)findViewById(R.id.imageButtonD1);
		buttonD2 = (ImageButton)findViewById(R.id.imageButtonD2);
		buttonD3 = (ImageButton)findViewById(R.id.imageButtonD3);
		buttonD4 = (ImageButton)findViewById(R.id.imageButtonD4);
		buttonD5 = (ImageButton)findViewById(R.id.imageButtonD5);
		buttonE1 = (ImageButton)findViewById(R.id.imageButtonE1);
		buttonE2 = (ImageButton)findViewById(R.id.imageButtonE2);
		buttonE3 = (ImageButton)findViewById(R.id.imageButtonE3);
		buttonE4 = (ImageButton)findViewById(R.id.imageButtonE4);
		buttonE5 = (ImageButton)findViewById(R.id.imageButtonE5);
		
		hiddenReset = (ImageButton)findViewById(R.id.ImageButton);
		score = (TextView)findViewById(R.id.score);
		score.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
	}
	
	// fillGame
	// For each location on the 2D array gameboard,
	// a random number is generated and then assigned
	// a value from 0-4 to be stored in the current location.
	// Checkboard locations are also set to 0.
	// totalScore is also calculeted here.
	protected void fillGame() {
		totalZero = 0;
		totalOne = 0;
		totalTwo = 0;
		totalThree = 0;
		
		for(int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				z = Math.random();
				if(z < .25) {
					z = 1;
					totalOne++;
				}
				else if(z >= .25 && z < .50) {
					z = 2;
					totalTwo++;
				}
				else if(z >= .50 && z < .75) {
					z = 3;
					totalThree++;
				}
				else {
					z = 0;
					totalZero++;
				}
				checkboard[i][j] = 0;
				gameboard[i][j] = (int)z;
				if(gameboard[i][j] !=0)
					totalScore *= gameboard[i][j]; 
			}
		}
		
		method1();
		method2();
	}
	
	// method1
	// Traverses the board to count the total
	// X's and values for each row and column.
	// The values are stored into the array countboard.
	protected void method1() {
		int
			x = 0,
			y = 0,
			sum = 0,
			vol = 0;
		
		for(int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if(gameboard[i][j] == 0)
					vol++;
				else
					sum += gameboard[i][j];
			}
			
			// X is the column for X's.
			// Y is the column for values other than X.
			// Stores values for the rows into first half of the array.
			countboard[x][y] = vol;
			y++;
			countboard[x][y] = sum;
			x++;
			y--;
			
			sum = 0;
			vol = 0;
		}
		
		
		for(int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if(gameboard[j][i] == 0)
					vol++;
				else
					sum += gameboard[j][i];
			}
			
			// X is the column for X's.
			// Y is the column for values other than X.
			// Stores values for the columns into second half of the array.
			countboard[x][y] = vol;
			y++;
			countboard[x][y] = sum;
			x++;
			y--;
			
			sum = 0;
			vol = 0;
		}
	}
	
	// method2
	// Sum and vol are reused to set the number of
	// X's and values at the end of the rows and columns.
	// System.gc() is called to help alleviate image resources.
	protected void method2() {
		sum = (TextView)findViewById(R.id.sumA);
		vol = (TextView)findViewById(R.id.volA);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[0][0]));
		sum.setText(String.valueOf(countboard[0][1]));
		
		sum = (TextView)findViewById(R.id.sumB);
		vol = (TextView)findViewById(R.id.volB);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[1][0]));
		sum.setText(String.valueOf(countboard[1][1]));
		
		sum = (TextView)findViewById(R.id.sumC);
		vol = (TextView)findViewById(R.id.volC);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[2][0]));
		sum.setText(String.valueOf(countboard[2][1]));
		
		sum = (TextView)findViewById(R.id.sumD);
		vol = (TextView)findViewById(R.id.volD);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[3][0]));
		sum.setText(String.valueOf(countboard[3][1]));
		
		sum = (TextView)findViewById(R.id.sumE);
		vol = (TextView)findViewById(R.id.volE);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[4][0]));
		sum.setText(String.valueOf(countboard[4][1]));
		
		sum = (TextView)findViewById(R.id.sumF1);
		vol = (TextView)findViewById(R.id.volF1);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[5][0]));
		sum.setText(String.valueOf(countboard[5][1]));
		
		sum = (TextView)findViewById(R.id.sumF2);
		vol = (TextView)findViewById(R.id.volF2);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[6][0]));
		sum.setText(String.valueOf(countboard[6][1]));
		
		sum = (TextView)findViewById(R.id.sumF3);
		vol = (TextView)findViewById(R.id.volF3);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[7][0]));
		sum.setText(String.valueOf(countboard[7][1]));
		
		sum = (TextView)findViewById(R.id.sumF4);
		vol = (TextView)findViewById(R.id.volF4);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[8][0]));
		sum.setText(String.valueOf(countboard[8][1]));
		
		sum = (TextView)findViewById(R.id.sumF5);
		vol = (TextView)findViewById(R.id.volF5);
		sum.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		vol.setText(String.valueOf(countboard[9][0]));
		sum.setText(String.valueOf(countboard[9][1]));
		
		System.gc();
	}
	
	// resetGame
	// Each tile's image is reset to its unflipped state.
	// Player's score and game's total score are reset to 1.
	// fillGame is called to reinitialize the gameboard.
	public void resetGame(View view) {
		buttonA1.setImageResource(R.drawable.ellipse_md);
		buttonA2.setImageResource(R.drawable.ellipse_md);
		buttonA3.setImageResource(R.drawable.ellipse_md);
		buttonA4.setImageResource(R.drawable.ellipse_md);
		buttonA5.setImageResource(R.drawable.ellipse_md);
		buttonB1.setImageResource(R.drawable.ellipse_md);
		buttonB2.setImageResource(R.drawable.ellipse_md);
		buttonB3.setImageResource(R.drawable.ellipse_md);
		buttonB4.setImageResource(R.drawable.ellipse_md);
		buttonB5.setImageResource(R.drawable.ellipse_md);
		buttonC1.setImageResource(R.drawable.ellipse_md);
		buttonC2.setImageResource(R.drawable.ellipse_md);
		buttonC3.setImageResource(R.drawable.ellipse_md);
		buttonC4.setImageResource(R.drawable.ellipse_md);
		buttonC5.setImageResource(R.drawable.ellipse_md);
		buttonD1.setImageResource(R.drawable.ellipse_md);
		buttonD2.setImageResource(R.drawable.ellipse_md);
		buttonD3.setImageResource(R.drawable.ellipse_md);
		buttonD4.setImageResource(R.drawable.ellipse_md);
		buttonD5.setImageResource(R.drawable.ellipse_md);
		buttonE1.setImageResource(R.drawable.ellipse_md);
		buttonE2.setImageResource(R.drawable.ellipse_md);
		buttonE3.setImageResource(R.drawable.ellipse_md);
		buttonE4.setImageResource(R.drawable.ellipse_md);
		buttonE5.setImageResource(R.drawable.ellipse_md);
		
		totalScore = 1;
		points = 1;
		fillGame();
		
		// Score is set back to display the beginning message.
		score.setText("Touch a Tile to Begin");
	} 
	
	// onClick
	// Handles clicks for each corresponding tile.
	// Each tile has a specific ID that the switch statement
	// uses to apply the proper image to the value of the tile.
	// The player's score is computed and, depending on what
	// the score becomes, either 0, some value, or the max score,
	// the gameOver method may be called.
	public void onClick(View v) {
		switch(v.getId()) {
        	case R.id.imageButtonA1:
        		if(checkboard[0][0] == 0) {
		    		if(gameboard[0][0] == 1)
		    			buttonA1.setImageResource(R.drawable.one);
		    		else if(gameboard[0][0] == 2)
		    			buttonA1.setImageResource(R.drawable.two);
		    		else if(gameboard[0][0] == 3)
			    			buttonA1.setImageResource(R.drawable.three);
		    		else if(gameboard[0][0] == 0)
		    			buttonA1.setImageResource(R.drawable.x);
		    		points *= gameboard[0][0];
			    	checkboard[0][0] = 1;
		    	}
        		break;
        	case R.id.imageButtonA2:
        		if(checkboard[0][1] == 0) {
		    		if(gameboard[0][1] == 1)
		    			buttonA2.setImageResource(R.drawable.one);
		    		else if(gameboard[0][1] == 2)
		    			buttonA2.setImageResource(R.drawable.two);
		    		else if(gameboard[0][1] == 3)
		    			buttonA2.setImageResource(R.drawable.three);
		    		else if(gameboard[0][1] == 0)
		    			buttonA2.setImageResource(R.drawable.x);
		    		points *= gameboard[0][1];
			    	checkboard[0][1] = 1;
		    	}
        		break;
        	case R.id.imageButtonA3:
        		if(checkboard[0][2] == 0) {
			    	if(gameboard[0][2] == 1)
			    		buttonA3.setImageResource(R.drawable.one);
			    	else if(gameboard[0][2] == 2)
			    		buttonA3.setImageResource(R.drawable.two);
			    	else if(gameboard[0][2] == 3)
			    		buttonA3.setImageResource(R.drawable.three);
			    	else if(gameboard[0][2] == 0)
			    		buttonA3.setImageResource(R.drawable.x);
			    	points *= gameboard[0][2];
			    	checkboard[0][2] = 1;
		    	}
        		break;
        	case R.id.imageButtonA4:
        		if(checkboard[0][3] == 0) {
			    	if(gameboard[0][3] == 1)
			    		buttonA4.setImageResource(R.drawable.one);
			    	else if(gameboard[0][3] == 2)
			    		buttonA4.setImageResource(R.drawable.two);
			    	else if(gameboard[0][3] == 3)
			    		buttonA4.setImageResource(R.drawable.three);
			    	else if(gameboard[0][3] == 0)
			    		buttonA4.setImageResource(R.drawable.x);
			    	points *= gameboard[0][3];
			    	checkboard[0][3] = 1;
		    	}
        		break;
        	case R.id.imageButtonA5:
        		if(checkboard[0][4] == 0) {
			    	if(gameboard[0][4] == 1)
			    		buttonA5.setImageResource(R.drawable.one);
			    	else if(gameboard[0][4] == 2)
			    		buttonA5.setImageResource(R.drawable.two);
			    	else if(gameboard[0][4] == 3)
			    		buttonA5.setImageResource(R.drawable.three);
			    	else if(gameboard[0][4] == 0)
			    		buttonA5.setImageResource(R.drawable.x);
			    	points *= gameboard[0][4];
			    	checkboard[0][4] = 1;
		    	}
        		break;
        	case R.id.imageButtonB1:
        		if(checkboard[1][0] == 0) {
			    	if(gameboard[1][0] == 1)
			    		buttonB1.setImageResource(R.drawable.one);
			    	else if(gameboard[1][0] == 2)
			    		buttonB1.setImageResource(R.drawable.two);
			    	else if(gameboard[1][0] == 3)
			    		buttonB1.setImageResource(R.drawable.three);
			    	else if(gameboard[1][0] == 0)
			    		buttonB1.setImageResource(R.drawable.x);
			    	points *= gameboard[1][0];
			    	checkboard[1][0] = 1;
		    	}
        		break;
        	case R.id.imageButtonB2:
        		if(checkboard[1][1] == 0) {
			    	if(gameboard[1][1] == 1)
			    		buttonB2.setImageResource(R.drawable.one);
			    	else if(gameboard[1][1] == 2)
			    		buttonB2.setImageResource(R.drawable.two);
			    	else if(gameboard[1][1] == 3)
			    		buttonB2.setImageResource(R.drawable.three);
			    	else if(gameboard[1][1] == 0)
			    		buttonB2.setImageResource(R.drawable.x);
			    	points *= gameboard[1][1];
			    	checkboard[1][1] = 1;
		    	}
        		break;
        	case R.id.imageButtonB3:
        		if(checkboard[1][2] == 0) {
			    	if(gameboard[1][2] == 1)
			    		buttonB3.setImageResource(R.drawable.one);
			    	else if(gameboard[1][2] == 2)
			    		buttonB3.setImageResource(R.drawable.two);
			    	else if(gameboard[1][2] == 3)
			    		buttonB3.setImageResource(R.drawable.three);
			    	else if(gameboard[1][2] == 0)
			    		buttonB3.setImageResource(R.drawable.x);
			    	points *= gameboard[1][2];
			    	checkboard[1][2] = 1;
		    	}
        		break;
        	case R.id.imageButtonB4:
        		if(checkboard[1][3] == 0) {
			    	if(gameboard[1][3] == 1)
			    		buttonB4.setImageResource(R.drawable.one);
			    	else if(gameboard[1][3] == 2)
			    		buttonB4.setImageResource(R.drawable.two);
			    	else if(gameboard[1][3] == 3)
			    		buttonB4.setImageResource(R.drawable.three);
			    	else if(gameboard[1][3] == 0)
			    		buttonB4.setImageResource(R.drawable.x);
			    	points *= gameboard[1][3];
			    	checkboard[1][3] = 1;
		    	}
        		break;
        	case R.id.imageButtonB5:
        		if(checkboard[1][4] == 0) {
			    	if(gameboard[1][4] == 1)
			    		buttonB5.setImageResource(R.drawable.one);
			    	else if(gameboard[1][4] == 2)
			    		buttonB5.setImageResource(R.drawable.two);
			    	else if(gameboard[1][4] == 3)
			    		buttonB5.setImageResource(R.drawable.three);
			    	else if(gameboard[1][4] == 0)
			    		buttonB5.setImageResource(R.drawable.x);
			    	points *= gameboard[1][4];
			    	checkboard[1][4] = 1;
		    	}
        		break;
        	case R.id.imageButtonC1:
        		if(checkboard[2][0] == 0) {
			    	if(gameboard[2][0] == 1)
			    		buttonC1.setImageResource(R.drawable.one);
			    	else if(gameboard[2][0] == 2)
			    		buttonC1.setImageResource(R.drawable.two);
			    	else if(gameboard[2][0] == 3)
			    		buttonC1.setImageResource(R.drawable.three);
			    	else if(gameboard[2][0] == 0)
			    		buttonC1.setImageResource(R.drawable.x);
			    	points *= gameboard[2][0];
			    	checkboard[2][0] = 1;
		    	}
        		break;
        	case R.id.imageButtonC2:
        		if(checkboard[2][1] == 0) {
			    	if(gameboard[2][1] == 1)
			    		buttonC2.setImageResource(R.drawable.one);
			    	else if(gameboard[2][1] == 2)
			    		buttonC2.setImageResource(R.drawable.two);
			    	else if(gameboard[2][1] == 3)
			    		buttonC2.setImageResource(R.drawable.three);
			    	else if(gameboard[2][1] == 0)
			    		buttonC2.setImageResource(R.drawable.x);
			    	points *= gameboard[2][1];
			    	checkboard[2][1] = 1;
		    	}
        		break;
        	case R.id.imageButtonC3:
        		if(checkboard[2][2] == 0) {
			    	if(gameboard[2][2] == 1)
			    		buttonC3.setImageResource(R.drawable.one);
			    	else if(gameboard[2][2] == 2)
			    		buttonC3.setImageResource(R.drawable.two);
			    	else if(gameboard[2][2] == 3)
			    		buttonC3.setImageResource(R.drawable.three);
			    	else if(gameboard[2][2] == 0)
			    		buttonC3.setImageResource(R.drawable.x);
			    	points *= gameboard[2][2];
			    	checkboard[2][2] = 1;
		    	}
        		break;
        	case R.id.imageButtonC4:
        		if(checkboard[2][3] == 0) {
			    	if(gameboard[2][3] == 1)
			    		buttonC4.setImageResource(R.drawable.one);
			    	else if(gameboard[2][3] == 2)
			    		buttonC4.setImageResource(R.drawable.two);
			    	else if(gameboard[2][3] == 3)
			    		buttonC4.setImageResource(R.drawable.three);
			    	else if(gameboard[2][3] == 0)
			    		buttonC4.setImageResource(R.drawable.x);
			    	points *= gameboard[2][3];
			    	checkboard[2][3] = 1;
		    	}
        		break;
        	case R.id.imageButtonC5:
        		if(checkboard[2][4] == 0) {
			    	if(gameboard[2][4] == 1)
			    		buttonC5.setImageResource(R.drawable.one);
			    	else if(gameboard[2][4] == 2)
			    		buttonC5.setImageResource(R.drawable.two);
			    	else if(gameboard[2][4] == 3)
			    		buttonC5.setImageResource(R.drawable.three);
			    	else if(gameboard[2][4] == 0)
			    		buttonC5.setImageResource(R.drawable.x);
			    	points *= gameboard[2][4];
			    	checkboard[2][4] = 1;
		    	}
        		break;
        	case R.id.imageButtonD1:
        		if(checkboard[3][0] == 0) {
			    	if(gameboard[3][0] == 1)
			    		buttonD1.setImageResource(R.drawable.one);
			    	else if(gameboard[3][0] == 2)
			    		buttonD1.setImageResource(R.drawable.two);
			    	else if(gameboard[3][0] == 3)
			    		buttonD1.setImageResource(R.drawable.three);
			    	else if(gameboard[3][0] == 0)
			    		buttonD1.setImageResource(R.drawable.x);
			    	points *= gameboard[3][0];
			    	checkboard[3][0] = 1;
		    	}
        		break;
        	case R.id.imageButtonD2:
        		if(checkboard[3][1] == 0) {
			    	if(gameboard[3][1] == 1)
			    		buttonD2.setImageResource(R.drawable.one);
			    	else if(gameboard[3][1] == 2)
			    		buttonD2.setImageResource(R.drawable.two);
			    	else if(gameboard[3][1] == 3)
			    		buttonD2.setImageResource(R.drawable.three);
			    	else if(gameboard[3][1] == 0)
			    		buttonD2.setImageResource(R.drawable.x);
			    	points *= gameboard[3][1];
			    	checkboard[3][1] = 1;
		    	}
        		break;
        	case R.id.imageButtonD3:
        		if(checkboard[3][2] == 0) {
			    	if(gameboard[3][2] == 1)
			    		buttonD3.setImageResource(R.drawable.one);
			    	else if(gameboard[3][2] == 2)
			    		buttonD3.setImageResource(R.drawable.two);
			    	else if(gameboard[3][2] == 3)
			    		buttonD3.setImageResource(R.drawable.three);
			    	else if(gameboard[3][2] == 0)
			    		buttonD3.setImageResource(R.drawable.x);
			    	points *= gameboard[3][2];
			    	checkboard[3][2] = 1;
		    	}
        		break;
        	case R.id.imageButtonD4:
        		if(checkboard[3][3] == 0) {
    			  	if(gameboard[3][3] == 1)
    			   		buttonD4.setImageResource(R.drawable.one);
    			  	else if(gameboard[3][3] == 2)
    			   		buttonD4.setImageResource(R.drawable.two);
    			   	else if(gameboard[3][3] == 3)
    			   		buttonD4.setImageResource(R.drawable.three);
    			   	else if(gameboard[3][3] == 0)
    			   		buttonD4.setImageResource(R.drawable.x);
    			   	points *= gameboard[3][3];
			    	checkboard[3][3] = 1;
    		    }
        		break;
        	case R.id.imageButtonD5:
        		if(checkboard[3][4] == 0) {
			    	if(gameboard[3][4] == 1)
			    		buttonD5.setImageResource(R.drawable.one);
			    	else if(gameboard[3][4] == 2)
			    		buttonD5.setImageResource(R.drawable.two);
			    	else if(gameboard[3][4] == 3)
			    		buttonD5.setImageResource(R.drawable.three);
			    	else if(gameboard[3][4] == 0)
			    		buttonD5.setImageResource(R.drawable.x);
			    	points *= gameboard[3][4];
			    	checkboard[3][4] = 1;
		    	}
        		break;
        	case R.id.imageButtonE1:
        		if(checkboard[4][0] == 0) {
			    	if(gameboard[4][0] == 1)
			    		buttonE1.setImageResource(R.drawable.one);
			    	else if(gameboard[4][0] == 2)
			    		buttonE1.setImageResource(R.drawable.two);
			    	else if(gameboard[4][0] == 3)
			    		buttonE1.setImageResource(R.drawable.three);
			    	else if(gameboard[4][0] == 0)
			    		buttonE1.setImageResource(R.drawable.x);
			    	points *= gameboard[4][0];
			    	checkboard[4][0] = 1;
		    	}
        		break;
        	case R.id.imageButtonE2:
        		if(checkboard[4][1] == 0) {
			    	if(gameboard[4][1] == 1)
			    		buttonE2.setImageResource(R.drawable.one);
			    	else if(gameboard[4][1] == 2)
			    		buttonE2.setImageResource(R.drawable.two);
			    	else if(gameboard[4][1] == 3)
			    		buttonE2.setImageResource(R.drawable.three);
			    	else if(gameboard[4][1] == 0)
			    		buttonE2.setImageResource(R.drawable.x);
			    	points *= gameboard[4][1];
			    	checkboard[4][1] = 1;
		    	}
        		break;
        	case R.id.imageButtonE3:
        		if(checkboard[4][2] == 0) {
			    	if(gameboard[4][2] == 1)
			    		buttonE3.setImageResource(R.drawable.one);
			    	else if(gameboard[4][2] == 2)
			    		buttonE3.setImageResource(R.drawable.two);
			    	else if(gameboard[4][2] == 3)
			    		buttonE3.setImageResource(R.drawable.three);
			    	else if(gameboard[4][2] == 0)
			    		buttonE3.setImageResource(R.drawable.x);
			    	points *= gameboard[4][2];
			    	checkboard[4][2] = 1;
		    	}
        		break;
        	case R.id.imageButtonE4:
        		if(checkboard[4][3] == 0) {
			    	if(gameboard[4][3] == 1)
			    		buttonE4.setImageResource(R.drawable.one);
			    	else if(gameboard[4][3] == 2)
			    		buttonE4.setImageResource(R.drawable.two);
			    	else if(gameboard[4][3] == 3)
			    		buttonE4.setImageResource(R.drawable.three);
			    	else if(gameboard[4][3] == 0)
			    		buttonE4.setImageResource(R.drawable.x);
			    	points *= gameboard[4][3];
			    	checkboard[4][3] = 1;
		    	}
        		break;
        	case R.id.imageButtonE5:
        		if(checkboard[4][4] == 0) {
			    	if(gameboard[4][4] == 1)
			    		buttonE5.setImageResource(R.drawable.one);
			    	else if(gameboard[4][4] == 2)
			    		buttonE5.setImageResource(R.drawable.two);
			    	else if(gameboard[4][4] == 3)
			    		buttonE5.setImageResource(R.drawable.three);
			    	else if(gameboard[4][4] == 0)
			    		buttonE5.setImageResource(R.drawable.x);
			    	points *= gameboard[4][4];
			    	checkboard[4][4] = 1;
		    	}
        		break;
		}
		
		// 1: Player wins.
		// 0: Player loses.
		if(points == totalScore)
    		gameOver(1);
    	else if(points == 0)
    		gameOver(0);
		
		// Score is formatted and updated to the score TextView.
    	score.setText(String.valueOf(decimalFormat.format(points)) + " / " + String.valueOf(decimalFormat.format(totalScore)));
	}
	
	// gameOver
	// Determines whether the player has won or lost.
	// The intent is prepared to call the GameOver.class.
	// The proper message and score is added to the intent.
	// New activity is started, and this activity closes.
	protected void gameOver(int game){
		String
			message;
		if (game == 1)
			message = "You Won!";
		else
			message = "You Lost!";
		Intent intent = new Intent(getApplicationContext(), GameOver.class);
		intent.putExtra("activity_message", message);
		intent.putExtra("score", points);
		startActivity(intent);
		finish();
	}
}