package com.sankuai.meituan.toolbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.menu.*;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;

public class AmayaActivity extends ActionBarActivity implements MenuItem.OnMenuItemClickListener {

    private Toolbar amayaBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaya);
        amayaBar = (Toolbar) findViewById(R.id.amaya_toolbar);
        setSupportActionBar(amayaBar);
        initMenu();
    }


    public void initMenu() {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuBuilder ms = new MenuBuilder(this);
        MenuPresenter mp = new MenuPresenter() {
            @Override
            public void initForMenu(Context context, MenuBuilder menuBuilder) {

            }

            @Override
            public MenuView getMenuView(ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void updateMenuView(boolean b) {

            }

            @Override
            public void setCallback(Callback callback) {

            }

            @Override
            public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
                return false;
            }

            @Override
            public void onCloseMenu(MenuBuilder menuBuilder, boolean b) {

            }

            @Override
            public boolean flagActionItems() {
                return false;
            }

            @Override
            public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItem) {
                return false;
            }

            @Override
            public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItem) {
                return false;
            }

            @Override
            public int getId() {
                return 0;
            }

            @Override
            public Parcelable onSaveInstanceState() {
                return null;
            }

            @Override
            public void onRestoreInstanceState(Parcelable parcelable) {

            }
        };
        ms.addMenuPresenter(mp);
        amayaBar.setMenu(ms, new ActionMenuPresenter(this));
        ms.add(0,0,0,"测试").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        ms.add(1,1,1,"哈哈").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        ms.add(2,2,2,"嘿嘿").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
