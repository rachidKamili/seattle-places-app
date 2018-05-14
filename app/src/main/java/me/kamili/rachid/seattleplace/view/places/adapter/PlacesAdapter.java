package me.kamili.rachid.seattleplace.view.places.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kamili.rachid.seattleplace.R;
import me.kamili.rachid.seattleplace.model.Category;
import me.kamili.rachid.seattleplace.model.Icon;
import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.utils.GeoUtils;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.Holder> {

    private List<Venue> mPlacesList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private OnPlaceClickListener mPlaceClickListener;
    private OnFavoritePlaceClickListener mFavoritePlaceClickListener;


    public PlacesAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @NonNull
    @Override
    public PlacesAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.place_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.Holder holder, int position) {
        holder.bind(mPlacesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlacesList.size();
    }

    public void addPlaces(List<Venue> venueList) {
        mPlacesList.addAll(venueList);
        notifyDataSetChanged();
    }

    public void clearPlaces() {
        mPlacesList.clear();
        notifyDataSetChanged();
    }

    public List<Venue> getAllPlaces() {
        return mPlacesList;
    }

    public void setPlaceClickListener(OnPlaceClickListener listener) {
        mPlaceClickListener = listener;
    }

    public void setFavoritePlaceClickListener(OnFavoritePlaceClickListener favListener) {
        mFavoritePlaceClickListener = favListener;
    }

    public interface OnPlaceClickListener {
        //Item in RV clicked
        void onPlaceClick(Venue place, int position);
    }

    public interface OnFavoritePlaceClickListener {
        //Fav btn in the list is clicked
        void onFavoritePlaceClick(Venue place);

        List<String> getFavoritePlace();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String ICON_PARAM = "bg_88";
        @BindView(R.id.venue_name)
        TextView tvVenueName;
        @BindView(R.id.category_name)
        TextView tvCategoryName;
        @BindView(R.id.distance)
        TextView tvDistance;
        @BindView(R.id.category_icon)
        ImageView ivCategoryIcon;
        @BindView(R.id.btnFav)
        FloatingActionButton btnFav;
        private Context mContext;
        private Venue mPlace;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(Venue place) {
            mPlace = place;
            tvVenueName.setText(place.getName());

            tvDistance.setText(GeoUtils.getDistance(
                    place.getLocation().getLat(), place.getLocation().getLng()
            ) + " miles away");

            //Check if the venue contains a category before loading the icon
            if (place.getCategories() != null && place.getCategories().size() > 0) {
                Category category = place.getCategories().get(0);

                tvCategoryName.setText(category.getName());

                Glide.with(mContext)
                        .load(getImageUrlFromIcon(category.getIcon()))
                        .into(ivCategoryIcon);
            }

            //Set the drawable to the btn
            setImageToBtn(btnFav, place.getId());

            //Fav click Listener
            btnFav.setOnClickListener(v -> {
                mFavoritePlaceClickListener.onFavoritePlaceClick(place);
                setImageToBtn(btnFav, place.getId());
            });

        }

        private void setImageToBtn(FloatingActionButton btn, String id) {
            if (mFavoritePlaceClickListener.getFavoritePlace().contains(id)) {
                btn.setImageResource(R.drawable.ic_favorite_red);
            } else {
                btn.setImageResource(R.drawable.ic_favorite_grey);
            }
        }


        private String getImageUrlFromIcon(Icon icon) {
            return icon.getPrefix() + ICON_PARAM + icon.getSuffix();
        }

        @Override
        public void onClick(View v) {
            if (mPlaceClickListener != null) {
                mPlaceClickListener.onPlaceClick(mPlace, getAdapterPosition());
            }
        }
    }
}
