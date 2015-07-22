package com.example.student.albemarleservicesandroid;

import android.content.Intent;
import android.graphics.Interpolator;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;


/**
 * Created by Student on 7/22/2015.
 */
public class ReportFormActivity extends ActionBarActivity {
    private static int PickImageId = 1000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set our view from the "report" layout resource
        setContentView(R.layout.report_form_res);

        // initializes a drop down menu that can be edited
        //Look in the Resources, Values, then Strings.xml for specific info
        //on what the selection types are for the dropdown menu
        Spinner probType = (Spinner) findViewById(R.id.probTypeField);

        // initializing textboxes that can be edited
        EditText location = (EditText) findViewById(R.id.locField);
        EditText probDesc = (EditText) findViewById(R.id.probDescField);
        EditText userEmailAddress = (EditText) findViewById(R.id.emailField);
        EditText userPhoneNumber = (EditText) findViewById(R.id.phoneField);

        // initializes the Image, and the button that is required to upload the image
        ImageView uploadedImg = (ImageView) findViewById(R.id.photoField);

        Button upload = (Button) findViewById(R.id.photoTitle);
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // this takes the place of the UploadOnClick method in Xamarin
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PickImageId);
            }
        });

        // initializes the button submitting the user info
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // this takes the place of the SubmitOnClick method in Xamarin

            }
        });
    }
}
