package android.exemple.com.moncabinetmedical.adapter;

import android.content.Context;
import android.exemple.com.moncabinetmedical.R;
import android.exemple.com.moncabinetmedical.fragment.List_Rdv_Fragment;
import android.exemple.com.moncabinetmedical.model.RdvItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 30/10/2015.
 */
public class RdvListAdapter extends BaseAdapter {
        private ArrayList<RdvItem> listData;
        private LayoutInflater layoutInflater;

        public RdvListAdapter(Context aContext, ArrayList<RdvItem> listData) {
            this.listData = listData;
            layoutInflater = LayoutInflater.from(aContext);
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.rdv_item_layout, null);
                holder = new ViewHolder();
                holder.heure_deb = (TextView) convertView.findViewById(R.id.txt_heure_deb);
                holder.nom = (TextView) convertView.findViewById(R.id.txt_nomp);
                holder.commentaire = (TextView) convertView.findViewById(R.id.txt_com);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.heure_deb.setText(listData.get(position).getHeure_deb());
            holder.nom.setText( listData.get(position).getNom() + " " + listData.get(position).getPrenom());
            holder.commentaire.setText(listData.get(position).getCommentaire());
            return convertView;
        }

        static class ViewHolder {
            TextView heure_deb;
            TextView nom;
            TextView commentaire;
        }

}
