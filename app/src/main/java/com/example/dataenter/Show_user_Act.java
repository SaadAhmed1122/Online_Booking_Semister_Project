package com.example.dataenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Show_user_Act extends AppCompatActivity {
    ListView showuserlist;
    DatabaseReference databaseUsers;
    List<Users> UserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_);
        showuserlist = (ListView) findViewById(R.id.userlist);

        UserList = new ArrayList<>();
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

    }


    @Override
    protected void onStart() {
        super.onStart();


        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserList.clear();

                for(DataSnapshot artistdatasnap: dataSnapshot.getChildren()){
                    Users users = artistdatasnap.getValue(Users.class);
                    UserList.add(users);

                }
                User_Adaptor adaptor = new User_Adaptor(Show_user_Act.this,UserList);
                showuserlist.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
