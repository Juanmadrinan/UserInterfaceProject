package com.example.userinterfaceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkInsertData = DB.insertUserData(nameTXT, contactTXT, dobTXT);
                if(checkInsertData==true){
                    Toast.makeText(MainActivity.this, "Creado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Intenta de nuevo Crear", Toast.LENGTH_SHORT).show();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkUpdateData = DB.updateUserData(nameTXT, contactTXT, dobTXT);
                if(checkUpdateData==true){
                    Toast.makeText(MainActivity.this, "Actualizado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Intenta de nuevo Actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                Boolean checkDeleteData = DB.deleteData(nameTXT);
                if(checkDeleteData==true){
                    Toast.makeText(MainActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Intenta de nuevo Eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No existe", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nombre: "+res.getString(0)+"\n");
                    buffer.append("Contacto: "+res.getString(1)+"\n");
                    buffer.append("Fecha de nacimiento: "+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}