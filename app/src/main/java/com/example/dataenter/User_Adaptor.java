package com.example.dataenter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class User_Adaptor extends ArrayAdapter<Users> {
    private Activity content;
    private List<Users> usersList;

    public User_Adaptor(Activity content, List<Users> usersList){
        super(content,R.layout.user_listlayout,usersList);
        this.content = content;
        this.usersList = usersList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = content.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.user_listlayout,null,true);
        TextView textViewusername = (TextView) listViewItem.findViewById(R.id.textViewuserName);
        TextView textViewcompanyname = (TextView) listViewItem.findViewById(R.id.textViewCompanyname);
        TextView textViewphone =(TextView) listViewItem.findViewById(R.id.user_phone_no);
        TextView textViewsaddress =(TextView) listViewItem.findViewById(R.id.User_add);


        Users usss = usersList.get(position);

        textViewusername.setText(usss.getUserName());
        textViewcompanyname.setText(usss.getCompanyName());
        textViewphone.setText(usss.getPhone_no());
        textViewsaddress.setText(usss.getAddress());

        return listViewItem;

    }
}
