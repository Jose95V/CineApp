package com.example.josedanilo.cineapp;

import android.content.Context;
import android.content.Intent;
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

    public CeldaComplejaAdapter(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda = layoutInflater.inflate(R.layout.celda_compleja,null);
        }

        TextView nombre=(TextView) celda.findViewById(R.id.textViewNombrePeli);
        TextView categoria=(TextView) celda.findViewById(R.id.textViewCategoriaPeli);
        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.networkImageViewPeli);

        JSONObject elemento=this.getItem(position);
        try {
            nombre.setText(elemento.getString("nombre"));
            categoria.setText(elemento.getString("categoria"));

            String imagen=elemento.getString("imagen");
           niv.setImageUrl(imagen,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        celda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Detalle.class);
                intent.putExtra("JSONObject", getItem(position).toString());
                getContext().startActivity(intent);
            }
        });

        return celda;
    }


}

