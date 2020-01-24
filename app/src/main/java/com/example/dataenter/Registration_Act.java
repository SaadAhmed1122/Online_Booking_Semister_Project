package com.example.dataenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_Act extends AppCompatActivity {
        EditText user,pas,c_pas;
    private FirebaseAuth mAuth;
    FirebaseDatabase userdata;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);
        mAuth = FirebaseAuth.getInstance();


        user = (EditText) findViewById(R.id.editText_u_reg);
        pas = (EditText) findViewById(R.id.editText3_u_p);
        c_pas = (EditText) findViewById(R.id.editText4_u_c_p);


    }

    public void reg(View view) {
        String email  = user.getText().toString();
        String password = pas.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration_Act.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                            showuserdialog();
                            // Sign in success, update UI with the signed-in user's information
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Registration_Act.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }




    public void showuserdialog(){
        userdata = FirebaseDatabase.getInstance();
        myRef = userdata.getReference("Users");

        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogview =inflater.inflate(R.layout.enter_user_detail,null);
        dialogbuilder.setView(dialogview);
        final AlertDialog alertDialog = dialogbuilder.create();
        alertDialog.show();

        final EditText user_name = (EditText) dialogview.findViewById(R.id.user_info_name);
        final EditText company_name = (EditText) dialogview.findViewById(R.id._user_company_name);
        final EditText user_phone = (EditText) dialogview.findViewById(R.id.user_phone);
        final EditText user_adress = (EditText) dialogview.findViewById(R.id.user_address);

        final Button save_user1122 = (Button) dialogview.findViewById(R.id.button_save_user);

        save_user1122.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_n = user_name.getText().toString();
                String com_name = company_name.getText().toString();
                String phone = user_phone.getText().toString();
                String address = user_adress.getText().toString();
                if(!TextUtils.isEmpty(user_n) || !TextUtils.isEmpty(com_name) || !TextUtils.isEmpty(address)){
                    String id = myRef.push().getKey();
                    Users users = new Users(user_n, com_name,phone,address);
                    myRef.child(id).setValue(users);
                    Toast.makeText(Registration_Act.this, "Users Enter Suuccess fully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration_Act.this, HomeActivity.class));

                }
                else
                {
                    Toast.makeText(Registration_Act.this, "Data Enter Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}
