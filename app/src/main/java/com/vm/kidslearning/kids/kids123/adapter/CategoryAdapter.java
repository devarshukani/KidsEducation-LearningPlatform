package com.vm.kidslearning.kids.kids123.adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vm.kidslearning.kids.kids123.R;
import com.vm.kidslearning.kids.kids123.activity.TTestNumber_Activity;
import com.vm.kidslearning.kids.kids123.dbhandler.DatabaseHandler;
import com.vm.kidslearning.kids.kids123.model.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.UserViewHolder> {

    Context context;
    ArrayList<CategoryModel> categoryModel;
    DatabaseHandler dbhandler;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModel){
        this.context = context;
        this.categoryModel = categoryModel;
        dbhandler = new DatabaseHandler(context);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_row_tcategory, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        holder.tvcat.setText(categoryModel.get(position).getCategoryName());
        Bitmap bmp = BitmapFactory.decodeByteArray(categoryModel.get(position).getImg(), 0, categoryModel.get(position).getImg().length);
        holder.ivcat.setImageBitmap(bmp);

        holder.LCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(categoryModel.get(position).getCategoryID());
                Intent in = new Intent(context, TTestNumber_Activity.class);
                in.putExtra("id",s);
                in.putExtra("cat",categoryModel.get(position).getCategoryName());
                context.startActivity(in);
            }

        });

    }

    @Override
    public int getItemCount(){
        return categoryModel.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView tvcat;
        ImageView ivcat;
        LinearLayout LCategory;
        public UserViewHolder(View v)
        {
            super(v);
            ivcat = v.findViewById(R.id.ivcat);
            tvcat = v.findViewById(R.id.tvcat);
            LCategory = v.findViewById(R.id.LCategory);
        }

    }
}
