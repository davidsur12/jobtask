package com.example.jobtask;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jobtask.Dialogos.DateFecha;

import java.util.Calendar;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//import Framents.DatePicker;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragmentdatos1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragmentdatos1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentdatos1 extends Fragment {

   private DateFecha dpicker;
    private String fecha;
    private Editable nom;
    private String gen;
    private EditText nombre,apellido,correo,contra,fechana;
    private String[] datosRegistro= new String[6];
    private FragmentManager fm;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
  //  View vista;

    public Fragmentdatos1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmentdatos1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmentdatos1 newInstance(String param1, String param2) {
        Fragmentdatos1 fragment = new Fragmentdatos1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View  vista =inflater.inflate(R.layout.fragment_fragmentdatos1,container,false);
         nombre=(EditText) vista.findViewById(R.id.nombre);
         apellido=(EditText) vista.findViewById(R.id.apellido);
         correo=(EditText) vista.findViewById(R.id.correo);
         contra=(EditText) vista.findViewById(R.id.psw);
        fechana=(EditText) vista.findViewById(R.id.fecnac);
        RadioGroup genero=(RadioGroup) vista.findViewById(R.id.ggenero);
        Button ok=(Button) vista.findViewById(R.id.ok);

        fechana.setInputType(InputType.TYPE_NULL);
        fechana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dpicker= DateFecha.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fecha=  fechaF(year , month , dayOfMonth);
                        Toast.makeText(getContext(),fecha + nom,Toast.LENGTH_SHORT).show();
                    }
                });
                dpicker.show(fm,"fm");

            }
        });

       genero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch(checkedId){
                   case R.id.masculino:
                       gen="masculino";
                       break;
                   case R.id.femenino:
                       gen="femenino";
                       break;
                   case R.id.otro:
                       gen="otro";

                       break;
               }
           }
       });




        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  datos();
                Toast.makeText(getContext(),"" + datosRegistro,Toast.LENGTH_SHORT).show();


            }
        });




        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public boolean validarCadena(String cadena){
        boolean valido=false;
        cadena.trim();
        cadena.replaceAll("\\s","");
        if(cadena.length()>0){
            valido=true;
        }
        return valido;

    }

    public void fecha(){
        Calendar C=Calendar.getInstance();
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }
    private String fechaF(int year , int moth , int dia){
        String fecha=dia + "/" + moth + "/" + year;
        return fecha;
    }
    private void datos(){

        datosRegistro[0]=nombre.getText().toString();
        datosRegistro[1]=apellido.getText().toString();
        datosRegistro[2]=correo.getText().toString();
        datosRegistro[3]=contra.getText().toString();
        datosRegistro[4]=fecha;
        datosRegistro[5]=gen;


    }



}
