package com.waiway.minipos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.waiway.minipos.DAO.ColorDAO;
import com.waiway.minipos.util.ColorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {


    public ColorFragment() {
        // Required empty public constructor
    }

    public  static  int selectedposition =0;

    static ColorDAO dao;
    static ColorAdapter adapter;
    static RecyclerView colorData;
    static GridLayoutManager gm;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_color, container, false);
        dao = new ColorDAO(getContext());
        colorData = v.findViewById(R.id.colorData);
        ColorAdapter.callingFragment = "Item";
        adapter = new ColorAdapter(dao.getAllColor());
        colorData.setAdapter(adapter);
        gm = new GridLayoutManager(getContext(),3);
        colorData.setLayoutManager(gm);
        return v;
    }

    public static void removeCheck()
    {
        for(int i=0;i<adapter.getItemCount();i++)
        {
            if(i!= selectedposition)
            {

                View v = gm.findViewByPosition(i);
                v.findViewById(R.id.checkcolor).setVisibility(View.GONE);
            }
        }
    }

}
