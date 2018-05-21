package com.example.alice.appproteco1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ingreso extends AppCompatActivity {
    TextView tx;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        btn = (Button)findViewById(R.id.ingresoBtnLogin);
        tx = (TextView)findViewById(R.id.ingresoTextViewNoAccount);
        tx.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent tx = new Intent(Ingreso.this, Registro.class);
                startActivity(tx);
            }
        });
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent tx = new Intent(Ingreso.this, Cursos.class);
                startActivity(tx);
            }
        });
    }
}
