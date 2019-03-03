package com.example.aysenur.ymtproje;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper myDB;

    EditText editName,editSurName,editMarks,editID;
    Button btnkaydet,btnguncelle,btnsil,btngoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.etIsim);
        editSurName = (EditText) findViewById(R.id.etSoyIsim);
        editMarks = (EditText) findViewById(R.id.etNumara);
        editID = (EditText) findViewById(R.id.etID);

        btnkaydet = (Button) findViewById(R.id.btnKaydet);
        btngoster = (Button) findViewById(R.id.btnGoster);
        btnguncelle = (Button) findViewById(R.id.btnGuncelle);
        btnsil = (Button) findViewById(R.id.btnSil);

        AddData();
        viewAll();
        updateData();
        delateData();

    }

    public void AddData(){
        btnkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(editName.getText().toString(),editSurName.getText().toString(),editMarks.getText().toString());

                if (isInserted == true){
                    Toast.makeText(MainActivity.this,"Veri Eklendi",Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(MainActivity.this,"Veri Eklenemedi",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewAll(){
        btngoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();

                if (res.getCount() ==0){
                   showMessage("Hata","Hiçbir veri bulunamadı");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("İsim :"+res.getString(1)+"\n");
                    buffer.append("Soyİsim :"+res.getString(2)+"\n");
                    buffer.append("Numara :"+res.getString(3)+"\n");

                }
                showMessage("Veriler",buffer.toString());

            }
        });
    }

    public void updateData(){
        btnkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateData(
                        editID.getText().toString(),
                        editName.getText().toString(),
                        editSurName.getText().toString(),
                        editMarks.getText().toString()
                );

                if (isUpdate){
                    Toast.makeText(MainActivity.this,"Veri Eklendi",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Veri Eklenemedi",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void  delateData(){
        btnsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDB.deleteData(
                        editID.getText().toString());
                if (deleteRows>0){
                    Toast.makeText(MainActivity.this,"Veri Silindi",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Veri Silinemedi",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
