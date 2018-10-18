package com.example.dennis.jipange;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dennis on 1/30/2018.
 */

public class contact extends Activity {
 EditText email,comment;
    Button feed,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        email = (EditText)findViewById(R.id.email);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        comment = (EditText)findViewById(R.id.comment);
        comment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        feed = (Button)findViewById(R.id.feedback);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EMAIL = new Intent(Intent.ACTION_SEND);
                EMAIL.setData(Uri.parse("mailto:"));
                String[] to ={"Jipangeapp254@gmail.com"};
                EMAIL.putExtra(Intent.EXTRA_EMAIL,to);
                EMAIL.putExtra(Intent.EXTRA_SUBJECT,email.getText().toString());
                EMAIL.putExtra(Intent.EXTRA_TEXT,comment.getText().toString());
                EMAIL.setType("message/rfc822");
                startActivity(Intent.createChooser(EMAIL,"SEND EMAIL"));
            }
        });

    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
