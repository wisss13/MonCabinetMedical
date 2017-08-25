package android.exemple.com.moncabinetmedical.provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by lenovo on 08/11/2015.
 */
public class SearchableProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "br.com.thiengo.tcmaterialdesign.provider.SearchableProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SearchableProvider(){
        setupSuggestions( AUTHORITY, MODE );
    }
}
