package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    public static ArrayList<Product> productList = new ArrayList<Product>();

    private ListView listView;

    private String selectedFilter = "all";
    private String currentSearchText = "";
    private SearchView searchView;
    private Button button;

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchWidgets();
        setupData();
        setUpList();
        setUpOnclickListener();

        btn = (Button) findViewById(R.id.profilebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sneakersize size = new Sneakersize();
                new Thread(size).start();

            }

            class Sneakersize implements Runnable {
                @Override
                public void run() {
                    btn.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, ActivitySettings.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        });

    }

    private void initSearchWidgets()
    {
        searchView = (SearchView) findViewById(R.id.productListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                currentSearchText = s;
                ArrayList<Product> filteredProducts = new ArrayList<Product>();

                for(Product product: productList)
                {
                    if(product.getName().toLowerCase().contains(s.toLowerCase()))
                    {
                        if(selectedFilter.equals("all"))
                        {
                            filteredProducts.add(product);
                        }
                        else
                        {
                            if(product.getName().toLowerCase().contains(selectedFilter))
                            {
                                filteredProducts.add(product);
                            }
                        }
                    }
                }
                ProductAdapter adapter = new ProductAdapter(getApplicationContext(), 0, filteredProducts);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    private void setupData()
    {
        Product adidas_forum = new Product("0", "Adidas Forum", R.drawable.adidasforum, R.drawable.adidas_forum_top, "These Forum Low Classic Shoes tap into that rich history for some fresh appeal. The signature layered build is streamlined, with a gum rubber outsole for traction with style.");
        productList.add(adidas_forum);

        Product adidas_superstar = new Product("1","Adidas Superstar", R.drawable.adidassuperstar, R.drawable.adidas_superstar_back, "Originally made for basketball courts in the '70s. Celebrated by hip hop royalty in the '80s. The adidas Superstar shoe is now a lifestyle staple for streetwear enthusiasts. The world-famous shell toe feature remains, providing style and protection. Just like it did on the B-ball courts back in the day.");
        productList.add(adidas_superstar);

        Product nike_af1 = new Product("2","Nike Air Force 1", R.drawable.nikeaf1, R.drawable.nikeaf1_back, "The radiance lives on in the Nike Air Force 1 '07, the basketball original that puts a fresh spin on what you know best: durably stitched overlays, clean finishes and the perfect amount of flash to make you shine.");
        productList.add(nike_af1);

        Product nike_air = new Product("3","Nike Air", R.drawable.nikeair, R.drawable.nikeair_back, "Nike Air Monarch IV sets you up for working out with durable leather on top for support. Lightweight foam teams up with Nike Air cushioning for comfort in every stride.");
        productList.add(nike_air);

        Product nike_air_max = new Product("4","Nike Air Max", R.drawable.nikeairmax, R.drawable.nikeairmax_back, "Layer on style with the Air Max 97. The cracked leather and soft suede update the iconic design while the original look (inspired by Japanese bullet trains and water droplets) still takes centre stage. Easy-to-style colours let you hit the streets quickly.");
        productList.add(nike_air_max);

        Product nike_cortez = new Product("5", "Nike Cortez", R.drawable.nikecortez, R.drawable.nikecortez_back, "The Nike Classic Cortez Shoe is Nike's original running shoe, designed by Bill Bowerman and released in 1972. This version features a leather and synthetic leather construction for added durability.");
        productList.add(nike_cortez);

        Product nike_aj1_chicago = new Product("6","Nike AJ1 Chicago", R.drawable.aj1chicago, R.drawable.aj1chicago_back, "Introducing the Air Jordan 1 'Chicago' " +
                "The AJ1 'Chicago' was inspired by the high -op Air Jordan 1 original colourway, first released in 1985. The shoe harkens back to a time when shoe boxes were often lost in inventory stockrooms, only to be found again years later.");
        productList.add(nike_aj1_chicago);

        Product nike_aj1_university = new Product("7","Nike AJ1 University", R.drawable.aj1uni, R.drawable.aj1uni_back, "Familiar colours, applied with a classic colour-blocking scheme, characterise this Air Jordan 1. The shoe brings genuine University Blue leather to the ankle, heel, toe and outsole, with black on the Swoosh and collar and contrasting white on the quarter panel, midsole, tongue and toe box.");
        productList.add(nike_aj1_university);

        Product nike_aj3_retro = new Product("8","Nike AJ3 Retro", R.drawable.aj3retro, R.drawable.aj3retro_back, "Clean and supreme, the AJ3 returns with all of its classic style and grace. Quality leather in the upper—with that luxurious elephant print texture—is combined with visible Nike Air in the sole to make a comfortable, everyday icon.");
        productList.add(nike_aj3_retro);

        Product nike_aj4_ow = new Product("9","Nike AJ4 Off-White", R.drawable.aj4offwhite, R.drawable.aj4ow_side, " Full-grain leather and grid mesh cover the shoe's upper, with clear textile adding a subtle layer of modernity. Bold, all-caps imprints label the laces and Air cushioning, with familiar lettering on the medial side detailing the shoe's Oregon origins.");
        productList.add(nike_aj4_ow);
    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.productsListView);

        ProductAdapter adapter = new ProductAdapter(getApplicationContext(), 0, productList);
        listView.setAdapter(adapter);
    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Product selectProduct = (Product) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id",selectProduct.getId());
                startActivity(showDetail);
            }
        });

    }



    private void filterList(String status)
    {
        selectedFilter = status;

        ArrayList<Product> filteredProducts = new ArrayList<Product>();

        for(Product product: productList)
        {
            if(product.getName().toLowerCase().contains(status))
            {
                if(currentSearchText == "")
                {
                    filteredProducts.add(product);
                }
                else
                {
                    if(product.getName().toLowerCase().contains(currentSearchText.toLowerCase()))
                    {
                        filteredProducts.add(product);
                    }
                }
            }
        }

        ProductAdapter adapter = new ProductAdapter(getApplicationContext(), 0, filteredProducts);
        listView.setAdapter(adapter);
    }




    public void allFilterTapped(View view)
    {
        selectedFilter = "all";

        ProductAdapter adapter = new ProductAdapter(getApplicationContext(), 0, productList);
        listView.setAdapter(adapter);
    }

    public void nikeFilterTapped(View view)
    {
        filterList("nike");
    }

    public void adidasFilterTapped(View view)
    {
        filterList("adidas");
    }

}