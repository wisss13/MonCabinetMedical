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

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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

public class ListContactFragment extends Fragment implements RecyclerViewOnClickListenerHack, View.OnClickListener{

    protected RecyclerView mRecyclerView;
    protected List<Contact> mList;
    protected FloatingActionMenu fab;

    protected Toolbar mToolbarButton;
    protected ImageView iv_settings;

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
        View rootView = inflater.inflate(R.layout.fragment_list_contact, container, false);

        // TOOLBAR BUTTON
        mToolbarButton = (Toolbar) rootView.findViewById(R.id.inc_tb_bottom);
        mToolbarButton.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_facebook:
                        Toast.makeText(getContext(), "Add new Contact pressed", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        iv_settings = (ImageView) rootView.findViewById(R.id.iv_settings);
        iv_settings.setImageResource(R.drawable.ic_account_plus_white);
        mToolbarButton.inflateMenu(R.menu.menu_bottom);
        mToolbarButton.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Add new Contact pressed", Toast.LENGTH_SHORT).show();
            }
        });

        // RECYCLERVIEW
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.listContact);
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


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setReverseLayout(false);
        mRecyclerView.setLayoutManager(llm);

        /*GridLayoutManager llm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);*/

        //mList = ((MainActivity) getActivity()).getSetContactList(10);
        ContactAdapter adapter = new ContactAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        //setFloatActionButton(rootView);

        return rootView;
    }

    @Override
    public void onClickListener(View view, int position) {

        Intent intent = new Intent(getActivity(), ContactActivity.class);
        intent.putExtra("contact", mList.get(position));
        getActivity().startActivity(intent);

       /* Toast.makeText(getActivity(), "onclicklisiter"  + position + 1, Toast.LENGTH_SHORT).show();

        ContactAdapter adapter = (ContactAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);*/
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        Toast.makeText(getActivity(), "onLongPressClickListener(): "+position, Toast.LENGTH_SHORT).show();

        /*ContactAdapter adapter = (ContactAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);*/
    }

    public static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {

        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh){
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if(cv != null && mRecyclerViewOnClickListenerHack != null){
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv,
                                rv.getChildAdapterPosition(cv) );
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if(cv != null && mRecyclerViewOnClickListenerHack != null){
                        mRecyclerViewOnClickListenerHack.onClickListener(cv,
                                rv.getChildAdapterPosition(cv));
                    }
                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {}
    }

    // / FAB
    public void setFloatActionButton(final View rootView){
        fab = (FloatingActionMenu) rootView.findViewById(R.id.fab);

        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean b) {
                Toast.makeText(getActivity(), "Is menu opened? " + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });

        fab.showMenuButton(true);
        fab.setClosedOnTouchOutside(true);

        com.github.clans.fab.FloatingActionButton fab1 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.fab1);
        com.github.clans.fab.FloatingActionButton fab2 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.fab2);
        com.github.clans.fab.FloatingActionButton fab3 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.fab3);
        com.github.clans.fab.FloatingActionButton fab4 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.fab4);
        com.github.clans.fab.FloatingActionButton fab5 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.fab5);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        String aux = "";

        switch ( v.getId()){
            case R.id.fab1:
                aux = "Facebook clicked";
                break;
            case R.id.fab2:
                aux = "fab2 clicked";
                break;
            case R.id.fab3:
                aux = "fab3 clicked";
                break;
            case R.id.fab4:
                aux = "fab4 clicked";
                break;
            case R.id.fab5:
                aux = "fab5 clicked";
                break;
        }

        Toast.makeText(getContext(), aux, Toast.LENGTH_SHORT).show();
    }

}