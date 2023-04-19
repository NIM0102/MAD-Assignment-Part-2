package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product>
{

    public ProductAdapter(Context context, int resource, List<Product> productList)
    {
        super(context,resource,productList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Product product = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_cell, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.productName);
        ImageView iv = (ImageView) convertView.findViewById(R.id.productImage);

        tv.setText(product.getName());
        iv.setImageResource(product.getImage());


        return convertView;
    }
}
