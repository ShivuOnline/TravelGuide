package com.security.travelguide.views.gardens;

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

public class GardensMainAdapter extends RecyclerView.Adapter<GardensMainAdapter.GardensAdapterViewHolder> {

    private static final String TAG = GardensMainAdapter.class.getSimpleName();
    /**
     * ArrayList of type PlaceItem
     */
    private List<PlaceItem> beachesMainItemList;
    private Context context;

    private PlaceItemClickListener listener;
    // endregion

    public GardensMainAdapter(Context context, List<PlaceItem> beachesMainItemList, PlaceItemClickListener listener) {
        this.context = context;
        this.beachesMainItemList = beachesMainItemList;
        this.listener = listener;
    }

    @Override
    public GardensMainAdapter.GardensAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places_main, parent, false);
        return new GardensMainAdapter.GardensAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final GardensMainAdapter.GardensAdapterViewHolder holder, int position) {
        try {
            if (beachesMainItemList.size() > 0) {
                holder.setItem(beachesMainItemList.get(position));
                PlaceItem gardensPlaceItem = beachesMainItemList.get(position);
                if (gardensPlaceItem != null) {
                    holder.textTitle.setText(gardensPlaceItem.getPlaceName());

                    holder.imageIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.gardensPlaceItemClicked(position,holder.imageIcon,holder.textTitle, gardensPlaceItem);
                        }
                    });

                    holder.textTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.gardensPlaceItemClicked(position,holder.imageIcon,holder.textTitle, gardensPlaceItem);
                        }
                    });

                    holder.gardensPlaceItemCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.gardensPlaceItemClicked(position,holder.imageIcon,holder.textTitle, gardensPlaceItem);
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
        void gardensPlaceItemClicked(int position,ImageView imagePlace, TextView textPlaceHeader,PlaceItem gardensPlaceItem);
    }

    static class GardensAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        PlaceItem gardensPlaceItem;
        ImageView imageIcon;
        TextView textTitle;
        CardView gardensPlaceItemCardView;

        GardensAdapterViewHolder(View itemView) {
            super(itemView);

            // Image View
            imageIcon = itemView.findViewById(R.id.item_icon);
            // Text View
            textTitle = itemView.findViewById(R.id.item_title);
            // Card View
            gardensPlaceItemCardView = itemView.findViewById(R.id.places_item_cardview);
        }

        public void setItem(PlaceItem item) {
            gardensPlaceItem = item;

            Glide.with(itemView)
                    .load(gardensPlaceItem.getPlaceImageDrawable())
                    .fitCenter()
                    .centerCrop()
                    .into(imageIcon);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
