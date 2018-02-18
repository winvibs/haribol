package com.mobinno.haribol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobinno.haribol.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);
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

            // Call makeGithubSearchQuery when the search menu item is clicked
            makeGithubSearchQuery();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * makeGithubSearchQuery() will perform the following tasks:
     * 1. Create the URL to query the github server
     * 2. --- (to be added)
     */
    public void makeGithubSearchQuery() {

        // Fetch the text/keyword entered into the EditText field
        String githubQuery = mSearchBoxEditText.getText().toString();

        // Create the Final URL using the Search parameter i.e 'githubQuery'
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);

        // Display the Final URL on TextView
        mUrlDisplayTextView.setText(githubSearchUrl.toString());
    }
}

