package org.itintern2albemarle.albemarleservicesappandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ReportFormActivity extends ActionBarActivity {

    private static int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private static int ProblemTypePosition = 0;
    protected Spinner probType;
    protected EditText location, probDesc, userEmailAddress, userPhoneNumber;
    protected ImageView uploadedImg;
    protected Button upload, submit;
    private String probTypeText = "", recipientEmailAddress = "", formatEmailBody = "";
    private Uri uri = null;
    private AlertDialog.Builder alert;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set our view from the "report" layout resource
        setContentView(R.layout.report_form_res);
        setTopBar();
        // initializes a drop down menu that can be edited
        //Look in the Resources, Values, then Strings.xml for specific info
        //on what the selection types are for the dropdown menu
        probType = (Spinner) findViewById(R.id.probTypeField);

        // initializing textboxes that can be edited
        location = (EditText) findViewById(R.id.locField);
        probDesc = (EditText) findViewById(R.id.probDescField);
        userEmailAddress = (EditText) findViewById(R.id.emailField);
        userPhoneNumber = (EditText) findViewById(R.id.phoneField);

        // initializes the Image, and the button that is required to upload the image
        uploadedImg = (ImageView) findViewById(R.id.photoField);
        upload = (Button) findViewById(R.id.photoTitle);

        // initializes the button submitting the user info
        submit = (Button) findViewById(R.id.submit);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // initializes the button that is required to upload the image
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // this takes the place of the UploadOnClick method in Xamarin
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }

        });


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // this takes the place of the SubmitOnClick method in Xamarin
                //sendEmail(formatEmailBody);

                alert = new AlertDialog.Builder(ReportFormActivity.this);
                alert.setCancelable(true);
                alert.setTitle("Albemarle County Services");
                alert.setPositiveButton("VISIT SITE NOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = "https://www.albemarle.org/policeonlinereporting/";
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(i);
                        dialog.cancel();
                    }
                });
                alert.setNegativeButton("I UNDERSTAND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendEmail("jwz2kn@virginia.edu");
                        //sendEmail(formatEmailBody);
                        dialog.cancel();
                    }
                });

                if(userPhoneNumber.getText().toString().matches("") ||
                        userEmailAddress.getText().toString().matches("") ||
                        probDesc.getText().toString().matches("")){
                    alert = new AlertDialog.Builder(ReportFormActivity.this);
                    alert.setCancelable(true);
                    alert.setTitle("Missing Information");
                    alert.setMessage("Please enter your preferred email address, phone number, " +
                            "and a description of the problem.");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                } else {
                    if (probTypeText.equals("Graffiti") || getProblemTypePosition() == 2) {
                        alert.setMessage("Graffiti and vandalism fall under the jurisdiction of the police department. " +
                                "Please be aware that your report will be sent to the police. \n" +
                                "For more information on which issues fall under police jurisdiction, please visit the website."
                        );
                        alert.show();
                    } else if (probTypeText.equals("Non-Emergency Law Enforcement Questions") ||
                            getProblemTypePosition() == 3) {
                        alert.setMessage(
                                "By clicking 'I UNDERSTAND' you agree that your law enforcement related complaint/concern is classified " +
                                "as a non-emergency and is not covered by the current online reporting system " +
                                "according to the police reporting site."
                        );
                        alert.show();
                    } else {
                        sendEmail("jwz2kn@virginia.edu");
                        //sendEmail(formatEmailBody);
                    }
                }

            }
        });

        String[] problem_array = getResources().getStringArray(R.array.problem_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,
                android.R.layout.simple_spinner_dropdown_item,
                problem_array);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        probType.setAdapter(adapter);
        probType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                probTypeText = parentView.getItemAtPosition(position).toString();
                ProblemTypePosition = position;
                switch (probTypeText) {
                    case "Other":
                        recipientEmailAddress = "AskAlb@albemarle.org";
                        break;
                    case "Broken Street Sign":
                        recipientEmailAddress = "GeneralServicesWorkOrderRequest@albemarle.org";
                        break;
                    case "Graffiti":
                        recipientEmailAddress = "hintzc@albemarle.org";
                        break;
                    case "Non-Emergency Law Enforcement Questions":
                        recipientEmailAddress = "hintzc@albemarle.org";
                        break;
                    case "Voter Information":
                        recipientEmailAddress = "VoterRegistration@albemarle.org";
                        break;
                    default:
                        recipientEmailAddress = "jwz2kn@virginia.edu";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        probType.setSelection(ProblemTypePosition);
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

    public static int getProblemTypePosition() {
        return ProblemTypePosition;
    }

    public static void setProblemTypePosition(int problemTypePosition) {
        ProblemTypePosition = problemTypePosition;
    }

    private String formatEmail(String value) {
        formatEmailBody = value;
        return formatEmailBody;
    }

    private String formatEmail() {
        formatEmailBody = "";
        formatEmailBody = "Type Of Problem: " + probTypeText;
        formatEmailBody += "\r\n\n";
        formatEmailBody = formatEmailBody + "Location: " + location.getText();
        formatEmailBody += "\r\n\n";
        formatEmailBody = formatEmailBody + "Description: " + probDesc.getText();
        formatEmailBody += "\r\n\n";
        formatEmailBody = formatEmailBody + "Email Address: " + userEmailAddress.getText();
        formatEmailBody += "\r\n\n";
        formatEmailBody = formatEmailBody + "Phone Number: " + userPhoneNumber.getText();
        formatEmailBody += "\r\n\n\n";
        formatEmailBody += "This message was generated by the Albemarle County Services Mobile Application.";
        return formatEmailBody;
    }

    private void sendEmail(String emailAddress) {
        Intent email = new Intent (android.content.Intent.ACTION_SEND);
        try
        {
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
            email.putExtra(Intent.EXTRA_SUBJECT, "Albemarle County Services Mobile App Problem Report: "
                    + probTypeText);
            email.putExtra(Intent.EXTRA_TEXT, formatEmail());
        } catch (Exception ex) {
            Toast.makeText(ReportFormActivity.this, "Please check the email's format!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        //attach photo to email
        try{
            email.putExtra(Intent.EXTRA_STREAM, uri);
        } catch (Exception ex) {
            Toast.makeText(ReportFormActivity.this, "Photo Not Found",
                    Toast.LENGTH_LONG).show();
            return;
        }

        //send email
        try {
            email.setType("message/rfc822");
            startActivity(email);
        } catch (Exception ex) {
            Toast.makeText(ReportFormActivity.this, "Email error. " +
                            "\nMake sure you have an email sending application " +
                            "installed on your device. " +
                            "\nPlease see Contact Us for help.",
                    Toast.LENGTH_LONG).show();
        }

        if (probTypeText.equals("Voter Information")) {
            String url = "http://" +
                    "www.albemarle.org/department.asp?department=registrar&relpage=2934";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {

            uri = imageReturnedIntent.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplicationContext().getContentResolver().query(
                    uri, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            final int REQUIRED_SIZE = 140;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;

            Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath, o2);
            while (yourSelectedImage.getHeight() > 4096 || yourSelectedImage.getWidth() > 4096) {
                yourSelectedImage = Bitmap.createScaledBitmap(yourSelectedImage,
                        yourSelectedImage.getWidth()/2, yourSelectedImage.getHeight()/2, true);
            }
            uploadedImg.setImageBitmap(null);
            uploadedImg.setImageBitmap(yourSelectedImage);
            //uploadedImg.setImageURI(uri);
        }
    }
}
