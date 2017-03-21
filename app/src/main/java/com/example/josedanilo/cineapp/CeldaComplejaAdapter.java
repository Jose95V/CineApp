package com.example.josedanilo.cineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Jose Danilo on 20/03/2017.
 */

public class CeldaComplejaAdapter extends ArrayAdapter<JSONObject> {
    public CeldaComplejaAdapter(Context context, int resource, List<JSONObject> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View celda = convertView;

        if (celda == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda = layoutInflater.inflate(R.layout.celda_compleja, null);

        }

        TextView nombre = (TextView) celda.findViewById(R.id.textViewNombrePeli);
        TextView categoria = (TextView) celda.findViewById(R.id.textViewCategoriaPeli);
        NetworkImageView niv = (NetworkImageView) celda.findViewById(R.id.networkImageViewPeli);

        JSONObject elemento = this.getItem(position);

        try {

            JSONObject imagen = elemento.getJSONObject("imagen");
            nombre.setText(elemento.getString("nombre"));
            categoria.setText(elemento.getString("categoria"));

            niv.setImageUrl("http://www.bfdistribution.cl/wp-content/uploads/2008/06/ElEspecialistaAficheWeb" + ".jpg", MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
