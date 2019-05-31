package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clothing.MainActivity;
import com.example.clothing.R;
import com.example.clothing.RecyclerCloth;

import clothapi.ClothesAPI;
import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener  {
    private EditText txtfname,txtlname,txtusername,txtpassword,txtrepassword;
    private Button btnsignup;


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sigup, container, false);
        txtfname=view.findViewById(R.id.txtfname);
        txtlname=view.findViewById(R.id.txtlname);
        txtusername=view.findViewById(R.id.txtusername);
        txtpassword=view.findViewById(R.id.txtpassword);
        txtrepassword=view.findViewById(R.id.txtrepassword);
        btnsignup=view.findViewById(R.id.btnregister);

        btnsignup.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(validate()){
            register();
        }

    }
    private void register() {
        ClothesAPI clothesAPI = Url.getInstance().create(ClothesAPI.class);
        String first_name= txtfname.getText().toString();
        String last_name= txtlname.getText().toString();
        String username= txtusername.getText().toString().trim();
        String password= txtpassword.getText().toString().trim();

        Call<Void> usersCall = clothesAPI.register(first_name,last_name,username,password);
        usersCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Registered Succesfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validate(){
        boolean checkvalidate=true;
        if(TextUtils.isEmpty(txtfname.getText().toString())){
            txtfname.setError("First Name is required");
            txtfname.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtlname.getText().toString())){
            txtlname.setError("Last Name is required");
            txtlname.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtusername.getText().toString())){
            txtusername.setError("Username is required");
            txtusername.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtpassword.getText().toString())){
            txtpassword.setError("Password is required");
            txtpassword.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(txtrepassword.getText().toString())){
            txtrepassword.setError("Password is required");
            txtrepassword.requestFocus();
            checkvalidate=false;
        }
        String pwd=txtpassword.getText().toString();
        String repwd=txtrepassword.getText().toString();
        if(pwd.equals(repwd)){
            checkvalidate=true;
        }else{
            txtpassword.setError("Invalid Match");
            txtrepassword.setError("Invalid Match");
            txtpassword.requestFocus();
            checkvalidate=false;
        }
        return  checkvalidate;

    }
}
