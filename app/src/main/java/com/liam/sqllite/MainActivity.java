package com.liam.sqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler data;
    private Button btn_add;
    private EditText edt_address,edt_phone,edt_name;
    RecyclerView rv_student;
    StudentAdapter adapter;
    List<Student>students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_student=findViewById(R.id.rv_student);
        btn_add=findViewById(R.id.btn_add);
        edt_address=findViewById(R.id.edt_address);
        edt_phone=findViewById(R.id.edt_phone);
        edt_name=findViewById(R.id.edt_name);
        data=new DatabaseHandler(this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student(1,edt_name.getText().toString()
                        ,edt_address.getText().toString(),edt_phone.getText().toString());
                data.addStudent(student);
                edt_address.setText("");
                edt_phone.setText("");
                edt_name.setText("");
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                List<Student>students=data.getAllStudents();
                adapter.setData(students);
            }
        });

        setUpListView();
    }

    private void setUpListView() {
        students=new ArrayList<>();
        students=data.getAllStudents();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_student.setLayoutManager(mLayoutManager);
        adapter=new StudentAdapter(this);
        adapter.setData(students);
        adapter.setListener(new StudentAdapter.IClickListener() {
            @Override
            public void onItemClick(Student student) {
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("id",student.getId());
                startActivity(intent);
            }
        });
        this.rv_student.setAdapter(adapter);
    }
}
