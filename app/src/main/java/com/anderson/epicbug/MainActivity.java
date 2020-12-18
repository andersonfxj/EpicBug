package com.anderson.epicbug;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Build;
import android.widget.Adapter;
import android.widget.Toast;
import com.anderson.epicbug.adapter.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

//Parte Guilherme
//Parte Guilherme
//Parte Guilherme

public class MainActivity extends AppCompatActivity {
   //atributos
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TabItem tabJogos;
    TabItem tabPedidos;
    TabItem tabCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definindo  barra de ferramentas, TabLayout e viewpager
        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        tabLayout = findViewById(R.id.tabLayout);
        tabJogos = findViewById(R.id.tabJogos);
        tabPedidos = findViewById(R.id.tabPedidos);
        viewPager = findViewById(R.id.viewPager);

        //Definindo page adapter
        PagerAdapter pagerAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        setSupportActionBar(toolbar);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
