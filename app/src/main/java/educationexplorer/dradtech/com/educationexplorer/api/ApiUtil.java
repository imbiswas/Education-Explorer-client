package educationexplorer.dradtech.com.educationexplorer.api;

/**
 * Created by siddhant on 7/25/17.
 */

public class ApiUtil {

    private ApiUtil(){}

    //public static final String BASE_URL = "http://192.168.1.27:5555/";
    public static final String BASE_URL = " http://192.168.0.220:5555";

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);

    }

}
