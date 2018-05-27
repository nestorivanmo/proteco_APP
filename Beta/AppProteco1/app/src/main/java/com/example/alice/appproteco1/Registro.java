package com.example.alice.appproteco1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private static final int CHOOSE_IMAGE = 101;
    EditText usuario,contrasena,confContr,username,numCuenta,correo;
    private FirebaseAuth mAuth;
    ProgressBar barra;
    ImageView imageView;
    String profileImageUrl;
    FirebaseFirestore db;
    Uri uriProfileImage;
    boolean usernameRepetido;
    boolean correoRepetido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario = (EditText) findViewById(R.id.registroEditTextNombre);
        contrasena = (EditText) findViewById(R.id.registroEditTextContrasena);
        confContr=(EditText) findViewById(R.id.registroEditTextContrasenaConfirmar);
        mAuth = FirebaseAuth.getInstance();
        barra = (ProgressBar) findViewById(R.id.barra);
        username = (EditText) findViewById(R.id.registroEditTextUsername);
        numCuenta = (EditText) findViewById(R.id.registroEditTextCuenta);
        correo= (EditText) findViewById(R.id.registroEditTextEmail);
        findViewById(R.id.ingresoTextViewNoAccount).setOnClickListener(this);
        findViewById(R.id.registroBtnEnviar).setOnClickListener(this);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        barra=(ProgressBar)findViewById(R.id.barra);
    }

    public void userLogin(){
        final String email=correo.getText().toString().trim();
        String password=contrasena.getText().toString().trim();
        final String password2=confContr.getText().toString().trim();
        final String user=username.getText().toString().trim();
        final String cuenta=numCuenta.getText().toString().trim();
        final String nombreCompleto=usuario.getText().toString().trim();

        if(nombreCompleto.isEmpty()){
            usuario.setError("Escribe tu nombre completo");
            usuario.requestFocus();
            return;
        }

        if(email.isEmpty()){
            correo.setError("Escribe un correo");
            correo.requestFocus();
            return;
        }

        if(correoRepetido(user,email)){
            correoRepetido=false;
            correo.setError("Ya ha sido asociada una cuenta ese correo");
            correo.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            correo.setError("Escribe un correo valido");
            correo.requestFocus();
            return;
        }
        if(password.isEmpty()){
            contrasena.setError("Escribe un usuario");
            contrasena.requestFocus();
            return;
        }

        if(password.length()<6){
            contrasena.setError("La longitud de la contraseña debe ser mayor a cinco");
            contrasena.requestFocus();
            return;
        }

        if(usernameRepetido(user)){
            username.setError("Ya se ha registrado un usuario con el mismo nombre");
            username.requestFocus();
            usernameRepetido=false;
            return;
        }


        if(password2.isEmpty()){
            confContr.setError("La longitud de la contraseña debe ser mayor a cinco");
            confContr.requestFocus();
            return;
        }

        if(cuenta.length()!=10&&!cuenta.isEmpty()){
            numCuenta.setError("Numero de cuenta invalido");
            numCuenta.requestFocus();
            return;
        }

        if(user.isEmpty()){
            username.setError("Escribe un usuario");
            username.requestFocus();
            return;
        }

        if(user.length()<6){
            username.setError("El usuario debe de tener mas de 6 caracteres");
            username.requestFocus();
            return;
        }

        barra.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Usuario creado=new Usuario(user,email,cuenta,nombreCompleto);
                            db.collection("Usuario").document(user).set(creado).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    barra.setVisibility(View.GONE);
                                    Intent intent = new Intent(Registro.this, Cursos.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }})
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                                        }
                                    });
/*
                            FirebaseDatabase.getInstance().getReference("Usuario").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(creado).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                    }else{
                                    }
                                }
                            });

*/
                            barra.setVisibility(View.GONE);


                        }else{
                            barra.setVisibility(View.GONE);
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Un error ha ocurrido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ingresoTextViewNoAccount:
                Intent tx = new Intent(Registro.this, Ingreso.class);
                startActivity(tx);
                break;
            case R.id.registroBtnEnviar:
                userLogin();
                break;
        }
    }


    public boolean usernameRepetido(String nombre){
        DocumentReference docRef = db.collection("Usuario").document(nombre);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        usernameRepetido=true;
                    } else {
                        usernameRepetido=false;
                    }
                } else {
                    usernameRepetido=false;
                }
            }
        });
        return usernameRepetido;
    }

    public boolean correoRepetido(String nombre, final String correo){
        DocumentReference docRef = db.collection("Usuario").document(nombre);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        if(document.getData().containsValue(correo)){
                            correoRepetido=false;
                        }else{
                            correoRepetido=true;
                        }
                    } else {
                        correoRepetido=false;
                    }
                } else {
                    correoRepetido=false;
                }
            }
        });
        return correoRepetido;
    }
}