package com.example.dataenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void show_p_ac(View view) {
        Intent ao = new Intent(HomeActivity.this, Add_product.class);
        startActivity(ao);
    }

    public void show_pro(View view) {
        Intent aa= new Intent(HomeActivity.this, Show_products.class);
        startActivity(aa);
    }

    public void show_user(View view) {
        startActivity(new Intent(HomeActivity.this,Show_user_Act.class));
    }
}
