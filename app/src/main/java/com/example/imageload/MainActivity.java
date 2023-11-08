package com.example.imageload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends Activity {

    private static final int GALLERY_REQUEST_CODE = 100;
    private ImageView imageView;
    private Button selectImageButton;
    private EditText etA;
    private EditText etB;
private Button btnCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectComponents();
        imageView = findViewById(R.id.imageView);
        selectImageButton = findViewById(R.id.selectImageButton);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void connectComponents() {
        etA=findViewById(R.id.etAMain);
        etB=findViewById(R.id.etBMain);
        btnCalculate=findViewById(R.id.btnCalculateMain);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                Bitmap selectedImageBitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(selectedImageBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void gotoCalculatePhytagoras(View view) {
        Double A= Double.valueOf(etA.getText().toString());
        Double B= Double.valueOf(etB.getText().toString());
        Double Result=Math.sqrt(Math.pow(A,2)+Math.pow(B,2));
        Toast.makeText(this, "The Answer Is:"+Result, Toast.LENGTH_LONG).show();

    }
}

