package com.example.walter.artifactlogger;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi; //why?
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Vector;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class Artifact extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    public ImageView preview;
    ImageButton add_picture_button;
    ImageButton save_artifact_button;
    ImageButton location_but;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;
    public String imageFileName;
    public int imageCount; //increment for every image saved
    public artifactObject newArtifact = new artifactObject();
    public EditText artifact_number_view;
    public EditText location_view;
    public EditText depth_view;
    public EditText pre_hist_view;
    public EditText desc_view;
    public String mCurrentPhotoPath;
    public Vector<String> photo_log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifact);

        //artifactObject newArtifact = new artifactObject();
        add_picture_button = (ImageButton)findViewById(R.id.add_picture_button);
        add_picture_button.setOnClickListener(this);
        save_artifact_button = (ImageButton)findViewById(R.id.save_button);
        save_artifact_button.setOnClickListener(this);
        location_but = (ImageButton)findViewById(R.id.location_button);
        location_but.setOnClickListener(this);
        //ImageView photoPreview = (ImageView)findViewById(R.id.camera_preview);
        artifact_number_view = (EditText)findViewById(R.id.artifact_number_text);
        location_view = (EditText)findViewById(R.id.location_text);
        depth_view = (EditText)findViewById(R.id.depth_text);
        pre_hist_view = (EditText)findViewById(R.id.pre_hist_text);
        desc_view = (EditText)findViewById(R.id.depth_text);
        preview = (ImageView)findViewById(R.id.camera_preview);
        photo_log = new Vector(1);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_picture_button:
                dispatchTakePictureIntent();
                photo_log.addElement(mCurrentPhotoPath);
                galleryAddPic();
                setPic();
                break;
            case R.id.save_button:
                //save Artifact as artifactObject
                newArtifact = saveArtifact(newArtifact);
                break;
            case R.id.location_button:
                //Pull GPS cords from phone
                location_view.setText(getLocation(), TextView.BufferType.EDITABLE);
                break;
            default:
                break;
        }
    }

    public artifactObject saveArtifact(artifactObject thisArtifact){
        thisArtifact.setArtifact_number(artifact_number_view.getText().toString());
        thisArtifact.setLocation(location_view.getText().toString());
        thisArtifact.setDepth(depth_view.getText().toString());
        thisArtifact.setHist_pre(pre_hist_view.getText().toString());
        thisArtifact.setDescription(desc_view.getText().toString());

        return thisArtifact;
    }

    public String getImageName(){
        String imageName = "ArtNum_" + "_ImageNum_";
        imageCount++;
        return imageName;
    }

    private File createImageFile() throws IOException {
         //Create an image file name
        //String imageFileName = getImageName();
        File artifactImages = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                "TESTIMAGE",  /* prefix */
                ".jpg",         /* suffix */
                artifactImages      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                return; //error occured, exit method...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.example.walter.artifactlogger.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            Bitmap photo = BitmapFactory.decodeFile(mCurrentPhotoPath);
            preview.setImageBitmap(photo);
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = preview.getWidth();
        int targetH = preview.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        preview.setImageBitmap(bitmap);
    }

    public boolean checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_ACCESS_FINE_LOCATION);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                return true;
            else
                return false;
        }
        else
            return true;
    }

    public String getLocation(){
        LocationManager lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkPermission() == true) {
            Location curLocation = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (curLocation != null) {
                double numLat = curLocation.getLatitude();
                double numLon = curLocation.getLongitude();
                DecimalFormat df = new DecimalFormat(".##");
                String StringLat = df.format(numLat);
                String StringLon = df.format(numLon);
                String thisLocation = ("Lat:" + StringLat + " Lon:" + StringLon);
                return thisLocation;
            }
            else
                return "GPS data no available";
        }
        else if (checkPermission() == false)
            return "false";
        else
            return "error";

    }





}

