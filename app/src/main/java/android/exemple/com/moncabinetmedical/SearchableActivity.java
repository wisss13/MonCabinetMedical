package android.exemple.com.moncabinetmedical;

import android.app.SearchManager;
import android.content.Intent;
import android.exemple.com.moncabinetmedical.adapter.ContactAdapter;
import android.exemple.com.moncabinetmedical.interfaces.RecyclerViewOnClickListenerHack;
import android.exemple.com.moncabinetmedical.model.Contact;
import android.exemple.com.moncabinetmedical.model.WrapObjToNetwork;
import android.exemple.com.moncabinetmedical.network.NetworkConnection;
import android.exemple.com.moncabinetmedical.network.Transaction;
import android.exemple.com.moncabinetmedical.provider.SearchableProvider;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 07/11/2015.
 */
public class SearchableActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack, Transaction {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<Contact> mList;
    private List<Contact> mListAux;
    private ContactAdapter adapter;
    private CoordinatorLayout clContainer;
    private ProgressBar mPbLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState != null){
            mList = savedInstanceState.getParcelableArrayList("mList");
            mListAux = savedInstanceState.getParcelableArrayList("mListAux");
        }
        else{
            mList = (new MainActivity().getSetContactList(10, 0));
            mListAux = new ArrayList<>();
        }

        clContainer = (CoordinatorLayout) findViewById(R.id.cl_container);

        mPbLoad = (ProgressBar) findViewById(R.id.pb_load);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager( this );
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        adapter = new ContactAdapter(this, mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        hendleSearch( getIntent() );
    }

    public void hendleSearch( Intent intent ){
        if( Intent.ACTION_SEARCH.equalsIgnoreCase( intent.getAction() ) ){
            String q = intent.getStringExtra( SearchManager.QUERY );

            mToolbar.setTitle(q);
            filterContacts( q );

            SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(this,
                    SearchableProvider.AUTHORITY,
                    SearchableProvider.MODE);
            searchRecentSuggestions.saveRecentQuery( q, null );
        }
    }

    public void filterContacts( String q ){
        mList.clear();
        NetworkConnection.getInstance(this).execute(this, SearchableActivity.class.getName());
    }

    @Override
    public void onClickListener(View view, int position) {

    }

    @Override
    public void onLongPressClickListener(View view, int position) {

    }

    @Override
    public WrapObjToNetwork doBefore() {
        return null;
    }

    @Override
    public void doAfter(JSONArray jsonArray) {

    }
}
