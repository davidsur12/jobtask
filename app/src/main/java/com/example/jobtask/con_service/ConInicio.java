package com.example.jobtask.con_service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jobtask.R;
import com.example.jobtask.RegistroUsuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConInicio {
    Intent actividad;
   public Context contexto;
    public String url,correo,psw;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public ConInicio(Context contexto) {
        this.contexto = contexto;
       // this.url=url;

    }


    public  void inicio(String url){
       actividad= new Intent(contexto.getApplicationContext(), RegistroUsuario.class);
        RequestQueue resques=ConSingleton.getInstance(contexto).getRequestQueue();
        StringRequest peticion= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jobj=new JSONObject(response);
                    JSONArray arrayjson = jobj.optJSONArray("dato");
                    String[] array =new String[2];
                    for(int i=0;i<1;i++) {
                        JSONObject json = arrayjson.getJSONObject(i);
                        array[0] =json.optString("id");
                    }
                    validacion(array[0]);

                    //contexto.startActivity(actividad);
                }catch(Exception x){
                    Toast.makeText(contexto,"error 1",Toast.LENGTH_SHORT).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto,"error en response", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                //parametros.put("nombre",nombre.getText().toString());
                parametros.put("correo",correo);
                parametros.put("psw",psw);

                return parametros;
            }
        };
    ConSingleton.getInstance(contexto).add(peticion);



    }
    public void validacion(String id){
        if(id.equals("0")){
            Toast.makeText(contexto,"Correo o contraseña erroneos" + id,Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(contexto,id,Toast.LENGTH_SHORT).show();
            Intent usuario=new Intent(contexto, Usuario.class);
            usuario.putExtra("id",id);
            contexto.startActivity(usuario);
            
        }

    }

}
