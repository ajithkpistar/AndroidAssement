package assessment.android.istar.com.androidassessment;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import assessment.android.istar.com.androidassessment.assessment_database.AssessmentDataHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;
import assessment.android.istar.com.androidassessment.assessment_result.Entry;
import assessment.android.istar.com.androidassessment.assessment_util.AssessmentLockableViewPager;
import assessment.android.istar.com.androidassessment.assessment_util.FetchAssessmentFromServer;
import assessment.android.istar.com.androidassessment.assessment_util.SubmitAssessmentAsyncTask;
import assessment.android.istar.com.androidassessment.assessment_util.ViewpagerAdapter;
import assessment.android.istar.com.androidassessment.istarindia.utils.SingletonStudent;

/**
 * Created by Feroz on 14-12-2016.
 */

public class CMSAssessmentFragment extends Fragment {
    public final static String ASSESSMENT_ID = "ASSESSMENT_ID";
    private static AssessmentLockableViewPager assessmentLockableViewPager;

    private AssessmentDataHandler assessmentDataHandler;
    private ViewpagerAdapter viewpagerAdapter;
    private int assessment_id;
    public static CMSAssessmentResult cmsAssessmentResult;
    static ArrayList<Entry> question_map, question_time;
    public static long start_time, end_time;
    private Toolbar toolbar;
    private TextView number_of_ques;
    private TextView progress_text;

    private Toast mToastToShow;
    private CountDownTimer countDownTimer;
    private int delay = 120000;
    private int progress_status = 0;
    private ProgressBar prograss_bar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cms_assessment_fragment, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        number_of_ques = (TextView) view.findViewById(R.id.number_of_ques);
        progress_text = (TextView) view.findViewById(R.id.progress_text);
        mToastToShow = Toast.makeText(view.getContext(), "Hurry Up.!\nlast 1 MIN Left.", Toast.LENGTH_SHORT);
        prograss_bar = (ProgressBar) view.findViewById(R.id.prograss_bar);
        prograss_bar.setIndeterminate(false);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        assessmentLockableViewPager = (AssessmentLockableViewPager) view.findViewById(R.id.assessment_viewpager);
        assessmentDataHandler = new AssessmentDataHandler(getContext());
        if (getArguments() != null) {
            if (getArguments().getString(ASSESSMENT_ID) != null) {
                assessment_id = Integer.parseInt(getArguments().getString(ASSESSMENT_ID));
            }
        }
        cmsAssessmentResult = new CMSAssessmentResult();

        assessmentLockableViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateslidePointerText();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        cmsAssessmentResult.setAssessment_id(assessment_id + "");
        cmsAssessmentResult.setUser_id(SingletonStudent.getInstance().getStudent().getId() + "");
        question_map = new ArrayList<>();
        question_time = new ArrayList<>();
        try {
            Cursor c = assessmentDataHandler.getData(assessment_id);
            if (c.moveToFirst()) {
                setupOfflineAssement(c.getString(1), viewpagerAdapter, assessmentLockableViewPager);
            } else {
                fetchAssessmentFromServer(assessment_id, assessmentDataHandler, viewpagerAdapter, assessmentLockableViewPager);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fetchAssessmentFromServer(assessment_id, assessmentDataHandler, viewpagerAdapter, assessmentLockableViewPager);
        }
        return view;
    }

    private void setupOfflineAssement(String assessment_string, ViewpagerAdapter viewpagerAdapter, AssessmentLockableViewPager viewpager) {
        StringReader reader = new StringReader(assessment_string);
        Serializer serializer = new Persister();
        try {
            CMSAssessment cmsAssessment = serializer.read(CMSAssessment.class, reader);

            viewpagerAdapter = new ViewpagerAdapter(getChildFragmentManager(), cmsAssessment);
            viewpager.setAdapter(viewpagerAdapter);
            delay = cmsAssessment.getAssessmentDurationMinutes() * 60000;
            setupObject();
        } catch (Exception e) {

        }
    }

    private void fetchAssessmentFromServer(int assessment_id, AssessmentDataHandler assessmentDataHandler, ViewpagerAdapter viewpagerAdapter, AssessmentLockableViewPager viewpager) {
        new FetchAssessmentFromServer(getContext(), viewpagerAdapter, assessmentLockableViewPager,
                assessmentDataHandler, getChildFragmentManager(),countDownTimer,number_of_ques,prograss_bar,progress_text,start_time).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, assessment_id + "");

    }

    public void setupObject() {
        start_time = System.currentTimeMillis();
        updateslidePointerText();


        play();
    }

    public static void previousViewpager() {
        if (assessmentLockableViewPager.getCurrentItem() != 0) {
            assessmentLockableViewPager.setCurrentItem(assessmentLockableViewPager.getCurrentItem() - 1);
        }
    }

    public static void nextViewpager(String key, String answer, String time) {
        if (assessmentLockableViewPager.getCurrentItem() != (assessmentLockableViewPager.getAdapter().getCount() - 1)) {
            assessmentLockableViewPager.setCurrentItem(assessmentLockableViewPager.getCurrentItem() + 1);
            addData(key, answer, time);
        }
    }

    static void addData(String key, String answer, String time) {
        question_map.add(new Entry(key, answer));
        question_time.add(new Entry(key, time));
    }


    public void play() {
        progress_status = 0;
        prograss_bar.setMax(delay / 1000);
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
                    mToastToShow.show();
                }
                prograss_bar.setProgress(progress_status++);
            }

            public void onFinish() {
                progress_text.setText("00:00");
                prograss_bar.setProgress(0);
                progress_status = 0;
            }
        }.start();
    }

    public void updateslidePointerText() {
        try {
            if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount() - 1) {
                number_of_ques.setText("");
            } else {
                number_of_ques.setText((assessmentLockableViewPager.getCurrentItem() + 1) + " of" + (assessmentLockableViewPager.getAdapter().getCount() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CMSAssessmentResult getAllAssmentResult() {
        cmsAssessmentResult.setQuestion_map(question_map);
        cmsAssessmentResult.setQuestion_time(question_time);
        end_time = System.currentTimeMillis();
        cmsAssessmentResult.setTotal_time((end_time - start_time) / 60000 + "");
        return cmsAssessmentResult;
    }

    @Override
    public void onStop() {
        super.onStop();

        if (getAllAssmentResult() != null) {
            new SubmitAssessmentAsyncTask(getContext().getApplicationContext(), cmsAssessmentResult).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
