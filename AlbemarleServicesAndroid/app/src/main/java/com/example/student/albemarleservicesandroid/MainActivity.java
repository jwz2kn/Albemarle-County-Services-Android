package com.example.student.albemarleservicesandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/*
Converting c# version of the Albemarle Services app to java.
 */
public class MainActivity extends ActionBarActivity {
    protected Button report, zoning, law, certificate, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get our buttons from the layout resource, the axml, in which we set their Android layout IDs
        // Each button declaration followed by its own onclicklistener to tell it what to do
        report = (Button) findViewById(R.id.report);
        zoning = (Button) findViewById(R.id.zoning);
        law = (Button) findViewById(R.id.law);
        certificate = (Button) findViewById(R.id.certificate);
        contact = (Button) findViewById(R.id.contact);
    }

    @Override
    protected void onResume() {
        super.onResume();

        report.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ReportActivity
                Intent i = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(i);
            }
        });

        zoning.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });

        law.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String url = "https://www.albemarle.org/policeonlinereporting/";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                ReportFormActivity.setProblemTypePosition(3);
                Intent j = new Intent(MainActivity.this, ReportFormActivity.class);
                startActivity(i);
                //startActivity(j);
            }
        });

        certificate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click, take to website
                String url = "http://www.vdh.virginia.gov/Vital_Records/";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action specified in ContactActivity
                Intent i = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(i);
            }
        });

    }

    // Should we have a settings menu that maybe takes you back to the main page?
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
