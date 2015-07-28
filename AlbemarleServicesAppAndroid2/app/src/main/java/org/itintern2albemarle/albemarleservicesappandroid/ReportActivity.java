package org.itintern2albemarle.albemarleservicesappandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class ReportActivity extends ActionBarActivity {
    protected Button streets, pothole, graffiti, other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set our view from the "report" layout resource
        setContentView(R.layout.report_res);
        setTopBar();
        // Get our buttons from the layout resource, the axml
        // Each button declaration followed by its own onclicklistener in onResume
        // to tell it what to do
        streets = (Button) findViewById(R.id.streets);
        pothole = (Button) findViewById(R.id.pothole);
        graffiti = (Button) findViewById(R.id.graffiti);
        other = (Button) findViewById(R.id.other);
    }

    @Override
    protected void onResume() {
        super.onResume();

        streets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ReportFormActivity
                ReportFormActivity.setProblemTypePosition(1);
                Intent i = new Intent(ReportActivity.this, ReportFormActivity.class);
                startActivity(i);
            }
        });

        pothole.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click, take to website
                String url = "https://my.vdot.virginia.gov/";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        graffiti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ReportFormActivity
                ReportFormActivity.setProblemTypePosition(2);
                Intent i = new Intent(ReportActivity.this, ReportFormActivity.class);
                startActivity(i);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ReportFormActivity
                ReportFormActivity.setProblemTypePosition(0);
                Intent i = new Intent(ReportActivity.this, ReportFormActivity.class);
                startActivity(i);
            }
        });
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
