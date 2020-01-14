package com.example.jobtask;

import android.net.Uri;

//import android.support.v4.app.FragmentManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegistroUsuario extends AppCompatActivity implements Fragmentdatos1.OnFragmentInteractionListener {

private EditText nombre,apellido,edad,correo,psw;
private CheckBox hombre,mujer,otro;
private Button siguiente;
private String[] datos=new String[5];
final String url="https://blyazkhb.lucusvirtual.es/service.php";
Fragmentdatos1 frame;
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
  frame=new Fragmentdatos1();
  frame.setFm(fm);
 // frame.con=this;
  getSupportFragmentManager().beginTransaction().add(R.id.framee,frame).commit();



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
