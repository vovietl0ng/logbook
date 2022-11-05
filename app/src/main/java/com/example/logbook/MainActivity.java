package com.example.logbook;

import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button Backward , Nextward;
    Picasso picasso;
    List<String> listImage;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView2);
        Backward = findViewById(R.id.button);
        Backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i--;
                if(i < 0){
                    i = listImage.size() - 1;
                }
                showImage(i);
            }
        });
        Nextward = findViewById(R.id.button2);
        Nextward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i>listImage.size() -1){
                    i = 0;
                }
                showImage(i);
            }
        });
        picasso = new Picasso.Builder(this).build();
        listImage = new ArrayList<>();
        listImage.add("https://tinyurl.com/ubedmu4m");
        listImage.add("https://tinyurl.com/2fehtenc");
        listImage.add("https://tinyurl.com/33dm7erv");
        showImage(i);
    }

    public void showImage(int position){
        picasso.get().load(listImage.get(position)).into(imageView);
    }
}