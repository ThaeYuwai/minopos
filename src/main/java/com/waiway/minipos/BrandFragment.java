package com.waiway.minipos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.waiway.minipos.DAO.BrandDAO;
import com.waiway.minipos.DAO.ColorDAO;
import com.waiway.minipos.Model.BrandModel;
import com.waiway.minipos.Model.ColorModel;
import com.waiway.minipos.util.ColorAdapter;
import com.waiway.minipos.util.ListAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrandFragment extends Fragment {

    View myView;
    BrandDAO brandDAO;
    ColorDAO colorDAO;
    public static TextView selectedColor;         ////display
    public static int selectedColorId;    /// save
    public RecyclerView rcColorData,rcList;
    public Button btnSave,btnCancel;
    public EditText name;
    public BrandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_brand, container, false);
        initializeUI();
        SaveModel();
        LoadData();
        LoadColor();
        return myView;
    }


    public void initializeUI()
    {
        rcColorData = myView.findViewById(R.id.color_item);
        rcList = myView.findViewById(R.id.color_View);
        btnSave = myView.findViewById(R.id.btn_save);
        btnCancel = myView.findViewById(R.id.btn_cancel);
        selectedColor = myView.findViewById(R.id.selectedcolor);
        colorDAO = new ColorDAO(getContext());
        brandDAO = new BrandDAO(getContext());
        name = myView.findViewById(R.id.name);
    }

    public void SaveModel()
    {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrandModel model = new BrandModel();
                model.Name = name.getText().toString().trim();
                model.ColorId = selectedColorId;
                brandDAO.saveModel(model);
                name.setText(" ");
            }
        });
    }

    public void LoadData()
    {
        ArrayList<BrandModel> brandModels = brandDAO.getModels();
        ListAdapter adapter = new ListAdapter(brandModels.toArray(),getContext(),"Brand         ");
        rcList.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcList.setLayoutManager(lm);
    }

    public void LoadColor()
    {
        ColorAdapter.callingFragment="Brand";
        ArrayList<ColorModel> colorModels = colorDAO.getAllColor();
        ColorAdapter adapter =new ColorAdapter(colorModels);
        rcColorData.setAdapter(adapter);
        GridLayoutManager gm = new GridLayoutManager(getContext(),3);
        rcColorData.setLayoutManager(gm);
    }


}
