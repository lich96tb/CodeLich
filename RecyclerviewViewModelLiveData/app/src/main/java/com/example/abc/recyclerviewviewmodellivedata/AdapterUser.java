package com.example.abc.recyclerviewviewmodellivedata;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc.recyclerviewviewmodellivedata.database.AppDatabase;
import com.example.abc.recyclerviewviewmodellivedata.database.User;

import java.util.ArrayList;
import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewModel> {

    private List<User> list  ;
    private IDelete deleteItem;

    public AdapterUser(IDelete deleteItem) {
        this.deleteItem = deleteItem;
        list=new ArrayList<>();
    }

    public AdapterUser(@NonNull List<User> list) {
        this.list = list;
    }

    public AdapterUser() {
    }

    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewModel(view);
    }

    public void SetData(List<User> listdata) {
        if (listdata != null) {
            list = listdata;
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel viewModel, int i) {
        viewModel.txtName.setText(list.get(i).getName());
        viewModel.txtAge.setText(list.get(i).getAge());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        TextView txtName, txtAge;

        public ViewModel(@NonNull final View itemView) {
            super(itemView);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtName = itemView.findViewById(R.id.txtName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem.delete(list.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
