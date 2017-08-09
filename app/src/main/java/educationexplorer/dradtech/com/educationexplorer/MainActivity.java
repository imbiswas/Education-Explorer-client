package educationexplorer.dradtech.com.educationexplorer;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import educationexplorer.dradtech.com.educationexplorer.api.ApiService;
import educationexplorer.dradtech.com.educationexplorer.api.ApiUtil;
import educationexplorer.dradtech.com.educationexplorer.model.ReturnResponse;
import educationexplorer.dradtech.com.educationexplorer.model.SubmitRequest;
import educationexplorer.dradtech.com.educationexplorer.utils.InternetConnection;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private View parentView;
    private Spinner districtSpinner, facultySpinner, affiliationSpinner;
    private ImageView searchImage;
    private RelativeLayout districtLayoutClick, facultyLayoutClick, affiliationLayoutCLick,
            selectedFeeLayout, ratingLayout;
    private LinearLayout districtSubLayout, facultySubLayout, affiliationSubLayout;
    private ImageView districtExpandableImage, facultyExpandableImage, affiliationExpandableImage;
    private EditText searchbox;
    private SimpleRatingBar simpleRatingBar;
    private DiscreteSeekBar discreteNormal;
    private RequestQueue queue;

    //Second Step Edit
    TextView selectedFee, ratingText;

    String districtValue = null;
    String facultyValue = null;
    int ratingValue = -1;
    String affilationValue = null;
    long feeValue = 0;

    private Button submitBtn;

    private ApiService mApiService;
    private static final String TAG = "Submit";

    public SubmitRequest submitRequest = new SubmitRequest();
    private List<ReturnResponse> returnResponsesList = new ArrayList<>();

    private boolean isDistrictSpinnerTouched = false;
    private boolean isFacultySpinnerTouched = false;
    private boolean isAffilationSpinnerTouched = false;

    public String address,affilation, college_name, district, faculty, logo, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentView = findViewById(R.id.parentLayout);

        // Initialize searchImage
        searchbox=(EditText)findViewById(R.id.searchBox);
        searchImage = (ImageView) findViewById(R.id.searchImage);

        districtExpandableImage = (ImageView) findViewById(R.id.districtExpandableImage);
        districtExpandableImage.setImageResource(0);
        Drawable draw01 = getResources().getDrawable(R.drawable.ic_expand_more_black_24dp);
        draw01 = resize(draw01);
        districtExpandableImage.setImageDrawable(draw01);

        districtSubLayout = (LinearLayout) findViewById(R.id.districtSubLayout);

        districtLayoutClick = (RelativeLayout) findViewById(R.id.districtLayoutClick);
        districtLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(districtSubLayout.getVisibility() == View.GONE){
                    if(districtSpinner.getVisibility() == View.GONE){
                        districtSpinner.setVisibility(View.VISIBLE);
                    }
                    districtLayoutExpand();
                    isDistrictSpinnerTouched = true;

                    districtExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_less_black_24dp);
                    draw = resize(draw);
                    districtExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Expand");
                }else if(districtSubLayout.getVisibility() == View.VISIBLE){
                    districtSpinner.setVisibility(View.GONE);
                    districtLayoutCollapse();
                    isDistrictSpinnerTouched = false;

                    districtExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_more_black_24dp);
                    draw = resize(draw);
                    districtExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Collapse");
                }
            }
        });

        facultyExpandableImage = (ImageView) findViewById(R.id.facultyExpandableImage);
        facultyExpandableImage.setImageResource(0);
        Drawable draw02 = getResources().getDrawable(R.drawable.ic_expand_more_black_24dp);
        draw02 = resize(draw02);
        facultyExpandableImage.setImageDrawable(draw02);

        facultySubLayout = (LinearLayout) findViewById(R.id.facultySubLayout);

        facultyLayoutClick = (RelativeLayout) findViewById(R.id.facultyLayoutClick);
        facultyLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(facultySubLayout.getVisibility() == View.GONE){
                    if(facultySpinner.getVisibility() == View.GONE){
                        facultySpinner.setVisibility(View.VISIBLE);
                    }
                    facultyLayoutExpand();
                    isFacultySpinnerTouched = true;

                    facultyExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_less_black_24dp);
                    draw = resize(draw);
                    facultyExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Expand");
                }else if(facultySubLayout.getVisibility() == View.VISIBLE){
                    facultySpinner.setVisibility(View.GONE);
                    facultyLayoutCollapse();
                    isFacultySpinnerTouched = false;

                    facultyExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_more_black_24dp);
                    draw = resize(draw);
                    facultyExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Collapse");
                }
            }
        });

        affiliationExpandableImage = (ImageView) findViewById(R.id.affiliationExpandableImage);
        affiliationExpandableImage.setImageResource(0);
        Drawable draw03 = getResources().getDrawable(R.drawable.ic_expand_more_black_24dp);
        draw03 = resize(draw03);
        affiliationExpandableImage.setImageDrawable(draw03);

        affiliationSubLayout = (LinearLayout) findViewById(R.id.affiliationSubLayout);

        affiliationLayoutCLick = (RelativeLayout) findViewById(R.id.affiliationLayoutClick);
        affiliationLayoutCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(affiliationSubLayout.getVisibility() == View.GONE){
                    if(affiliationSpinner.getVisibility() == View.GONE){
                        affiliationSpinner.setVisibility(View.VISIBLE);
                    }
                    affiliationLayoutExpand();
                    isAffilationSpinnerTouched = true;

                    affiliationExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_less_black_24dp);
                    draw = resize(draw);
                    affiliationExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Expand");
                }else if(affiliationSubLayout.getVisibility() == View.VISIBLE){
                    affiliationSpinner.setVisibility(View.GONE);
                    affiliationLayoutCollapse();
                    isAffilationSpinnerTouched = false;

                    affiliationExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_more_black_24dp);
                    draw = resize(draw);
                    affiliationExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Collapse");
                }
            }
        });

        addItemOnDistrictSpinner();

        addItemOnFacultySpinner();

        addItemOnAffiliationSpinner();

        // Initialize RatingBar
        //ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        simpleRatingBar = (SimpleRatingBar) findViewById(R.id.simpleRatingBar);

        ratingLayout = (RelativeLayout) findViewById(R.id.rating_layout);

        ratingText = (TextView) findViewById(R.id.rating);

        getRating();

        //Initialize DiscreteSeekBar
        discreteNormal = (DiscreteSeekBar) findViewById(R.id.discrete_normal);

        initSeekBar();

        selectedFeeLayout = (RelativeLayout) findViewById(R.id.selected_fee_layout);

        selectedFee = (TextView) findViewById(R.id.selected_fee);

        mApiService = ApiUtil.getApiService();
    }

    public void getRating(){
        simpleRatingBar.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                ratingValue = (int) rating;
                String stringValue = String.valueOf(ratingValue);
                if(!stringValue.equals("")){
                    ratingText.setText(stringValue);
                    submitRequest.setRating(ratingValue);

                    Log.e("SeekBar Value", stringValue);
                    if(ratingLayout.getVisibility() == View.GONE){
                        ratingLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }
    // add items into spinner dynamically
    public void addItemOnDistrictSpinner(){

        // Initialize districtSpinner
        districtSpinner = (Spinner) findViewById(R.id.districtSpinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.district_arrays, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(dataAdapter);

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                districtValue =  adapterView.getSelectedItem().toString();
                submitRequest.setDistrict(districtValue);

                Log.e("Item:", districtValue);
                //Toast.makeText(MainActivity.this, districtValue,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void searchBtn(View view){
        searchImage = (ImageView) findViewById(R.id.searchImage);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(searchbox.getText().toString().length() != 0){
                    //Toast.makeText(MainActivity.this,"Search button clicked !!",Toast.LENGTH_SHORT).show();

                    if(InternetConnection.checkConnection(getApplicationContext())){
                        final ProgressDialog dialog;
                        /**
                         * Progress Dialog for User Interaction
                         */
                        dialog = new ProgressDialog(MainActivity.this);
                        dialog.setTitle("Please wait...");
                        dialog.setMessage("While Processing...");
                        dialog.show();

                        RequestBody searchRequestBody = RequestBody.create(MediaType.parse("text/plain"), searchbox.getText().toString());

                        //Creating an object of our api interface
                        ApiService api = ApiUtil.getApiService();

                        /**
                         * Calling JSON*/
                        Call<JsonObject> call = api.search(searchRequestBody);

                        /**
                         * Enqueue CallBack will be when get response*/

                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                dialog.dismiss();

                                if(response.isSuccessful()){

                                    JsonObject jsonObject = response.body();
                                    JsonArray jsonArray = jsonObject.getAsJsonArray("tasks");

                                    if(jsonArray != null){

                                        for(int i=0; i<jsonArray.size(); i++){
                                            address = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Address"));
                                            affilation = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Affilation"));
                                            college_name = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("College Name"));
                                            district = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("District"));
                                            faculty = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Faculty"));
                                            logo = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("logo"));
                                            url = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("url"));

                                            Log.e("Address", String.valueOf(jsonArray.get(i).getAsJsonArray().get(i).getAsJsonObject().get("Address")));
                                            Log.e("Affilation", String.valueOf(jsonArray.get(i).getAsJsonArray().get(i).getAsJsonObject().get("Affilation")));
                                            Log.e("College Name", String.valueOf(jsonArray.get(i).getAsJsonArray().get(i).getAsJsonObject().get("College Name")));
                                            Log.e("District", String.valueOf(jsonArray.get(i).getAsJsonArray().get(i).getAsJsonObject().get("District")));
                                            Log.e("Faculty", String.valueOf(jsonArray.get(i).getAsJsonArray().get(i).getAsJsonObject().get("Faculty")));
                                            Log.e("logo", String.valueOf(jsonArray.get(i).getAsJsonArray().get(i).getAsJsonObject().get("logo")));
                                            Log.e("url", String.valueOf(jsonArray.get(i).getAsJsonArray().get(i).getAsJsonObject().get("url")));

                                            //Removing double quoted
                                            String trimAddress = address.substring(1, address.length()-1);
                                            String trimAffilation = affilation.substring(1, affilation.length()-1);
                                            String trimCollegeName = college_name.substring(1, college_name.length()-1);
                                            String trimDistrict = district.substring(1, district.length()-1);
                                            String trimFaculty = faculty.substring(1, faculty.length()-1);
                                            String trimLogo = logo.substring(1, logo.length()-1);
                                            String trimUrl = url.substring(1, url.length()-1);

                                            ReturnResponse returnResponse = new ReturnResponse(trimAddress, trimAffilation, trimCollegeName, trimDistrict,
                                                    trimFaculty, trimLogo, trimUrl);

                                            returnResponsesList.add(returnResponse);
                                        }

                                        Intent intent = new Intent(MainActivity.this, Result.class);
                                        intent.putExtra("responseList", (Serializable) returnResponsesList);
                                        startActivity(intent);
                                    }

                                }else if(response.code() == 400){
                                    try {
                                        Log.e("Error code 400",response.errorBody().string());
                                        Toast.makeText(MainActivity.this,R.string.string_some_thing_wrong,Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                else {
                                    try {
                                        Log.e("Error code 400",response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this,"Failed to connect to server. Please try again later !!",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {
                        Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this,"Search box is empty !!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validateRating(int ratingValue){
        if(ratingValue < 0){
            return false;
        }
        return true;
    }

    public boolean validateFee(long feeValue){
        if(feeValue == 0){
            return false;
        }
        return true;
    }

    public void submitBtn(View view){
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Checking Internet Connection
                 */

                if(InternetConnection.checkConnection(getApplicationContext())){

                    if(isDistrictSpinnerTouched && isAffilationSpinnerTouched &&
                            isFacultySpinnerTouched && validateRating(ratingValue) &&
                            validateFee(feeValue)){

                        final ProgressDialog dialog;
                        /**
                         * Progress Dialog for User Interaction
                         */
                        dialog = new ProgressDialog(MainActivity.this);
                        dialog.setTitle("Please wait...");
                        dialog.setMessage("While Processing...");
                        dialog.show();

                        //Creating an object of our api interface
                        ApiService api = ApiUtil.getApiService();

                        RequestBody districtRequestBody = RequestBody.create(MediaType.parse("text/plain"),
                                                            submitRequest.getDistrict());

                        RequestBody facultyRequestBody = RequestBody.create(MediaType.parse("text/plain"),
                                                            submitRequest.getFaculty());

                        RequestBody ratingRequestBody = RequestBody.create(MediaType.parse("text/plain"),
                                                            String.valueOf(submitRequest.getRating()));

                        RequestBody affilationRequestBody = RequestBody.create(MediaType.parse("text/plain"),
                                                            submitRequest.getAffilation());

                        RequestBody feeRequestBody = RequestBody.create(MediaType.parse("text/plain"),
                                                            String.valueOf(submitRequest.getFee()));
                        /**
                         * Calling JSON*/
                        Call<JsonObject> call = api.submit(districtRequestBody, facultyRequestBody, ratingRequestBody,
                                                            affilationRequestBody, feeRequestBody);

                        /**
                         * Enqueue CallBack will be when get response*/

                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                //Dismiss Dialog
                                dialog.dismiss();

                                if(response.isSuccessful()){

                                    JsonObject jsonObject = response.body();
                                    JsonArray jsonArray = jsonObject.getAsJsonArray("tasks");

                                    /**
                                    if(jsonArray != null){
                                        Toast.makeText(MainActivity.this,"Values: "+jsonArray,Toast.LENGTH_SHORT).show();
                                        Log.e("Response: ",jsonArray.toString());
                                        Log.e("Status Code: ", String.valueOf(statusCode));

                                    }   */

                                    for(int i=0; i<jsonArray.size(); i++) {

                                        address = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Address"));
                                        affilation = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Affilation"));
                                        college_name = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("College Name"));
                                        district = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("District"));
                                        faculty = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Faculty"));
                                        logo = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("logo"));
                                        url = String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("url"));

                                        Log.e("Address", String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Address")));
                                        Log.e("Affilation", String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Affilation")));
                                        Log.e("College Name", String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("College Name")));
                                        Log.e("District", String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("District")));
                                        Log.e("Faculty", String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("Faculty")));
                                        Log.e("logo", String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("logo")));
                                        Log.e("url", String.valueOf(jsonArray.get(i).getAsJsonArray().get(0).getAsJsonObject().get("url")));

                                        //Removing double quoted
                                        String trimAddress = address.substring(1, address.length() - 1);
                                        String trimAffilation = affilation.substring(1, affilation.length() - 1);
                                        String trimCollegeName = college_name.substring(1, college_name.length() - 1);
                                        String trimDistrict = district.substring(1, district.length() - 1);
                                        String trimFaculty = faculty.substring(1, faculty.length() - 1);
                                        String trimLogo = logo.substring(1, logo.length() - 1);
                                        String trimUrl = url.substring(1, url.length() - 1);

                                        ReturnResponse returnResponse = new ReturnResponse(trimAddress, trimAffilation, trimCollegeName, trimDistrict,
                                                trimFaculty, trimLogo, trimUrl);

                                        returnResponsesList.add(returnResponse);
                                    }

                                    Intent intent = new Intent(MainActivity.this, Result.class);
                                    intent.putExtra("responseList", (Serializable) returnResponsesList);
                                    startActivity(intent);

                                }else if(response.code() == 400){
                                    try {
                                        Log.e("Error code 400",response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    try {
                                        Log.e("Error code 400",response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(MainActivity.this,R.string.string_some_thing_wrong,Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this,"Failed to connect to server. Please try again later !!",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else{
                        Toast.makeText(MainActivity.this,R.string.select_all_to_submit,Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this,R.string.string_internet_connection_not_available,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void addItemOnFacultySpinner(){
        // Initialize facultySpinner
        facultySpinner = (Spinner) findViewById(R.id.facultySpinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.faculty_arrays, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(dataAdapter);

        facultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                facultyValue = adapterView.getSelectedItem().toString();
                submitRequest.setFaculty(facultyValue);

                Log.e("Item:", facultyValue);
                //Toast.makeText(MainActivity.this, facultyValue ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // add items into spinner dynamically
    public void addItemOnAffiliationSpinner(){
        // Initialize affiliationSpinner
        affiliationSpinner = (Spinner) findViewById(R.id.affiliationSpinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.affiliation_arrays, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        affiliationSpinner.setAdapter(dataAdapter);

        affiliationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                affilationValue =  adapterView.getSelectedItem().toString();
                submitRequest.setAffilation(affilationValue);

                Log.e("Item:", affilationValue);
                //Toast.makeText(MainActivity.this, affilationValue ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSeekBar(){
        discreteNormal.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser){
                //Log.e("SeekBar Value", String.valueOf(value));
            }
            @Override public void onStartTrackingTouch(DiscreteSeekBar seekBar){

            }
            @Override public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                String value = String.valueOf(seekBar.getProgress());
                feeValue = seekBar.getProgress();
                submitRequest.setFee(feeValue);
                if(!value.equals("")){
                    selectedFee.setText(value);
                    if(selectedFeeLayout.getVisibility() == View.GONE){
                        selectedFeeLayout.setVisibility(View.VISIBLE);
                    }
                }
                Log.e("SeekBar Value", String.valueOf(seekBar.getProgress()));
            }
        });
    }

    public void districtLayoutExpand(){
        //set Visibility
        districtSubLayout.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        districtSubLayout.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = districtLayoutSlideAnimator(0, districtSubLayout.getMeasuredHeight());
        mAnimator.start();
    }

    public void districtLayoutCollapse(){
        int finalHeight = districtSubLayout.getHeight();

        ValueAnimator mAnimator = districtLayoutSlideAnimator(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //Height=0, but it set visibility to GONE
                districtSubLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mAnimator.start();
    }

    private ValueAnimator districtLayoutSlideAnimator(int start, int end){
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //Update Height
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = districtSubLayout.getLayoutParams();
                layoutParams.height = value;
                districtSubLayout.setLayoutParams(layoutParams);
            }
        });

        return  animator;
    }

    public void facultyLayoutExpand(){
        //set Visibility
        facultySubLayout.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        facultySubLayout.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = facultyLayoutSlideAnimator(0, facultySubLayout.getMeasuredHeight());
        mAnimator.start();
    }

    public void facultyLayoutCollapse(){
        int finalHeight = facultySubLayout.getHeight();

        ValueAnimator mAnimator = facultyLayoutSlideAnimator(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //Height=0, but it set visibility to GONE
                facultySubLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mAnimator.start();
    }

    private ValueAnimator facultyLayoutSlideAnimator(int start, int end){
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //Update Height
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = facultySubLayout.getLayoutParams();
                layoutParams.height = value;
                facultySubLayout.setLayoutParams(layoutParams);
            }
        });

        return  animator;
    }

    public void affiliationLayoutExpand(){
        //set Visibility
        affiliationSubLayout.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        affiliationSubLayout.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = affiliationLayoutSlideAnimator(0, affiliationSubLayout.getMeasuredHeight());
        mAnimator.start();
    }

    public void affiliationLayoutCollapse(){
        int finalHeight = affiliationSubLayout.getHeight();

        ValueAnimator mAnimator = affiliationLayoutSlideAnimator(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //Height=0, but it set visibility to GONE
                affiliationSubLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mAnimator.start();
    }

    private ValueAnimator affiliationLayoutSlideAnimator(int start, int end){
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //Update Height
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = affiliationSubLayout.getLayoutParams();
                layoutParams.height = value;
                affiliationSubLayout.setLayoutParams(layoutParams);
            }
        });

        return  animator;
    }

    private Drawable resize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                (int) (bitmap.getWidth() * 0.5), (int) (bitmap.getHeight() * 0.5), false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }
}
