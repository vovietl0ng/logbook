package com.example.logbook;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText input;
    Button Backward ,Nextward, Add_Url;
    Picasso picasso;
    List<String> listImage;
    MyDatabaseHelper myDB;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listImage = new ArrayList<>();
        picasso = new Picasso.Builder(this).build();
        imageView = findViewById(R.id.imageView2);
        Backward = findViewById(R.id.button);
        input = findViewById(R.id.inputURL);
        Add_Url = findViewById(R.id.button_add);
        myDB = new MyDatabaseHelper(MainActivity.this);
        listImage = storeDataInArrays();
        if(listImage.size() > 0){
            showImage(0);
        }
        Add_Url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = input.getText().toString().trim();
                Add_Image(url);

            }
        });
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
                if(i > listImage.size() -1){
                    i = 0;
                }
                showImage(i);
            }
        });
//        listImage.add("https://tinyurl.com/ubedmu4m");
//        listImage.add("https://tinyurl.com/2fehtenc");
//        listImage.add("https://tinyurl.com/33dm7erv");
    }
    public void Add_Image(String url){
        picasso.get()
                .load(url).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                        myDB.addLink(url);
                        i = listImage.size() - 1;
                        listImage = storeDataInArrays();
                        showImage(i);
                        Toast.makeText(MainActivity.this
                                , "Add Success", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Exception e) {
                        if(listImage.size() >0){
                            showImage(i);
                        }
                        Toast.makeText(MainActivity.this
                                , "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void showImage(int position){
        picasso.get().load(listImage.get(position)).into(imageView);
    }
    public List<String> storeDataInArrays () {
        Cursor cursor = myDB.readAllData();
            List<String> storeData = new ArrayList<>();
            while (cursor.moveToNext()) {
                String url = cursor.getString(cursor.getColumnIndexOrThrow("image")).trim();
                storeData.add(url);
        }

            return storeData;
    }
}