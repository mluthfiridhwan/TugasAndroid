package com.example.mluth.suapin;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    // list of images
    public int[] lst_images = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4
    };
    // list of titles
    public String[] lst_title = {
            "Suap.in",
            "Donation",
            "Food Concern",
            "Healthy Life"
    }   ;
    // list of descriptions
    public String[] lst_description = {
            "adalah upaya penyelamatan surplus makanan yang dihasilkan oleh industri ini dari potensi terbuang. Makanan berlebih tersebut akan diperiksa kembali kualitasnya, dikemas ulang, lalu dibagikan kepada masyarakat yang membutuhkan,",
            "Kami bekerjasama dengan komunitas lokal, pengurus warga, dan individu yang memahami lebih jauh mengenai kondisi warga masyarakatnya. Rekomendasi dan bekal informasi tersebut lebih lanjut kami pastikan di lapangan dengan wawancara singkat beberapa warga, dan pengamatan menyeluruh ke seluruh daerah pemukiman. Dengan memastikan langsung kondisi di lapangan, kami bisa menentukan jenis donasi dan distribusi yang tepat untuk lokasi tersebut, serta memastikan bahwa distribusi dilakukan secara tepat sasaran.,",
            "Mengurangi surplus makanan yang dihasilkan oleh industri ini dari potensi terbuang yang masih layak untuk dimakan oleh orang yang membutuhkan,",
            "organisasi yang mengordinasi makanan berlebih berpotensi terbuang untuk didonasikan pada masyarakat membutuhkan. Gerakan ini adalah upaya untuk mengatasi isu pembuangan makanan yang terjadi di Indonesia, sekaligus mengusung kepedulian terhadap isu lingkungan dan sosial.,"
    };
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212)
    };


    public AboutActivity(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_about,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}