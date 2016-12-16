package assessment.android.istar.com.androidassessment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import assessment.android.istar.com.androidassessment.assessment_util.Assessmentutil;
import assessment.android.istar.com.androidassessment.assessment_util.SaveAllAssessmentAsyncTask;
import assessment.android.istar.com.androidassessment.istarindia.utils.SingletonStudent;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frame_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame_container = (FrameLayout)findViewById(R.id.frame_container);
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
      /*  AssessmentDataHandler AaaAssessmentDataHandler = new AssessmentDataHandler(this);


        AssessmentStatusHandler assessmentStatusHandler = new AssessmentStatusHandler(this);
        assessmentStatusHandler.saveContent("1","content","COMPLETED","1");*/

        try {
            Assessmentutil assessmentutil=new Assessmentutil(this);
            System.out.println("-----------------------------------------------------------"+SingletonStudent.getInstance().getStudent().getId());
            new SaveAllAssessmentAsyncTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }catch (Exception e){
            e.printStackTrace();
        }


        final Button submit=(Button) findViewById(R.id.submit);
        final EditText editText=(EditText)findViewById(R.id.assment_id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);

                Bundle bundle = new Bundle();
                bundle.putString(CMSAssessmentFragment.ASSESSMENT_ID,editText.getText().toString()+"");
                Fragment fragment = new CMSAssessmentFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            }
        });




        /*Intent i = new Intent(MainActivity.this,MyActivity.class);
        startActivity(i);*/

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
