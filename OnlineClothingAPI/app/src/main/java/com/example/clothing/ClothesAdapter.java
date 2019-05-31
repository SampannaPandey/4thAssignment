package com.example.clothing;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.Clothes;
import url.Url;

class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {

    private List<Clothes> clothesList;
    private Context context;

    public ClothesAdapter(List<Clothes> clothesList, Context context){
        this.clothesList = clothesList;
        this.context=context;
    }

    @NonNull
    @Override
    public ClothesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.clothes,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ClothesAdapter.ViewHolder viewHolder, int i) {
        final Clothes clothes = clothesList.get(i);
        String imgPath = Url.BASE_URL+"uploads/"+ clothes.getImage();
        StrictMode();

        try{
            URL url = new URL(imgPath);
            viewHolder.imgPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.tvName.setText(clothes.getName());
        viewHolder.tvId.setText(clothes.get_id());

        viewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ItemDescriptionActivity.class);
                intent.putExtra("image",clothes.getImage());
                intent.putExtra("name",clothes.getName());
                intent.putExtra("price",clothes.getPrice());
                intent.putExtra("desc",clothes.getDesc());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.imgPhoto);
            tvName=itemView.findViewById(R.id.tvName);
            tvId=itemView.findViewById(R.id.tvID);

        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
