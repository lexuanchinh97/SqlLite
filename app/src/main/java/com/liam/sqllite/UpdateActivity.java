package com.liam.sqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    DatabaseHandler data;
    private Button btn_update,btn_delete;
    private int id=1;
    private EditText edt_name,edt_address,edt_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);
        edt_name=findViewById(R.id.edt_name);
        edt_address=findViewById(R.id.edt_address);
        edt_phone=findViewById(R.id.edt_phone);
        data=new DatabaseHandler(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }
        Student temp=data.getStudent(id);
        edt_name.setText(temp.getName());
        edt_address.setText(temp.getAddress());
        edt_phone.setText(temp.getPhone_number());
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student(id,edt_name.getText().toString(),edt_address.getText().toString(),edt_phone.getText().toString());
                data.updateStudent(student);
                Toast.makeText(UpdateActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteStudent(id);
                Toast.makeText(UpdateActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
            }
        });
    }
}
