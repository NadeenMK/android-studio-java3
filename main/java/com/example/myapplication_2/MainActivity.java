package com.example.myapplication_2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.sms).setOnClickListener(this);
        findViewById(R.id.email).setOnClickListener(this);
        findViewById(R.id.googleMap).setOnClickListener(this);
        findViewById(R.id.picture).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sms){
            String message = "Hello, this is your SMS message!";
            String users = "1234567890,9876543210,556699888,556699220";

            Uri smsUri = Uri.parse("smsto:" + users);
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        }
        else if(v.getId() == R.id.email){
            String[] users = {"user1@gmail.com", "user2@gmail.com","user3@gmail.com"};
            String[] cc = {"cc@gmail.com"};
            String[] bcc = {"bcc@gmail.com"};
            String subject = "Subject of your e-mail";
            String body = "This is email.";

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, users);
            emailIntent.putExtra(Intent.EXTRA_CC, cc);
            emailIntent.putExtra(Intent.EXTRA_BCC, bcc);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(emailIntent, "Send e-mail"));
        }
        else if(v.getId() == R.id.googleMap){
            String location = "palestine";
            Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        else if(v.getId() == R.id.picture){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }

}