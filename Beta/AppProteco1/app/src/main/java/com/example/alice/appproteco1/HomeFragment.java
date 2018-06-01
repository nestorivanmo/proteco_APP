package com.example.alice.appproteco1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alice.appproteco1.Objetos.Blog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    private ArrayList<String> cursos = new ArrayList<>();
    private ArrayList<String> imagenesCursos = new ArrayList<>();
    private ArrayList<String> descripciones = new ArrayList<>();
    private ArrayList<String> desc_larga = new ArrayList<>();
    private ArrayList<String> horarios = new ArrayList<>();
    private ArrayList<String> fechas = new ArrayList<>();
    private ArrayList<String> precios = new ArrayList<>();
    private ArrayList<String> lugares = new ArrayList<>();
    private ArrayList<String> titulares = new ArrayList<>();

    private Context mContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);
        mBlogList = view.findViewById(R.id.myrecyclerview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
                (Blog.class, R.layout.blog_row, BlogViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(final BlogViewHolder viewHolder, Blog model, final int position) {

                cursos.add(model.getTitle());
                imagenesCursos.add(model.getImage());
                descripciones.add(model.getDesc());
                desc_larga.add(model.getDesc_larga());
                horarios.add(model.getHorario());
                precios.add(model.getPrecio());
                titulares.add(model.getTitular());
                fechas.add(model.getFecha());
                lugares.add(model.getLugar());

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setHorarioCurso(model.getFecha());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());

            }

            @Override
            public void onBindViewHolder(final BlogViewHolder viewHolder, final int position) {
                super.onBindViewHolder(viewHolder, position);

                viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.d(TAG, "onClick: clicked on: " + cursos.get(position));
                        Intent intent = new Intent(getContext(), GalleryActivity.class);
                        intent.putExtra("imagen_url", imagenesCursos.get(position));
                        intent.putExtra("titulo", cursos.get(position));
                        intent.putExtra("desc", desc_larga.get(position));
                        intent.putExtra("horario", horarios.get(position));
                        intent.putExtra("precio", precios.get(position));
                        intent.putExtra("titular", titulares.get(position));
                        intent.putExtra("lugar", lugares.get(position));
                        getContext().startActivity(intent);

                        //mContext.startActivity(intent);
                    }
                });
            }

        };


        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;
        ImageView image;
        TextView tituloCurso;
        TextView descripcionCurso;
        TextView horarioCurso;
        CardView parentLayout;

        public BlogViewHolder(final View itemView){
            super(itemView);
            mView = itemView;
            image = itemView.findViewById(R.id.post_image);
            tituloCurso = itemView.findViewById(R.id.post_title);
            descripcionCurso = itemView.findViewById(R.id.post_desc);
            horarioCurso = itemView.findViewById(R.id.cursoHorario);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

        public TextView getHorarioCurso() {
            return horarioCurso;
        }

        public void setHorarioCurso(String horarioCurso) {
            TextView fecha = mView.findViewById(R.id.cursoHorario);
            fecha.setText(horarioCurso);
        }

        public void setTitle(String title){
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_Image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_Image);
        }
        public CardView getParentLayout(){
            CardView parentL = (CardView) mView.findViewById(R.id.parent_layout);
            return  parentL;
        }
    }
}
