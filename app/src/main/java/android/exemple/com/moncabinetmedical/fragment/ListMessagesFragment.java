package android.exemple.com.moncabinetmedical.fragment;

import android.app.Activity;
import android.exemple.com.moncabinetmedical.MainActivity;
import android.exemple.com.moncabinetmedical.adapter.MessageAdapter;
import android.exemple.com.moncabinetmedical.adapter.RdvListAdapter;
import android.exemple.com.moncabinetmedical.model.Message;
import android.exemple.com.moncabinetmedical.model.RdvItem;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.exemple.com.moncabinetmedical.R;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ListMessagesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Message> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

 /*   private final static String[] data;

    static {
        data = new String[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = UUID.randomUUID().toString();
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_messages, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.listMsg);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                MessageAdapter adapter = (MessageAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Message> listAux = getSetMessageList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }

            }
        });
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = getSetMessageList(10);
        MessageAdapter adapter = new MessageAdapter(getActivity(), mList);
        mRecyclerView.setAdapter( adapter );

        return rootView;
    }

    // CAR
    public List<Message> getSetMessageList(int qt){
        String[] nom = new String[]{"Foulen Ben Foulen", "Foulen Ben Foulen", "Foulen Ben Foulen", "Foulen Ben Foulen", "Foulen Ben Foulen", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        //String[] cmsg = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
        String[] date = new String[]{"12/05/2015", " 13/05/2015", "13/05/2015", "16/05/2015", "12/06/2015", "17/07/2015", " 02/10/2015", "12/10/2015", "15/10/2015", "09/11/2015"};
        int[] photos = new int[]{R.drawable.wissem_photo, R.drawable.person_1, R.drawable.person_2, R.drawable.person_3, R.drawable.person_4, R.drawable.person_1, R.drawable.person_3, R.drawable.person_4, R.drawable.person_1, R.drawable.person_2, R.drawable.person_3};
        String[] cmsg = new String[20];
        for (int i = 0; i < cmsg.length; i++) {
            cmsg[i] = "A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                    "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                    "        \"technologically advanced and open to imagination and magic";
        }
        List<Message> listAux = new ArrayList<>();

        for(int i = 0; i < qt; i++){
            Message c = new Message( nom[i % nom.length], cmsg[ i % cmsg.length], date[ i % date.length],photos[i % nom.length]);
            listAux.add(c);
        }
        return(listAux);
    }
/*
    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            return new MyViewHolder(
                    MaterialRippleLayout.on(inflater.inflate(R.layout.demo_recycler_item, viewGroup, false))
                            .rippleOverlay(true)
                            .rippleAlpha(0.2f)
                            .rippleColor(0xFF585858)
                            .rippleHover(true)
                            .create()
            );
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int i) {
            //viewHolder.img_contact_msg.setImageResource(R.drawable.person_1);
           // viewHolder.nom_msg.setText("Foulen Ben Foulen");
            viewHolder.cont_msg.setText(data[i]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        ImageView img_contact_msg;
        TextView nom_msg;
        TextView cont_msg;


        public MyViewHolder(View itemView) {
            super(itemView);
            img_contact_msg = (ImageView) itemView.findViewById(android.R.id.icon);
            nom_msg = (TextView) itemView.findViewById(android.R.id.text2);
            cont_msg = (TextView) itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Rippled item: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            if (getAdapterPosition() % 2 == 0) {
                Toast.makeText(v.getContext(), "long item: " + getAdapterPosition() + " and not consumed",
                        Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
            Toast.makeText(v.getContext(), "long item: " + getAdapterPosition() + " and consumed", Toast.LENGTH_SHORT)
                    .show();
            return true;
        }
    }*/
}