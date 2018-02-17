package com.mobinno.haribol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

// Do 2 - 7 in menu.xml ///////////////////////////////////////////////////////////////////////
// COMPLETED (2) Create a menu xml called 'main.xml' in the res->menu folder
// COMPLETED (3) Add one menu item to your menu
// COMPLETED (4) Give the menu item an id of @+id/action_search
// COMPLETED (5) Set the orderInCategory to 1
// COMPLETED (6) Show this item if there is room (use app:showAsAction, not android:showAsAction)
// COMPLETED (7) Set the title to the search string ("Search") from strings.xml
// Do 2 - 7 in menu.xml ///////////////////////////////////////////////////////////////////////

// COMPLETED (8) Override onCreateOptionsMenu
// COMPLETED (9) Within onCreateOptionsMenu, use getMenuInflater().inflate to inflate the menu
// COMPLETED (10) Return true to display your menu

// COMPLETED (11) Override onOptionsItemSelected
// COMPLETED (12) Within onOptionsItemSelected, get the ID of the item that was selected
// COMPLETED (13) If the item's ID is R.id.action_search, show a Toast and return true to tell Android that you've handled this menu click
// COMPLETED (14) Don't forgot to call .show() on your Toast
// COMPLETED (15) If you do NOT handle the menu click, return super.onOptionsItemSelected to let Android handle the menu click

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClicked = item.getItemId();
        if(itemThatWasClicked == R.id.action_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}

