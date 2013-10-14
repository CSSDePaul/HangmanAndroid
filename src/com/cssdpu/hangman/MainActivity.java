package com.cssdpu.hangman;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText mEditText;
	private TextView mGuessedTextView;
	private String mGuessedString = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void showToast(String message) {
		Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
	}
	
	//checks if 'text' has already been guessed
	//returns true if already guessed
	private boolean didGuess(String text) {
		if (mGuessedString.contains(text))
			return true;
		return false;
	}
	
	public void onSubmitPress(View view) {
		final String text = getEditText().getText().toString().toUpperCase();
		getEditText().setText(""); //erase text
		
		if (didGuess(text)) {
			showToast(text+" was already guessed!");
			return; //don't penalize - just have them guess again
		}
		
		//add to guess string
		addToGuessedString(text);
		
		
	}
	
	private void addToGuessedString(String text) {
		if (mGuessedString.length() == 0)
			mGuessedString = "Guessed: ";
		
		mGuessedString += text;
		getGuessedTextView().setText(mGuessedString);
	}
	
	public TextView getGuessedTextView() {
		if (mGuessedTextView == null)
			mGuessedTextView = (TextView) findViewById(R.id.guessedTextView);
		return mGuessedTextView;
	}
	
	public EditText getEditText() {
		if (mEditText == null)
			mEditText = (EditText) findViewById(R.id.editText);
		return mEditText;
	}

}
