package assessment.android.istar.com.androidassessment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import assessment.android.istar.com.androidassessment.assessment_database.AssessmentStatusHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.AssessmentStatus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_SMS, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WAKE_LOCK, Manifest.permission.RECEIVE_BOOT_COMPLETED,
                Manifest.permission.SET_ALARM, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SEND_SMS, Manifest.permission.VIBRATE, Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.BROADCAST_STICKY};


        //for all permission
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        AssessmentStatusHandler assessmentStatusHandler = new AssessmentStatusHandler(this);
        assessmentStatusHandler.saveContent("1", "hi", "INCOMPLETE", "1");

        /*delete
        assessmentStatusHandler.deleteContent(1);
        */
        Cursor cursor=assessmentStatusHandler.getData(1);
        AssessmentStatus assessmentStatus=null;
        if(cursor.moveToFirst()){
            assessmentStatus=new AssessmentStatus(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        }

        if(assessmentStatus!=null){
            System.out.println("assssss000000"+assessmentStatus.getStatus());

        }


        List<AssessmentStatus> allContent = assessmentStatusHandler.getAllContent();
        for (AssessmentStatus s : allContent) {
            System.out.println("jhjjjjjjjjjjjjjjjjjjj--->" + s.getData());
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
