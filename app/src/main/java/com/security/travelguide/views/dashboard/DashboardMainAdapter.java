package com.security.travelguide.views.dashboard;

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
import com.security.travelguide.model.DashboardItem;

import java.util.List;

public class DashboardMainAdapter extends RecyclerView.Adapter<DashboardMainAdapter.DashboardAdapterViewHolder> {

    private static final String TAG = DashboardMainAdapter.class.getSimpleName();
    /**
     * ArrayList of type DashboardItem
     */
    private List<DashboardItem> dashboardItemMainList;
    private Context context;

    private DashboardItemClickListener listener;
    // endregion

    public DashboardMainAdapter(Context context, List<DashboardItem> dashboardItemMainList, DashboardItemClickListener listener) {
        this.context = context;
        this.dashboardItemMainList = dashboardItemMainList;
        this.listener = listener;
    }

    @Override
    public DashboardMainAdapter.DashboardAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_main, parent, false);
        return new DashboardMainAdapter.DashboardAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DashboardMainAdapter.DashboardAdapterViewHolder holder, int position) {
        try {
            if (dashboardItemMainList.size() > 0) {
                holder.setItem(dashboardItemMainList.get(position));

                DashboardItem dashboardItem = dashboardItemMainList.get(position);

                if (dashboardItem != null) {
                    holder.textTitle.setText(dashboardItem.getTitle());

                    Glide.with(holder.itemView)
                            .load(dashboardItem.getImageDrawable())
                            .fitCenter()
                            .centerCrop()
                            .into(holder.imageIcon);

                    holder.imageIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.dashboardItemClicked(position, dashboardItem);
                        }
                    });

                    holder.textTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.dashboardItemClicked(position, dashboardItem);
                        }
                    });

                    holder.dashBoardItemCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.dashboardItemClicked(position, dashboardItem);
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
        return dashboardItemMainList.size();
    }

    public interface DashboardItemClickListener {
        void dashboardItemClicked(int position, DashboardItem dashboardItem);
    }

    static class DashboardAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        DashboardItem sliderItem;
        ImageView imageIcon;
        TextView textTitle;
        CardView dashBoardItemCardView;

        DashboardAdapterViewHolder(View itemView) {
            super(itemView);

            // Image View
            imageIcon = itemView.findViewById(R.id.item_icon);
            // Text View
            textTitle = itemView.findViewById(R.id.item_title);
            // Card View
            dashBoardItemCardView = itemView.findViewById(R.id.dashboard_item_cardview);
        }

        public void setItem(DashboardItem item) {
            sliderItem = item;
        }

        @Override
        public void onClick(View v) {
        }
    }
}
