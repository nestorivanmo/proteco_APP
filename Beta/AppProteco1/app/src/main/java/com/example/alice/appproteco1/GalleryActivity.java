package com.example.alice.appproteco1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alice.appproteco1.Objetos.Blog;
import com.squareup.picasso.Picasso;

public class GalleryActivity extends AppCompatActivity{

    private static final String TAG = "GalleryActivity";

    private FloatingActionButton agregarBTN;

    String imagen_url,titulo_curso, desc_curso, precioC, horarioC, titularC, lugarC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started...");
        agregarBTN = findViewById(R.id.agregarCursoButton);
        getIncomingIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();

        agregarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GalleryActivity.this, "Agregar curso", Toast.LENGTH_LONG).show();
                //FavoritosFragment fragment = (FavoritosFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_favoritos);
                //fragment.doSomething("prueba");
            }
        });
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");
        if(getIntent().hasExtra("titulo") && getIntent().hasExtra("imagen_url") && getIntent().hasExtra("desc")){

            Log.d(TAG, "getIncomingIntent: found intent extras");

            imagen_url = getIntent().getStringExtra("imagen_url");
            String titulo_curso = getIntent().getStringExtra("titulo");
            String desc_curso = getIntent().getStringExtra("desc");
            String precioC = getIntent().getStringExtra("precio");
            String horarioC = getIntent().getStringExtra("horario");
            String titularC = getIntent().getStringExtra("titular");
            String lugarC = getIntent().getStringExtra("lugar");
            setImage(imagen_url, titulo_curso, desc_curso, horarioC, precioC, titularC, lugarC);

        }

    }

    private void setImage(String imageUrl, String tituloCurso, String descCurso, String horario, String precio, String titular, String lugar){
        Log.d(TAG, "setImage: setting the image and name to widgets");

        TextView name = findViewById(R.id.tituloCurso);
        name.setText(tituloCurso);

        TextView desc =findViewById(R.id.descripcionCurso);
        desc.setText(descCurso);

        ImageView image = findViewById(R.id.imagenCurso);

        TextView precioCurso = findViewById(R.id.precio);
        precioCurso.setText(precio);

        TextView horarioCurso = findViewById(R.id.cursosH);
        horarioCurso.setText(horario);

        TextView titularCurso = findViewById(R.id.titular);
        titularCurso.setText(titular);

        TextView lugarCurso = findViewById(R.id.lugar);
        lugarCurso.setText(lugar);

        Picasso.with(this).load(imageUrl).into(image);
    }

}
