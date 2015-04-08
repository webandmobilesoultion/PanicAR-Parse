package com.raoul.cudenver;

import android.app.Application;

import com.dopanic.panicarkit.lib.PARApplication;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseTwitterUtils;

/**
 * Created by doPanic on 26.02.14.
 */
public class CudenverApplication extends PARApplication{

    @Override
    public void onCreate() {
        super.onCreate();





		/*
		 * Fill in this section with your Parse credentials
		 */
        Parse.initialize(this, getString(R.string.parse_app_id),
                getString(R.string.parse_client_key));
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        // Optional - If you don't want to allow Twitter login, you can
        // remove this line (and other related ParseTwitterUtils calls)
//        ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key),
//                getString(R.string.twitter_consumer_secret));


		/*
		 * For more information on app security and Parse ACL:
		 * https://www.parse.com/docs/android_guide#security-recommendations
		 */
        ParseACL defaultACL = new ParseACL();

		/*
		 * If you would like all objects to be private by default, remove this
		 * line
		 */
        defaultACL.setPublicReadAccess(true);

		/*
		 * Default ACL is public read access, and user read/write access
		 */
        ParseACL.setDefaultACL(defaultACL, true);

		/*
		 *  Register for push notifications.
		 */


        ParseInstallation installation = ParseInstallation.getCurrentInstallation();

        installation.saveInBackground();



    }

    @Override
    public String setApiKey() {
        return "";
    }
}
