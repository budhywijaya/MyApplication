package com.app.budi.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView UserText;
    private TextView PassText;
    public Button Loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserText = (TextView)findViewById(R.id.UserText);
        PassText = (TextView) findViewById(R.id.PassText);
        Loginbtn = (Button) findViewById(R.id.Loginbtn);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(UserText.getText().toString().equalsIgnoreCase("admin") && PassText.getText().toString().equals("pass"))
            {
                Toast.makeText(getApplicationContext(),"Berhasil Login",Toast.LENGTH_LONG).show();
                Intent i = new Intent(LoginActivity.this,InputActivity.class);
                startActivity(i);
            }
               else if(UserText.getText().toString().equalsIgnoreCase("user") && PassText.getText().toString().equals("pass")) {
                Toast.makeText(getApplicationContext(),"Berhasil Login",Toast.LENGTH_LONG).show();
                Intent a = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(a);
            }
                else{
                Toast.makeText(getApplicationContext(),"Gagal Login",Toast.LENGTH_LONG).show();
            }
            }
        });

    }
}
