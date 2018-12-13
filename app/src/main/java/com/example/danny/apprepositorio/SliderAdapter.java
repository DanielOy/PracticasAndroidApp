package com.example.danny.apprepositorio;

import android.content.Context;
import android.support.annotation.NonNull;
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

    public SliderAdapter(Context context) {

        this.context = context;
    }

    public int [] slide_images =
            {

                    R.drawable.crear_circulos,
                   R.drawable.ic_school_black_24dp,
                    R.drawable.ic_laptop_windows_black_24dp

            };

    public String [] slide_headings ={

            "DESARROLLADORES" ,
            "INSTITUTO TECNOLOGICO SUPERIOR DE VALLADOLID",
            "INGENIERIA EN SISTEMAS COMPUTACIONALES"
    };

    public String [] slide_descs ={

            "EL EQUIPO DE DESARROLLO DEL PROYECTO MOVIL ESTA CONFORMADO POR: " +
                    "consectetur justo, non porta velit maximus sed. Aenean consectetur tortor " +
                    "eu elementum blandit. Aliquam ut volutpat elit. Suspendisse id fermentum. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse condimentum" +
                    " consectetur justo, non porta velit maximus sed. Aenean consectetur tortor " +
                    "eu elementum blandit. Aliquam ut volutpat elit. Suspendisse id fermentum. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse condimentum" +
                    " consectetur justo, non porta velit maximus sed. Aenean consectetur tortor eu " +
                    "elementum blandit. Aliquam ut volutpat elit. Suspendisse id fermentum. "
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
