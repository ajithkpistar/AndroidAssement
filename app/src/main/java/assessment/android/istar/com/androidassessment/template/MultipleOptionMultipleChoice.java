package assessment.android.istar.com.androidassessment.template;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.andexert.library.RippleView;

import assessment.android.istar.com.androidassessment.CMSAssessmentFragment;
import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;


public class MultipleOptionMultipleChoice extends AssessmentCard {

    private WebView question, passage;
    private TextView option1, option2, option3, option4, option5;
    private int position;
    private CMSQuestion cmsQuestion;
    private long start_time;
    private ScrollView mainLayout;
    private AppCompatCheckBox checkbtn1, checkbtn2, checkbtn3, checkbtn4, checkbtn5;
    private View view;
    private TextView hidden_key, hidden_value, hidden_time, submit;
    private ThemeUtils themeutil;
    private RelativeLayout label_view;
    private RippleView layoutBtn1, layoutBtn2, layoutBtn3, layoutBtn4, layoutBtn5, layoutBtn6;
    private String optionCardColor = "#ffffff";
    private CountDownTimer countDownTimer;
    private Boolean submitCheck = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_fragment_multiple_option_multiple_choice, container, false);

        //hardware acceleration disable
        try {
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (Exception e) {
        }

        label_view = (RelativeLayout) view.findViewById(R.id.label_view);

        mainLayout = (ScrollView) view.findViewById(R.id.mainLayout);
        submit = (TextView) view.findViewById(R.id.submit);
        question = (WebView) view.findViewById(R.id.question);
        passage = (WebView) view.findViewById(R.id.passage);
        option1 = (TextView) view.findViewById(R.id.option1);
        option2 = (TextView) view.findViewById(R.id.option2);
        option3 = (TextView) view.findViewById(R.id.option3);
        option4 = (TextView) view.findViewById(R.id.option4);
        option5 = (TextView) view.findViewById(R.id.option5);

        checkbtn1 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn1);
        checkbtn2 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn2);
        checkbtn3 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn3);
        checkbtn4 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn4);
        checkbtn5 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn5);

        layoutBtn1 = (RippleView) view.findViewById(R.id.layoutBtn1);
        layoutBtn2 = (RippleView) view.findViewById(R.id.layoutBtn2);
        layoutBtn3 = (RippleView) view.findViewById(R.id.layoutBtn3);
        layoutBtn4 = (RippleView) view.findViewById(R.id.layoutBtn4);
        layoutBtn5 = (RippleView) view.findViewById(R.id.layoutBtn5);
        layoutBtn6 = (RippleView) view.findViewById(R.id.layoutBtn6);

        hidden_key = (TextView) view.findViewById(R.id.hidden_key);
        hidden_value = (TextView) view.findViewById(R.id.hidden_value);
        hidden_time = (TextView) view.findViewById(R.id.hidden_time);
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();

        themeutil = new ThemeUtils();

        if (getArguments() != null) {
            if (getArguments().getSerializable(AssessmentCard.CMSASSESSMENT) != null) {
                cmsQuestion = (CMSQuestion) getArguments().getSerializable(AssessmentCard.CMSASSESSMENT);
                if (getArguments().getInt(AssessmentCard.POSITION, -1) != -1)
                    position = getArguments().getInt(AssessmentCard.POSITION, -1);
            }
        }

        if (cmsQuestion != null) {
            if (cmsQuestion.getQuestionText() != null) {
                hidden_key.setText(cmsQuestion.getId() + "");
                themeutil.getThemeQuestion(cmsQuestion, question, getActivity(), externalReadable);
            }

            if (cmsQuestion.getComprehensive_passage() != null) {
                themeutil.getThemePassage(cmsQuestion, passage, getActivity(), externalReadable);
            }

            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {

                        themeutil.getThemeMultipleOption(cmsQuestion, option1, checkbtn1, layoutBtn1, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 1) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option2, checkbtn2, layoutBtn2, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 2) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option3, checkbtn3, layoutBtn3, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 3) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option4, checkbtn4, layoutBtn4, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 4) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option5, checkbtn5, layoutBtn5, cmsOption, getActivity(), externalReadable);
                    }
                    temp++;
                }
            }

            if (cmsQuestion.getTheme() != null) {
                mainLayout.setBackgroundColor(Color.parseColor("#0097a7"));
                label_view.setBackgroundColor(Color.parseColor("#0097a7"));
            }

        }

        layoutBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!submitCheck) {
                        countDownTimer = new CountDownTimer(350, 1000) { // adjust the milli seconds here
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                try {
                                    CMSAssessmentFragment.nextViewpager(hidden_key.getText().toString(), hidden_value.getText().toString(), ((System.currentTimeMillis() - start_time) / 1000) + "");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        submitCheck = true;
                    }
                } catch (Exception e) {
                }
            }
        });

        checkbtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        webviewSetup();
        return view;
    }

    private void updateValues() {
        String result = "";
        boolean checkbox1 = ((CheckBox) view.findViewById(R.id.checkbtn1)).isChecked();
        if (checkbox1 == true) {
            result = result + "," + checkbtn1.getTag();
        }
        boolean checkbox2 = ((CheckBox) view.findViewById(R.id.checkbtn2)).isChecked();
        if (checkbox2 == true) {
            result = result + "," + checkbtn2.getTag();
        }
        boolean checkbox3 = ((CheckBox) view.findViewById(R.id.checkbtn3)).isChecked();
        if (checkbox3 == true) {
            result = result + "," + checkbtn3.getTag();
        }
        boolean checkbox4 = ((CheckBox) view.findViewById(R.id.checkbtn4)).isChecked();
        if (checkbox4 == true) {
            result = result + "," + checkbtn4.getTag();
        }
        boolean checkbox5 = ((CheckBox) view.findViewById(R.id.checkbtn5)).isChecked();
        if (checkbox5 == true) {
            result = result + "," + checkbtn5.getTag();
        }
        result = result.replaceFirst("^,", "");

        if (result.equalsIgnoreCase("")) {
            result = "-1";
        }

        hidden_value.setText(result + "");
    }


    private void webviewSetup() {
        forceWebViewRedraw(layoutBtn1);
        forceWebViewRedraw(layoutBtn2);
        forceWebViewRedraw(layoutBtn3);
        forceWebViewRedraw(layoutBtn4);
        forceWebViewRedraw(layoutBtn5);

        question.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

    }


    private void forceWebViewRedraw(final RippleView rippleView) {

        if (rippleView != null) {
            rippleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (view.getId()) {
                        case R.id.layoutBtn1:
                            if (checkbtn1.isChecked()) {
                                checkbtn1.setChecked(false);
                                layoutBtn1.setBackgroundColor(Color.parseColor(optionCardColor));
                            } else {
                                checkbtn1.setChecked(true);
                                layoutBtn1.setBackgroundColor(getResources().getColor(R.color.selectedOption));
                            }
                            break;
                        case R.id.layoutBtn2:
                            if (checkbtn2.isChecked()) {
                                checkbtn2.setChecked(false);
                                layoutBtn2.setBackgroundColor(Color.parseColor(optionCardColor));
                            } else {
                                checkbtn2.setChecked(true);
                                layoutBtn2.setBackgroundColor(getResources().getColor(R.color.selectedOption));
                            }
                            break;
                        case R.id.layoutBtn3:
                            if (checkbtn3.isChecked()) {
                                checkbtn3.setChecked(false);
                                layoutBtn3.setBackgroundColor(Color.parseColor(optionCardColor));

                            } else {
                                checkbtn3.setChecked(true);
                                layoutBtn3.setBackgroundColor(getResources().getColor(R.color.selectedOption));
                            }
                            break;
                        case R.id.layoutBtn4:
                            if (checkbtn4.isChecked()) {
                                checkbtn4.setChecked(false);
                                layoutBtn4.setBackgroundColor(Color.parseColor(optionCardColor));

                            } else {
                                checkbtn4.setChecked(true);
                                layoutBtn4.setBackgroundColor(getResources().getColor(R.color.selectedOption));

                            }
                            break;
                        case R.id.layoutBtn5:
                            if (checkbtn5.isChecked()) {
                                checkbtn5.setChecked(false);
                                layoutBtn5.setBackgroundColor(Color.parseColor(optionCardColor));
                            } else {
                                checkbtn5.setChecked(true);
                                layoutBtn5.setBackgroundColor(getResources().getColor(R.color.selectedOption));
                            }
                            break;
                    }
                    updateValues();
                }
            });
        }

    }


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
