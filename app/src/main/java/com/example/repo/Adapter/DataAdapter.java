package com.example.repo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repo.R;
import com.example.repo.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.BaseViewHolder> {
    private static final String TAG = "Adapter";
    private List<DataModel> dataList;
    DataAdapter dataAdapter;
    public DataAdapter(List<DataModel> dataList) {
        this.dataList = dataList;
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row_items, parent, false));
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getItemCount() {
        if (dataList != null && dataList.size() > 0) {
            return dataList.size();
        } else {
            return 0;
        }
    }

    public void setData(ArrayList<DataModel> dataModels) {
       dataList = dataModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {
        ImageView iv_square,iv_color;
        TextView tv_square,tv_title,tv_desc,tv_lang,tv_num;
        RatingBar ratingBar;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_square = itemView.findViewById(R.id.iv_square);
            tv_square = itemView.findViewById(R.id.tv_square);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            iv_color = itemView.findViewById(R.id.iv_color);
            tv_lang = itemView.findViewById(R.id.tv_lang);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            tv_num = itemView.findViewById(R.id.tv_num);
        }
        protected void clear() {
            iv_square.setImageResource(android.R.color.transparent);
            tv_square.setText("");
            tv_title.setText("");
            tv_desc.setText("");

            tv_lang.setText("");
//            ratingBar.setRating(0);
            tv_num.setText("");
        }
        public void onBind(int position) {
            super.onBind(position);
            final   DataModel dataModel = dataList.get(position);

            iv_square.setImageResource(R.drawable.square_icon);
            tv_square.setText("square");

            tv_title.setText(dataModel.getTitle());


            tv_desc.setText(dataModel.getDescription() );
            iv_color.setImageResource(R.drawable.bg_circle);


            tv_lang.setText(dataModel.getLanguage());

            tv_num.setText(dataModel.getRating());


        }
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        private int mCurrentPosition;
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
        protected abstract void clear();
        public void onBind(int position) {
            mCurrentPosition = position;
            clear();
        }
        public int getCurrentPosition() {
            return mCurrentPosition;
        }
    }
}
