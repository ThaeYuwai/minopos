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

import com.waiway.minipos.DAO.ColorDAO;
import com.waiway.minipos.DAO.UnitDAO;
import com.waiway.minipos.Model.ColorModel;
import com.waiway.minipos.Model.UnitModel;
import com.waiway.minipos.util.ColorAdapter;
import com.waiway.minipos.util.ListAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnitFragment extends Fragment {


    public UnitFragment() {
        // Required empty public constructor
    }
    UnitDAO unitDAO;
    ColorDAO colorDAO;
    RecyclerView rcColorData, rcList;
    public  static TextView selectedColor; // display
    public  static  int selectedColorId;// save
    Button btnsave,btncancel;
    View myView;
    EditText name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_unit, container, false);
        initializeUI();
        saveModel();
        loadData();
        loadColor();
        return  myView;
    }

    public void  initializeUI()
    {
        rcColorData = myView.findViewById(R.id.color_item);
        rcList = myView.findViewById(R.id.color_View);
        btnsave = myView.findViewById(R.id.btn_save);
        btncancel = myView.findViewById(R.id.btn_cancel);
        selectedColor = myView.findViewById(R.id.selectedcolor);
        name = myView.findViewById(R.id.name);
        colorDAO = new ColorDAO(getContext());
        unitDAO = new UnitDAO(getContext());

    }

    public  void saveModel()
    {
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnitModel model = new UnitModel();
                model.Name = name.getText().toString().trim();
                model.ColorId = selectedColorId;
                unitDAO.saveModel(model);
                name.setText(" ");
                loadData();
            }
        });
    }

    public  void  loadData()
    {
        ArrayList<UnitModel> unitModels = new ArrayList<>();
        ListAdapter adapter = new ListAdapter(unitModels.toArray(),getContext(),"Unit");
        rcList.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcList.setLayoutManager(lm);
    }

    public  void  loadColor()
    {
        ColorAdapter.callingFragment = "Unit";
        ArrayList<ColorModel> colorModels = colorDAO.getAllColor();
        ColorAdapter adapter = new ColorAdapter(colorModels);
        rcColorData.setAdapter(adapter);
        GridLayoutManager gm = new GridLayoutManager(getContext(),3);
        rcColorData.setLayoutManager(gm);
    }
}
