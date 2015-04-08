package com.raoul.cudenver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends ActionBarActivity {
    private static final int LOGIN_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            Intent intent = new Intent(this, ARPortraitActivity.class);
            startActivity(intent);
        } else if (!(currentUser == null)) {
            Intent intent = new Intent(this, ARPortraitActivity.class);
            startActivity(intent);
        }
        else {
            ParseLoginBuilder loginBuilder = new ParseLoginBuilder(
                    MainActivity.this);
            startActivityForResult(loginBuilder.build(), LOGIN_REQUEST);
        }


    }



}
