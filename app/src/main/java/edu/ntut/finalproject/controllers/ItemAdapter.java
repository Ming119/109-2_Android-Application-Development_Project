package edu.ntut.finalproject.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Item;
import edu.ntut.finalproject.views.ItemDetailsActivity;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

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
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (itemArray == null) return;
        holder.bindTo(itemArray.get(position));
    }

    @Override
    public int getItemCount() {
        if (itemArray == null) return 0;
        return itemArray.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView itemImage;
        private final TextView itemTitle;
        private final TextView  itemInfo;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.image_item);
            itemTitle = itemView.findViewById(R.id.text_item_title);
            itemInfo  = itemView.findViewById(R.id.text_item_info);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Item item = itemArray.get((getAdapterPosition()));
            Intent itemDetail = new Intent(context, ItemDetailsActivity.class);

            itemDetail.putExtra("TITLE", item.getTitle());
            itemDetail.putExtra("DESC", item.getDescription());
            itemDetail.putExtra("PRICE", String.valueOf(item.getPrice()));
            itemDetail.putExtra("SELLER", item.getUid());

            context.startActivity(itemDetail);
        }

        public void bindTo(@NonNull Item item) {
            itemImage.setImageResource(R.drawable.was);
            itemTitle.setText(item.getTitle());
            itemInfo.setText(item.getDescription());
        }
    }
}
