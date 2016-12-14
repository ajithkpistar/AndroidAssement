package assessment.android.istar.com.androidassessment.assessment_util;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_database.AssessmentDataHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;

/**
 * Created by Feroz on 14-12-2016.
 */

public class FetchAssessmentFromServer extends AsyncTask<String, Integer, String> {
    private ViewpagerAdapter viewpagerAdapter;
    private AssessmentLockableViewPager assessmentLockableViewPager;
    private AssessmentDataHandler assessmentDataHandler;
    private Context context;
    private FragmentManager fm;
    public FetchAssessmentFromServer(Context context, ViewpagerAdapter viewpagerAdapter,
                                     AssessmentLockableViewPager assessmentLockableViewPager,
                                     AssessmentDataHandler assessmentDataHandler, FragmentManager fm){
        this.context = context;
        this.viewpagerAdapter = viewpagerAdapter;
        this.assessmentLockableViewPager = assessmentLockableViewPager;
        this.assessmentDataHandler = assessmentDataHandler;
        this.fm = fm;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params) {
        String xml_object = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String BASE_URL = context.getResources().getString(R.string.server_ip) + "/get_offline_assessment?assessment_id=" + params[0];
            Log.v("Talentify", "BASE_URL " + BASE_URL);

            int timeout = 80; // seconds
            HttpParams httpParams = httpclient.getParams();
            httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
            httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);
            HttpPost httppost = new HttpPost(BASE_URL);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            xml_object = EntityUtils.toString(entity, "UTF-8");
            assessmentDataHandler.saveContent(params[0],xml_object);
        }catch (Exception e){

        }
        return xml_object;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null && !result.equalsIgnoreCase("")){
            StringReader reader = new StringReader(result);
            Serializer serializer = new Persister();
            try {
                CMSAssessment cmsAssessment = serializer.read(CMSAssessment.class,reader);
                viewpagerAdapter = new ViewpagerAdapter(fm,cmsAssessment);
                assessmentLockableViewPager.setAdapter(viewpagerAdapter);
                assessmentLockableViewPager.setSwipeLocked(true);
            }catch (Exception e){

            }
        }
    }
}
