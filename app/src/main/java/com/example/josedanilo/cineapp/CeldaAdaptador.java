package com.example.josedanilo.cineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class CeldaAdaptador extends ArrayAdapter<JSONObject> {

    public CeldaAdaptador(Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaAdaptador(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_compleja,null);
        }
        //Video video;


        TextView nombre=(TextView) celda.findViewById(R.id.nombre);
        TextView categoria=(TextView) celda.findViewById(R.id.categoria);
        RatingBar raiting=(RatingBar) celda.findViewById(R.id.raiting);


        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagen);

        JSONObject elemento=this.getItem(position);
        try {

            String url=elemento.getString("imagen_url");
            nombre.setText(elemento.getString("nombreP"));
            categoria.setText(elemento.getString("categoriaP"));
            raiting.setRating(Float.parseFloat(elemento.getString("raiting")));

            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
