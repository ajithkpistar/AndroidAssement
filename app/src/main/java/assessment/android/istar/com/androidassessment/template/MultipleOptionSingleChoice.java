package assessment.android.istar.com.androidassessment.template;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

/**
 * Created by Feroz on 14-12-2016.
 */

public class MultipleOptionSingleChoice extends AssessmentCard {

    private WebView question,option1, option2, option3, option4, option5;
    private RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;
    private int position;
    private CMSQuestion cmsQuestion;
    private RadioGroup Rgroup;
    private CardView cv;
    private View view;
    private String selectedVal = "";
    private long start_time;
    private boolean chck_1 = false, chck_2 = false, chck_3 = false, chck_4 = false, chck_5 = false;
    private TextView hidden_key, hidden_value, hidden_time;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.multipleoption_singlechoice, container, false);

        cv = (CardView) view.findViewById(R.id.cv);
        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);

        rbtn1 = (RadioButton) view.findViewById(R.id.rbtn1);
        rbtn2 = (RadioButton) view.findViewById(R.id.rbtn2);
        rbtn3 = (RadioButton) view.findViewById(R.id.rbtn3);
        rbtn4 = (RadioButton) view.findViewById(R.id.rbtn4);
        rbtn5 = (RadioButton) view.findViewById(R.id.rbtn5);
        ThemeUtils themeutil = new ThemeUtils();

        Rgroup = (RadioGroup) view.findViewById(R.id.options);

        hidden_key = (TextView) view.findViewById(R.id.hidden_key);
        hidden_value = (TextView) view.findViewById(R.id.hidden_value);
        hidden_time = (TextView) view.findViewById(R.id.hidden_time);

        start_time = System.currentTimeMillis();
        hidden_time.setText(start_time + "");

        if (getArguments() != null) {
            if (getArguments().getSerializable(AssessmentCard.CMSASSESSMENT) != null) {
                cmsQuestion = (CMSQuestion) getArguments().getSerializable(AssessmentCard.CMSASSESSMENT);
                if (getArguments().getInt(AssessmentCard.POSITION, -1) != -1)
                    position = getArguments().getInt(AssessmentCard.POSITION, -1);
            }
        }

        if (cmsQuestion != null) {
            if (cmsQuestion.getQuestionText() != null) {
                themeutil.getThemeQuestion(cmsQuestion, question);
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                hidden_key.setText(cmsQuestion.getId() + "");

                selectedVal = "";
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {

                        themeutil.getThemeSingleOption(cmsQuestion, option1, rbtn1, cmsOption);
                    }
                    if (temp == 1) {
                        themeutil.getThemeSingleOption(cmsQuestion, option2, rbtn2, cmsOption);
                    }
                    if (temp == 2) {
                        themeutil.getThemeSingleOption(cmsQuestion, option3, rbtn3, cmsOption);
                    }
                    if (temp == 3) {
                        themeutil.getThemeSingleOption(cmsQuestion, option4, rbtn4, cmsOption);
                    }
                    if (temp == 4) {
                        themeutil.getThemeSingleOption(cmsQuestion, option5, rbtn5, cmsOption);
                    }
                    temp++;
                }
            }
            if (cmsQuestion.getTheme() != null) {
                cv.setBackgroundColor(Color.parseColor(cmsQuestion.getTheme().getBackgroundColor()));
            }

        }

        rbtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (chck_1) {
                    selectUnselect(0);
                    chck_1 = false;
                } else {
                    selectUnselect(1);
                }
            }
        });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (chck_2) {
                    selectUnselect(0);
                    chck_2 = false;
                } else {
                    selectUnselect(2);
                }

            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (chck_3) {
                    selectUnselect(0);
                    chck_3 = false;
                } else {
                    selectUnselect(3);
                }

            }
        });
        rbtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (chck_4) {
                    selectUnselect(0);
                    chck_4 = false;
                } else {
                    selectUnselect(4);
                }

            }
        });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (chck_5) {
                    selectUnselect(0);
                    chck_5 = false;
                } else {
                    selectUnselect(5);
                }
            }
        });

        return view;
    }


    public void selectUnselect(int position) {
        switch (position) {
            case 1:
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn1.getTag().toString();
                chck_1 = true;
                chck_2 = false;
                chck_3 = false;
                chck_4 = false;
                chck_5 = false;
                break;
            case 2:
                rbtn1.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn2.getTag().toString();
                chck_2 = true;
                chck_1 = false;
                chck_3 = false;
                chck_4 = false;
                chck_5 = false;
                break;
            case 3:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn3.getTag().toString();
                chck_3 = true;
                chck_1 = false;
                chck_2 = false;
                chck_4 = false;
                chck_5 = false;
                break;
            case 4:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn4.getTag().toString();
                chck_4 = true;
                chck_1 = false;
                chck_2 = false;
                chck_3 = false;
                chck_5 = false;
                break;
            case 5:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                selectedVal = rbtn5.getTag().toString();
                chck_5 = true;
                chck_1 = false;
                chck_2 = false;
                chck_3 = false;
                chck_4 = false;
                break;
            default:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = "";
                break;
        }
        hidden_value.setText(selectedVal + "");
    }
}
