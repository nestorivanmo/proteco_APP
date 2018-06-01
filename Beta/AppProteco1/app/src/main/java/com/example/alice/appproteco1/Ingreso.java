package com.example.alice.appproteco1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Ingreso extends AppCompatActivity {
    TextView tx, txOlvidar;
    Button btn;

    private FirebaseAuth mAuth;
    EditText editTextEmail,editTextContrasena;
    ProgressBar barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        txOlvidar = (TextView)findViewById(R.id.ingresoTextForgetPassword);
        btn = (Button)findViewById(R.id.ingresoBtnLogin);
        tx = (TextView)findViewById(R.id.ingresoTextViewNoAccount);

        tx.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent tx = new Intent(Ingreso.this, Registro.class);
                startActivity(tx);
            }
        });

        txOlvidar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent tx2 = new Intent(Ingreso.this, RecuperarContraseña.class);
                startActivity(tx2);
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


        editTextContrasena=(EditText) findViewById(R.id.ingresoEditTextContrasena);
        editTextEmail=(EditText)findViewById(R.id.ingresoEditTextEmail);
        mAuth = FirebaseAuth.getInstance();
        barra=(ProgressBar)findViewById(R.id.barra);

    }

    public void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextContrasena.getText().toString().trim();
        if(email.isEmpty()){
            editTextEmail.setError("Error, escribe un correo");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            if(ObtenerDatos.verificarNombre(email)){
                email=ObtenerDatos.deNombreACorreo(email);
            }else{
                editTextEmail.setError("Error, escribe un usuario valido");
                editTextEmail.requestFocus();
                return;
            }
        }
        if(password.isEmpty()){
            editTextContrasena.setError("Error, escribe un usuario");
            editTextContrasena.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextContrasena.setError("Error, la longitud de la contraseña debe ser mayor a cinco");
            editTextContrasena.requestFocus();
            return;
        }
        barra.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            barra.setVisibility(View.GONE);
                            Intent tx = new Intent(Ingreso.this, Cursos.class);
                            startActivity(tx);
                        }else{
                            barra.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


    }
}
