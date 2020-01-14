package com.example.jobtask;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jobtask.con_service.ConInicio;
import com.example.jobtask.con_service.ConSingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
//import  android.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText correo,psw;
Button logeo;
Button enviar;
TextView registro;
RequestQueue queue;
JsonObjectRequest json;
ConInicio conini;
String tpsw,tcorreo;
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

final String url="http://wedmkwmt.lucusvirtual.es/con.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //nombre=(EditText) findViewById(R.id.nombre);
        correo=(EditText) findViewById(R.id.correo);
        psw=(EditText) findViewById(R.id.psw);
        enviar=(Button) findViewById(R.id.enviar);
        enviar.setOnClickListener(this);
      queue=Volley.newRequestQueue(getApplicationContext());



        conini=new ConInicio(this);
       registro=(TextView) findViewById(R.id.registrarse);
       registro.setOnClickListener(this);
    }

  public void ejecutarservicio(String url){

       StringRequest stringreques=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               Toast.makeText(getApplicationContext(), "envio exitoso" + response, Toast.LENGTH_SHORT).show();

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(getApplicationContext(), "envio fallido"+error   , Toast.LENGTH_SHORT).show();
           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> parametros=new HashMap<String,String>();
               //parametros.put("nombre",nombre.getText().toString());
              parametros.put("correo",correo.getText().toString());
               parametros.put("psw",psw.getText().toString());

               return parametros;
           }
       };
        RequestQueue reques= Volley.newRequestQueue(this);
        reques.add(stringreques);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrarse:
               Intent registro=new Intent(MainActivity.this,RegistroUsuario.class);
                startActivity(registro);


            break;
            case R.id.enviar:
                //ejecutarservicio(url);
                try {
                    //obtengo el txt de correo y psw
                    tcorreo=correo.getText().toString();
                    tpsw=psw.getText().toString();
                    conini.setCorreo(tcorreo);
                    conini.setPsw(tpsw);

                   conini.inicio(url);
                   // conini.validacion("3");
                  //conwenservice(urll);
                 // conarray(urll);

                }catch(Exception x){
                    Toast.makeText(getApplicationContext(),"error  main",Toast.LENGTH_SHORT).show();}

                break;
        }
    }

    public void conwenservice( String url)  {
        Map<String,String> parametros=new HashMap<String,String>();
        parametros.put("correo","jhonatan");
        parametros.put("psw","3213");
       JSONObject obj = new JSONObject();
       try {
           obj.put("correo", "jhonatannn");
           obj.put("psw", "3213");

       }catch(Exception x){
           Toast.makeText(getApplicationContext(), "fallo en jsononjecreques", Toast.LENGTH_SHORT).show();
       }

        RequestQueue queue = Volley.newRequestQueue(this);
     JsonObjectRequest re=new JsonObjectRequest(Request.Method.POST, url, obj,new Response.Listener<JSONObject>() {
         @Override
         public void onResponse(JSONObject response) {
              Toast.makeText(getApplicationContext(),"funciono  " + response.toString(),Toast.LENGTH_SHORT).show();
          /*  try {
                 JSONArray arrayjson = response.optJSONArray("dato");
                 String[] array =new String[2];
                 for(int i=0;i<1;i++) {
                     JSONObject json = arrayjson.getJSONObject(i);
                     array[0] =json.optString("correo");
                     array[1]=json.optString("psw");

                 }
                 Toast.makeText(getApplicationContext(), "funciona"+array[0]+array[1] , Toast.LENGTH_SHORT).show();
             }catch(Exception x){
                 Toast.makeText(getApplicationContext(),"error 1",Toast.LENGTH_SHORT).show();

             }*/
         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             Toast.makeText(getApplicationContext(),"error 3"+error.toString(),Toast.LENGTH_SHORT).show();

         }
     })/*{
         @Override
         protected Map<String, String> getParams() throws AuthFailureError {
             Map<String,String> parametros=new HashMap<String,String>();
             //parametros.put("nombre",nombre.getText().toString());
             parametros.put("correo",correo.getText().toString());
             parametros.put("psw",psw.getText().toString());

             return parametros;
         }
     }*/
         ;
        queue.add(re);
       }
    public void envInnicio(String url){
       RequestQueue re= ConSingleton.getInstance(getApplicationContext()).getRequestQueue();
        //RequestQueue re=Volley.newRequestQueue(getApplicationContext());
        StringRequest consulta=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "envio exitoso" + response , Toast.LENGTH_SHORT).show();
                try{
                    JSONObject r=new JSONObject(response);
                      try {
                 JSONArray arrayjson = r.optJSONArray("dato");
                 String[] array =new String[2];
                 for(int i=0;i<1;i++) {
                     JSONObject json = arrayjson.getJSONObject(i);
                     array[0] =json.optString("id");
                 }
                 Toast.makeText(getApplicationContext(), "funciona"+array[0] , Toast.LENGTH_SHORT).show();
             }catch(Exception x){
                 Toast.makeText(getApplicationContext(),"error 1",Toast.LENGTH_SHORT).show();

             }
                }catch(Exception x){ Toast.makeText(getApplicationContext(),
                        "fallo al tratar de convetirlo en jsonobject " + response , Toast.LENGTH_SHORT).show();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "envio fallido"+error   , Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                //parametros.put("nombre",nombre.getText().toString());
                parametros.put("correo",correo.getText().toString());
                parametros.put("psw",psw.getText().toString());

                return parametros;
            }
        };
      //re.add(consulta);
        ConSingleton.getInstance(getApplicationContext()).add(consulta);

    }
    public void conarray(String url){
RequestQueue requess=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest reques=new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    String[] array =new String[2];
                    for(int i=0;i<1;i++) {
                        JSONObject json = response.getJSONObject(i);
                        array[0] =json.optString("correo");
                        array[1]=json.optString("psw");

                    }
                    Toast.makeText(getApplicationContext(), "funciona"+array[0]+array[1] , Toast.LENGTH_SHORT).show();
                }catch(Exception x){
                    Toast.makeText(getApplicationContext(),"error 1",Toast.LENGTH_SHORT).show();

                }

            }

            }
        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
 requess.add(reques);

}}
