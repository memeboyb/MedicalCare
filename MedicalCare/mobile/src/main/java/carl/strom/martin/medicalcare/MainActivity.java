package carl.strom.martin.medicalcare;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlarmManager;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        tabLayout.addTab(tabLayout.newTab().setText("Medicines"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setAlarm();
        saveInfo();
        readInfo();
    }

    public boolean saveArray(String[] array, String arrayName, Context mContext) {//http://www.sherif.mobi/2012/05/string-arrays-and-object-arrays-in.html
        SharedPreferences prefs = mContext.getSharedPreferences("carl.strom.martin.medicalcare", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public String[] loadArray(String arrayName, Context mContext) {//http://www.sherif.mobi/2012/05/string-arrays-and-object-arrays-in.html
        SharedPreferences prefs = mContext.getSharedPreferences("carl.strom.martin.medicalcare", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }


    void saveInfo(){
        String medicineNames[] = {"a", "b", "c"};
        String timeH[] = {"10","15","20"};
        String timeM[] = {"00", "20", "00"};
        saveArray(medicineNames,"medicineNames", this);
    }

    void readInfo(){
       // String loadedArray[] = loadArray("medicineNames", this);
       // Toast.makeText(this, loadedArray[0], Toast.LENGTH_SHORT).show();
    }

    void setAlarm() {
       String loadedArray[] = loadArray("medicineNames", this);
       for(int i = 0; i < 3; i++) {
            Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            alarmIntent.putExtra("keyName", i);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, i, alarmIntent, 0);


            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            int interval = 8000;

            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
            Toast.makeText(this, "setAlarm()", Toast.LENGTH_SHORT).show();
       }
    }
    /*void setAlarm(){
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM *//*
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 48);

        /* Repeating on every 20 minutes interval *//*
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);

        Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show();
        // manager.setExactAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent operation);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}