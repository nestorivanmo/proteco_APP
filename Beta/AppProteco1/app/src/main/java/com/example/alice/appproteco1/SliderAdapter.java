package com.example.alice.appproteco1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount(){
        return slide_headings.length;
    }

    public int[] slide_images = {R.mipmap.ic_launcher_foreground_quienes, R.mipmap.ic_launcher_foreground_mision, R.mipmap.ic_launcher_foreground_vision};
    public String [] slide_headings = {"¿Quiénes somos?", "Misión", "Visión"};

    public String [] slide_descs = {
            "PROTECO es el Programa de Tecnología en Cómputo que busca formar personas altamente capacitadas en temas de computo.",
            "Brindar un servicio de calidad llevando en alto el nombre y los valores de nuestra Máxima Casa de Estudios.",
            "Establecer un proceso permanente, con herramientas de cómputo, para la formación de personal docente y de."
    };
    @Override
    public boolean isViewFromObject(View view, Object o){
        return view == (RelativeLayout)o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }

}
