package com.mobinno.haribol.utilities;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hejvibs on 2/17/18.
 */

public class NetworkUtils {

    final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    static String PARAM_QUERY = "q";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    static String PARAM_SORT = "sort";
    static String sortBy = "stars";

    /**
     * Builds the URL used to query Github.
     *
     * @param githubSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String githubSearchQuery) {

        // Create the desired Uri using the Android’s ‘Uri.Builder’ framework i.e.
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        // Convert the above produced Android Uri i.e. 'builtUri' into a Java Url i.e.
        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
