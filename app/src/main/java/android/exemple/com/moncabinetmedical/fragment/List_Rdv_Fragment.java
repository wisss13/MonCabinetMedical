package android.exemple.com.moncabinetmedical.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.content.DialogInterface;
import android.exemple.com.moncabinetmedical.adapter.RdvListAdapter;
import android.exemple.com.moncabinetmedical.model.RdvItem;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.exemple.com.moncabinetmedical.R;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.TimeAnimator;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class List_Rdv_Fragment extends Fragment implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, DialogInterface.OnCancelListener{

    private TextView tvTestDrive;
    private Button bt_rdv;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list_rdv, container, false);


            tvTestDrive = (TextView) rootView.findViewById(R.id.tv_Rdv);

            bt_rdv = (Button) rootView.findViewById(R.id.bt_Rdv);
            bt_rdv.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    scheduleTestRdv(v);
                }
            });

            ArrayList image_details = getListData();
            final ListView lv1 = (ListView) rootView.findViewById(R.id.listRdv);
            lv1.setAdapter(new RdvListAdapter(getActivity(), image_details));

            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = lv1.getItemAtPosition(position);
                    RdvItem rdvData = (RdvItem) o;
                    Toast.makeText(getContext(), "Selected :" + " " + rdvData, Toast.LENGTH_LONG).show();
                }
            });

            //setFloatActionButton(rootView);

            // Inflate the layout for this fragment
            return rootView;
        }

        private ArrayList getListData() {
            ArrayList<RdvItem> rdvs = new ArrayList<RdvItem>();
            RdvItem rdvData = new RdvItem();
            rdvData.setHeure_deb("13:35");
            rdvData.setNom("Ben Foulen");
            rdvData.setPrenom("Foulen");
            rdvData.setCommentaire("Visite de controle");
            for(int i = 0; i<10;i++){
                rdvs.add(rdvData);
            }


            return rdvs;
        }

        // Test Rdv
        private int year, month, day, hour, minute;

        public void scheduleTestRdv(View v){
            initDateTimeData();
            Calendar cDefault = Calendar.getInstance();
            cDefault.set(year, month, day);

            com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                    this,
                    cDefault.get(Calendar.YEAR),
                    cDefault.get(Calendar.MONTH),
                    cDefault.get(Calendar.DAY_OF_MONTH)
            );

            Calendar cMin = Calendar.getInstance();
            Calendar cMax = Calendar.getInstance();
            cMax.set(cMax.get(Calendar.YEAR), 11, 31);
            datePickerDialog.setMinDate(cMin);
            datePickerDialog.setMaxDate(cMax);

            List<Calendar> daysList = new LinkedList<>();
            Calendar[] daysArray;
            Calendar cAux = Calendar.getInstance();

            while( cAux.getTimeInMillis() <= cMax.getTimeInMillis() ){
                if( cAux.get( Calendar.DAY_OF_WEEK ) != 1 && cAux.get( Calendar.DAY_OF_WEEK ) != 7 ){
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis( cAux.getTimeInMillis() );

                    daysList.add( c );
                }
                cAux.setTimeInMillis( cAux.getTimeInMillis() + ( 24 * 60 * 60 * 1000 ) );
            }
            daysArray = new Calendar[ daysList.size() ];
            for( int i = 0; i < daysArray.length; i++ ){
                daysArray[i] = daysList.get(i);
            }

            datePickerDialog.setSelectableDays(daysArray);
            datePickerDialog.setOnCancelListener(this);
            datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");

        }

        public void initDateTimeData(){
            if( year == 0 ){
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
            }

        }


            @Override
            public void onCancel(DialogInterface dialog) {
                year = month = day = hour = minute = 0;
                tvTestDrive.setText("");
            }

            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog, int i, int i1, int i2) {
                Calendar tDefault = Calendar.getInstance();
                tDefault.set(year, month, day, hour, minute);

                year = i;
                month = i1;
                day = i2;

                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                        this,
                        tDefault.get(Calendar.HOUR_OF_DAY),
                        tDefault.get(Calendar.MINUTE),
                        true
                );
                timePickerDialog.setOnCancelListener(this);
                timePickerDialog.show(getActivity().getFragmentManager(), "timePickerDialog");
                timePickerDialog.setTitle("Horário Test Drive");

                timePickerDialog.setThemeDark(true);
            }

            @Override
            public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {
                if( i < 9 || i > 18 ){
                    onDateSet(null, year, month, day);
                    Toast.makeText(getContext(), "Somente entre 9h e 18h", Toast.LENGTH_SHORT).show();
                    return;
                }

                hour = i;
                minute = i1;

                tvTestDrive.setText( (day < 10 ? "0"+day : day)+"/"+
                        (month+1 < 10 ? "0"+(month+1) : month+1)+"/"+
                        year+" às "+
                        (hour < 10 ? "0"+hour : hour)+"h"+
                        (minute < 10 ? "0"+minute : minute));
            }
}
