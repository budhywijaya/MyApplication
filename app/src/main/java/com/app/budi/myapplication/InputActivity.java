package com.app.budi.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InputActivity extends AppCompatActivity {

    EditText NamaText, LksText ;
    ImageView iView;
    Button Inputbtn, Imagebtn, Listbtn;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLite sqlite;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);

        init();

        sqlite = new SQLite (this, "ListDB.sqlite", null, 1);
        sqlite.queryData("CREATE TABLE IF NOT EXISTS LIST (id INTEGER PRIMARY KEY AUTOINCREMENT, nama VARCHAR, lokasi VARCHAR, image BLOB)");

        Imagebtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(InputActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY
                );
            }
        });

        Inputbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{

                    sqlite.insertData(
                            NamaText.getText().toString().trim(),
                            LksText.getText().toString().trim(),
                            iViewToByte(iView)
                    );

                    Toast.makeText(getApplicationContext(),"Lokasi Berhasil Ditambahkan!", Toast.LENGTH_SHORT).show();
                    NamaText.setText("");
                    LksText.setText("");

                    iView.setImageResource(R.mipmap.ic_launcher);

                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        Listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(InputActivity.this, MainActivity.class);
                startActivity(o);
            }
        });
    }

    private byte[] iViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent q = new Intent (Intent.ACTION_PICK);
                q.setType("image/*");
                startActivityForResult(q, REQUEST_CODE_GALLERY);

            }
            else{
                Toast.makeText(getApplicationContext(),"Permission not granted, SORRY!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        NamaText = (EditText) findViewById(R.id.NamaText);
        LksText = (EditText) findViewById(R.id.LksText);

        iView = (ImageView)findViewById(R.id.iView);

        Inputbtn = (Button) findViewById(R.id.Inputbtn);
        Imagebtn = (Button) findViewById(R.id.Imagebtn);
        Listbtn = (Button) findViewById(R.id.Listbtn);



    }
}