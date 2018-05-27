package com.example.alice.appproteco1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alice.appproteco1.Objetos.Titular;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BusquedaFragment extends Fragment {

    private RecyclerView mBecariosList;
    private DatabaseReference mDatabaseBecarios;

    private ArrayList<String> titulares = new ArrayList<>();
    private ArrayList<String> imagenesTitulares = new ArrayList<>();
    private ArrayList<String> quotesTitulares = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_busqueda, container, false);

        mDatabaseBecarios = FirebaseDatabase.getInstance().getReference().child("Titulares");
        mDatabaseBecarios.keepSynced(true);
        mBecariosList = view.findViewById(R.id.recyclerViewNoticias);
        mBecariosList.setHasFixedSize(true);
        mBecariosList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Titular, TitularViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Titular, TitularViewHolder>
                (Titular.class, R.layout.activity_card_becarios, TitularViewHolder.class, mDatabaseBecarios) {
            @Override
            protected void populateViewHolder(final TitularViewHolder viewHolder, Titular model, final int position) {

                imagenesTitulares.add(model.getImage());
                titulares.add(model.getTitular());
                quotesTitulares.add(model.getQuote());

                viewHolder.setImagenTitular(getActivity().getApplicationContext(), model.getImage());
                viewHolder.setNombreTitular(model.getTitular());
                viewHolder.setQuoteTitular(model.getQuote());
            }

            @Override
            public void onBindViewHolder(TitularViewHolder viewHolder, final int position) {
                super.onBindViewHolder(viewHolder, position);
                viewHolder.imagenTitular.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };


        mBecariosList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TitularViewHolder extends RecyclerView.ViewHolder{
        View mView;

        CircleImageView imagenTitular;
        TextView nombreTitular;
        TextView quoteTitular;


        public TitularViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            imagenTitular = itemView.findViewById(R.id.titularImagen);
            nombreTitular = itemView.findViewById(R.id.titularNombre);
            quoteTitular = itemView.findViewById(R.id.titularQuote);
        }

        public void setImagenTitular(Context ctx, String imagenTitular) {
            CircleImageView post_Image =  mView.findViewById(R.id.titularImagen);
            Picasso.with(ctx).load(imagenTitular).into(post_Image);
        }

        public void setNombreTitular(String nombreTitular) {
            TextView nombreTV = mView.findViewById(R.id.titularNombre);
            nombreTV.setText(nombreTitular);
        }

        public void setQuoteTitular(String quoteTitular) {
            TextView quoteTV = mView.findViewById(R.id.titularQuote);
            quoteTV.setText(quoteTitular);
        }
    }

}
