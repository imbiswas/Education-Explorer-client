package educationexplorer.dradtech.com.educationexplorer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import educationexplorer.dradtech.com.educationexplorer.R;
import educationexplorer.dradtech.com.educationexplorer.model.ReturnResponse;

/**
 * Created by siddhant on 7/27/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    Context context;
    List<ReturnResponse> returnResponseList = Collections.emptyList();
    public RecyclerViewAdapter(){

    }

    public RecyclerViewAdapter (Context context, List<ReturnResponse> returnResponseList){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.returnResponseList = returnResponseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        ReturnResponse item = getItem(position);

        //Set logo in imageView
       /* Picasso.with(context).load(item.getLogo()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(holder.imageView);*/
       String image_url="http://www.educationxplorer.net"+item.getLogo();
        Picasso.with(context).load(image_url).into(holder.imageView);

        holder.collegeName.setText(item.getCollege_name());
        holder.address.setText(item.getAddress());
        holder.district.setText(item.getDistrict());
        holder.affilation.setText(item.getAffilation());
        holder.faculty.setText(item.getFaculty());
        holder.website.setText(item.getUrl());

        holder.website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = holder.website.getText().toString();
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                    }
                });
            }
        });

        holder.text_district.setText("District:");
        holder.text_affilation.setText("Affilation:");
        holder.text_faculty.setText("Faculty:");
        holder.text_website.setText("Website:");
        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"success",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public ReturnResponse getItem(int position){
        return returnResponseList.get(position);
    }

    @Override
    public int getItemCount() {
        return returnResponseList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView collegeName;
        TextView address;
        TextView district, text_district;
        TextView affilation, text_affilation;
        TextView faculty, text_faculty;
        TextView website, text_website;
        ImageButton map;

        public MyViewHolder(View view){
            super(view);

            imageView = (ImageView) view.findViewById(R.id.logo);
            collegeName = (TextView) view.findViewById(R.id.college_name);
            address = (TextView) view.findViewById(R.id.address);
            district = (TextView) view.findViewById(R.id.district);
            affilation = (TextView) view.findViewById(R.id.affilation);
            faculty = (TextView) view.findViewById(R.id.faculty);
            website = (TextView) view.findViewById(R.id.website);
            map=(ImageButton) view.findViewById(R.id.mapbtn);

            text_district = (TextView) view.findViewById(R.id.text_district);
            text_affilation = (TextView) view.findViewById(R.id.text_affilation);
            text_faculty = (TextView) view.findViewById(R.id.text_faculty);
            text_website = (TextView) view.findViewById(R.id.text_website);

        }
    }
}
