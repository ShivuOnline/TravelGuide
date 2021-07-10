package com.security.travelguide.views.hillstations;

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

public class HillStationMainAdapter extends RecyclerView.Adapter<HillStationMainAdapter.HillStationAdapterViewHolder> {

    private static final String TAG = HillStationMainAdapter.class.getSimpleName();
    /**
     * ArrayList of type PlaceItem
     */
    private List<PlaceItem> hillStationsMainItemList;
    private Context context;

    private PlaceItemClickListener listener;
    // endregion

    public HillStationMainAdapter(Context context, List<PlaceItem> hillStationsMainItemList, PlaceItemClickListener listener) {
        this.context = context;
        this.hillStationsMainItemList = hillStationsMainItemList;
        this.listener = listener;
    }

    @Override
    public HillStationAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places_main, parent, false);
        return new HillStationAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HillStationAdapterViewHolder holder, int position) {
        try {
            if (hillStationsMainItemList.size() > 0) {
                holder.setItem(hillStationsMainItemList.get(position));
                PlaceItem hillStationPlaceItem = hillStationsMainItemList.get(position);
                if (hillStationPlaceItem != null) {
                    holder.textTitle.setText(hillStationPlaceItem.getPlaceName());

                    holder.imageIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.hillStationsPlaceItemClicked(position, holder.imageIcon,holder.textTitle, hillStationPlaceItem);
                        }
                    });

                    holder.textTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.hillStationsPlaceItemClicked(position, holder.imageIcon,holder.textTitle, hillStationPlaceItem);
                        }
                    });

                    holder.hillStationPlaceItemCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.hillStationsPlaceItemClicked(position, holder.imageIcon,holder.textTitle, hillStationPlaceItem);
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
        return hillStationsMainItemList.size();
    }

    public interface PlaceItemClickListener {
        void hillStationsPlaceItemClicked(int position, ImageView imagePlace,TextView textPlaceHeader, PlaceItem hillStationPlaceItem);
    }

    static class HillStationAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        PlaceItem hillStationPlaceItem;
        ImageView imageIcon;
        TextView textTitle;
        CardView hillStationPlaceItemCardView;

        HillStationAdapterViewHolder(View itemView) {
            super(itemView);

            // Image View
            imageIcon = itemView.findViewById(R.id.item_icon);
            // Text View
            textTitle = itemView.findViewById(R.id.item_title);
            // Card View
            hillStationPlaceItemCardView = itemView.findViewById(R.id.places_item_cardview);
        }

        public void setItem(PlaceItem item) {
            hillStationPlaceItem = item;

            Glide.with(itemView)
                    .load(hillStationPlaceItem.getPlaceImageDrawable())
                    .fitCenter()
                    .centerCrop()
                    .into(imageIcon);
        }

        @Override
        public void onClick(View v) {
        }
    }


}
