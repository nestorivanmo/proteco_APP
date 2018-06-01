package com.example.alice.appproteco1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Juego extends AppCompatActivity implements View.OnClickListener{
    Button botones[];
    private boolean turno=true; //true==o
    private boolean ganador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
        b1=(Button)findViewById(R.id.boton1);
        b2=(Button)findViewById(R.id.boton2);
        b3=(Button)findViewById(R.id.boton3);
        b4=(Button)findViewById(R.id.boton4);
        b5=(Button)findViewById(R.id.boton5);
        b6=(Button)findViewById(R.id.boton6);
        b7=(Button)findViewById(R.id.boton7);
        b8=(Button)findViewById(R.id.boton8);
        b9=(Button)findViewById(R.id.boton9);
        botones= new Button[]{b1, b2, b3, b4, b5, b6, b7, b8, b9};
        for(int i=0;i<botones.length;i++){
            botones[i].setOnClickListener(this);
        }
        findViewById(R.id.reiniciar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton1:
                accionBoton(0);
                break;

            case R.id.boton2:
                accionBoton(1);
                break;

            case R.id.boton3:
                accionBoton(2);
                break;

            case R.id.boton4:
                accionBoton(3);
                break;

            case R.id.boton5:
                accionBoton(4);
                break;

            case R.id.boton6:
                accionBoton(5);
                break;

            case R.id.boton7:
                accionBoton(6);
                break;

            case R.id.boton8:
                accionBoton(7);
                break;

            case R.id.boton9:
                accionBoton(8);
                break;

            case R.id.reiniciar:
                reiniciar();
                break;
        }
    }

    public void reiniciar(){
        for(Button temp: botones){
            temp.setText(" ");
        }
    }

    public void accionBoton(int i){
        Button obtenido = botones[i];
        boolean llenado;
        if(!(llenado=lleno())&&!ganador()){
            if(obtenido.getText().toString().equals(" ")){
                obtenido.setText(original());
                ganador();
                if(turno){
                    turno=false;
                }else{
                    turno=true;
                }
            }else if(obtenido.getText().toString().equals("x")||obtenido.getText().toString().equals("o")){
                Toast.makeText(getApplicationContext(), "Elige otra casilla", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(!ganador()){
                Toast.makeText(getApplicationContext(), "Empate, reinicia la partida", Toast.LENGTH_SHORT).show();
            }else{
                ganador();
            }
        }
    }

    private boolean lleno() {
        for (int i=0;i<botones.length;i++){
            if (botones[i].getText().toString().equals(" ")){
                return false;
            }
        }
        return true;
    }

    public String original(){
        if(turno){
            return "0";
        }
        return "x";
    }

    public boolean compararValores(Button a, Button b){
        if(a.getText().toString().equals(b.getText().toString())){
            return true;
        }
        return false;
    }

    public boolean ganador(){
        int a,b,c;
        int i=0;

        //error vertical
        while (i < 3){
            a=i;
            b=i+3;
            c=a+6;
            if ( compararValores(botones[a],botones[b]) && compararValores(botones[a],botones[c])&&!botones[a].getText().toString().equals(" ")) {
                if (botones[a].getText().toString().equals("x")) {
                    Toast.makeText(getApplicationContext(), "Gano jugador x", Toast.LENGTH_SHORT).show();
                    ganador = true;
                } else {
                    Toast.makeText(getApplicationContext(), "Gano jugador o", Toast.LENGTH_SHORT).show();
                    ganador = false;
                }
                return true;
            }
            i+=1;
        }

        //horizonal
        i=0;
        while ( i < 3 ) {
            a = i * 3;
            b = a + 1;
            c = b + 1;
            if (compararValores(botones[a], botones[b]) && compararValores(botones[a], botones[c]) && !botones[a].getText().toString().equals(" ")) {
                if (botones[a].getText().toString().equals("x")) {
                    Toast.makeText(getApplicationContext(), "Gano jugador x", Toast.LENGTH_SHORT).show();
                    ganador = true;
                } else {
                    Toast.makeText(getApplicationContext(), "Gano jugador o", Toast.LENGTH_SHORT).show();
                    ganador = false;

                }
                return true;
            }
            i=i+1;
        }
        //diagonales
        if(compararValores(botones[0],botones[4])) {
            if (compararValores(botones[0], botones[8]) && !botones[0].getText().toString().equals(" ")) {
                if (botones[0].getText().toString().equals("x")) {
                    Toast.makeText(getApplicationContext(), "Gano jugador x", Toast.LENGTH_SHORT).show();
                    ganador = true;
                } else {
                    Toast.makeText(getApplicationContext(), "Gano jugador o", Toast.LENGTH_SHORT).show();

                    ganador = false;

                }
                return true;
            }
        }
        if(compararValores(botones[2],botones[4]) ){
            if(compararValores(botones[2],botones[6])&&!botones[2].getText().toString().equals(" ") ){
                if(botones[2].getText().toString().equals("x") ) {
                    Toast.makeText(getApplicationContext(), "Gano jugador x", Toast.LENGTH_SHORT).show();
                    ganador = true;
                }else {
                    Toast.makeText(getApplicationContext(), "Gano jugador o", Toast.LENGTH_SHORT).show();
                    ganador = false;
                }
                return true;
            }
        }
        return false;
    }
}

