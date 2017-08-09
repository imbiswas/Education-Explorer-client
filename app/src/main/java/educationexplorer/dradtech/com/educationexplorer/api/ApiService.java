package educationexplorer.dradtech.com.educationexplorer.api;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by siddhant on 7/25/17.
 */

public interface ApiService {

    @Multipart
    @POST("api")
    Call<JsonObject> submit(@Part("district") RequestBody district, @Part("faculty") RequestBody faculty,
                            @Part("rating") RequestBody rating, @Part("affilation") RequestBody affilation,
                            @Part("fee") RequestBody fee);

    @Multipart
    @POST("search")
    Call<JsonObject> search(@Part("values") RequestBody values);

    //Call<ReturnResponse> search(@Body SearchRequest searchRequest);
}
