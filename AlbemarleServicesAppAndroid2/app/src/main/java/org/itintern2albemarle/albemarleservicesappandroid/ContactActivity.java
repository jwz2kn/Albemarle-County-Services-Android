package org.itintern2albemarle.albemarleservicesappandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ContactActivity extends ActionBarActivity {
    private TextView contact5;
    private TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set our view from the "report" layout resource
        setContentView(R.layout.contact_res);
        setTopBar();
        // Get the text specified in the xml
//        TextView contact1 = (TextView) findViewById(R.id.contactinfo1);
//        TextView contact2 = (TextView) findViewById(R.id.contactinfo2);
//        TextView contact3 = (TextView) findViewById(R.id.contactinfo3);
//        TextView contact4 = (TextView) findViewById(R.id.contactinfo4);
        contact5 = (TextView) findViewById(R.id.contactinfo5);
        about = (TextView) findViewById(R.id.about);
        // Make contact5 (the albemarle.org link) a clickable link
        contact5.setTextIsSelectable(true);
        contact5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click, take to website
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "http://www.albemarle.org"));
                startActivity(i);
            }
        });

        about.setTextIsSelectable(true);
        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click, take to website
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "http://www.albemarle.org/page.asp?info=demog"));
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTopBar(){
        Bitmap barBackground = BitmapFactory.decodeResource(getResources(), R.drawable.albemarleviewlong2);
        BitmapDrawable actionBarBackground = new BitmapDrawable(getResources(), barBackground);
        Bitmap barLogo = BitmapFactory.decodeResource(getResources(), R.drawable.albemarlecounty);
        BitmapDrawable actionBarLogo = new BitmapDrawable(getResources(), barLogo);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(actionBarBackground);
        bar.setIcon(actionBarLogo);
        bar.setDisplayUseLogoEnabled(true);
        bar.setDisplayShowHomeEnabled(false);
    }
}

