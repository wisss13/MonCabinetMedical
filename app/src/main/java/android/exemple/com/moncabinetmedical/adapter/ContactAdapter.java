package android.exemple.com.moncabinetmedical.adapter;

import android.content.Context;
import android.exemple.com.moncabinetmedical.R;
import android.exemple.com.moncabinetmedical.extras.ImageHelper;
import android.exemple.com.moncabinetmedical.interfaces.RecyclerViewOnClickListenerHack;
import android.exemple.com.moncabinetmedical.model.Contact;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

/**
 * Created by lenovo on 02/11/2015.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    private Context mContext;
    private List<Contact> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width, height, roundPixels;

    private boolean withAnimation;

    public ContactAdapter(Context c, List<Contact> l){
        this(c, l, true, true);
    }

    public ContactAdapter(Context c, List<Contact> l, boolean wa, boolean wcl){
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 9;

        withAnimation = wa;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("LOG", "onCreateViewHolder");
        View v = mLayoutInflater.inflate(R.layout.contact_list_item, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("LOG", "onBindViewHolder");
        myViewHolder.imgContact.setImageResource(mList.get(position).getPhoto());
        myViewHolder.tvNom.setText(mList.get(position).getNom() + " " + mList.get(position).getPrenom());
        myViewHolder.tvTel.setText(mList.get(position).getTel());
/*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            myViewHolder.imgContact.setImageResource(mList.get(position).getPhoto());
        }
        else{
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mList.get(position).getPhoto());
            bitmap = bitmap.createScaledBitmap(bitmap, width, height, false);

            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 10, width, height, false, false, true, true);
            myViewHolder.imgContact.setImageBitmap(bitmap);
        }
*/
        if(withAnimation){
            try{
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .playOn(myViewHolder.itemView);
            }
            catch(Exception e){}
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Contact c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNom;
        public TextView tvTel;
        public ImageView imgContact;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNom = (TextView) itemView.findViewById(R.id.tv_nom);
            tvTel = (TextView) itemView.findViewById(R.id.tv_tel);
            imgContact = (ImageView) itemView.findViewById(R.id.img_contact);
        }
    }
}
