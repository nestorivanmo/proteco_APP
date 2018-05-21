package com.example.alice.appproteco1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Portada extends AppCompatActivity {

    Button btn1, btn2;
    public static final long DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        btn1 = (Button)findViewById(R.id.portadaBtnIniciarSesion);
        btn2 = (Button)findViewById(R.id.portadaBtnCursos);
     //   setContentView(R.layout.activity_inicio_efecto);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Portada.this, Ingreso.class);
                startActivity(int1);
            }

            /*private Transition transition;

            public void onFadeClicked(View v){
                transition = new Fade(Fade.OUT);
            }

            public void iniciarActividadSecundaria(){
                transition.setDuration(DURATION);
                transition.setInterpolator(new DecelerateInterpolator());
                getWindow().setExitTransition(transition);
            }*/
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Portada.this, Cursos.class);
                startActivity(int2);
            }
        });
    }
}

