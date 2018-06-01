package com.example.alice.appproteco1;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.support.constraint.Constraints.TAG;

public class Usuario {
    private String username,email,cuenta,nombre;
    static boolean recuperado;


    public Usuario(String username, String email, String cuenta, String nombre){
        this.username=username;
        this.email=email;
        this.cuenta=cuenta;
        this.nombre=nombre;
    }



    public static boolean iniciadoSesion(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();
    }

    public static boolean enviarCorreo(String emailAddress){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        recuperado=false;
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            recuperado=true;
                        }
                    }
                });
        return recuperado;
    }



    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return username;
    }
}
