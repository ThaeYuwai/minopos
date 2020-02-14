package com.waiway.minipos.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waiway.minipos.DAO.ColorDAO;
import com.waiway.minipos.Model.BrandModel;
import com.waiway.minipos.Model.CategoryModel;
import com.waiway.minipos.Model.ColorModel;
import com.waiway.minipos.Model.UnitModel;
import com.waiway.minipos.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    ArrayList<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
    ArrayList<BrandModel> brandModels = new ArrayList<BrandModel>();
    ArrayList<UnitModel> unitModels = new ArrayList<UnitModel>();
    Context context; String fragName;
    public ListAdapter(Object[] models, Context context, String fragName) {

        if(fragName.equals("Category"))
        {
            for (Object o : models)
                this.categoryModels.add((CategoryModel)o);
        }
        if(fragName.equals("Brand"))
        {
            for(Object o : models)
                this.brandModels.add((BrandModel)o);
        }
        if(fragName.equals("Unit"))
        {
            for(Object o : models)
                this.unitModels.add((UnitModel)o);
        }
        this.context = context;
        this.fragName = fragName;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        ListHolder holder = new ListHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {

        if(fragName.equals("Category"))
        {
            holder.sr.setText(categoryModels.get(position).Id+".");
            holder.name.setText(categoryModels.get(position).Name);
            ColorDAO colorDAO = new ColorDAO(context);
            ColorModel model = colorDAO.getColorById(categoryModels.get(position).ColorId);
            int colorcode = Color.argb(255,model.ColorRed,model.ColorGreen,model.ColorBlue);
            holder.colorItem.setBackgroundColor(colorcode);
        }
        else  if(fragName.equals("Brand"))
        {
            holder.sr.setText(brandModels.get(position).Id+".");
            holder.name.setText(brandModels.get(position).Name);
            ColorDAO colorDAO = new ColorDAO(context);
            ColorModel model = colorDAO.getColorById(brandModels.get(position).ColorId);
            int colorcode = Color.argb(255,model.ColorRed,model.ColorGreen,model.ColorBlue);
            holder.colorItem.setBackgroundColor(colorcode);
        }
        else  if(fragName.equals("Unit"))
        {
            holder.sr.setText(unitModels.get(position).Id+".");
            holder.name.setText(unitModels.get(position).Name);
            ColorDAO colorDAO = new ColorDAO(context);
            ColorModel model = colorDAO.getColorById(unitModels.get(position).ColorId);
            int colorcode = Color.argb(255,model.ColorRed,model.ColorGreen,model.ColorBlue);
            holder.colorItem.setBackgroundColor(colorcode);
        }

    }

    @Override
    public int getItemCount() {
        if(fragName.equals("Category"))
            return categoryModels.size();
        else if(fragName.equals("Brand"))
            return brandModels.size();
        else  if (fragName.equals("unit"))
            return unitModels.size();
        return 0;
    }

    public class ListHolder extends  RecyclerView.ViewHolder{
        TextView sr,name,colorItem;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            sr = itemView.findViewById(R.id.sr);
            name = itemView.findViewById(R.id.name);
            colorItem = itemView.findViewById(R.id.coloritem);
        }
    }
}
