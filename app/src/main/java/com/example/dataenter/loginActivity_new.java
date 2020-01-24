package com.example.dataenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity_new extends AppCompatActivity {

    EditText user, pass,con_pass;
    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthlistner;
    //private FirebaseAuth mAuth;

    public FirebaseAuth.AuthStateListener mAuthListener;
    ProgressBar pr;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        FirebaseUser user_enter = FirebaseAuth.getInstance().getCurrentUser();
        if(user_enter != null){
            startActivity(new Intent(loginActivity_new.this,HomeActivity.class));
        }
        else {
//            // Initialize Firebase Auth
            mAuth = FirebaseAuth.getInstance();


            user = (EditText) findViewById(R.id.editText_user_name);
            pass = (EditText) findViewById(R.id.editText_pass);
            // con_pass =(EditText) findViewById(R.id.conform_pass);
            pr = (ProgressBar) findViewById(R.id.progressBar);
            //final String u1 = user.getText().toString();
            //final String p1 = pass.getText().toString();
            Button b1 = (Button) findViewById(R.id.button_login);
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() != null) ;
                    Intent oo = new Intent(loginActivity_new.this, HomeActivity.class);
                }
            };

//        if (restorePrefData()) {
//            Intent io = new Intent(getApplicationContext(), Add_product.class);
//            startActivity(io);
//            finish();
//        }


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
//                if (TextUtils.isEmpty(user.getText().toString()) && TextUtils.isEmpty(pass.getText().toString())) {
//                    user.setError("Plz enter Username");
//                    pass.setError("Plz enter Password");
//                }
////                if(!(user.getText().toString().equals("admin"))  && !(pass.getText().toString().equals("admin1122")) ) {
//                    showdialogBox();
//                }

//                else {
////                    savaData();
////
////                    Intent ii = new Intent(loginActivity_new.this, HomeActivity.class);
////                    startActivity(ii);
//                }
                }
            });
        }
    }

    private void showdialogBox() {
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogview =inflater.inflate(R.layout.enter_user_detail,null);
        dialogbuilder.setView(dialogview);

        final AlertDialog alertDialog = dialogbuilder.create();
        alertDialog.show();

    }

    private void savaData() {
        String user_name = user.getText().toString();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user_name", user_name);
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }
    private void startsignIn() {

        String email = user.getText().toString();
        String password = pass.getText().toString();
        String con_p = con_pass.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Field Are empty", Toast.LENGTH_SHORT).show();
        }
        else{
            pr.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(loginActivity_new.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    pr.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(loginActivity_new.this, "Registration SuccessFull", Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(loginActivity_new.this, "Authontication Faild", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    public void Log(View view) {
        startActivity(new Intent(loginActivity_new.this,Registration_Act.class));
    }
    public void login(){
        String email = user.getText().toString();
        String password = pass.getText().toString();
        //String con_p = con_pass.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Field Are empty", Toast.LENGTH_SHORT).show();
        }
        else {
            pr.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(loginActivity_new.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(loginActivity_new.this,HomeActivity.class);
                            startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(loginActivity_new.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        }
}
