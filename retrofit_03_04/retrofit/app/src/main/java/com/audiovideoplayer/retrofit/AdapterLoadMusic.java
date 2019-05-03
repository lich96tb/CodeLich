package com.audiovideoplayer.retrofit;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.audiovideoplayer.retrofit.interfaces.ILoadMore;
import com.audiovideoplayer.retrofit.model.ApiMusic;

import java.util.ArrayList;
import java.util.List;

public class AdapterLoadMusic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ApiMusic> list = new ArrayList<>();
    int lastVisibleItem, totalItemCount;
    private ILoadMore iLoadMores;
    private boolean checkLoad = false;


    public AdapterLoadMusic(RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                totalItemCount = recyclerView.getLayoutManager().getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
               Log.e("ASDF ","hhhhhhhhhdd   "+ checkLoad +"  "+totalItemCount+" last "+lastVisibleItem);
                if (!checkLoad && lastVisibleItem+1 == totalItemCount) {
                    Log.e("ASsssDF ","hhhhhhhhh");
                    iLoadMores.onLoadMore();
                    checkLoad = true;
                }
            }
        });

    }
    public void setData(List<ApiMusic> list){
        this.list=list;
        notifyDataSetChanged();
        checkLoad=false;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading, viewGroup, false);
            return new LoadHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
            return new ItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof ItemHolder) {
            ItemHolder itemVidwHolder = (ItemHolder) viewHolder;
            itemVidwHolder.txtName.setText(list.get(i).getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }


    private class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    public void setLoadMore(ILoadMore iLoadMores){
        this.iLoadMores=iLoadMores;
    }
}
