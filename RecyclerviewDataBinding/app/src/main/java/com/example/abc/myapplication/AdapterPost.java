package com.example.abc.myapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.List;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHoler> {
private List<Post> list;

    public AdapterPost(List<Post> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterPost.ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item, viewGroup, false);
        return new ViewHoler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPost.ViewHoler viewHoler, int i) {
        final Post post=list.get(i);
        viewHoler.bin(post);

        viewHoler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(post);
                notifyDataSetChanged();
                Log.e("ADDdfsdfsdSF ","dkjskdj "+post.getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        public ViewHoler(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bin(Post post) {
            binding.setVariable(com.example.abc.myapplication.BR.op,post);
            binding.executePendingBindings();
        }
    }
}
