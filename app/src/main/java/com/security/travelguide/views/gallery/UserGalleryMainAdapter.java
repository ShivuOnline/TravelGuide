package com.security.travelguide.views.gallery;

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
import com.security.travelguide.model.galleryDetails.GalleryUploadMain;

import java.util.List;

public class UserGalleryMainAdapter extends RecyclerView.Adapter<UserGalleryMainAdapter.UserGalleryAdapterViewHolder> {

    private static final String TAG = UserGalleryMainAdapter.class.getSimpleName();
    /**
     * ArrayList of type PlaceItem
     */
    private List<GalleryUploadMain> galleryUploadMainList;
    private Context context;

    private UserGalleryItemClickListener listener;
    // endregion

    public UserGalleryMainAdapter(Context context, List<GalleryUploadMain> galleryUploadMainList, UserGalleryItemClickListener listener) {
        this.context = context;
        this.galleryUploadMainList = galleryUploadMainList;
        this.listener = listener;
    }

    @Override
    public UserGalleryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places_main, parent, false);
        return new UserGalleryAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final UserGalleryAdapterViewHolder holder, int position) {
        try {
            if (galleryUploadMainList.size() > 0) {
                holder.setItem(galleryUploadMainList.get(position));
                GalleryUploadMain galleryUploadMain = galleryUploadMainList.get(position);
                if (galleryUploadMain != null) {
                    holder.textTitle.setText(galleryUploadMain.getPlace());

                    holder.imageIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.userItemItemClicked(position, galleryUploadMain);
                        }
                    });

                    holder.textTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.userItemItemClicked(position, galleryUploadMain);
                        }
                    });

                    holder.galleryItemCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.userItemItemClicked(position, galleryUploadMain);
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
        return galleryUploadMainList.size();
    }

    public interface UserGalleryItemClickListener {
        void userItemItemClicked(int position, GalleryUploadMain galleryUploadMain);
    }

    static class UserGalleryAdapterViewHolder extends RecyclerView.ViewHolder {

        GalleryUploadMain galleryUploadMain;
        ImageView imageIcon;
        TextView textTitle;
        CardView galleryItemCardView;

        UserGalleryAdapterViewHolder(View itemView) {
            super(itemView);

            // Image View
            imageIcon = itemView.findViewById(R.id.item_icon);
            // Text View
            textTitle = itemView.findViewById(R.id.item_title);
            // Card View
            galleryItemCardView = itemView.findViewById(R.id.places_item_cardview);
        }

        public void setItem(GalleryUploadMain item) {
            try {
                galleryUploadMain = item;

                Glide.with(itemView)
                        .load(galleryUploadMain.getPlacePhotoPath())
                        .fitCenter()
                        .centerCrop()
                        .into(imageIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
