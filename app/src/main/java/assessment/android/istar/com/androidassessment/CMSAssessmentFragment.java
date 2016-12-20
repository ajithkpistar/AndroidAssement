package assessment.android.istar.com.androidassessment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import assessment.android.istar.com.androidassessment.assessment_database.AssessmentDataHandler;
import assessment.android.istar.com.androidassessment.assessment_database.AssessmentStatusHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;
import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;
import assessment.android.istar.com.androidassessment.assessment_result.Entry;
import assessment.android.istar.com.androidassessment.assessment_util.AssessmentLockableViewPager;
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
    private TextView number_of_ques, progress_text;
    private Toast mToastToShow;
    private CountDownTimer countDownTimer, questionTimer;
    private int delay = 120000;
    private int progress_status = 0;
    private ProgressBar prograss_bar;
    private AssessmentStatusHandler assessmentStatusHandler;
    private Button submit_question;
    private ArrayList<Integer> questionTimerData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cms_assessment_fragment, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        submit_question = (Button) view.findViewById(R.id.submit_question);
        number_of_ques = (TextView) view.findViewById(R.id.number_of_ques);
        progress_text = (TextView) view.findViewById(R.id.progress_text);
        prograss_bar = (ProgressBar) view.findViewById(R.id.prograss_bar);
        prograss_bar.setIndeterminate(false);
        mToastToShow = Toast.makeText(view.getContext(), "Hurry Up.!\n1 Minute left to submit assessment", Toast.LENGTH_LONG);
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
                if (assessmentLockableViewPager != null && assessmentLockableViewPager.getCurrentItem() != assessmentLockableViewPager.getAdapter().getCount() - 1
                        && questionTimerData != null && questionTimerData.get(assessmentLockableViewPager.getCurrentItem()) != null)
                    setUpQuestionTimer(questionTimerData.get(assessmentLockableViewPager.getCurrentItem()));
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
                updateCmsAssesmentResult(false);
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
            updateQuestionTimer(cmsAssessment);
            //update the slide pointer.
            setupOfflineAssessmentSlide(cmsAssessment);
            setupObject();


        } catch (Exception e) {
            e.printStackTrace();
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
        new FetchAssessmentFromServer(getContext(), getChildFragmentManager()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, assessment_id + "");

    }

    public void setupObject() {
        updateslidePointerText();
        progress_status = 0;
        prograss_bar.setMax(delay / 1000);
        countDownTimer = new CountDownTimer(delay, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                try {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFinish() {
                try {
                    progress_text.setText("00:00");
                    prograss_bar.setProgress(0);
                    progress_status = 0;

                    if (assessmentLockableViewPager.getCurrentItem() != assessmentLockableViewPager.getAdapter().getCount() - 1) {
                        updateCmsAssesmentResult(true);
                    }

                    //send to next fragment and submit data.
                    if (getActivity() != null)
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new NextFragment()).commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //start question Timer
        if (questionTimerData != null && assessmentLockableViewPager != null && questionTimerData.get(assessmentLockableViewPager.getCurrentItem()) != null)
            setUpQuestionTimer(questionTimerData.get(assessmentLockableViewPager.getCurrentItem()));
        //visible toolbar
        toolbar.setVisibility(View.VISIBLE);
    }

    private void setUpQuestionTimer(long questionDelay) {
        if (questionTimer != null) {
            questionTimer.cancel();
            questionTimer = null;
        }
        questionTimer = new CountDownTimer(questionDelay, 1000) {
            public void onTick(long millisUntilFinished) {
                try {
                    long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));

                    if (min == 0 && sec == 10) {
                        Toast.makeText(getContext(), "Hurry Up.!\n" + "10 Second is left for Answer this question", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFinish() {
                try {
                    updateCmsAssesmentResult(true);
                    if (assessmentLockableViewPager.getCurrentItem() == assessmentLockableViewPager.getAdapter().getCount() - 1) {
                        if (getActivity() != null)
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new NextFragment()).commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
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


    private void updateCmsAssesmentResult(boolean flag) {
        try {
            final String key, value, time;
            final long start_time_of_question;

            if (viewpagerAdapter.getItem(assessmentLockableViewPager.getCurrentItem()) instanceof MultipleOptionSingleChoice) {
                key = ((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_key)).getText().toString();
                value = ((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_value)).getText().toString();
                start_time_of_question = Long.parseLong(((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_time)).getText().toString());

            } else {
                key = ((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_key)).getText().toString();
                value = ((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_value)).getText().toString();
                start_time_of_question = Long.parseLong(((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_time)).getText().toString());
            }
            time = ((System.currentTimeMillis() - start_time_of_question) / 1000) + "";

            if (value != null && !value.equalsIgnoreCase("")) {
                System.out.println("-----------selected Ansewer-----> " + value);
                nextViewpager(key, value, time);
            } else if (flag) {
                nextViewpager(key, -1 + "", time);
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


    public class FetchAssessmentFromServer extends AsyncTask<String, Integer, String> {

        private Context context;
        private FragmentManager fm;
        boolean response_success = true;

        public FetchAssessmentFromServer(Context context, FragmentManager fm) {
            this.context = context;
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
                assessmentDataHandler.saveContent(params[0], xml_object);
            } catch (Exception e) {
                response_success = false;
                e.printStackTrace();
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
                    start_time = System.currentTimeMillis();

                    try {
                        toolbar.setBackgroundColor(Color.parseColor(cmsAssessment.getTheme().getBackgroundColor()));
                        progress_text.setTextColor(Color.parseColor(cmsAssessment.getTheme().getTitleFontColor()));
                        number_of_ques.setTextColor(Color.parseColor(cmsAssessment.getTheme().getTitleFontColor()));
                    } catch (Exception e) {
                    }

                    updateQuestionTimer(cmsAssessment);
                    //update the slide pointer.
                    setupOfflineAssessmentSlide(cmsAssessment);
                    if (response_success)
                        setupObject();
                } catch (Exception e) {

                }
            }
        }
    }


    private void updateQuestionTimer(CMSAssessment cmsAssessment) {
        questionTimerData = new ArrayList<>();
        if (cmsAssessment != null && cmsAssessment.getAssessmentDurationMinutes() != null && cmsAssessment.getQuestions() != null && cmsAssessment.getQuestions().size() > 0) {
            List<CMSQuestion> cmsQuestions = cmsAssessment.getQuestions();

            for (CMSQuestion cmsQuestion : cmsQuestions) {
                int questionDuration = cmsQuestion.getDurationInSec() * 1000;
                questionTimerData.add(questionDuration);
            }
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
                    assessmentStatusHandler.saveContent(assessment_id + "", value, "INCOMPLETED", (assessmentLockableViewPager.getCurrentItem()) + "");
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
        if (questionTimer != null) {
            questionTimer.cancel();
            questionTimer = null;
        }
    }
}