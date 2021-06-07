package edu.ntut.finalproject.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Item;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemViewHolder> {

    private final Context context;

    private ArrayList<Item> itemArray;
    private ArrayList<Item> filteredItemArray;

    public SearchAdapter(Context context, ArrayList<Item> itemArray) {
        this.context = context;
        this.itemArray = itemArray;
        this.filteredItemArray = itemArray;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (filteredItemArray == null) return;
        holder.bindTo(filteredItemArray.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredItemArray.size();
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView searchItemTitle;
        private TextView searchItemInfo;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            searchItemTitle = itemView.findViewById(R.id.search_item_title);
            searchItemInfo  = itemView.findViewById(R.id.search_item_info);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindTo(@NonNull Item item) {
            searchItemTitle.setText(item.getTitle());
            searchItemInfo.setText(item.getDescription());
        }
    }
}
