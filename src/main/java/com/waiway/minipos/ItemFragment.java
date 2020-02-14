package com.waiway.minipos;


import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.waiway.minipos.DAO.BrandDAO;
import com.waiway.minipos.DAO.CategoryDAO;
import com.waiway.minipos.DAO.ItemDAO;
import com.waiway.minipos.DAO.UnitDAO;
import com.waiway.minipos.Model.BrandModel;
import com.waiway.minipos.Model.CategoryModel;
import com.waiway.minipos.Model.ItemModel;
import com.waiway.minipos.Model.UnitModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {


    public ItemFragment() {
        // Required empty public constructor
    }

    public static int selectedId;
    View myView;
    Spinner category,brand,unit;
    EditText name,originalprice,saleprice;
    Button btnsave,btncancel;
    CategoryDAO categoryDAO;
    UnitDAO unitDAO;
    BrandDAO brandDAO;
    RecyclerView rcList;
    ItemDAO itemDAO;
    ArrayList<CategoryModel> categoryModels;
    ArrayList<BrandModel> brandModels;
    ArrayList<UnitModel> unitModels;
    RadioButton rbtusecolor,rbtuseimg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_item, container, false);
        InitializeUI();;
        initSpinner();
        saveItem();
        return myView;
    }

    public void InitializeUI()
    {
        rcList = myView.findViewById(R.id.rc_View);
        btnsave = myView.findViewById(R.id.btn_save);
        btncancel = myView.findViewById(R.id.btn_cancel);
        name = myView.findViewById(R.id.name);
        originalprice =  myView.findViewById(R.id.oprice);
        saleprice =  myView.findViewById(R.id.sprice);
        category =  myView.findViewById(R.id.category);
        brand =  myView.findViewById(R.id.brand);
        unit = myView.findViewById(R.id.unit);
        itemDAO = new ItemDAO(getContext());
        categoryDAO = new CategoryDAO(getContext());
        brandDAO = new BrandDAO(getContext());
        unitDAO = new UnitDAO((getContext()));
        rbtusecolor  = myView.findViewById(R.id.rbtusecolor);
        rbtuseimg = myView.findViewById(R.id.rbtuseimage);


        rbtusecolor.setChecked(true);  //set default
        setFragment( new ColorFragment());
        rbtusecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ColorFragment());
            }
        });

        rbtuseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ItemFragment());
            }
        });
    }
    FragmentManager fm;
    FragmentTransaction ft;
    public  void  setFragment(Fragment f)
    {
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.displayData,f);
        ft.commit();
    }

    public void initSpinner()
    {
        categoryModels = categoryDAO.getModels();
        brandModels = brandDAO.getModels();
        unitModels = unitDAO.getModels();
        ArrayList<String> names = new ArrayList<>();
        for(CategoryModel c : categoryModels)
        {
            names.add(c.Name);
        }
        ArrayAdapter<String> catdata = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,names);

        ArrayList<String> brands = new ArrayList<>();
        for(BrandModel b : brandModels)
        {
            brands.add(b.Name);
        }
        ArrayAdapter<String> branddata = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,brands);


        ArrayList<String> units = new ArrayList<>();
        for(UnitModel u : unitModels)
        {
            units.add(u.Name);
        }
        ArrayAdapter<String> unitdata = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,units);

    }

    public void ClearData()
    {
        name.setText("");
        originalprice.setText("");
        saleprice.setText("");
    }


    public  void saveItem()
    {
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModel model = new ItemModel();
                model.BrandId =  brandModels.get(brand.getSelectedItemPosition()).Id;
                model.CategoryId = categoryModels.get(category.getSelectedItemPosition()).Id;
                model.ColorId=selectedId;
                model.PicturePath=ImageFragment.imageuri.toString();
                model.UnitId = unitModels.get(unit.getSelectedItemPosition()).Id;
                model.OPrice = Integer.parseInt(originalprice.getText().toString().trim());
                model.SPrice = Integer.parseInt(saleprice.getText().toString().trim());
                model.Name = name.getText().toString().trim();

                itemDAO.saveModel(model);
                ClearData();
            }
        });
    }
}
