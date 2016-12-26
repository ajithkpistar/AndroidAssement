package assessment.android.istar.com.androidassessment.template;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.andexert.library.RippleView;


import assessment.android.istar.com.androidassessment.CMSAssessmentFragment;
import assessment.android.istar.com.androidassessment.NextFragment;
import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

/**
 * Created by Feroz on 14-12-2016.
 */

public class MultipleOptionSingleChoice extends AssessmentCard {

    private WebView question, option1, option2, option3, option4, option5;
    private AppCompatRadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;
    private int position;
    private CMSQuestion cmsQuestion;
    private View view;
    private String selectedVal = "";
    private long start_time;
    private boolean chck_1 = false, chck_2 = false, chck_3 = false, chck_4 = false, chck_5 = false;
    private TextView hidden_key, hidden_value, hidden_time;
    private ScrollView mainLayout;
    private RelativeLayout label_view;
    private RippleView layoutBtn1, layoutBtn2, layoutBtn3, layoutBtn4, layoutBtn5;
    private ThemeUtils themeutil;
    private CountDownTimer countDownTimer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_multipleoption_singlechoice, container, false);

        //hardware acceleration disable
        try {
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (Exception e) {
        }
        label_view = (RelativeLayout) view.findViewById(R.id.label_view);
        mainLayout = (ScrollView) view.findViewById(R.id.mainLayout);
        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);

        rbtn1 = (AppCompatRadioButton) view.findViewById(R.id.rbtn1);
        rbtn2 = (AppCompatRadioButton) view.findViewById(R.id.rbtn2);
        rbtn3 = (AppCompatRadioButton) view.findViewById(R.id.rbtn3);
        rbtn4 = (AppCompatRadioButton) view.findViewById(R.id.rbtn4);
        rbtn5 = (AppCompatRadioButton) view.findViewById(R.id.rbtn5);

        layoutBtn1 = (RippleView) view.findViewById(R.id.layoutBtn1);
        layoutBtn2 = (RippleView) view.findViewById(R.id.layoutBtn2);
        layoutBtn3 = (RippleView) view.findViewById(R.id.layoutBtn3);
        layoutBtn4 = (RippleView) view.findViewById(R.id.layoutBtn4);
        layoutBtn5 = (RippleView) view.findViewById(R.id.layoutBtn5);


        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        themeutil = new ThemeUtils();

        hidden_key = (TextView) view.findViewById(R.id.hidden_key);
        hidden_value = (TextView) view.findViewById(R.id.hidden_value);
        hidden_time = (TextView) view.findViewById(R.id.hidden_time);
        if (getArguments() != null) {
            if (getArguments().getSerializable(AssessmentCard.CMSASSESSMENT) != null) {
                cmsQuestion = (CMSQuestion) getArguments().getSerializable(AssessmentCard.CMSASSESSMENT);
                if (getArguments().getInt(AssessmentCard.POSITION, -1) != -1)
                    position = getArguments().getInt(AssessmentCard.POSITION, -1);
            }
        }

        if (cmsQuestion != null) {
            if (cmsQuestion.getQuestionText() != null) {
                themeutil.getThemeQuestion(cmsQuestion, question, getActivity(), externalReadable);
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                hidden_key.setText(cmsQuestion.getId() + "");

                selectedVal = "";
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {
                        themeutil.getThemeSingleOption(cmsQuestion, option1, rbtn1, layoutBtn1, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 1) {
                        themeutil.getThemeSingleOption(cmsQuestion, option2, rbtn2, layoutBtn2, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 2) {
                        themeutil.getThemeSingleOption(cmsQuestion, option3, rbtn3, layoutBtn3, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 3) {
                        themeutil.getThemeSingleOption(cmsQuestion, option4, rbtn4, layoutBtn4, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 4) {
                        themeutil.getThemeSingleOption(cmsQuestion, option5, rbtn5, layoutBtn5, cmsOption, getActivity(), externalReadable);
                    }
                    temp++;
                }
            }
            if (cmsQuestion.getTheme() != null) {
                mainLayout.setBackgroundColor(Color.parseColor("#0097a7"));
                label_view.setBackgroundColor(Color.parseColor("#0097a7"));
                /*if(cmsQuestion.getTheme().getBackgroundColor().equalsIgnoreCase("#ffffff")){
                    optionCardColor="#e0e0e0";
                }*/
                selectUnselect(0);
            }
        }
        webviewSetup();
        return view;
    }

    public void createCountDownTimer() {
        try {
            countDownTimer = new CountDownTimer(350, 1000) { // adjust the milli seconds here
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    try {
                        CMSAssessmentFragment.nextViewpager(hidden_key.getText().toString(), selectedVal, ((System.currentTimeMillis() - start_time) / 1000) + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        } catch (Exception e) {

        }
    }

    public void selectUnselect(int position) {
        switch (position) {
            case 1:
                selectedVal = rbtn1.getTag().toString();

                chck_1 = true;
                chck_2 = false;
                chck_3 = false;
                chck_4 = false;
                chck_5 = false;
                break;
            case 2:
                selectedVal = rbtn2.getTag().toString();
                chck_2 = true;
                chck_1 = false;
                chck_3 = false;
                chck_4 = false;
                chck_5 = false;
                break;
            case 3:
                selectedVal = rbtn3.getTag().toString();
                chck_3 = true;
                chck_1 = false;
                chck_2 = false;
                chck_4 = false;
                chck_5 = false;
                break;
            case 4:
                selectedVal = rbtn4.getTag().toString();
                chck_4 = true;
                chck_1 = false;
                chck_2 = false;
                chck_3 = false;
                chck_5 = false;
                break;
            case 5:
                selectedVal = rbtn5.getTag().toString();
                chck_5 = true;
                chck_1 = false;
                chck_2 = false;
                chck_3 = false;
                chck_4 = false;
                break;
            default:
                selectedVal = "";
                break;
        }
        hidden_value.setText(selectedVal + "");
    }

    private void webviewSetup() {
        forceWebViewRedraw(question, null);
        forceWebViewRedraw(option1, layoutBtn1);
        forceWebViewRedraw(option2, layoutBtn2);
        forceWebViewRedraw(option3, layoutBtn3);
        forceWebViewRedraw(option4, layoutBtn4);
        forceWebViewRedraw(option5, layoutBtn5);
    }

    private void forceWebViewRedraw(final WebView mWebView, final RippleView rippleView) {

        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        if (rippleView != null){
            rippleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (view.getId()) {
                        case R.id.layoutBtn1:
                            layoutBtn1.setBackgroundColor(getResources().getColor(R.color.selectedOption));

                            if (chck_1) {
                                selectUnselect(0);
                                chck_1 = false;
                            } else {
                                selectUnselect(1);
                            }
                            break;
                        case R.id.layoutBtn2:
                            layoutBtn2.setBackgroundColor(getResources().getColor(R.color.selectedOption));

                            if (chck_2) {
                                selectUnselect(0);
                                chck_2 = false;
                            } else {
                                selectUnselect(2);
                            }
                            break;
                        case R.id.layoutBtn3:
                            layoutBtn3.setBackgroundColor(getResources().getColor(R.color.selectedOption));
                            if (chck_3) {
                                selectUnselect(0);
                                chck_3 = false;
                            } else {
                                selectUnselect(3);
                            }
                            break;
                        case R.id.layoutBtn4:
                            layoutBtn4.setBackgroundColor(getResources().getColor(R.color.selectedOption));
                            if (chck_4) {
                                selectUnselect(0);
                                chck_4 = false;
                            } else {
                                selectUnselect(4);
                            }
                            break;
                        case R.id.layoutBtn5:
                            layoutBtn5.setBackgroundColor(getResources().getColor(R.color.selectedOption));

                            if (chck_5) {
                                selectUnselect(0);
                                chck_5 = false;
                            } else {
                                selectUnselect(5);
                            }
                            break;
                    }
                    createCountDownTimer();
                }
            });
        }
    }

    /* mWebView.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;


            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (fingerState == FINGER_RELEASED) fingerState = FINGER_TOUCHED;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;

                            switch (view.getId()) {
                                case R.id.option1:
                                    if (chck_1) {
                                        selectUnselect(0);
                                        chck_1 = false;
                                    } else {
                                        selectUnselect(1);
                                    }
                                    break;
                                case R.id.option2:
                                    if (chck_2) {
                                        selectUnselect(0);
                                        chck_2 = false;
                                    } else {
                                        selectUnselect(2);
                                    }
                                    break;
                                case R.id.option3:
                                    if (chck_3) {
                                        selectUnselect(0);
                                        chck_3 = false;
                                    } else {
                                        selectUnselect(3);
                                    }
                                    break;
                                case R.id.option4:
                                    if (chck_4) {
                                        selectUnselect(0);
                                        chck_4 = false;
                                    } else {
                                        selectUnselect(4);
                                    }
                                    break;
                                case R.id.option5:
                                    if (chck_5) {
                                        selectUnselect(0);
                                        chck_5 = false;
                                    } else {
                                        selectUnselect(5);
                                    }
                                    break;
                            }
                            if (view.getId() != R.id.question)
                                createCountDownTimer();

                        } else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    default:
                        fingerState = FINGER_UNDEFINED;
                        break;
                }
                return false;
            }
        });*/

    /*private void setColorforRadioButton() {
        try {
            String color = "#000000";
            try {
                color = cmsQuestion.getTheme().getListitemFontColor();
            } catch (Exception e) {
            }
            int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
            int[] colors = new int[]{Color.parseColor(color), Color.parseColor(color)};
            ColorStateList colorStateList = new ColorStateList(states, colors);
            CompoundButtonCompat.setButtonTintList(rbtn1, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn2, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn3, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn4, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn5, colorStateList);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            if (isVisibleToUser) {
                start_time = System.currentTimeMillis();
                hidden_time.setText(start_time + "");
            }
        } catch (Exception e) {
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
