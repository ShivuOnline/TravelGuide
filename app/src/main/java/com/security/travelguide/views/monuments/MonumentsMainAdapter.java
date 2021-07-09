package com.security.travelguide.views.monuments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.security.travelguide.R;
import com.security.travelguide.model.PlaceItem;

import java.util.List;

public class MonumentsMainAdapter extends RecyclerView.Adapter<MonumentsMainAdapter.MonumentsAdapterViewHolder> {

    private static final String TAG = MonumentsMainAdapter.class.getSimpleName();
    /**
     * ArrayList of type PlaceItem
     */
    private List<PlaceItem> monumentsMainItemList;
    private Context context;

    private PlaceItemClickListener listener;
    // endregion

    public MonumentsMainAdapter(Context context, List<PlaceItem> monumentsMainItemList, PlaceItemClickListener listener) {
        this.context = context;
        this.monumentsMainItemList = monumentsMainItemList;
        this.listener = listener;
    }

    @Override
    public MonumentsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places_main, parent, false);
        return new MonumentsAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MonumentsAdapterViewHolder holder, int position) {
        try {
            if (monumentsMainItemList.size() > 0) {
                holder.setItem(monumentsMainItemList.get(position));
                PlaceItem monumentsPlaceItem = monumentsMainItemList.get(position);
                if (monumentsPlaceItem != null) {
                    holder.textTitle.setText(monumentsPlaceItem.getPlaceName());

                    holder.imageIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.monumentsPlaceItemClicked(position, holder.imageIcon, holder.textTitle, monumentsPlaceItem);
                        }
                    });

                    holder.textTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.monumentsPlaceItemClicked(position, holder.imageIcon, holder.textTitle, monumentsPlaceItem);
                        }
                    });

                    holder.monumentsPlaceItemCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.monumentsPlaceItemClicked(position, holder.imageIcon, holder.textTitle, monumentsPlaceItem);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return monumentsMainItemList.size();
    }

    public interface PlaceItemClickListener {
        void monumentsPlaceItemClicked(int position, ImageView imagePlace, TextView textPlaceHeader, PlaceItem monumentsPlaceItem);
    }

    static class MonumentsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        PlaceItem monumentsPlaceItem;
        ImageView imageIcon;
        TextView textTitle;
        CardView monumentsPlaceItemCardView;

        MonumentsAdapterViewHolder(View itemView) {
            super(itemView);

            // Image View
            imageIcon = itemView.findViewById(R.id.item_icon);
            // Text View
            textTitle = itemView.findViewById(R.id.item_title);
            // Card View
            monumentsPlaceItemCardView = itemView.findViewById(R.id.places_item_cardview);
        }

        public void setItem(PlaceItem item) {
            monumentsPlaceItem = item;

            Glide.with(itemView)
                    .load(monumentsPlaceItem.getPlaceImageDrawable())
                    .fitCenter()
                    .centerCrop()
                    .into(imageIcon);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
