package com.codeanthem.travelworld.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeanthem.travelworld.R;
import com.codeanthem.travelworld.home.model.DestinationModel;
import com.codeanthem.travelworld.home.viewmodel.SearchViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class DestinationAdaptor extends RecyclerView.Adapter {

    List<DestinationModel> list;

    public DestinationAdaptor(List<DestinationModel> list){
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_info, parent, false);

        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DestinationViewHolder myHolder = (DestinationViewHolder) holder;

        // set data
        DestinationModel model = list.get(position);

        if(model.getCategory() == SearchViewModel.CATEGORY_REDEEM_POINTS){
            myHolder.tvPrice.setVisibility(View.INVISIBLE);
            myHolder.tvCashBackOffer.setVisibility(View.GONE);
        }
        else if(model.getCategory() == SearchViewModel.CATEGORY_SUMMER_DEALS){
            myHolder.tvRedeemOffer.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DestinationViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPreviewImage;
        MaterialTextView tvName;
        MaterialTextView tvPlace;
        MaterialTextView tvStarRating;
        MaterialTextView tvRating;
        MaterialTextView tvRatingText;
        MaterialTextView tvPrice;
        MaterialTextView tvCashBackOffer;
        MaterialTextView tvRedeemOffer;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPreviewImage = itemView.findViewById(R.id.ivPreviewImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvStarRating = itemView.findViewById(R.id.tvStarRating);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvRatingText = itemView.findViewById(R.id.tvRatingText);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCashBackOffer = itemView.findViewById(R.id.tvCashbackOffer);
            tvRedeemOffer = itemView.findViewById(R.id.tvRedeemOffer);

        }
    }
}
