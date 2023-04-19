package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity
{
    Product selectedProduct;
    Button button;
    Button sizebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSelectedProduct();
        setValues();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "https://www.instagram.com/";
                String shareSub = "Your subject";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
        });

        sizebutton = (Button) findViewById(R.id.sizebtn);
        sizebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sneakersize size = new Sneakersize();
                new Thread(size).start();

            }

            class Sneakersize implements Runnable {
                @Override
                public void run() {
                    sizebutton.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DetailActivity.this, SneakerSize.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        });

    }

    public void googleMaps(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/Nearest+Nike+and+Adidas+Store/@4.6365216,101.0652528,13z/data=!3m1!4b1"));
        startActivity(browserIntent);
    }

    private void getSelectedProduct()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedProduct = MainActivity.productList.get(Integer.valueOf(parsedStringID));
    }

    private void setValues()
    {
        TextView tv = (TextView) findViewById(R.id.productName);
        ImageView iv = (ImageView) findViewById(R.id.productImage);

        TextView desc = (TextView) findViewById((R.id.productDesc));
        ImageView bv = (ImageView) findViewById(R.id.productImage2);


        tv.setText(selectedProduct.getName());
        iv.setImageResource(selectedProduct.getImage());
        bv.setImageResource(selectedProduct.getImage2());
        desc.setText(selectedProduct.getDesc());
    }
}