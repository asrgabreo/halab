package com.example.app4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    EditText name,contact,age;
    String nameString,contactString,ageString;
    Button insert,update,delete,view;
    DBHelper Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        age=findViewById(R.id.age);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        view=findViewById(R.id.view);
        delete=findViewById(R.id.delete);
        Db=new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString=name.getText().toString();
                contactString=contact.getText().toString();
                ageString=age.getText().toString();
                if(nameString.length()>0 && contactString.length()>0 && ageString.length()>0){
                    if(Db.insertData(nameString,contactString,ageString)==true)
                        Toast.makeText(MainActivity.this, "New Record Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this,"Insertion Failed",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Data Missing",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString();
                contactString = contact.getText().toString();
                ageString = age.getText().toString();
                if(nameString.length()>0 && contactString.length()>0 && ageString.length()>0){
                    if (Db.updateData(nameString, contactString, ageString) == true)
                        Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Data Missing",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString();
                if(nameString.length()>0){
                    if (Db.deleteData(nameString)==true)
                        Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Deletion Failed", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Name Missing", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result=Db.viewData();
                if(result.getCount()==0)
                    Toast.makeText(MainActivity.this,"No Record Exist",Toast.LENGTH_LONG).show();
                else {
                    StringBuffer buffer = new StringBuffer();
                    while(result.moveToNext()){
                        buffer.append("Name :"+result.getString(0)+"\n");
                        buffer.append("Contact :"+result.getString(1)+"\n");
                        buffer.append("Age :"+result.getString(2)+"\n\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Data");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });
    }
}