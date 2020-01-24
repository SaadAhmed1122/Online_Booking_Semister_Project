package com.example.dataenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_product extends AppCompatActivity {
    EditText Product_name,Pro_Desc,Pro_prize,stock;
    Spinner Units;
    TextView Tv;
    ImageButton Save_btn;
    DatabaseReference databaseProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

       // SharedPreferences pref= getApplicationContext().getSharedPreferences("User",MODE_PRIVATE);
//        String user= "defaultname";
        //String user_name = pref.getString("user_name","No user Name");
        //Intent ii =  getIntent();
        //String Username1 = ii.getStringExtra(LoginActivity.USER_NAME);

        Product_name = (EditText) findViewById(R.id.editText_proname);
        Pro_Desc= (EditText) findViewById(R.id.editText_pro_dec);
        Pro_prize =(EditText) findViewById(R.id.editTect_product_pri);
        stock =(EditText) findViewById(R.id.editText_ava_stock);
        Units = (Spinner) findViewById(R.id.spinner_unit);
        Save_btn = (ImageButton) findViewById(R.id.button_save);

        Tv = (TextView) findViewById(R.id.textView_show);
        //Tv.setText(user_name);
        databaseProducts = FirebaseDatabase.getInstance().getReference("Products");

        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });


    }

    private void addProduct() {
    String pro_name = Product_name.getText().toString().trim();
    String pro_desc = Pro_Desc.getText().toString().trim();
    String Unit = Units.getSelectedItem().toString();
    int pro_price= Integer.parseInt(Pro_prize.getText().toString());
    int stocks = Integer.parseInt(stock.getText().toString());

        if(!TextUtils.isEmpty(pro_name) && !TextUtils.isEmpty(pro_desc) && !TextUtils.isEmpty(Unit) ){
            String id = databaseProducts.push().getKey();
            Products artist = new Products(id,pro_name,Unit,pro_desc,pro_price,stocks);
            databaseProducts.child(id).setValue(artist);

            Toast.makeText(this, "Products Added Succeessfully", Toast.LENGTH_SHORT).show();

        }


    }
    public void banc(View view) {
        Intent ii = new Intent(Add_product.this,HomeActivity.class);
        startActivity(ii);
    }
}
