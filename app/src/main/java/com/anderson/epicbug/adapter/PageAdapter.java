package com.anderson.epicbug.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.anderson.epicbug.fragment.CarrinhoFragment;
import com.anderson.epicbug.fragment.JogosFragment;
import com.anderson.epicbug.fragment.PedidosFragment;


//Parte Guilherme
//Parte Guilherme
//Parte Guilherme
//
public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new JogosFragment();
            case 1:
                return new PedidosFragment();
            case 2:
                return new CarrinhoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return numOfTabs;
    }
}
