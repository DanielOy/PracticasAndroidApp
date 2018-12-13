package com.example.danny.apprepositorio.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danny.apprepositorio.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter
{
    private Context context;
    private ArrayList<Entidad> listItems;

    public Adaptador(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad item = (Entidad)getItem(position);

       convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.idImg);
        TextView tvTitulo = (TextView)convertView.findViewById(R.id.tvTitulo);
        TextView tvContenido = (TextView)convertView.findViewById(R.id.tvContenido);

        imgFoto.setImageResource(item.getImgFoto());
        tvTitulo.setText(item.getTitulo());
        tvContenido.setText(item.getContenido());
        return convertView;
    }
}
