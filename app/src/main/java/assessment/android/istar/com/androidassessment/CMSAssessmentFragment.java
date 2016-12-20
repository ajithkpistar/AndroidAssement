package assessment.android.istar.com.androidassessment;

import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import assessment.android.istar.com.androidassessment.assessment_database.AssessmentDataHandler;
import assessment.android.istar.com.androidassessment.assessment_database.AssessmentStatusHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;
import assessment.android.istar.com.androidassessment.assessment_result.Entry;
import assessment.android.istar.com.androidassessment.assessment_util.AssessmentLockableViewPager;
import assessment.android.istar.com.androidassessment.assessment_util.FetchAssessmentFromServer;
import assessment.android.istar.com.androidassessment.assessment_util.SubmitAssessmentAsyncTask;
import assessment.android.istar.com.androidassessment.assessment_util.ViewpagerAdapter;
import assessment.android.istar.com.androidassessment.istarindia.utils.SingletonStudent;
import assessment.android.istar.com.androidassessment.template.MultipleOptionMultipleChoice;
import assessment.android.istar.com.androidassessment.template.MultipleOptionSingleChoice;

/**
 * Created by Feroz on 14-12-2016.
 */

public class CMSAssessmentFragment extends Fragment {
    public final static String ASSESSMENT_ID = "ASSESSMENT_ID";
    private AssessmentLockableViewPager assessmentLockableViewPager;
    private AssessmentDataHandler assessmentDataHandler;
    private ViewpagerAdapter viewpagerAdapter;
    private int assessment_id;
    private CMSAssessmentResult cmsAssessmentResult;
    private ArrayList<Entry> question_map, question_time;
    private long start_time, end_time;
    private Toolbar toolbar;
    private TextView number_of_ques;
    private TextView progress_text;
    private Toast mToastToShow;
    private CountDownTimer countDownTimer;
    private int delay = 120000;
    private int progress_status = 0;
    private ProgressBar prograss_bar;
    private AssessmentStatusHandler assessmentStatusHandler;
    private Button submit_question;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cms_assessment_fragment, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        submit_question = (Button) view.findViewById(R.id.submit_question);
        number_of_ques = (TextView) view.findViewById(R.id.number_of_ques);
        progress_text = (TextView) view.findViewById(R.id.progress_text);
        mToastToShow = Toast.makeText(view.getContext(), "Hurry Up.!\nlast 1 MIN Left.", Toast.LENGTH_SHORT);
        prograss_bar = (ProgressBar) view.findViewById(R.id.prograss_bar);
        prograss_bar.setIndeterminate(false);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        assessmentLockableViewPager = (AssessmentLockableViewPager) view.findViewById(R.id.assessment_viewpager);
        assessmentDataHandler = new AssessmentDataHandler(getContext());
        assessmentStatusHandler = new AssessmentStatusHandler(getContext());
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
        assessmentLockableViewPager.setSwipeLocked(true);

