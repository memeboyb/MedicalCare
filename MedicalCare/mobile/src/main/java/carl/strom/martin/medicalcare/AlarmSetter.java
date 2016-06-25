package carl.strom.martin.medicalcare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by martin on 2016-05-23.
 */
public class AlarmSetter {
    private PendingIntent pendingIntent;

   public void setNextAlarm(){
       /*AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
       int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM */
      /* Calendar calendar = Calendar.getInstance();
       calendar.setTimeInMillis(System.currentTimeMillis());
       calendar.set(Calendar.HOUR_OF_DAY, 10);
       calendar.set(Calendar.MINUTE, 30);*/

        /* Repeating on every 20 minutes interval */
      /* manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
               1000 * 60 * 20, pendingIntent);*/
    }
}
