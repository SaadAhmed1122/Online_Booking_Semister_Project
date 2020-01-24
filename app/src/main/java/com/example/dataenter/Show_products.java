package com.example.dataenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Show_products extends AppCompatActivity {

   public static final String Product_NAME = "ProductName";
    public static final String Product_ID = "Productid";
    public static final String Product_desc = "Productdesc";
    public static final String Product_price = "Productprice";
    public static final String Unit = "measureunit";
    public static final String avastock = "Ava_stock";



    ListView showproductlist;
    DatabaseReference databaseArtists;

    List<Products> productsList;
    //List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);



        productsList = new ArrayList<>();
        showproductlist = (ListView) findViewById(R.id.lv_pro);
        databaseArtists = FirebaseDatabase.getInstance().getReference("Products");

       showproductlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Products artist = productsList.get(position);
//
//                Intent ia = new Intent(getApplicationContext(),Add_track.class);
//                ia.putExtra(Product_ID, artist.getPro_id());
//                ia.putExtra(Product_NAME, artist.getPro_name());
//                ia.putExtra(Product_desc,artist.getPro_desc());
//                ia.putExtra(Product_price,artist.getPro_pri());
//                ia.putExtra(Unit,artist.getUnits()) ;
//
//                startActivity(ia);

            }
        });


        showproductlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Products pro = productsList.get(position);
                showUpdtaedialog(pro.getPro_id(),pro.getPro_name(),pro.getPro_desc(),pro.getPro_pri(),pro.getStck(),pro.getUnits());

                return false;
            }
        });
}

    private void showUpdtaedialog(final String pro_id, String pro_name, String pro_desc, int pro_pri, int stck, String units) {

            AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogview =inflater.inflate(R.layout.update_dialog,null);
            dialogbuilder.setView(dialogview);

            final EditText editText_pro_name = (EditText) dialogview.findViewById(R.id.editText_proname_update);
            final EditText editText_pro_desc = (EditText) dialogview.findViewById(R.id.editText_pro_dec_update);
            final EditText editText_pro_price = (EditText) dialogview.findViewById(R.id.editTect_product_pri_update);
            final EditText editText_ava_stock = (EditText) dialogview.findViewById(R.id.editText_ava_stock_update);


            final Button button_update = (Button) dialogview.findViewById(R.id.button_update);
            final Spinner spinner = (Spinner) dialogview.findViewById(R.id.spinner_unit_update);
            final Button delete_btn = (Button) dialogview.findViewById(R.id.Delete);

            dialogbuilder.setTitle("Updating Product:\n"+ pro_name);

            final AlertDialog alertDialog = dialogbuilder.create();
            alertDialog.show();

            button_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name= editText_pro_name.getText().toString().trim();
                    String desc= editText_pro_desc.getText().toString().trim();
                    int price= Integer.parseInt(editText_pro_price.getText().toString());
                    int ava_stock= Integer.parseInt(editText_ava_stock.getText().toString());

                    String Unit = spinner.getSelectedItem().toString();
                    if(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && TextUtils.isEmpty(Unit) ){
                        editText_pro_name.setError("Name Required");
                        editText_pro_desc.setError("Name Required");
                        return;
                    }
                    updateAritist(pro_id,name,desc,price,ava_stock,Unit);
                    alertDialog.dismiss();
                }
            });
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteArtist(pro_id);
                }
            });

        }

    private void deleteArtist(String pro_id) {
        DatabaseReference drAritist=  FirebaseDatabase.getInstance().getReference("Products").child(pro_id);
        drAritist.removeValue();


        Toast.makeText(this, "Product Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    private boolean updateAritist(String pro_id, String name, String desc, int price, int ava_stock, String unit) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products").child(pro_id);
        Products products = new Products(pro_id,name, unit,desc,price, ava_stock);
        databaseReference.setValue(products);
        Toast.makeText(this, "Products Update Successfully", Toast.LENGTH_SHORT).show();
       return true;
    }


    @Override
        protected void onStart(){
            super.onStart();


            databaseArtists.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    productsList.clear();

                    for(DataSnapshot dataSnapshot12: dataSnapshot.getChildren()){
                        Products product = dataSnapshot12.getValue(Products.class);
                        productsList.add(product);

                    }
                    products_adaptor adaptor = new products_adaptor(Show_products.this,productsList);
                     showproductlist.setAdapter(adaptor);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Show_products.this, "Unable to Fatch Data", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
