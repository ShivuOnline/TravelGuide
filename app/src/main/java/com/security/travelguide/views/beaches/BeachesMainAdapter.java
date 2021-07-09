package com.security.travelguide.views.beaches;

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

public class BeachesMainAdapter extends RecyclerView.Adapter<BeachesMainAdapter.BeachesAdapterViewHolder> {

    private static final String TAG = BeachesMainAdapter.class.getSimpleName();
    /**
     * ArrayList of type PlaceItem
     */
    private List<PlaceItem> beachesMainItemList;
    private Context context;

    private PlaceItemClickListener listener;
    // endregion

    public BeachesMainAdapter(Context context, List<PlaceItem> beachesMainItemList, PlaceItemClickListener listener) {
        this.context = context;
        this.beachesMainItemList = beachesMainItemList;
        this.listener = listener;
    }

    @Override
    public BeachesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places_main, parent, false);
        return new BeachesAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final BeachesAdapterViewHolder holder, int position) {
        try {
            if (beachesMainItemList.size() > 0) {
                holder.setItem(beachesMainItemList.get(position));
                PlaceItem beachPlaceItem = beachesMainItemList.get(position);
                if (beachPlaceItem != null) {
                    holder.textTitle.setText(beachPlaceItem.getPlaceName());

                    holder.imageIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.beachPlaceItemClicked(position,holder.imageIcon,holder.textTitle, beachPlaceItem);
                        }
                    });

                    holder.textTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.beachPlaceItemClicked(position,holder.imageIcon,holder.textTitle, beachPlaceItem);
                        }
                    });

                    holder.beachPlaceItemCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.beachPlaceItemClicked(position,holder.imageIcon, holder.textTitle, beachPlaceItem);
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
        return beachesMainItemList.size();
    }

    public interface PlaceItemClickListener {
        void beachPlaceItemClicked(int position,ImageView imagePlace, TextView textPlaceHeader, PlaceItem beachPlaceItem);
    }

    static class BeachesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        PlaceItem beachPlaceItem;
        ImageView imageIcon;
        TextView textTitle;
        CardView beachPlaceItemCardView;

        BeachesAdapterViewHolder(View itemView) {
            super(itemView);

            // Image View
            imageIcon = itemView.findViewById(R.id.item_icon);
            // Text View
            textTitle = itemView.findViewById(R.id.item_title);
            // Card View
            beachPlaceItemCardView = itemView.findViewById(R.id.places_item_cardview);
        }

        public void setItem(PlaceItem item) {
            beachPlaceItem = item;

            Glide.with(itemView)
                    .load(beachPlaceItem.getPlaceImageDrawable())
                    .fitCenter()
                    .centerCrop()
                    .into(imageIcon);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
