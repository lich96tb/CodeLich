package com.example.volleymp3loadmore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewModel> {
    List<Music> list;
    Context context;

    public AdapterMusic(List<Music> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel viewModel, int i) {
        Log.e("ACD",list.get(i).getArtistname());
        Glide.with(context)
                .load(list.get(i).getSongPic())
                .into(viewModel.img);
//        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        viewModel.txtName.setText(list.get(i).getArtistname());
        viewModel.txtArtistname.setText(list.get(i).getSongname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtName, txtArtistname;

        public ViewModel(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtName = itemView.findViewById(R.id.txtName);
            txtArtistname = itemView.findViewById(R.id.txtArtistname);
        }
    }
}
