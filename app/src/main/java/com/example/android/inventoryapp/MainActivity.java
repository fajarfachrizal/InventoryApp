package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.android.inventoryapp.data.BierDbHelper;
import com.example.android.inventoryapp.data.BierItem;


public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    BierDbHelper dbHelper;
    BierCursorAdapter adapter;
    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new BierDbHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readSupply();

        adapter = new BierCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        invalidateOptionsMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readSupply());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readSupply());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                addDummyData();
                adapter.swapCursor(dbHelper.readSupply());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Add data for demo purposes
     */
    private void addDummyData() {
        BierItem erdinger = new BierItem(
                "Erdinger",
                "22 €",
                12,
                "Erdinger GmbH",
                "+49 000 000 0000",
                "info@erdinger.de",
                "android.resource://com.example.android.inventoryapp/drawable/bier_erdinger");
        dbHelper.insertItem(erdinger);

        BierItem heineken = new BierItem(
                "Heineken",
                "30 €",
                12,
                "Heinken GmbH",
                "+49 000 000 0000",
                " info@germany.heineken.com",
                "android.resource://com.example.android.inventoryapp/drawable/bier_heineken");
        dbHelper.insertItem(heineken);

        BierItem koelsch = new BierItem(
                "Koelsch",
                "19 €",
                12,
                "Koelsch GmbH",
                "+49 000 000 0000",
                "info@sester.de",
                "android.resource://com.example.android.inventoryapp/drawable/bier_koelsch");
        dbHelper.insertItem(koelsch);

        BierItem schlenkerla = new BierItem(
                "Schlenkerla",
                "28 €",
                12,
                "Schlenkerla GmbH",
                "+49 000 000 0000",
                "info@schlenkerla.de",
                "android.resource://com.example.android.inventoryapp/drawable/bier_schlenkerla");
        dbHelper.insertItem(schlenkerla);

    }
}
