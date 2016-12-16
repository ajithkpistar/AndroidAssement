package assessment.android.istar.com.androidassessment.assessment_util;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.ArrayList;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_database.AssessmentDataHandler;
import assessment.android.istar.com.androidassessment.assessment_database.AssessmentStatusHandler;
import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;

/**
 * Created by ajith on 15-12-2016.
 */

public class SubmitAssessmentAsyncTask extends AsyncTask<String, Integer, String> {
    private Context context;
    private CMSAssessmentResult cmsAssessmentResult;
    private AssessmentStatusHandler assessmentStatusHandler;
    private int last_pointer;

    public SubmitAssessmentAsyncTask(Context context, CMSAssessmentResult cmsAssessmentResult, int last_pointer) {
        System.out.println("-----------------------------------------caledddddddddddddddddddddddddddd");
        this.context = context;
        this.cmsAssessmentResult = cmsAssessmentResult;
        this.last_pointer = last_pointer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        assessmentStatusHandler = new AssessmentStatusHandler(context);
    }

    @Override
    protected String doInBackground(String... params) {
        String value = null;
        try {
            Serializer serializer = new Persister();
            StringWriter stringWriter = new StringWriter();
            serializer.write(cmsAssessmentResult, stringWriter);
            value = stringWriter.toString();
            System.out.println("value---------------->\n" + value);

            HttpClient httpclient = new DefaultHttpClient();
            String BASE_URL = context.getResources().getString(R.string.server_ip) + "/submit_assessment_offline";
            Log.v("Talentify", "BASE_URL " + BASE_URL);

            int timeout = 20; // seconds
            HttpParams httpParams = httpclient.getParams();
            httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
            httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);

            HttpPost httppost = new HttpPost(BASE_URL);
            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("result", value));

            httppost.setEntity(new UrlEncodedFormEntity(postParameters));

            HttpResponse response = httpclient.execute(httppost);
            int code = response.getStatusLine().getStatusCode();

            System.out.println("Response for------>" + cmsAssessmentResult.getAssessment_id() + " --------- " + response.getStatusLine());

            if (code != 200) {
                assessmentStatusHandler.saveContent(cmsAssessmentResult.getAssessment_id(), value, "COMPLETED", last_pointer + "");
            } else {
                Cursor c = assessmentStatusHandler.getData(Integer.parseInt(cmsAssessmentResult.getAssessment_id()));
                if (c.moveToFirst() && c.getString(2) != null && c.getString(2).equalsIgnoreCase("COMPLETED")) {
                    assessmentStatusHandler.deleteContent(Integer.parseInt(cmsAssessmentResult.getAssessment_id()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (value != null)
                assessmentStatusHandler.saveContent(cmsAssessmentResult.getAssessment_id(), value, "COMPLETED", last_pointer + "");
        }
        return null;
    }

    protected void onPostExecute(String result) {

    }
}
