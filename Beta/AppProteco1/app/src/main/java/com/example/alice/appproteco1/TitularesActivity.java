package com.example.alice.appproteco1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TitularesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulares);
        getIncomingIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("imagen_url") && getIntent().hasExtra("titular") && getIntent().hasExtra("quote") && getIntent().hasExtra("curso")){

            String imagen_url = getIntent().getStringExtra("imagen_url");
            String titular = getIntent().getStringExtra("titular");
            String quote = getIntent().getStringExtra("quote");
            String curso = getIntent().getStringExtra("curso");
            setDatos(imagen_url, titular, quote, curso);

        }

    }

    private void setDatos(String imagenURL, String titularNombre, String titularQuote, String titularCurso){
        TextView nombre = findViewById(R.id.titularNombre);
        nombre.setText(titularNombre);

        TextView curso =findViewById(R.id.titularCurso);
        curso.setText(titularCurso);

        TextView quote = findViewById(R.id.titularQuote);
        quote.setText(titularQuote);

        ImageView image = findViewById(R.id.imagenTitular);
        Picasso.with(this).load(imagenURL).into(image);
    }

}
