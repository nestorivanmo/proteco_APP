package com.example.alice.appproteco1;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecuperarContraseña extends AppCompatActivity implements View.OnClickListener{

    TextView olvidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        olvidar=(TextView)findViewById(R.id.ingresoBtnRecuperar);
        findViewById(R.id.ingresoBtnRecuperar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ingresoBtnRecuperar:
                if(Usuario.enviarCorreo(olvidar.getText().toString())){
                    Toast.makeText(getApplicationContext(),"El correo ha sido enviado",Toast.LENGTH_SHORT).show();
                    Intent tx2 = new Intent(RecuperarContraseña.this, Ingreso.class);
                    startActivity(tx2);
                }else{
                    olvidar.setError("Verifica el correo");
                }


                break;
        }
    }
}