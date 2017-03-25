package com.example.josedanilo.cineapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.josedanilo.cineapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetalleActivity extends AppCompatActivity {
    public static Context mContext;
    JSONObject jsonKivaP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        mContext=this;

        setTitle("Detalles de la pelicula");

        String tempkiva=this.getIntent().getStringExtra("kiva");
        Integer posicion=this.getIntent().getIntExtra("numero", 0);

        try {
            jsonKivaP= new JSONObject(tempkiva);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        llenarInformacion(posicion);

    }

    private void llenarInformacion(Integer p){
        String loans=null;

        try {
            loans = jsonKivaP.getString("pelicula");
            JSONArray arregloPersonas = new JSONArray(loans);

            JSONObject persona = (JSONObject) arregloPersonas.get(p);

            String nombre = persona.getString("nombreP");
            String categoria = persona.getString("categoriaP");
            String descripcion = persona.getString("descripcionP");
            JSONObject imagen = persona.getJSONObject("imagen_url");
            String raiting = persona.getString("raiting");


            NetworkImageView avatar = (NetworkImageView) findViewById(R.id.imagen);
            avatar.setImageUrl(imagen + ".jpg", MySingleton.getInstance(mContext).getImageLoader());

            TextView tv = (TextView) findViewById(R.id.nombreD);
            tv.setText(nombre);
            TextView tv2 = (TextView) findViewById(R.id.categoriaD);
            tv2.setText(categoria);
            TextView tv3 = (TextView) findViewById(R.id.descripcionD);
            tv3.setText(descripcion);
            RatingBar tv4 = (RatingBar) findViewById(R.id.raitingD);
            tv4.setRating(Float.parseFloat(raiting));

        } catch (JSONException e) {
            e.printStackTrace();
            TextView tvJoseNombre = (TextView) findViewById(R.id.nombreD);
            tvJoseNombre.setText("error");

        }
    }

}
