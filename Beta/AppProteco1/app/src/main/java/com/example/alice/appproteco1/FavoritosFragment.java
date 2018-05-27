package com.example.alice.appproteco1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alice.appproteco1.Objetos.Titular;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FavoritosFragment extends Fragment {

    private Button signOutBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view= inflater.inflate(R.layout.fragment_favoritos, container, false);

        signOutBtn = view.findViewById(R.id.signOutButton);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Usuario.iniciadoSesion()){
                                 Toast.makeText(getContext(), "Ha iniciado sesion", Toast.LENGTH_SHORT).show();
                                 signOutBtn.setText("Sign out");


                                 signOutBtn.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Toast.makeText(getContext(), "Cerrando sesion", Toast.LENGTH_SHORT).show();
                                         Usuario.cerrarSesion();
                                     }

                                 });


        }else {
            Toast.makeText(getContext(), "No ha iniciado sesion", Toast.LENGTH_SHORT).show();
            signOutBtn.setText("Log in");

            signOutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Iniciar Sesión", Toast.LENGTH_SHORT).show();
                    Intent tx = new Intent(getActivity(), Ingreso.class);
                    startActivity(tx);
                }

            });

        }
    }
}

