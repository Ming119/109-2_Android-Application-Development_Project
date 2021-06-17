package edu.ntut.finalproject.adapters;

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

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.activities.ItemDetailsActivity;
import edu.ntut.finalproject.models.Item;
import edu.ntut.finalproject.util;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemViewHolder> implements Filterable {

    private final Context context;

    private ArrayList<Item> itemArray;
    private ArrayList<Item> filteredItemArray;

    public SearchAdapter(Context context, ArrayList<Item> itemArray) {
        this.context = context;
        this.itemArray = itemArray;
        this.filteredItemArray = this.itemArray;
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
    public int getItemCount() { return filteredItemArray.size(); }

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

    public void setItemArray(ArrayList<Item> itemArray) {
        this.itemArray = itemArray;
        this.filteredItemArray = this.itemArray;
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView searchItemTitle;
        private final TextView searchItemInfo;

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

            itemDetail.putExtra(util.ID, item.getID());
            itemDetail.putExtra(util.TITLE, item.getTitle());
            itemDetail.putExtra(util.DESC, item.getDescription());
            itemDetail.putExtra(util.PRICE, String.valueOf(item.getPrice()));
            itemDetail.putExtra(util.SELLER, item.getUid());

            context.startActivity(itemDetail);
        }

        public void bindTo(@NonNull Item item) {
            searchItemTitle.setText(item.getTitle());
            searchItemInfo.setText(item.getDescription());
        }
    }
}
