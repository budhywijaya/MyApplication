package com.app.budi.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Item> itemlist;


    public ItemAdapter(Context context, int layout, ArrayList<Item> itemlist) {
        this.context = context;
        this.layout = layout;
        this.itemlist = itemlist;
    }


    @Override
    public int getCount() {
        return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView namatxt, lkstxt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.namatxt = (TextView) row.findViewById(R.id.namatxt);
            holder.lkstxt = (TextView) row.findViewById(R.id.lkstxt);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);

        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = itemlist.get(position);

        holder.namatxt.setText(item.getNama());
        holder.lkstxt.setText(item.getLokasi());


        byte[] itemImage = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return null;
    }

}
