package org.itintern2albemarle.albemarleservicesappandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class ContactActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set our view from the "report" layout resource
        setContentView(R.layout.contact_res);

        // Get the text specified in the xml
        TextView contact1 = (TextView) findViewById(R.id.contactinfo1);
        TextView contact2 = (TextView) findViewById(R.id.contactinfo2);
        TextView contact3 = (TextView) findViewById(R.id.contactinfo3);
        TextView contact4 = (TextView) findViewById(R.id.contactinfo4);
        TextView contact5 = (TextView) findViewById(R.id.contactinfo5);

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

    }
}

