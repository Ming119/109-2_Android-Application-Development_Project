package edu.ntut.finalproject.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final Context context;

    private ArrayList<Item> itemArray;

    public ItemAdapter(Context context) {
        this.context = context;

        Item item = new Item();

        try {
            this.itemArray = item.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (itemArray == null) return;
        holder.bindTo(itemArray.get(position));
    }

    @Override
    public int getItemCount() {
        if (itemArray == null) return 0;
        return itemArray.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImage;
        private TextView  itemTitle;
        private TextView  itemInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.image_item);
            itemTitle = itemView.findViewById(R.id.text_item_title);
            itemInfo  = itemView.findViewById(R.id.text_item_info);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindTo(@NonNull Item item) {
            itemImage.setImageResource(R.drawable.was);
            itemTitle.setText(item.getTitle());
            itemInfo.setText(item.getDescription());
        }
    }

}
