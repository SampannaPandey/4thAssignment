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
public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText txtusername,txtpassword;
    private Button btnlogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        txtusername=view.findViewById(R.id.txtusername);
        txtpassword=view.findViewById(R.id.txtpassword);
        btnlogin=view.findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(validate()){
            checkUser();
        }

    }
    private boolean validate(){
        boolean checkvalidate=true;
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
        return  checkvalidate;

    }
    private void checkUser() {
        ClothesAPI clothesAPI = Url.getInstance().create(ClothesAPI.class);

        String username= txtusername.getText().toString().trim();
        String password= txtpassword.getText().toString().trim();

        Call<LoginSignupResponse> usersCall = clothesAPI.checkUser(username,password);
        usersCall.enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Either Username or Password is Incorrect ",Toast.LENGTH_SHORT).show();
                    return;

                }else{
                    Toast.makeText(getActivity()," "+response.body().getToken(),Toast.LENGTH_SHORT).show();


                    if(response.body().getSuccess()){
                        Url.token=response.body().getToken();
                        Toast.makeText(getActivity(),"Success ",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), RecyclerCloth.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Error "+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}
