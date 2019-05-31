package clothapi;

import java.util.List;

import model.Clothes;
import model.ImageResponse;
import model.LoginSignupResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ClothesAPI {

    @FormUrlEncoded
    @POST("api/product/create")
    Call<Void> addCloth (@Header("x-access-token") String token,@Field("name") String name ,@Field("price") String price ,@Field("description") String descrition,@Field("image") String image);



    @Multipart
    @POST("api/product/upload")
    Call<ImageResponse> uploadImage(@Header("x-access-token") String token,@Part MultipartBody.Part img);

    @GET("api/product")
    Call<List<Clothes>>getAllCloth(@Header("x-access-token") String token);


    @FormUrlEncoded
    @POST("api/account/login")
    Call<LoginSignupResponse> checkUser(@Field("username") String username , @Field("password") String password);

    @FormUrlEncoded
    @POST("api/account/signup")
    Call<Void> register(@Field("first_name") String first_name , @Field("last_name") String last_name,@Field("username") String username , @Field("password") String password);

}
