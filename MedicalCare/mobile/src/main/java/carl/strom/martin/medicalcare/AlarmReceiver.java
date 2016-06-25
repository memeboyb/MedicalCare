package carl.strom.martin.medicalcare;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
        //Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        String loadedArray[] = loadArray("medicineNames", context);
        //Toast.makeText(context, loadedArray[0], Toast.LENGTH_SHORT).show();

        int key = intent.getIntExtra("keyName", 0);
        //Toast.makeText(context, key, Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.pill)
                        .setContentTitle("Medicine")
                        .setContentText(loadedArray[key]);

        // Sets an ID for the notification
        int mNotificationId = key;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    public String[] loadArray(String arrayName, Context mContext) {//http://www.sherif.mobi/2012/05/string-arrays-and-object-arrays-in.html
        SharedPreferences prefs = mContext.getSharedPreferences("carl.strom.martin.medicalcare", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

}