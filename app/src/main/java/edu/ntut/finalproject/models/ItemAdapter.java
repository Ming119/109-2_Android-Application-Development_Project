//package edu.ntut.finalproject.models;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import edu.ntut.finalproject.R;
//
//public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
//
//    private final LayoutInflater inflater;
//
//    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        public final TextView subject;
//        public final TextView title;
//        public final TextView price;
//        final ItemAdapter itemAdapter;
//
//        public ItemViewHolder(@NonNull View itemView, ItemAdapter adapter) {
//            super(itemView);
//
//            subject = itemView.findViewById(R.id.item_subject);
//            title = itemView.findViewById(R.id.item_title);
//            price = itemView.findViewById(R.id.item_price);
//
//            this.itemAdapter = adapter;
//
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            int pos = getLayoutPosition();
//
//        }
//    }
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = inflater.inflate(R.layout.items_main, parent, false);
//        return new ItemViewHolder(itemView, this);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        holder.
//    }
//
//    public ItemAdapter(Context context) {
//        inflater = LayoutInflater.from(context);
//    }
//}
