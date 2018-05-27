package com.example.alice.appproteco1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alice.appproteco1.Objetos.Titular;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BusquedaFragment extends Fragment {

    private RecyclerView mBecariosList;
    private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_busqueda, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);
        mBecariosList = view.findViewById(R.id.recyclerViewNoticias);
        mBecariosList.setHasFixedSize(true);
        mBecariosList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        FirebaseRecyclerAdapter<Titular, TitularViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Titular, TitularViewHolder>(Titular.class, R.layout.activity_card_becarios, TitularViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(TitularViewHolder viewHolder, Titular model, int position) {

                //viewHolder.setImagenTitular(getActivity().getApplicationContext(), model.getImage());
                //viewHolder.setNombreTitular(model.getTitular());
               // viewHolder.setQuoteTitular(model.getQuote());

            }
        };

        mBecariosList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TitularViewHolder extends RecyclerView.ViewHolder{
        View mView;
        ImageView imagenTitular;
        TextView nombreTitular;
        TextView quoteTitular;

        public TitularViewHolder(final View itemView){
            super(itemView);
            mView = itemView;
            imagenTitular = itemView.findViewById(R.id.titularImagen);
            nombreTitular = itemView.findViewById(R.id.titularNombre);
            quoteTitular = itemView.findViewById(R.id.titularQuote);
        }

        public void setImagenTitular(Context ctx, String imagenTitular) {
            ImageView post_Image = (ImageView) mView.findViewById(R.id.post_image);
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
