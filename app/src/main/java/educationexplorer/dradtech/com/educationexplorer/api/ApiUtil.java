package educationexplorer.dradtech.com.educationexplorer.api;

/**
 * Created by siddhant on 7/25/17.
 */

public class ApiUtil {

    private ApiUtil(){}

    public static final String BASE_URL = "http://192.168.43.6:5555/";
//    public static final String BASE_URL = " http://192.168.0.118:5555";

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);

    }

}
