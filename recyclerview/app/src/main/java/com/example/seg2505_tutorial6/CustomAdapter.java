package com.example.seg2505_tutorial6;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String item, int position);
    }

    private final String[] localDataSet;
    private final OnItemClickListener listener;

    /** ViewHolder : garde une référence vers les vues d'une ligne. */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public CustomAdapter(String[] dataSet, OnItemClickListener listener) {
        this.localDataSet = dataSet;
        this.listener = listener;
    }

    // Appelée pour créer une nouvelle ligne (ViewHolder)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Appelée pour associer (lier) les données à une ligne
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final String item = localDataSet[position];
        viewHolder.getTextView().setText(item);
        viewHolder.itemView.setOnClickListener(v -> {
            int pos = viewHolder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                listener.onItemClick(localDataSet[pos], pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}