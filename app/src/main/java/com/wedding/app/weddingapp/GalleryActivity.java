package com.wedding.app.weddingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_gallery);

        //Initialize ImageView
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        ImageView imageView7 = (ImageView) findViewById(R.id.imageView7);
        ImageView imageView8 = (ImageView) findViewById(R.id.imageView8);
        ImageView imageView9 = (ImageView) findViewById(R.id.imageView9);

        //Loading image from below url into imageView
        Glide.with(this)
                .load(R.drawable.pic_1)
                .into(imageView);
        Glide.with(this)
                .load(R.drawable.pic_2)
                .into(imageView2);
        Glide.with(this)
                .load(R.drawable.pic_3)
                .into(imageView3);
        Glide.with(this)
                .load(R.drawable.pic_4)
                .into(imageView4);
        Glide.with(this)
                .load(R.drawable.pic_5)
                .into(imageView5);
        Glide.with(this)
                .load(R.drawable.pic_6)
                .into(imageView6);
        Glide.with(this)
                .load(R.drawable.pic_7)
                .into(imageView7);
        Glide.with(this)
                .load(R.drawable.pic_8)
                .into(imageView8);
        Glide.with(this)
                .load(R.drawable.pic_9)
                .into(imageView9);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        System.out.println("----main activity---onStart---");
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
