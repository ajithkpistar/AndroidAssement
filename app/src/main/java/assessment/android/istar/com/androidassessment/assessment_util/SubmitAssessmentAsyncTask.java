package assessment.android.istar.com.androidassessment.assessment_util;

import android.content.Context;
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
import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;

/**
 * Created by ajith on 15-12-2016.
 */

public class SubmitAssessmentAsyncTask extends AsyncTask<String, Integer, String> {
    private Context context;
    private CMSAssessmentResult cmsAssessmentResult;

    public SubmitAssessmentAsyncTask(Context context, CMSAssessmentResult cmsAssessmentResult) {
        System.out.println("-----------------------------------------caledddddddddddddddddddddddddddd");
        this.context = context;
        this.cmsAssessmentResult = cmsAssessmentResult;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            Serializer serializer = new Persister();
            StringWriter stringWriter = new StringWriter();
            serializer.write(cmsAssessmentResult, stringWriter);
            String value = stringWriter.toString();
            System.out.println("value---------------->\n"+value);

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
            HttpEntity entity = response.getEntity();

            System.out.println("Response for------>"+cmsAssessmentResult.getAssessment_id()+" --------- "+response.getStatusLine());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result) {

    }
}
