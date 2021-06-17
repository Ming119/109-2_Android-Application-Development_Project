package edu.ntut.finalproject.adapters;

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
import edu.ntut.finalproject.activities.ItemDetailsActivity;
import edu.ntut.finalproject.util;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final Context context;

    private ArrayList<Item> itemArray;

    public ItemAdapter(Context context, ArrayList<Item> itemArray) {
        this.context = context;
        this.itemArray = itemArray;
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

    public void setItemArray(ArrayList<Item> itemArray) {
        this.itemArray = itemArray;
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

            itemDetail.putExtra(util.ID, item.getID());
            itemDetail.putExtra(util.TITLE, item.getTitle());
            itemDetail.putExtra(util.DESC, item.getDescription());
            itemDetail.putExtra(util.PRICE, String.valueOf(item.getPrice()));
            itemDetail.putExtra(util.SELLER, item.getUid());

            context.startActivity(itemDetail);
        }

        public void bindTo(@NonNull Item item) {
            itemImage.setImageResource(R.drawable.ntut);
            itemTitle.setText(item.getTitle());
            itemInfo.setText(item.getDescription());
        }
    }


}
