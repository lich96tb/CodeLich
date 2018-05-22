package com.example.cntt.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CNTT on 3/22/2018.
 */

public class AdapterStudent extends RecyclerView.Adapter<AdapterStudent.ViewHolder> {
    List<Student> listStudent;
    Click click;

    public AdapterStudent(List<Student> listStudent, Click click) {
        this.listStudent = listStudent;
        this.click = click;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student=listStudent.get(position);
        holder.txt_name.setText(student.getName());
        holder.txt_year_old.setText(student.getYear_old()+"");


    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView txt_name, txt_year_old;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_year_old = itemView.findViewById(R.id.txt_yead_old);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onClickView(getPosition());
                }
            });
        }

    }
}
