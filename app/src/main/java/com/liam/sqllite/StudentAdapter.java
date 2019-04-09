package com.liam.sqllite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    private Context context;
    List<Student> data;
    private StudentAdapter.IClickListener listener;
    public StudentAdapter(Context context){
        this.context=context;
    }
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Student>data){
        this.data=data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder viewHolder, int position) {
        Student student=data.get(position);
        if(student!=null){
            viewHolder.txt_id.setText(String.valueOf(student.getId()));
            viewHolder.txt_name.setText(student.getName());
            viewHolder.txt_address.setText(student.getAddress());
            viewHolder.txt_phone.setText(student.getPhone_number());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_name,txt_address,txt_phone,txt_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_address=itemView.findViewById(R.id.txt_address);
            txt_phone=itemView.findViewById(R.id.txt_phone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
    public interface IClickListener {
        void onItemClick(Student student);
    }
}
