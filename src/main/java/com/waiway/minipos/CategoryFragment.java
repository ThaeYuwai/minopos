package com.waiway.minipos;


import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.waiway.minipos.DAO.CategoryDAO;
import com.waiway.minipos.DAO.ColorDAO;
import com.waiway.minipos.Model.CategoryModel;
import com.waiway.minipos.Model.ColorModel;
import com.waiway.minipos.util.ColorAdapter;
import com.waiway.minipos.util.ListAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    public  static  TextView selecedcolor;
    public static int selectedColorId = 1;  /// database ka 1 ka sa

    public  CategoryFragment() {
        // Required empty public constructor
    }

    RecyclerView rcView;
    CategoryDAO categoryDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_category, container, false);

        rcView = myView.findViewById(R.id.rc_View);         // 1
        selecedcolor = myView.findViewById(R.id.selectedcolor);
        RecyclerView rc = myView.findViewById(R.id.color_item); //////// 2.color pya phot
        categoryDAO = new CategoryDAO(getContext());
        ColorDAO dao = new ColorDAO(getContext());
        ColorModel firstColor = new ColorModel();
        int colorcode = Color.argb(255,firstColor.ColorRed,firstColor.ColorGreen,firstColor.ColorBlue);
        selecedcolor.setBackgroundColor(colorcode);

        ColorAdapter.callingFragment = "Category";
        ColorAdapter adapter = new ColorAdapter(dao.getAllColor());
        rc.setAdapter(adapter);
        final GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        rc.setLayoutManager(manager);


        /////////////////
        final EditText name = myView.findViewById(R.id.name);
        Button save = myView.findViewById(R.id.catego_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryModel model = new CategoryModel();
                model.Name = name.getText().toString().trim();
                model.ColorId = selectedColorId;
                categoryDAO.saveModel(model);
                name.setText(" ");
                LoadData();
            }
        });

        LoadData();
        return myView;
    }

    public void LoadData()
    {
        ArrayList<CategoryModel> categoryModels = categoryDAO.getModels();
        ListAdapter adapter = new ListAdapter(categoryModels.toArray(),getContext(),"Category");
        rcView.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);

    }

}
