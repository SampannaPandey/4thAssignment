package com.example.clothing;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fragment.LoginFragment;
import fragment.SignupFragment;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button btnfragment;
    private Boolean status=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        btnfragment=findViewById(R.id.btngo);
        btnfragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        if(status){
            LoginFragment firstFragment=new LoginFragment();
            fragmentTransaction.replace(R.id.fragmentcontainer,firstFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            btnfragment.setText("Register");
            status=false;
        }
        else{
            SignupFragment secondFragment =new SignupFragment();
            fragmentTransaction.replace(R.id.fragmentcontainer,secondFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            btnfragment.setText("Login");
            status=true;
        }
    }
}
