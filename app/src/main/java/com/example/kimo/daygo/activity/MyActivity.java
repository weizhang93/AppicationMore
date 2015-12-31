package com.example.kimo.daygo.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kimo.daygo.R;

/**
 * Created by Administrator on 2015/12/24 0024.
 */
public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Toolbar myTollbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myTollbar);

        //getActionBar().setDisplayHomeAsUpEnabled(true);//回到主菜单的按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                viewSnakebar();
                return true;
            case R.id.action_settings:

                return true;
            case R.id.action_search:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void viewSnakebar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar), "This is a Snackbar",
                Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        return super.onCreateOptionsMenu(menu);
    }
}
