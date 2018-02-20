package com.mobinno.haribol;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobinno.haribol.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;

    // COMPLETED (12) Create a variable to store a reference to the error message TextView
    private TextView mErrorMessageTextView;

    // COMPLETED (24) Create a ProgressBar variable to store a reference to the ProgressBar
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);

        // COMPLETED (13) Get a reference to the error TextView using findViewById
        mErrorMessageTextView =(TextView) findViewById(R.id.tv_error_message_display);

        // COMPLETED (25) Get a reference to the ProgressBar using findViewById
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
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
     * 1. Create and Display the Query URL.
     * 2. Query the github server over HTTP using the Created URL.
     * 3. Display the Query result in the TextView.
     */
    private void makeGithubSearchQuery() {

        // Fetch the text/keyword entered into the EditText field
        String githubQuery = mSearchBoxEditText.getText().toString();

        // Create the Final URL using the Search parameter i.e 'githubQuery'
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);

        // Display the Final URL on TextView
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        // Creating a new GithubQueryTask and calling its execute method, passing in the url to query
        new GithubQueryTask().execute(githubSearchUrl);
    }

       // COMPLETED (14) Create a method called showJsonDataView to show the data and hide the error
       private void showJsonDataView() {
           mErrorMessageTextView.setVisibility(View.INVISIBLE);
           mSearchResultsTextView.setVisibility(View.VISIBLE);
       }

        // COMPLETED (15) Create a method called showErrorMessage to show the error and hide the data
        private void showErrorMessage() {
            mSearchResultsTextView.setVisibility(View.INVISIBLE);
            mErrorMessageTextView.setVisibility(View.VISIBLE);
        }

    // Creating a 'GithubQueryTask' class that extends AsyncTask
    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        // Overriding the 'doInBackground' method to Query the github server and return the results.
        @Override
        protected String doInBackground(URL... params) {

            URL searchUrl = params[0];
            String githubSearchResults = null;

            // Call getResponseFromHttpUrl inside a try / catch block to catch an IOException
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // added temp delay on Background Thread to show ProgressBar
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return githubSearchResults;
        }

        // Overriding 'onPostExecute' to display the results in the TextView
        @Override
        protected void onPostExecute(String githubSearchResults) {

            // COMPLETED (27) As soon as the loading is complete, hide the loading indicator
            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if(githubSearchResults != null && !githubSearchResults.equals("")) {
                // COMPLETED (17) Call showJsonDataView if we have valid, non-null results
                showJsonDataView();
                mSearchResultsTextView.setText(githubSearchResults);
            } else {
                // COMPLETED (16) Call showErrorMessage if the result is null in onPostExecute
                showErrorMessage();
            }

        }
    }
}