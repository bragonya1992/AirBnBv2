package com.example.brayany.airbnb.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.brayany.airbnb.R;
import com.example.brayany.airbnb.interfaces.OnClickItem;
import com.example.brayany.airbnb.retrofit.SearchResult;
import com.example.brayany.airbnb.storage.database;

/**
 * Created by brayany on 11/9/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<SearchResult> SearchResultList;
    private Context mContext;
    OnClickItem listener;

    public MyRecyclerViewAdapter(Context context, List<SearchResult> SearchResultList, OnClickItem listener) {
        this.SearchResultList = SearchResultList;
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, int i) {

        //Render image using Picasso library
        final SearchResult actualSearch = SearchResultList.get(i);
        if (!TextUtils.isEmpty(actualSearch.getListing().getPictureUrl())) {
            Picasso.with(mContext)
                    .load(actualSearch.getListing().getPictureUrl())
                    .into(customViewHolder.background);
        }

        //Setting text view title
        customViewHolder.name.setText(actualSearch.getListing().getName());
        customViewHolder.price.setText(actualSearch.getPricingQuote().getLocalizedCurrency()+" "+actualSearch.getPricingQuote().getNightlyPrice());
        customViewHolder.type.setText(actualSearch.getListing().getPropertyType());
        customViewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(actualSearch.getListing().getLat(),actualSearch.getListing().getLng(),actualSearch.getPricingQuote().getLocalizedCurrency()+" "+actualSearch.getPricingQuote().getNightlyPrice().toString());
                }
            }
        });

        if(database.isInFav(actualSearch.getListing().getName(),customViewHolder.getView().getContext())){
            customViewHolder.fav.setBackgroundResource(R.drawable.ic_star_black_24dp);
        }
        customViewHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    if(!database.isInFav(actualSearch.getListing().getName(),customViewHolder.getView().getContext())) {
                        listener.addFav(actualSearch);
                        customViewHolder.fav.setBackgroundResource(R.drawable.ic_star_black_24dp);
                    }else{
                        listener.deleteFav(actualSearch.getListing().getName());
                        customViewHolder.fav.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != SearchResultList ? SearchResultList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView background;
        protected TextView name;
        protected TextView type;
        protected TextView price;
        protected ImageView fav;
        View view;

        public CustomViewHolder(View view) {
            super(view);
            this.background =  view.findViewById(R.id.backgroud);
            this.name =  view.findViewById(R.id.name);
            this.type =  view.findViewById(R.id.type);
            this.price =  view.findViewById(R.id.price);
            this.fav =  view.findViewById(R.id.fav);
            this.view = view;
        }

        public View getView(){
            return view;
        }
    }
}