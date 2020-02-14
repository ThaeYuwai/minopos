package com.waiway.minipos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.waiway.minipos.DAO.ColorDAO;
import com.waiway.minipos.Model.ColorModel;
import com.waiway.minipos.util.ViewPagerAdapter;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadTableItem();

    }

    public void LoadTableItem()
    {
        TabLayout layout = findViewById(R.id.tabLayout);
        ViewPager pager= findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SaleFragment(),"sale");
        adapter.addFragment(new PurchaseFragment(),"purchase");
        adapter.addFragment(new ReportFragment(),"report");
        adapter.addFragment(new SetUpFragment(),"set up");
        adapter.addFragment(new SettingFragment(),"setting");
        pager.setAdapter(adapter);
        layout.setupWithViewPager(pager);
        saveColor();
    }

    public void saveColor()
    {
        ColorDAO dao = new ColorDAO(getApplicationContext());
        if(dao.getAllColor().size()<=0)
        {
            for(int i =1;i<=12;i++)
            {
                Random random = new Random();
                ColorModel model = new ColorModel();
                model.ColorRed = random.nextInt(256);
                model.ColorGreen = random.nextInt(256);
                model.ColorBlue = random.nextInt(256);
                dao.saveColor(model);
            }
        }
    }
}
