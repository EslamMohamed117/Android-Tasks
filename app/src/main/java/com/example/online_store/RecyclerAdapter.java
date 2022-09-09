package com.example.online_store;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_store.database.DatabaseContract;
import com.example.online_store.database.DatabaseContract.ProductInfo;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    //private final ProductsList mList;
    public static final String ITEMPOSITIONTAG = "RITEMPOSITIONTAG";
    private Cursor mCursor;
    private int mProductIDPos;
    private int mProductNamePos;
    private int mProductPricePos;
    private int mProductDescriptionPos;
    private int mProductCountPos;
    private int mProductPicturePos;


    public RecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        //mList= ProductsList.getInstance();
        mCursor = cursor;
        populateColumnsPosition();
    }

    private void populateColumnsPosition() {
        if(mCursor == null)
            return;
        mProductIDPos = mCursor.getColumnIndex(ProductInfo.PRODUCT_ID);
        mProductNamePos = mCursor.getColumnIndex(ProductInfo.PRODUCT_NAME);
        mProductPricePos = mCursor.getColumnIndex(ProductInfo.PRODUCT_PRICE);
        mProductDescriptionPos = mCursor.getColumnIndex(ProductInfo.PRODUCT_DESCRIPTION);
        mProductCountPos = mCursor.getColumnIndex(ProductInfo.PRODUCT_COUNT);
        mProductPicturePos = mCursor.getColumnIndex(ProductInfo.PRODUCT_PICTURE);
    }
    public void changeCursor(Cursor cursor)
    {
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        populateColumnsPosition();
        notifyDataSetChanged();
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
        mCursor.moveToPosition(position);
        holder.mProductName.setText(mCursor.getString(mProductNamePos));
        holder.mProductDescription.setText(mCursor.getString(mProductDescriptionPos));
        holder.mPicture.setImageResource(mCursor.getInt(mProductPicturePos));
        holder.mCurrentPosition=position;
    }

    @Override
    public int getItemCount() {
        return mCursor==null ? 0 : mCursor.getCount();
    }
}