        cmsAssessmentResult.setAssessment_id(assessment_id + "");
        cmsAssessmentResult.setUser_id(SingletonStudent.getInstance().getStudent().getId() + "");
        question_map = new ArrayList<>();
        question_time = new ArrayList<>();
        try {
            Cursor c = assessmentDataHandler.getData(assessment_id);
            if (c.moveToFirst()) {
                setupOfflineAssement(c.getString(1));
            } else {
                fetchAssessmentFromServer(assessment_id, assessmentDataHandler, viewpagerAdapter, assessmentLockableViewPager);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fetchAssessmentFromServer(assessment_id, assessmentDataHandler, viewpagerAdapter, assessmentLockableViewPager);
        }

        submit_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCmsAssesmentResult();
            }
        });


        return view;
    }

    private void setupOfflineAssement(String assessment_string) {
        StringReader reader = new StringReader(assessment_string);
        Serializer serializer = new Persister();
        try {
            CMSAssessment cmsAssessment = serializer.read(CMSAssessment.class, reader);

            viewpagerAdapter = new ViewpagerAdapter(getChildFragmentManager(), cmsAssessment);
            assessmentLockableViewPager.setAdapter(viewpagerAdapter);
            delay = cmsAssessment.getAssessmentDurationMinutes() * 60000;
            start_time = System.currentTimeMillis();

            try {
                toolbar.setBackgroundColor(Color.parseColor(cmsAssessment.getTheme().getBackgroundColor()));
                progress_text.setTextColor(Color.parseColor(cmsAssessment.getTheme().getTitleFontColor()));
                number_of_ques.setTextColor(Color.parseColor(cmsAssessment.getTheme().getTitleFontColor()));
            } catch (Exception e) {
            }

            //update the slide pointer.
            setupOfflineAssessmentSlide(cmsAssessment);
            setupObject();


        } catch (Exception e) {

        }
    }

    private void setupOfflineAssessmentSlide(CMSAssessment cmsAssessment) {
        try {
            Cursor c = assessmentStatusHandler.getData(assessment_id);
            if (c.moveToFirst()) {
                if (c.getString(2).equalsIgnoreCase("INCOMPLETED")) {
                    Serializer serializer = new Persister();
                    serializer.read(cmsAssessmentResult, c.getString(1));
                    int last_pointer = Integer.parseInt(c.getString(3));
                    delay = (cmsAssessment.getAssessmentDurationMinutes() * 60000) - Integer.parseInt(cmsAssessmentResult.getTotal_time());
                    assessmentLockableViewPager.setCurrentItem(last_pointer);

                    for (Entry entry : cmsAssessmentResult.getQuestion_map()) {
                        question_map.add(entry);
                    }
                    for (Entry entry : cmsAssessmentResult.getQuestion_time()) {
                        question_time.add(entry);
                    }
                    start_time = System.currentTimeMillis() - (Long.parseLong(cmsAssessmentResult.getTotal_time()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchAssessmentFromServer(int assessment_id, AssessmentDataHandler assessmentDataHandler, ViewpagerAdapter viewpagerAdapter, AssessmentLockableViewPager viewpager) {
        new FetchAssessmentFromServer(getContext(), viewpagerAdapter, assessmentLockableViewPager,
                assessmentDataHandler, getChildFragmentManager(), countDownTimer, number_of_ques, prograss_bar, progress_text, start_time, toolbar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, assessment_id + "");

    }

    public void setupObject() {
        updateslidePointerText();
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

                //send to next fragment and submit data.
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new NextFragment()).commit();
            }
        }.start();
        //visible toolbar
        toolbar.setVisibility(View.VISIBLE);
    }

    public void previousViewpager() {
        if (assessmentLockableViewPager.getCurrentItem() != 0) {
            assessmentLockableViewPager.setCurrentItem(assessmentLockableViewPager.getCurrentItem() - 1);
        }
    }

    public void nextViewpager(String key, String answer, String time) {
        if (assessmentLockableViewPager.getCurrentItem() != (assessmentLockableViewPager.getAdapter().getCount() - 1)) {
            assessmentLockableViewPager.setCurrentItem(assessmentLockableViewPager.getCurrentItem() + 1);
            addData(key, answer, time);
        }
    }

    void addData(String key, String answer, String time) {
        question_map.add(new Entry(key, answer));
        question_time.add(new Entry(key, time));
    }


    public void updateslidePointerText() {
        try {
            if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount() - 1) {
                number_of_ques.setText("");
                toolbar.setVisibility(View.GONE);
            } else {
                number_of_ques.setText((assessmentLockableViewPager.getCurrentItem() + 1) + "/" + (assessmentLockableViewPager.getAdapter().getCount() - 1));
                toolbar.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CMSAssessmentResult getAllAssmentResult() {
        cmsAssessmentResult.setQuestion_map(question_map);
        cmsAssessmentResult.setQuestion_time(question_time);
        end_time = System.currentTimeMillis();
        cmsAssessmentResult.setTotal_time((end_time - start_time) + "");
        return cmsAssessmentResult;
    }


    private void updateCmsAssesmentResult() {
        try {
            final String key, value, time;
            final long start_time;

            if (viewpagerAdapter.getItem(assessmentLockableViewPager.getCurrentItem()) instanceof MultipleOptionSingleChoice) {
                key = ((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_key)).getText().toString();
                value = ((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_value)).getText().toString();
                start_time = Long.parseLong(((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_time)).getText().toString());

            } else {
                key = ((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_key)).getText().toString();
                value = ((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_value)).getText().toString();
                start_time = Long.parseLong(((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_time)).getText().toString());
            }
            time = ((System.currentTimeMillis() - start_time) / 1000) + "";

            if (value != null && !value.equalsIgnoreCase("")) {
                System.out.println("-----------selected Ansewer-----> " + value);
                nextViewpager(key, value, time);
            } else {
                new MaterialDialog.Builder(getContext())
                        .title(R.string.content_for_skip_title)
                        .content(R.string.content_for_skip)
                        .positiveText("Yes")
                        .negativeText("No")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                nextViewpager(key, -1 + "", time);
                                dialog.dismiss();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        try {
            if (getAllAssmentResult() != null) {
                if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount() - 1) {
                    new SubmitAssessmentAsyncTask(getContext().getApplicationContext(), cmsAssessmentResult, assessmentLockableViewPager.getCurrentItem()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } else {
                    AssessmentStatusHandler assessmentStatusHandler = new AssessmentStatusHandler(getContext());
                    Serializer serializer = new Persister();
                    StringWriter stringWriter = new StringWriter();
                    serializer.write(cmsAssessmentResult, stringWriter);
                    String value = stringWriter.toString();
                    System.out.println("value---------------->\n" + value);
                    assessmentStatusHandler.saveContent(assessment_id + "", value, "INCOMPLETED", assessmentLockableViewPager.getCurrentItem() + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
