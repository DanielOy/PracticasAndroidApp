package com.example.danny.apprepositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danny.apprepositorio.utilidades.UtilidadesCirculos;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrearCirculosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrearCirculosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearCirculosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    EditText nombre_circulo,categoria_circulo, descrpcion_ciruclo;
    Button  btnaceptarcirculo, btncancealrcirculo;
    public CrearCirculosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearCirculosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearCirculosFragment newInstance(String param1, String param2) {
        CrearCirculosFragment fragment = new CrearCirculosFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crear_circulos, container, false);

        nombre_circulo = (EditText) v.findViewById(R.id.nombreCirculo);
        categoria_circulo = (EditText) v.findViewById(R.id.categoriaCirculo);
        descrpcion_ciruclo = (EditText) v.findViewById(R.id.categoriaCirculo);
        btnaceptarcirculo = (Button) v.findViewById(R.id.btnAceptarCirculos);
        btnaceptarcirculo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(),"bd_app",null,1);

                        SQLiteDatabase db = conn.getWritableDatabase();
                        Random r = new Random();
                        int i = r.nextInt();
                        ContentValues values = new ContentValues();
                        values.put(UtilidadesCirculos.CAMPO_ID,(i+""));
                        values.put(UtilidadesCirculos.CAMPO_NOMBRE,nombre_circulo.getText().toString());
                        values.put(UtilidadesCirculos.CAMPO_DESCRIPCION,descrpcion_ciruclo.getText().toString());
                        values.put(UtilidadesCirculos.CAMPO_CATEGORIA,categoria_circulo.getText().toString());


                        Long idResultante = db.insert(UtilidadesCirculos.TABLA_CIRCULOS,UtilidadesCirculos.CAMPO_ID,values);

                        Toast.makeText(getActivity(), "ID registro:"+idResultante, Toast.LENGTH_SHORT).show();
                        db.close();
                    }
                }
        );
        btncancealrcirculo =(Button) v.findViewById(R.id.btnCancelarCirculos);
        btncancealrcirculo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nombre_circulo.setText("");
                        categoria_circulo.setText("");
                        descrpcion_ciruclo.getEditableText().clear();
                        Toast.makeText(getActivity(),"Se cancelo la creacion",Toast.LENGTH_LONG).show();
                    }
                }
        );

        return v;


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
}
