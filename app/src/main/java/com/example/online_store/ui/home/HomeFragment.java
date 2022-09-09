package com.example.online_store.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_store.RecyclerAdapter;
import com.example.online_store.database.DatabaseContract;
import com.example.online_store.database.DatabaseContract.ProductInfo;
import com.example.online_store.database.DatabaseOpenHelper;
import com.example.online_store.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerAdapter adapter;
    private DatabaseOpenHelper mOpenHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mOpenHelper = new DatabaseOpenHelper(getContext());
        final RecyclerView mList = binding.listItems;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mList.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(getContext(), null);
        mList.setAdapter(adapter);
        return root;
    }

    @Override
    public void onResume() {
        loadProducts();
        super.onResume();
    }

    private void loadProducts() {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final String[] columns = {ProductInfo.PRODUCT_NAME, ProductInfo.PRODUCT_DESCRIPTION, ProductInfo.PRODUCT_PICTURE};
        Cursor cursor = db.query(ProductInfo.TABLE_NAME, columns, null, null, null, null, null);
        adapter.changeCursor(cursor);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        mOpenHelper.close();
        super.onDestroyView();
    }
}