package com.security.travelguide.views.religious;

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

public class ReligiousMainAdapter extends RecyclerView.Adapter<ReligiousMainAdapter.ReligiousAdapterViewHolder> {

    private static final String TAG = ReligiousMainAdapter.class.getSimpleName();
    /**
     * ArrayList of type PlaceItem
     */
    private List<PlaceItem> religiousMainItemList;
    private Context context;

    private PlaceItemClickListener listener;
    // endregion

    public ReligiousMainAdapter(Context context, List<PlaceItem> religiousMainItemList, PlaceItemClickListener listener) {
        this.context = context;
        this.religiousMainItemList = religiousMainItemList;
        this.listener = listener;
    }

    @Override
    public ReligiousAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places_main, parent, false);
        return new ReligiousAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ReligiousAdapterViewHolder holder, int position) {
        try {
            if (religiousMainItemList.size() > 0) {
                holder.setItem(religiousMainItemList.get(position));
                PlaceItem religiousPlaceItem = religiousMainItemList.get(position);
                if (religiousPlaceItem != null) {
                    holder.textTitle.setText(religiousPlaceItem.getPlaceName());

                    holder.imageIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.religiousPlaceItemClicked(position, holder.imageIcon,holder.textTitle, religiousPlaceItem);
                        }
                    });

                    holder.textTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.religiousPlaceItemClicked(position, holder.imageIcon,holder.textTitle, religiousPlaceItem);
                        }
                    });

                    holder.religiousPlaceItemCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.religiousPlaceItemClicked(position, holder.imageIcon,holder.textTitle, religiousPlaceItem);
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
        return religiousMainItemList.size();
    }

    public interface PlaceItemClickListener {
        void religiousPlaceItemClicked(int position, ImageView imagePlace,TextView textPlaceHeader, PlaceItem religiousPlaceItem);
    }

    static class ReligiousAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        PlaceItem religiousPlaceItem;
        ImageView imageIcon;
        TextView textTitle;
        CardView religiousPlaceItemCardView;

        ReligiousAdapterViewHolder(View itemView) {
            super(itemView);

            // Image View
            imageIcon = itemView.findViewById(R.id.item_icon);
            // Text View
            textTitle = itemView.findViewById(R.id.item_title);
            // Card View
            religiousPlaceItemCardView = itemView.findViewById(R.id.places_item_cardview);
        }

        public void setItem(PlaceItem item) {
            religiousPlaceItem = item;

            Glide.with(itemView)
                    .load(religiousPlaceItem.getPlaceImageDrawable())
                    .fitCenter()
                    .centerCrop()
                    .into(imageIcon);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
