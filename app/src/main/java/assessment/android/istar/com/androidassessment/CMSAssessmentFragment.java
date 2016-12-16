package assessment.android.istar.com.androidassessment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;
import java.util.ArrayList;

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
    private CMSAssessmentResult cmsAssessmentResult;
    static ArrayList<Entry> question_map, question_time;
    private long start_time, end_time;
    private Toolbar toolbar;
    private static TextView number_of_ques;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cms_assessment_fragment, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        number_of_ques = (TextView) view.findViewById(R.id.number_of_ques);
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
        cmsAssessmentResult.setAssessment_id(assessment_id + "");
        cmsAssessmentResult.setUser_id(SingletonStudent.getInstance().getStudent().getId() + "");
        question_map = new ArrayList<>();
        question_time = new ArrayList<>();
        Cursor c = assessmentDataHandler.getData(assessment_id);
        if (c.moveToFirst()) {
            setupOfflineAssement(c.getString(1), viewpagerAdapter, assessmentLockableViewPager);
        } else {
            fetchAssessmentFromServer(assessment_id, assessmentDataHandler, viewpagerAdapter, assessmentLockableViewPager);
        }


        start_time = System.currentTimeMillis();
        return view;
    }

    private void setupOfflineAssement(String assessment_string, ViewpagerAdapter viewpagerAdapter, AssessmentLockableViewPager viewpager) {
        StringReader reader = new StringReader(assessment_string);
        Serializer serializer = new Persister();
        try {
            CMSAssessment cmsAssessment = serializer.read(CMSAssessment.class, reader);

            viewpagerAdapter = new ViewpagerAdapter(getChildFragmentManager(), cmsAssessment);
            viewpager.setAdapter(viewpagerAdapter);

            if (cmsAssessment != null) {
                number_of_ques.setText("1 of" + (cmsAssessment.getNumber_of_questions()));
            }
            assessmentLockableViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount()-1) {
                        number_of_ques.setText("");
                    } else {
                        number_of_ques.setText((assessmentLockableViewPager.getCurrentItem() + 1) + " of" + (assessmentLockableViewPager.getAdapter().getCount() - 1));
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            //   viewpager.setSwipeLocked(true);
        } catch (Exception e) {

        }
    }

    private void fetchAssessmentFromServer(int assessment_id, AssessmentDataHandler assessmentDataHandler, ViewpagerAdapter viewpagerAdapter, AssessmentLockableViewPager viewpager) {
        new FetchAssessmentFromServer(getContext(), viewpagerAdapter, assessmentLockableViewPager,
                assessmentDataHandler, getChildFragmentManager()).execute(assessment_id + "");


    }


    public static void nextViewpager(String key, String answer, String time) {
        if (assessmentLockableViewPager.getCurrentItem() != (assessmentLockableViewPager.getAdapter().getCount() - 1)) {
            assessmentLockableViewPager.setCurrentItem(assessmentLockableViewPager.getCurrentItem() + 1);
            addData(key, answer, time);
            if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount()-1) {
                number_of_ques.setText("");
            } else {
                number_of_ques.setText((assessmentLockableViewPager.getCurrentItem() + 1) + " of" + (assessmentLockableViewPager.getAdapter().getCount() - 1));
            }
        }
    }


    public static void previousViewpager() {
        if (assessmentLockableViewPager.getCurrentItem() != 0) {
            assessmentLockableViewPager.setCurrentItem(assessmentLockableViewPager.getCurrentItem() - 1);
            if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount()-1) {
                number_of_ques.setText("");
            } else {
                number_of_ques.setText((assessmentLockableViewPager.getCurrentItem() + 1) + " of" + (assessmentLockableViewPager.getAdapter().getCount() - 1));
            }
        }

    }


    static void addData(String key, String answer, String time) {
        question_map.add(new Entry(key, answer));
        question_time.add(new Entry(key, time));
    }

    @Override
    public void onStop() {
        super.onStop();
        cmsAssessmentResult.setQuestion_map(question_map);
        cmsAssessmentResult.setQuestion_time(question_time);
        end_time = System.currentTimeMillis();
        cmsAssessmentResult.setTotal_time((end_time - start_time) / 60000 + "");
        if (cmsAssessmentResult != null)
            new SubmitAssessmentAsyncTask(getContext().getApplicationContext(), cmsAssessmentResult).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
