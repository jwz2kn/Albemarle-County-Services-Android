package com.example.student.albemarleservicesandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Student on 7/22/2015.
 */
public class ReportActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set our view from the "report" layout resource
        setContentView(R.layout.report_res);

        // Get our buttons from the layout resource, the axml
        // Each button declaration followed by its own onclicklistener to tell it what to do
        Button streets = (Button) findViewById(R.id.streets);
        streets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ReportFormActivity
                //ReportFormActivity.ProblemTypePosition = 1;
                Intent i = new Intent(ReportActivity.this, ReportFormActivity.class);
                startActivity(i);
            }
        });

        Button pothole = (Button) findViewById(R.id.pothole);
        pothole.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click, take to website
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "http://www.my.vdot.virginia.gov/"));
                startActivity(i);
            }
        });

        Button graffiti = (Button) findViewById(R.id.graffiti);
        graffiti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ReportFormActivity
                //ReportFormActivity.ProblemTypePosition = 2;
                Intent i = new Intent(ReportActivity.this, ReportFormActivity.class);
                startActivity(i);
            }
        });

        Button other = (Button) findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ReportFormActivity
                //ReportFormActivity.ProblemTypePosition = 0;
                Intent i = new Intent(ReportActivity.this, ReportFormActivity.class);
                startActivity(i);
            }
        });
    }
}
