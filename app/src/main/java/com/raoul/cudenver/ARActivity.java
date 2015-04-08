package com.raoul.cudenver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

/*
* dummy activity to display PanicAR fragment
* */
public abstract class ARActivity extends ActionBarActivity {
    private static final int LOGIN_REQUEST = 0;
    ImageButton search_button;
    ImageButton logout_imagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        search_button=(ImageButton)findViewById(R.id.find_positionimageButton);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ARActivity.this,LocationActivity.class);
                startActivity(intent);
            }
        });
        logout_imagebutton=(ImageButton)findViewById(R.id.logout_imageButton);
        logout_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
        } else if (!(currentUser == null)) {

        }
        else {
            ParseLoginBuilder loginBuilder = new ParseLoginBuilder(
                    ARActivity.this);
            startActivityForResult(loginBuilder.build(), LOGIN_REQUEST);
        }
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PanicARFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
