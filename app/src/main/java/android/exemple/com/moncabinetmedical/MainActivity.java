package android.exemple.com.moncabinetmedical;

import android.app.Activity;
import android.content.Intent;
import android.exemple.com.moncabinetmedical.adapter.TabsAdapter;
import android.exemple.com.moncabinetmedical.extras.SlidingTabLayout;
import android.exemple.com.moncabinetmedical.fragment.AProposFragment;
import android.exemple.com.moncabinetmedical.fragment.ContactsFragment;
import android.exemple.com.moncabinetmedical.fragment.ListContactFragment;
import android.exemple.com.moncabinetmedical.fragment.ListMessagesFragment;
import android.exemple.com.moncabinetmedical.fragment.List_Rdv_Fragment;
import android.exemple.com.moncabinetmedical.fragment.LuxuryCarFragment;
import android.exemple.com.moncabinetmedical.fragment.ParametreFragment;
import android.exemple.com.moncabinetmedical.model.Contact;
import android.exemple.com.moncabinetmedical.model.Personne;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private AccountHeader.Result headerNavigationLeft;
    private FloatingActionMenu fab;
    private int mProfileDrawerSelected;

    private List<Personne> listProfile;
    private List<Contact> listContact;
    private ImageView img;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            //mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
            mProfileDrawerSelected = savedInstanceState.getInt("mProfileDrawerSelected", 0);
            //listCars = savedInstanceState.getParcelableArrayList("listCars");
        }
        else{
            listContact = getSetContactList(50);
        }

        img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.wess);

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // DRAWERLATOUT
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(R.id.mes_rdv);

        setFloatActionButton();
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(fab.isOpened()){
            fab.close(true);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int i = item.getItemId();
        displayView(i);
        return true;
    }

    private void displayView(int id) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        if (id == R.id.mes_rdv) {
            fragment = new List_Rdv_Fragment();
            title = getString(R.string.title_mes_rdv);

        } else if (id == R.id.mes_contacts) {
            fragment = new ContactsFragment();
            title = getString(R.string.title_mes_contacts);

        } else if (id == R.id.mes_msg) {
            fragment = new ListMessagesFragment();
            title = getString(R.string.title_mes_msg);

        } else if (id == R.id.mes_param) {
            Intent intent = new Intent(MainActivity.this, ParametreActivity.class);
            startActivity(intent);
           // title = getString(R.string.title_mes_param);

        } else if (id == R.id.a_propos) {
            fragment = new AProposFragment();
            title = getString(R.string.title_a_propos);
        } else if (id == R.id.deconect) {
            finish();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    // Contact
    public List<Contact> getSetContactList(int qtd){
        return(getSetContactList(qtd, 0));
    }

    public List<Contact> getListContacts(){
        return(listContact);
    }

    public List<Contact> getSetContactList(int qtd, int category){
        String[] noms = new String[]{"Wissem", "Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] tels = new String[]{"52567577", "22556688", " 55225588", "98552233", "44552233", "22556688", "44552233", "55225588", "44552233", "98552233", "98552233"};
        int[] categories = new int[]{1, 2, 1, 2, 1, 1, 4, 3, 2, 4, 1};
        int[] photos = new int[]{R.drawable.wissem_photo, R.drawable.person_1, R.drawable.person_2, R.drawable.person_3, R.drawable.person_4, R.drawable.person_1, R.drawable.person_3, R.drawable.person_4, R.drawable.person_1, R.drawable.person_2, R.drawable.person_3};
        //String[] urlPhotos = new String[]{"person_1.jpg", "person_2.jpg", "person_3.jpg", "person_4.jpg", "person_1.jpg", "person_3.jpg", "person_4.jpg", "person_1.jpg", "person_2.jpg", "person_3.jpg"};
        String description = "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.";
        List<Contact> listAux = new ArrayList<>();

        for(int i = 0; i < qtd; i++){
            Contact c = new Contact( noms[i % noms.length], noms[i % noms.length], tels[ i % tels.length], photos[i % noms.length] );
            c.setDescription(description);
            c.setCategory(categories[i % tels.length]);
            c.setTel("52 56 75 77");

            if(category != 0 && c.getCategory() != category){
                continue;
            }
            listAux.add(c);
        }
        return(listAux);
    }

    // /FAB
    public void setFloatActionButton(/*final View rootView*/){
        fab = (FloatingActionMenu) findViewById(R.id.fab);

        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean b) {
                Toast.makeText(MainActivity.this, "Is menu opened? " + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });

        com.github.clans.fab.FloatingActionButton fab1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);
        com.github.clans.fab.FloatingActionButton fab2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab2);
        com.github.clans.fab.FloatingActionButton fab3 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab3);
        com.github.clans.fab.FloatingActionButton fab4 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab4);
        com.github.clans.fab.FloatingActionButton fab5 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab5);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);

        fab.showMenuButton(true);
        fab.setClosedOnTouchOutside(true);
    }

    @Override
    public void onClick(View v){
        Intent it = null;

        switch ( v.getId()){
            case R.id.fab1:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://www.facebook.com"));
                break;
            case R.id.fab2:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://www.youtube.com"));
                break;
            case R.id.fab3:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://plus.google.com"));
                break;
            case R.id.fab4:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://www.linkedin.com"));
                break;
            case R.id.fab5:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://www.whatsapp.com"));
                break;
        }

        startActivity(it);
        //Toast.makeText(MainActivity.this, aux, Toast.LENGTH_SHORT).show();
    }


}
