package com.example.clothing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemDescriptionActivity extends AppCompatActivity {
    ImageView imgview;
    TextView txtname,txtprice,txtdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);
        txtname=findViewById(R.id.tvName);
        txtprice=findViewById(R.id.tvPrice);
        txtdesc=findViewById(R.id.tvDesc);



        Bundle bundle =getIntent().getExtras();
        if(bundle !=null){
            txtname.setText(bundle.getString("name"));
            txtprice.setText("रू "+bundle.getString("price"));
            txtdesc.setText(bundle.getString("desc"));

        }
    }
}





