package com.example.alice.testslider;

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

    public int[] slide_images = {R.drawable.bolitas1, R.drawable.bolitas2, R.drawable.bolitas4};
    public String [] slide_headings = {"Primer Vista",
    "Segunda Vista", "Tercer vista"};

    public String [] slide_descs = {"Lorem ipsum dolor sit amet, lobortis auctor eget eleifend sodales in. Arcu laoreet eu ipsum. Et in, magna tortor pellentesque dictum varius. Condimentum bibendum ac tempor, curabitur eget senectus, eum nullam voluptatem integer ut nonummy pede, id convallis facilisi. Voluptatum eu et, egestas elit nec in non tempus in, quia mi, dui erat quis in facilisi eros, nam magna metus.",
            "Lorem ipsum dolor sit amet, lobortis auctor eget eleifend sodales in. Arcu laoreet eu ipsum. Et in, magna tortor pellentesque dictum varius. Condimentum bibendum ac tempor, curabitur eget senectus, eum nullam voluptatem integer ut nonummy pede, id convallis facilisi. Voluptatum eu et, egestas elit nec in non tempus in, quia mi, dui erat quis in facilisi eros, nam magna metus. ",
            "Lorem ipsum dolor sit amet, lobortis auctor eget eleifend sodales in. Arcu laoreet eu ipsum. Et in, magna tortor pellentesque dictum varius. Condimentum bibendum ac tempor, curabitur eget senectus, eum nullam voluptatem integer ut nonummy pede, id convallis facilisi. Voluptatum eu et, egestas elit nec in non tempus in, quia mi, dui erat quis in facilisi eros, nam magna metus. "
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
