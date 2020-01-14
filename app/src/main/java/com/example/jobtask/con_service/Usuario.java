package com.example.jobtask.con_service;
import com.example.jobtask.R;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Usuario extends AppCompatActivity {
String id;
TextView txtid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        txtid=(TextView) findViewById(R.id.idusuario);

        id= getIntent().getStringExtra("id");

        Toast.makeText(this, "id = " + id, Toast.LENGTH_SHORT).show();
        txtid.setText("id  : " + id);

    }
}
