package com.example.dataenter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class products_adaptor extends ArrayAdapter<Products> {
    private Activity content;
    private List<Products> productsList;

  public products_adaptor(Activity content, List<Products> productsList){
      super(content,R.layout.list_layout,productsList);
      this.content = content;
      this.productsList = productsList;
  }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = content.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewdesc = (TextView) listViewItem.findViewById(R.id.textViewdesc);
        TextView textViewprice =(TextView) listViewItem.findViewById(R.id.pro_price);
        TextView textViewstock =(TextView) listViewItem.findViewById(R.id.stock);
        TextView textViewunit =(TextView) listViewItem.findViewById(R.id.unit1);


        Products artist = productsList.get(position);

        textViewName.setText(artist.getPro_name());
        textViewdesc.setText(artist.getPro_desc());
        textViewprice.setText(String.valueOf(artist.getPro_pri()));
        textViewstock.setText(String.valueOf(artist.getStck()));
        textViewunit.setText(artist.getUnits());

        return listViewItem;

    }
}
