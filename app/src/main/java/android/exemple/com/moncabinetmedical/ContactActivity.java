package android.exemple.com.moncabinetmedical;

/**
 * Created by lenovo on 06/11/2015.
 */

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.exemple.com.moncabinetmedical.model.Contact;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;


import me.drakeet.materialdialog.MaterialDialog;



public class ContactActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Contact contact;
    private Button btPhone;
    private ImageView ivContact;
    private MaterialDialog mMaterialDialog;
    private TextView tvNom, tvTel, tvDescription;
    private ViewGroup mRoot;
    private boolean isUsingTransition = false;

    private TextView tvTestDrive;
    private Button btTestDrive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if(savedInstanceState != null){
            contact = savedInstanceState.getParcelable("contact");
        }
        else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("contact") != null) {
                contact = getIntent().getExtras().getParcelable("contact");
            }
            else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        tvDescription = (TextView) findViewById(R.id.tv_description);
        ivContact = (ImageView) findViewById(R.id.iv_contact);
        //SimpleDraweeView ivCar = (SimpleDraweeView) findViewById(R.id.iv_car);
        tvNom = (TextView) findViewById(R.id.tv_nom);
        tvTel = (TextView) findViewById(R.id.tv_tel);

        ivContact.setImageResource(contact.getPhoto());
        tvNom.setText(contact.getNom() + " " + contact.getPrenom());
        tvTel.setText(contact.getTel());
        tvDescription.setText(contact.getDescription());

        btPhone = (Button) findViewById(R.id.bt_phone);
     /**/   btPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog = new MaterialDialog(new ContextThemeWrapper(ContactActivity.this, R.style.MyAlertDialog))
                        .setTitle("Téléphone")
                        .setMessage(contact.getTel())
                        .setPositiveButton("Joindre", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(Intent.ACTION_CALL);
                                it.setData(Uri.parse("tél:" + contact.getTel().trim()));
                                startActivity(it);
                            }
                        })
                        .setNegativeButton("Retour", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {return true;}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("contact", contact);
    }

}
