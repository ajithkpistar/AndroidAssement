package assessment.android.istar.com.androidassessment.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;
import java.util.List;

import assessment.android.istar.com.androidassessment.assessment_database.AssessmentStatusHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.AssessmentStatus;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;
import assessment.android.istar.com.androidassessment.assessment_util.SubmitAssessmentAsyncTask;
import assessment.android.istar.com.androidassessment.assessment_util.ViewpagerAdapter;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private AssessmentStatusHandler assessmentStatusHandler;
    private List<AssessmentStatus> assessmentStatuses;

    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {

        String status = NetworkUtil.getConnectivityStatusString(context);
        assessmentStatusHandler = new AssessmentStatusHandler(context);
        if (!status.equalsIgnoreCase("Not connected to Internet")) {
            assessmentStatuses = assessmentStatusHandler.getAllContent();
            for (AssessmentStatus assessmentStatus : assessmentStatuses) {

                if (assessmentStatus.getStatus() != null && assessmentStatus.getStatus().equalsIgnoreCase("COMPLETED")) {
                    String assessmentStatusData = assessmentStatus.getData();
                    StringReader reader = new StringReader(assessmentStatusData);
                    Serializer serializer = new Persister();
                    try {
                        CMSAssessmentResult cmsAssessmentResult = serializer.read(CMSAssessmentResult.class, reader);
                        new SubmitAssessmentAsyncTask(context, cmsAssessmentResult, Integer.parseInt(assessmentStatus.getLast_pointer())).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Status----------------------->" + status);
    }
}
