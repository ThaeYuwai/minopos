package com.waiway.minipos.util;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waiway.minipos.BrandFragment;
import com.waiway.minipos.CategoryFragment;
import com.waiway.minipos.ColorFragment;
import com.waiway.minipos.ItemFragment;
import com.waiway.minipos.Model.ColorModel;
import com.waiway.minipos.R;
import com.waiway.minipos.UnitFragment;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorHolder> {

    ArrayList<ColorModel> colorModels = new ArrayList<>();
    public static String callingFragment="";

    public ColorAdapter(ArrayList<ColorModel> colorModels) {
        this.colorModels = colorModels;
    }

    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coloritem,parent,false);
        ColorHolder colorHolder = new ColorHolder(view);

        return colorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ColorHolder holder, final int position) {
        final ColorModel temp = colorModels.get(position);
        final int colorcode = Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
        holder.coloritem.setBackgroundColor(colorcode);

        holder.coloritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callingFragment.equals("Category"))
                {
                    CategoryFragment.selecedcolor.setBackgroundColor(colorcode);
                    CategoryFragment.selectedColorId=temp.ColorId;
                }
                else if(callingFragment.equals("Brand"))
                {
                    BrandFragment.selectedColor.setBackgroundColor(colorcode);
                    BrandFragment.selectedColorId = temp.ColorId;
                }
                else if(callingFragment.equals("Unit"))
                {
                    UnitFragment.selectedColor.setBackgroundColor(colorcode);
                    UnitFragment.selectedColorId = temp.ColorId;
                }
                else  if(callingFragment.equals("Item"))
                {
                    ItemFragment.selectedId = temp.ColorId;
                    holder.check.setVisibility(View.VISIBLE);
                    ColorFragment.selectedposition = position;
                    ColorFragment.removeCheck();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorModels.size();
    }

    public class ColorHolder extends  RecyclerView.ViewHolder{
        TextView coloritem;
        ImageView check;

        public ColorHolder(@NonNull View itemView) {
            super(itemView);
            coloritem = itemView.findViewById(R.id.coloritem);
            check = itemView.findViewById(R.id.checkcolor);
        }
    }
}
