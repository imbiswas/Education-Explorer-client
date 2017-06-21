package educationexplorer.dradtech.com.educationexplorer;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class MainActivity extends AppCompatActivity {

    private Spinner districtSpinner, facultySpinner, affiliationSpinner;
    private ImageView searchImage;
    private RatingBar ratingBar;
    private RelativeLayout districtLayoutClick, facultyLayoutClick, affiliationLayoutCLick;
    private LinearLayout districtSubLayout, facultySubLayout, affiliationSubLayout;
    private ImageView districtExpandableImage, facultyExpandableImage, affiliationExpandableImage;

    private SimpleRatingBar simpleRatingBar;
    private DiscreteSeekBar discreteNormal;

    private Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize searchImage
        searchImage = (ImageView) findViewById(R.id.searchImage);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "SearchImage is Clicked !!", Toast.LENGTH_SHORT).show();
            }
        });

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

                    districtExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_less_black_24dp);
                    draw = resize(draw);
                    districtExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Expand");
                }else if(districtSubLayout.getVisibility() == View.VISIBLE){
                    districtSpinner.setVisibility(View.GONE);
                    districtLayoutCollapse();

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

                    facultyExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_less_black_24dp);
                    draw = resize(draw);
                    facultyExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Expand");
                }else if(facultySubLayout.getVisibility() == View.VISIBLE){
                    facultySpinner.setVisibility(View.GONE);
                    facultyLayoutCollapse();

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

                    affiliationExpandableImage.setImageResource(0);
                    Drawable draw = getResources().getDrawable(R.drawable.ic_expand_less_black_24dp);
                    draw = resize(draw);
                    affiliationExpandableImage.setImageDrawable(draw);
                    Log.e("District Layout","Expand");
                }else if(affiliationSubLayout.getVisibility() == View.VISIBLE){
                    affiliationSpinner.setVisibility(View.GONE);
                    affiliationLayoutCollapse();

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

        //Initialize DiscreteSeekBar
        discreteNormal = (DiscreteSeekBar) findViewById(R.id.discrete_normal);

        initSeekBar();
    }

    // add items into spinner dynamically
    public void addItemOnDistrictSpinner(){

        // Initialize districtSpinner
        districtSpinner = (Spinner) findViewById(R.id.districtSpinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.district_arrays, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(dataAdapter);
    }

    public void submitBtn(View view){
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Submit button clicked !!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    // add items into spinner dynamically
    public void addItemOnFacultySpinner(){
        // Initialize facultySpinner
        facultySpinner = (Spinner) findViewById(R.id.facultySpinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.faculty_arrays, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(dataAdapter);
    }

    // add items into spinner dynamically
    public void addItemOnAffiliationSpinner(){
        // Initialize affiliationSpinner
        affiliationSpinner = (Spinner) findViewById(R.id.affiliationSpinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.affiliation_arrays, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        affiliationSpinner.setAdapter(dataAdapter);
    }

    private void initSeekBar(){
        discreteNormal.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser){
                Log.e("SeekBar Value", String.valueOf(value));
            }
            @Override public void onStartTrackingTouch(DiscreteSeekBar seekBar){

            }
            @Override public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

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
}
