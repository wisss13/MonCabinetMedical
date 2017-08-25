package android.exemple.com.moncabinetmedical.adapter;

import android.content.Context;
import android.exemple.com.moncabinetmedical.model.Message;
import android.exemple.com.moncabinetmedical.R;
import android.exemple.com.moncabinetmedical.interfaces.RecyclerViewOnClickListenerHack;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

/**
 * Created by lenovo on 30/11/2015.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private List<Message> mList;
    private LayoutInflater mLayoutInflater;

    public MessageAdapter(Context c, List<Message> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addListItem(Message m, int position){
        mList.add(m);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        /*View v = mLayoutInflater.inflate(R.layout.item_message, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);*/
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new MyViewHolder(
                MaterialRippleLayout.on(inflater.inflate(R.layout.item_message, viewGroup, false))
                        .rippleOverlay(true)
                        .rippleAlpha(0.2f)
                        .rippleColor(0xFF585858)
                        .rippleHover(true)
                        .create()
        );
        //return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.ivContact.setImageResource(mList.get(position).getPhoto());
        myViewHolder.tvNom.setText(mList.get(position).getNom());
        myViewHolder.tvMsg.setText(mList.get(position).getMsg());
        myViewHolder.tvDate.setText(mList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivContact;
        public TextView tvNom;
        public TextView tvMsg;
        public TextView tvDate;

        public MyViewHolder(View itemView){
            super(itemView);

            ivContact = (ImageView) itemView.findViewById(R.id.iv_contact_msg);
            tvNom = (TextView) itemView.findViewById(R.id.tv_nom_msg);
            tvMsg = (TextView) itemView.findViewById(R.id.tv_cont_msg);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date_msg);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
