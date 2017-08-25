package android.exemple.com.moncabinetmedical.fragment;

import android.exemple.com.moncabinetmedical.MainActivity;
import android.exemple.com.moncabinetmedical.R;
import android.exemple.com.moncabinetmedical.adapter.ContactAdapter;
import android.exemple.com.moncabinetmedical.model.Contact;
import android.exemple.com.moncabinetmedical.network.NetworkConnection;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/*import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;*/

public class LuxuryCarFragment extends ListContactFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_contact, container, false);

        mList = new ArrayList<>();
        //mPbLoad = (ProgressBar) view.findViewById(R.id.pb_load);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.listContact);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager llm = (GridLayoutManager) mRecyclerView.getLayoutManager();

                ContactAdapter adapter = (ContactAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Contact> listAux = ((MainActivity) getActivity()).getSetContactList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }

            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));

        GridLayoutManager llm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);

        mList = ((MainActivity) getActivity()).getSetContactList(10);
        ContactAdapter adapter = new ContactAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        setFloatActionButton(view);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        NetworkConnection.getInstance(getActivity()).getRequestQueue().cancelAll(LuxuryCarFragment.class.getName());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
