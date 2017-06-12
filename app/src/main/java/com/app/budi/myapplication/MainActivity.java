package com.app.budi.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    ArrayList<Item> list;
    ListView listView;
    ItemAdapter adapter = null;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new ItemAdapter(this, R.layout.list_items, list);
        listView.setAdapter(adapter);

        Cursor cursor = InputActivity.sqlite.getData("SELECT * FROM LIST ");
        //list.clear();
       while (cursor.moveToNext()){
          int id = cursor.getInt(0);
            String nama = cursor.getString(1);
            String lokasi = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Item(nama, lokasi,image,id));
        }

        adapter.notifyDataSetChanged();
    }
}
