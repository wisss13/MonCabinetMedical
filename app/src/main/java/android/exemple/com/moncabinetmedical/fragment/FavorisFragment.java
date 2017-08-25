package android.exemple.com.moncabinetmedical.fragment;

import android.content.Context;
import android.content.Intent;
import android.exemple.com.moncabinetmedical.ContactActivity;
import android.exemple.com.moncabinetmedical.MainActivity;
import android.exemple.com.moncabinetmedical.adapter.ContactAdapter;
import android.exemple.com.moncabinetmedical.adapter.TabsAdapter;
import android.exemple.com.moncabinetmedical.extras.SlidingTabLayout;
import android.exemple.com.moncabinetmedical.interfaces.RecyclerViewOnClickListenerHack;
import android.exemple.com.moncabinetmedical.model.Contact;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import android.exemple.com.moncabinetmedical.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FavorisFragment extends ListContactFragment implements RecyclerViewOnClickListenerHack, View.OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            mList = savedInstanceState.getParcelableArrayList("mList");
        }
        else{
            mList = ((MainActivity) getActivity()).getSetContactList(10);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favoris, container, false);

        mToolbarButton = (Toolbar) rootView.findViewById(R.id.inc_tb_bottom);
        mToolbarButton.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                return true;
            }
        });
        mToolbarButton.inflateMenu(R.menu.menu_bottom);
        iv_settings = (ImageView) rootView.findViewById(R.id.iv_settings);
        iv_settings.setImageResource(R.drawable.ic_account_star_white);
        mToolbarButton.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Add new Favorite Contact pressed", Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.listFavoris);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                /*if(dy > 0){
                    fab.hideMenuButton(true);
                }else {
                    fab.showMenuButton(true);
                }*/

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                ContactAdapter adapter = (ContactAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Contact> listAux = ((MainActivity) getActivity()).getSetContactList(10);
                    ((MainActivity) getActivity()).getListContacts().addAll( listAux );

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));


        /*LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setReverseLayout(false);
        mRecyclerView.setLayoutManager(llm);*/

        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);

        //mList = ((MainActivity) getActivity()).getSetContactList(10);
        ContactAdapter adapter = new ContactAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        //setFloatActionButton(rootView);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle onState){
        super.onSaveInstanceState(onState);
    }

}