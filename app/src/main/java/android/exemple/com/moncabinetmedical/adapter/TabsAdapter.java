package android.exemple.com.moncabinetmedical.adapter;

/**
 * Created by lenovo on 13/11/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.exemple.com.moncabinetmedical.ParametreActivity;
import android.exemple.com.moncabinetmedical.R;
import android.exemple.com.moncabinetmedical.fragment.AProposFragment;
import android.exemple.com.moncabinetmedical.fragment.ContactsFragment;
import android.exemple.com.moncabinetmedical.fragment.FavorisFragment;
import android.exemple.com.moncabinetmedical.fragment.GroupsFragment;
import android.exemple.com.moncabinetmedical.fragment.ListContactFragment;
import android.exemple.com.moncabinetmedical.fragment.ListMessagesFragment;
import android.exemple.com.moncabinetmedical.fragment.List_Rdv_Fragment;
import android.exemple.com.moncabinetmedical.fragment.ParametreFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;


public class TabsAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles = {"CONTACTS", "FAVORIS", "GROUPES"};
    private int[] icons = new int[]{R.drawable.ic_account, R.drawable.ic_favoris,R.drawable.ic_groups};
    private int heightIcon;


    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);

        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon = (int)( 24 * scale + 0.5f );
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;

        if(position == 0){ // ALL Contacts
            frag = new ListContactFragment();
        }
        else if(position == 1){ // ALL Favoris
            frag = new FavorisFragment();
        }
        else if(position == 2){ // ALL Groups
            frag = new GroupsFragment();
        }

        Bundle b = new Bundle();
        b.putInt("position", position);

        frag.setArguments(b);

        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        /*Drawable d = mContext.getResources().getDrawable( icons[position] );
        d.setBounds(0, 0, heightIcon, heightIcon);

        ImageSpan is = new ImageSpan( d );

        SpannableString sp = new SpannableString(" ");
        sp.setSpan( is, 0, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );


        return ( sp );*/
        return ( titles[position] );
    }

}

