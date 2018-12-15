package com.example.danny.apprepositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danny.apprepositorio.entidades.Circulos;
import com.example.danny.apprepositorio.utilidades.UtilidadesCirculos;
import com.example.danny.apprepositorio.utilidades.UtilidadesMiscirculos;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCirculosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCirculosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCirculosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ListView listViewCirculos;
    ArrayList<String> listaInformacion;
    ArrayList<Circulos> listaCirculos;
    ConexionSQLiteHelper conn;

    public ListaCirculosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCirculosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCirculosFragment newInstance(String param1, String param2) {
        ListaCirculosFragment fragment = new ListaCirculosFragment();
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

    private void consultarListaForo() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Circulos circulos = null;
        listaCirculos = new ArrayList<Circulos>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UtilidadesCirculos.TABLA_CIRCULOS, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            circulos = new Circulos();
            circulos.setId(cursor.getInt(0));
            circulos.setNombrecirculo(cursor.getString(1));
            circulos.setDescrip(cursor.getString(2));
            circulos.setCategoria(cursor.getString(3));

            listaCirculos.add(circulos);
        }
        obtenerLista();
    }

    public void consultarlist() {
        conn = new ConexionSQLiteHelper(getActivity(), "bd_app", null, 1);

        try {
            consultarListaForo();

            ArrayAdapter adaptador = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listaInformacion);
            listViewCirculos.setAdapter(adaptador);
            //Comentario
            listViewCirculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String informacion="";// = "id: " + listaCirculos.get(position).getId() + "\n";
                    informacion += "Titulo: " + listaCirculos.get(position).getNombrecirculo() + "\n";
                    informacion += "Descripcion: " + listaCirculos.get(position).getDescrip();
                    Toast.makeText(getActivity(), informacion, Toast.LENGTH_SHORT).show();
                    registar(view, listaCirculos.get(position));
                }
            });
        } catch (Exception e) {
            //Toast.makeText(getActivity(), "Error we", Toast.LENGTH_SHORT).show();
        }
    }

    public void registar(View v, Circulos circulos) {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "bd_app", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();
        Random r = new Random();
        int i = r.nextInt(1000);
        ContentValues values = new ContentValues();
        values.put(UtilidadesMiscirculos.CAMPO_ID, (circulos.getId()));
        values.put(UtilidadesMiscirculos.CAMPO_NOMBRE, circulos.getNombrecirculo());
        values.put(UtilidadesMiscirculos.CAMPO_DESCRIPCION, circulos.getDescrip());
        values.put(UtilidadesMiscirculos.CAMPO_USER, "user");
        values.put(UtilidadesMiscirculos.CAMPO_IDUSER, 1);

        Long idResultante = db.insert(UtilidadesMiscirculos.TABLA_MISCIRCULOS, UtilidadesMiscirculos.CAMPO_ID, values);
        Toast.makeText(getActivity(), "ID registro mi circulo:" + idResultante, Toast.LENGTH_SHORT).show();
        db.close();

        MisCirculosFragment frag = new MisCirculosFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorfragmentscirculos, frag)
                .addToBackStack(null)
                .commit();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaCirculos.size(); i++) {
            listaInformacion.add(listaCirculos.get(i).getNombrecirculo() + " - " +
                    listaCirculos.get(i).getDescrip());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_circulos, container, false);
        listViewCirculos = (ListView) v.findViewById(R.id.gruposCirculos);
        consultarlist();
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
