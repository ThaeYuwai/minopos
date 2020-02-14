package com.waiway.minipos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cunoraz.gifview.library.GifView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetUpFragment extends Fragment implements View.OnClickListener {


    public SetUpFragment() {
        // Required empty public constructor
    }

    LinearLayout btnPannel;
    FrameLayout frame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_set_up, container, false);
        GifView gif_Item = (GifView) myView.findViewById(R.id.gifItem);
        gif_Item.setVisibility(View.VISIBLE);
        gif_Item.play();
        gif_Item.setOnClickListener(this);

        GifView gif_Category = (GifView) myView.findViewById(R.id.gifCategory);
        gif_Category.setVisibility(View.VISIBLE);
        gif_Category.play();
        gif_Category.setOnClickListener(this);

        GifView gif_Brand = (GifView) myView.findViewById(R.id.gifBrand);
        gif_Brand.setVisibility(View.VISIBLE);
        gif_Brand.play();
        gif_Brand.setOnClickListener(this);

        GifView gif_Unit = (GifView) myView.findViewById(R.id.gifUnit);
        gif_Unit.setVisibility(View.VISIBLE);
        gif_Unit.play();
        gif_Unit.setOnClickListener(this);

        btnPannel = myView.findViewById(R.id.btn_pannel);
        frame = myView.findViewById(R.id.item_frame);
        return  myView;
    }

    @Override
    public void onClick(View v) {
        GifView selected = (GifView) v;
        if(selected.getId() == R.id.gifItem)
        {
            setFragment(new ItemFragment());
        }
        else if(selected.getId() == R.id.gifCategory)
        {
            setFragment(new CategoryFragment());
        }
        else if(selected.getId() == R.id.gifBrand)
        {
            setFragment(new BrandFragment());
        }
        else {
            setFragment(new UnitFragment());
        }
        btnPannel.setVisibility(View.GONE);
        frame.setVisibility(View.VISIBLE);
    }

    public void setFragment(Fragment f)
    {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.item_frame,f);
        ft.commit();
    }
}
