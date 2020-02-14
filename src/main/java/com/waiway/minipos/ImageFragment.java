package com.waiway.minipos;


import android.Manifest;
import android.app.AppComponentFactory;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    private static final int GALLERY_CODE = 125;

    public ImageFragment() {
        // Required empty public constructor
    }

    View v;
    Button btnCapture, choosePhoto;
    ImageView img;
    Context context;

    public static  int PERMISSION_CODE = 123, CAMERA_CODE=124,PIC_IMG=126;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_image, container, false);
        btnCapture = v.findViewById(R.id.takePhoto);
        choosePhoto = v.findViewById(R.id.choosePicture);
        img = v.findViewById(R.id.itemImage);
        context = getContext();
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission(btnCapture.getId());
            }
        });
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission(choosePhoto.getId());
            }
        });

        return v;
    }


    public void CheckPermission(int id)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            && context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            )
            {
                if(id == R.id.takePhoto)
                    takePhoto();
                else
                    choosePhotos();

            }
            else {
                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                && shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                 )
                {
                    Toast.makeText(getContext(),"read Storage is required", Toast.LENGTH_LONG).show();
                }
                String [] permissions = new String[]
                        {
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        };
                if(id == R.id.takePhoto)
                    requestPermissions(permissions,PERMISSION_CODE);
                else
                    requestPermissions(permissions,GALLERY_CODE);
            }
        }
    }

    public  void  choosePhotos()
    {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(gallery,PIC_IMG);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_DENIED
            && grantResults [1] == PackageManager.PERMISSION_DENIED
            && grantResults[2] == PackageManager.PERMISSION_DENIED)
            {

                takePhoto();
            }
        }
        else
        {
            if(grantResults[0] == PackageManager.PERMISSION_DENIED
                    && grantResults [1] == PackageManager.PERMISSION_DENIED
                    && grantResults[2] == PackageManager.PERMISSION_DENIED)
            {

                choosePhotos();
            }
        }
    }

    public  static Uri imageuri;
    private void takePhoto() {
        SimpleDateFormat myFormat = new SimpleDateFormat("ddMyyyyhh:mm:ss");
        String picname = myFormat.format(new Date());
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,picname);
        values.put(MediaStore.Images.Media.DESCRIPTION,"Minipos");
        imageuri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        startActivityForResult(cameraIntent,PERMISSION_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_CODE)
        {
            if(resultCode == AppCompatActivity.RESULT_OK)
            {
                img.setImageURI(imageuri);
            }
        }
        else if(requestCode == PIC_IMG)
        {
            if(resultCode == AppCompatActivity.RESULT_OK) {
                img.setImageURI(data.getData());
                imageuri = data.getData();
            }
        }
    }
}
