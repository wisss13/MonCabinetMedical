package android.exemple.com.moncabinetmedical.fragment;


import android.exemple.com.moncabinetmedical.MainActivity;
import android.exemple.com.moncabinetmedical.adapter.TabsAdapter;
import android.exemple.com.moncabinetmedical.extras.SlidingTabLayout;
import android.exemple.com.moncabinetmedical.model.Contact;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.exemple.com.moncabinetmedical.R;

import android.widget.Toast;
import com.github.clans.fab.FloatingActionMenu;
import java.util.List;




public class ContactsFragment extends Fragment implements  View.OnClickListener{

    protected List<Contact> mList;
    protected FloatingActionMenu fab;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

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
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        // TABS
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new TabsAdapter(getFragmentManager(), getContext()));

        mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                //navigationDrawerLeft.setSelection(position);

            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        mSlidingTabLayout.setViewPager(mViewPager);

        //setFloatActionButton(rootView);

        return rootView;
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

        fab.showMenuButton(false);
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