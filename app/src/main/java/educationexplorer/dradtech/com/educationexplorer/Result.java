package educationexplorer.dradtech.com.educationexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import educationexplorer.dradtech.com.educationexplorer.adapter.RecyclerViewAdapter;
import educationexplorer.dradtech.com.educationexplorer.model.ReturnResponse;

/**
 * Created by Biswas on 6/23/2017.
 */

public class Result extends AppCompatActivity {

    private List<ReturnResponse> returnResponseList = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        setAdapter();
    }

    public void setAdapter(){
        /**
         * Binding that List to adapter
         */

        returnResponseList = (ArrayList<ReturnResponse>) getIntent().getSerializableExtra("responseList");
        adapter = new RecyclerViewAdapter(Result.this, returnResponseList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
