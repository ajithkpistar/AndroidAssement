package assessment.android.istar.com.androidassessment.assessment_util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.concurrent.TimeUnit;

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
    private CountDownTimer countDownTimer;
    private TextView no_of_ques, progress_text;
    private ProgressBar progressBar;
    private long start_time;
    private int delay;
    private int progress_status;
    private Toolbar toolbar;

    public FetchAssessmentFromServer(Context context, ViewpagerAdapter viewpagerAdapter,
                                     AssessmentLockableViewPager assessmentLockableViewPager,
                                     AssessmentDataHandler assessmentDataHandler, FragmentManager fm, CountDownTimer countDownTimer,
                                     TextView no_of_ques, ProgressBar prograss_bar, TextView progress_text, long start_time, Toolbar toolbar) {
        this.context = context;
        this.viewpagerAdapter = viewpagerAdapter;
        this.assessmentLockableViewPager = assessmentLockableViewPager;
        this.assessmentDataHandler = assessmentDataHandler;
        this.fm = fm;
        this.countDownTimer = countDownTimer;
        this.no_of_ques = no_of_ques;
        this.progress_text = progress_text;
        this.progressBar = prograss_bar;
        this.start_time = start_time;
        this.toolbar = toolbar;
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
            assessmentDataHandler.saveContent(params[0], xml_object);
        } catch (Exception e) {

        }
        return xml_object;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null && !result.equalsIgnoreCase("")) {
            StringReader reader = new StringReader(result);
            Serializer serializer = new Persister();
            try {
                CMSAssessment cmsAssessment = serializer.read(CMSAssessment.class, reader);
                viewpagerAdapter = new ViewpagerAdapter(fm, cmsAssessment);
                assessmentLockableViewPager.setAdapter(viewpagerAdapter);
                delay = cmsAssessment.getAssessmentDurationMinutes() * 60000;

                setupObject();
            } catch (Exception e) {

            }
        }
    }

    public void setupObject() {
        start_time = System.currentTimeMillis();
        updateslidePointerText();
        progress_status = 0;
        progressBar.setMax(delay / 1000);
        countDownTimer = new CountDownTimer(delay, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                String timerString = "00:00", minString = "" + min, secString = "" + sec;
                if (min < 10) {
                    minString = "0" + min;
                }
                if (sec < 10) {
                    secString = "0" + sec;
                }
                timerString = minString + ":" + secString;
                progress_text.setText(timerString);

                if (min == 1 && sec == 0) {
                    try {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                    }
                }
                progressBar.setProgress(progress_status++);
            }

            public void onFinish() {
                progress_text.setText("00:00");
                progressBar.setProgress(0);
                progress_status = 0;
            }
        }.start();
        toolbar.setVisibility(View.VISIBLE);
    }


    public void updateslidePointerText() {
        try {
            if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount() - 1) {
                no_of_ques.setText("");
            } else {
                no_of_ques.setText((assessmentLockableViewPager.getCurrentItem() + 1) + "/" + (assessmentLockableViewPager.getAdapter().getCount() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
