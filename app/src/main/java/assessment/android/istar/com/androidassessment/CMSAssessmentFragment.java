package assessment.android.istar.com.androidassessment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    private RelativeLayout main_layout;
    private TextView number_of_ques, progress_text;
    private Toast mToastToShow;
    private CountDownTimer countDownTimer, questionTimer;
    private int delay = 120000;
    private int progress_status = 0, question_progress_status = 0;
    private ProgressBar prograss_bar, question_prograss_bar;
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
        question_prograss_bar = (ProgressBar) view.findViewById(R.id.question_prograss_bar);
        main_layout = (RelativeLayout) view.findViewById(R.id.main_layout);
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
                try {
                    if (assessmentLockableViewPager != null && assessmentLockableViewPager.getCurrentItem() != assessmentLockableViewPager.getAdapter().getCount() - 1
                            && questionTimerData != null && questionTimerData.get(assessmentLockableViewPager.getCurrentItem()) != null) {
                        setUpQuestionTimer(questionTimerData.get(assessmentLockableViewPager.getCurrentItem()));
                    } else {
                        if (questionTimer != null) {
                            questionTimer.cancel();
                            questionTimer = null;
                        }
                    }
                } catch (Exception e) {
                    if (questionTimer != null) {
                        questionTimer.cancel();
                        questionTimer = null;
                    }
                }
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
                question_prograss_bar.setProgressDrawable(generateProgressDrawable(cmsAssessment.getTheme().getBackgroundColor()));
            } catch (Exception e) {
            }
            createQuestionTimerValues(cmsAssessment);
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
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
            progress_status = 0;
            prograss_bar.setProgress(0);
        }
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
        main_layout.setVisibility(View.VISIBLE);
    }

    private void setUpQuestionTimer(int questionDelay) {
        if (questionTimer != null) {
            questionTimer.cancel();
            questionTimer = null;
            question_progress_status = 0;
            question_prograss_bar.setProgress(0);
        }
        question_prograss_bar.setMax((questionDelay / 1000) * 10000);

        questionTimer = new CountDownTimer(questionDelay, 1000) {
            public void onTick(long millisUntilFinished) {
                try {
                    long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));

                    if (min == 0 && sec == 10) {
                        Toast.makeText(getContext(), "Hurry Up.!\n" + "10 Second is left for Answer this question", Toast.LENGTH_SHORT).show();
                    }
                    setProgressAnimate(question_prograss_bar, question_progress_status++);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFinish() {
                try {
                    question_progress_status = 0;
                    setProgressAnimate(question_prograss_bar, 0);
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

    private void setProgressAnimate(ProgressBar pb, int progressTo) {
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 10000);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
    }

    protected Drawable generateProgressDrawable(String colorProgress) {

        if (colorProgress.equalsIgnoreCase("#ffffff")) {
            colorProgress = "#000000";
        } else if (colorProgress.equalsIgnoreCase("#000000")) {
            colorProgress = "#ffffff";
        }

        final float[] roundedCorners = new float[]{1, 1, 1, 1, 1, 1, 1, 1};
        // Create a ShapeDrawable to generate progress bar background
        ShapeDrawable backgroundDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners, null, null));
        backgroundDrawable.getPaint().setColor(Color.parseColor("#E0E0E0"));

        // Initialize a new shape drawable to draw progress bar progress
        ShapeDrawable progressDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners, null, null));
        //progressDrawable.getPaint().set(paint);
        progressDrawable.getPaint().setColor(Color.parseColor(colorProgress));

        // Another shape drawable to draw secondary progress
        ShapeDrawable secondaryProgressDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners, null, null));
        secondaryProgressDrawable.getPaint().setColor(Color.parseColor(colorProgress));

        // Initialize a ClipDrawable to generate progress on progress bar
        ClipDrawable progressClip = new ClipDrawable(progressDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        // Another clip drawable to draw secondary progress
        ClipDrawable secondaryProgressClip = new ClipDrawable(secondaryProgressDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        // Initialize a new LayerDrawable to hold progress bar all states
        LayerDrawable layer = new LayerDrawable(new Drawable[]{backgroundDrawable, secondaryProgressClip, progressClip});

        // Set the ids for different layers on layer drawable
        layer.setId(0, android.R.id.background);
        layer.setId(1, android.R.id.secondaryProgress);
        layer.setId(2, android.R.id.progress);

        // Return the LayerDrawable as progress bar progress drawable
        return layer;
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
                main_layout.setVisibility(View.GONE);
            } else {
                number_of_ques.setText((assessmentLockableViewPager.getCurrentItem() + 1) + "/" + (assessmentLockableViewPager.getAdapter().getCount() - 1));
                main_layout.setVisibility(View.VISIBLE);
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
            long start_time_of_question;

            if (viewpagerAdapter.getItem(assessmentLockableViewPager.getCurrentItem()) instanceof MultipleOptionSingleChoice) {
                key = ((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_key)).getText().toString();
                value = ((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_value)).getText().toString();
                start_time_of_question = Long.parseLong(((TextView) ((MultipleOptionSingleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_time)).getText().toString());

            } else {
                key = ((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_key)).getText().toString();
                value = ((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_value)).getText().toString();
                start_time_of_question = Long.parseLong(((TextView) ((MultipleOptionMultipleChoice) viewpagerAdapter.instantiateItem(assessmentLockableViewPager, assessmentLockableViewPager.getCurrentItem())).getView().findViewById(R.id.hidden_time)).getText().toString());
            }
            if (assessmentLockableViewPager != null && assessmentLockableViewPager.getCurrentItem() == 0) {
                start_time_of_question = start_time;
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
                        question_prograss_bar.setProgressDrawable(generateProgressDrawable(cmsAssessment.getTheme().getBackgroundColor()));
                    } catch (Exception e) {
                    }

                    createQuestionTimerValues(cmsAssessment);
                    //update the slide pointer.
                    setupOfflineAssessmentSlide(cmsAssessment);
                    if (response_success)
                        setupObject();
                } catch (Exception e) {

                }
            }
        }
    }


    private void createQuestionTimerValues(CMSAssessment cmsAssessment) {
        try {
            questionTimerData = new ArrayList<>();
            if (cmsAssessment != null && cmsAssessment.getAssessmentDurationMinutes() != null && cmsAssessment.getQuestions() != null && cmsAssessment.getQuestions().size() > 0) {
                List<CMSQuestion> cmsQuestions = cmsAssessment.getQuestions();

                for (CMSQuestion cmsQuestion : cmsQuestions) {
                    if (cmsQuestion != null && cmsQuestion.getDurationInSec() != null && cmsQuestion.getDurationInSec() != 0) {
                        int questionDuration = cmsQuestion.getDurationInSec() * 1000;
                        questionTimerData.add(questionDuration);
                    } else {
                        questionTimerData.add(0);
                    }
                }
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
                    AssessmentStatusHandler assessmentStatusHandler = new AssessmentStatusHandler(getContext());
                    Serializer serializer = new Persister();
                    StringWriter stringWriter = new StringWriter();
                    serializer.write(cmsAssessmentResult, stringWriter);
                    String value = stringWriter.toString();
                    assessmentStatusHandler.saveContent(assessment_id + "", value, "COMPLETED", (assessmentLockableViewPager.getCurrentItem()) + "");

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