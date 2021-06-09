package edu.ntut.finalproject.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Item;
import edu.ntut.finalproject.views.ItemDetailsActivity;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemViewHolder> implements Filterable {

    private final Context context;

    private ArrayList<Item> itemArray;
    private ArrayList<Item> filteredItemArray;

    public SearchAdapter(Context context) {
        this.context = context;

        Item item = new Item();

        try {
            this.itemArray = item.getItems();
            this.filteredItemArray = itemArray;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();
                if (filterPattern.isEmpty())
                    filteredItemArray = itemArray;
                else {
                    ArrayList<Item> filteredItem = new ArrayList<>();
                    for (Item item: itemArray) {
                        if (item.getTitle().toLowerCase().contains(filterPattern) || item.getDescription().toLowerCase().contains(filterPattern))
                            filteredItem.add(item);
                    }
                    filteredItemArray = filteredItem;
                }

                FilterResults results = new FilterResults();
                results.values = filteredItemArray;
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItemArray = (ArrayList<Item>) results.values;
                notifyDataSetChanged();
            }
        };
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
            Item item = filteredItemArray.get((getAdapterPosition()));
            Intent itemDetail = new Intent(v.getContext(), ItemDetailsActivity.class);

            itemDetail.putExtra("TITLE", item.getTitle());
            itemDetail.putExtra("DESC", item.getDescription());
            itemDetail.putExtra("PRICE", String.valueOf(item.getPrice()));
            itemDetail.putExtra("SELLER", item.getUid());

            context.startActivity(itemDetail);
        }

        public void bindTo(@NonNull Item item) {
            searchItemTitle.setText(item.getTitle());
            searchItemInfo.setText(item.getDescription());
        }
    }
}
