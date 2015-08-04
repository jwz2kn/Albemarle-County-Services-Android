package org.itintern2albemarle.albemarleservicesappandroid;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
Converting c# version of the Albemarle Services app to java.
 */
public class MainActivity extends ActionBarActivity {
    protected Button report, zoning, law, certificate, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTopBar();
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
                String url = "http://www.albemarle.org/department.asp?department=cdd&relpage=2778";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        law.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ReportFormActivity.setProblemTypePosition(3);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setCancelable(true);
                alert.setTitle("ACoS Law Enforcement Questions");
                alert.setMessage(
                        "Please visit the online police reporting site to ensure " +
                        "your question is both non-emergency and not covered by the current online reporting system. \n\n" +
                        "Go back to this app to access the ACoS report form if still applicable."
                );
                alert.setPositiveButton("VISIT SITE NOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = "https://www.albemarle.org/policeonlinereporting/";
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        Intent j = new Intent(MainActivity.this, ReportFormActivity.class);
                        startActivity(j);
                        startActivity(i);
                        dialog.cancel();
//                        new Thread(new Runnable() {
//                            public void run() {
//                                try {
//                                    Thread.sleep(4000);
//                                    Intent j = new Intent(MainActivity.this,
//                                            ReportFormActivity.class);
//                                    startActivity(j);
//                                } catch (Exception ex) {
//
//                                }
//
//                            }
//                        }).start();
                    }
                });
                alert.setNegativeButton("DON'T VISIT SITE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(MainActivity.this, ReportFormActivity.class);
                        startActivity(j);
                        dialog.cancel();
                    }
                });
                alert.show();
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
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowHomeEnabled(false);
    }
}
