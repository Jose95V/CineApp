package com.example.josedanilo.cineapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jose Danilo on 20/03/2017.
 */

public class Detalle extends AppCompatActivity {
    public static Context mContext;
    JSONObject jsonPelis;
    String idActorr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        mContext = this;

        setTitle("Detalles de la Pelicula");

        String tempkiva = this.getIntent().getStringExtra("pelicula");
        Integer posicion = this.getIntent().getIntExtra("numero", 0);

        try {
            jsonPelis = new JSONObject(tempkiva);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        llenarInformacion(posicion);

    }

    private void llenarInformacion(Integer p) {
        String peliculas = null;

        try {
            peliculas = jsonPelis.getString("peliculas");
            JSONArray arregloPersonas = new JSONArray(peliculas);

            JSONObject pelicula = (JSONObject) arregloPersonas.get(p);

            String nombre = pelicula.getString("nombre");

            JSONObject imagen = pelicula.getJSONObject("imagen");
            String idImagen=imagen.getString("idpelicula");
            String categoria = pelicula.getString("categoria");
            String descripcion = pelicula.getString("descripcion");

            NetworkImageView avatar = (NetworkImageView) findViewById(R.id.networkImageViewImagen);
            avatar.setImageUrl("http://www.bfdistribution.cl/wp-content/uploads/2008/06/ElEspecialistaAficheWeb" + idImagen + ".jpg", MySingleton.getInstance(mContext).getImageLoader());

            TextView tv = (TextView) findViewById(R.id.textViewNombre2);
            tv.setText(nombre);
            TextView tv2 = (TextView) findViewById(R.id.textViewCategoria2);
            tv2.setText(categoria);
            TextView tv3 = (TextView) findViewById(R.id.textViewDescripcion);
            tv3.setText(descripcion);

        } catch (JSONException e) {
            e.printStackTrace();
            TextView tvNombre = (TextView) findViewById(R.id.textViewNombre2);
            tvNombre.setText("error");
        }
    }
}
