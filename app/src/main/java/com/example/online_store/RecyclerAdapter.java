package com.example.online_store;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final ProductsList mList;
    public static final String ITEMPOSITIONTAG = "RITEMPOSITIONTAG";


    public RecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mList= ProductsList.getInstance();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mProductName;
        public final TextView mProductDescription;
        private final ImageView mPicture;
        private int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mProductName = (TextView) itemView.findViewById(R.id.product_profile_name);
            mProductDescription = (TextView) itemView.findViewById(R.id.product_profile_desc);
            mPicture = (ImageView) itemView.findViewById(R.id.product_profile_picture_nav);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ProductActivity.class);
                intent.putExtra(RecyclerAdapter.ITEMPOSITIONTAG,mCurrentPosition);
                mContext.startActivity(intent);
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mProductName.setText(mList.getList().get(position).getProductName());
        holder.mProductDescription.setText(mList.getList().get(position).getProductDescription());
        holder.mPicture.setImageResource(mList.getList().get(position).getProductPicture());
        holder.mCurrentPosition=position;
    }

    @Override
    public int getItemCount() {
        return mList.getList().size();
    }
}
