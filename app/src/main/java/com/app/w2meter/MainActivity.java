package com.app.w2meter;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.w2meter.adapter.AdapterGraph;
import com.app.w2meter.data.ChartItem;
import com.app.w2meter.data.LineChartItem;
import com.app.w2meter.reciver.AlarmReceiver;
import com.app.w2meter.utils.SharedManagerUtil;
import com.app.w2meter.view.AboutActivity;
import com.app.w2meter.view.GroupActivity;
import com.app.w2meter.view.ProfileActivity;
import com.app.w2meter.view.SplashActivity;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    ListView listView;
    SharedManagerUtil session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session=new SharedManagerUtil(this);

        if(!session.isLogin()){
            Calendar calendar = Calendar.getInstance();
            // we can set time by open date and time picker dialog
            calendar.set(Calendar.HOUR_OF_DAY, 22);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    MainActivity.this, 0, intent1,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) MainActivity.this
                    .getSystemService(MainActivity.this.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);

            session.createLoginSession("101");
        }



        listView=findViewById(R.id.listView);
        ArrayList<ChartItem> list = new ArrayList<ChartItem>();
        for (int i = 0; i < 10; i++) {
            list.add(new LineChartItem(generateDataLine(i + 1), getApplicationContext()));
        }

        MainActivity.ChartDataAdapter cda = new MainActivity.ChartDataAdapter(getApplicationContext(), list);
        listView.setAdapter(cda);

        Intent intent = getIntent();
        Boolean notification=intent.getBooleanExtra("notification",false);
        if(notification){
            Log.e("Dialog call","...");
            openDialog();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(true);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        if (item.getItemId() == R.id.action_profile) {
            Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(intent);
            finish();
        }

        if (item.getItemId() == R.id.action_about) {
            Intent intent=new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
            finish();
        }

        if (item.getItemId() == R.id.action_group) {
            Intent intent=new Intent(MainActivity.this,GroupActivity.class);
            startActivity(intent);
            finish();
        }

        if (item.getItemId() == R.id.action_refresh) {
            //refresh........
        }
        return super.onOptionsItemSelected(item);
    }


    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            return getItem(position).getItemType();
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        /*for (int i = 0; i < 12; i++) {
            e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
            e1.add(new Entry(i,-10));
        }*/

        e1.add(new Entry(0,-10));
        e1.add(new Entry(1,10));
        e1.add(new Entry(2,-10));
        e1.add(new Entry(3,15));
        e1.add(new Entry(4,-15));
        e1.add(new Entry(5,20));
        e1.add(new Entry(6,10));
        e1.add(new Entry(7,25));
        e1.add(new Entry(8,5));
        e1.add(new Entry(9,10));
        e1.add(new Entry(10,7));
        e1.add(new Entry(11,25));
        e1.add(new Entry(12,20));
        e1.add(new Entry(13,15));
        e1.add(new Entry(14,10));

        //LineDataSet d1 = new LineDataSet(e1, "Group " + cnt);
        LineDataSet d1 = new LineDataSet(e1, "");
        d1.setLineWidth(4.5f);
        d1.setCircleRadius(1.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);


        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(sets);
        return cd;
    }


    public void openDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_question);


        ImageView iv_ans1 = dialog.findViewById(R.id.iv_ans1);


        iv_ans1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

}
