package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import assessment.android.istar.com.androidassessment.CMSAssessmentFragment;
import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;


public class MultipleOptionMultipleChoice extends AssessmentCard {

    private WebView question, option1, option2, option3, option4, option5;
    private int position;
    private CMSQuestion cmsQuestion;
    private long start_time, end_time;
    private CardView cv;
    private CheckBox checkbtn1, checkbtn2, checkbtn3, checkbtn4, checkbtn5;
    private View view;
    private TextView hidden_key, hidden_value, hidden_time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_multiple_option_multiple_choice, container, false);
        cv = (CardView) view.findViewById(R.id.cv);

        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);

        checkbtn1 = (CheckBox) view.findViewById(R.id.checkbtn1);
        checkbtn2 = (CheckBox) view.findViewById(R.id.checkbtn2);
        checkbtn3 = (CheckBox) view.findViewById(R.id.checkbtn3);
        checkbtn4 = (CheckBox) view.findViewById(R.id.checkbtn4);
        checkbtn5 = (CheckBox) view.findViewById(R.id.checkbtn5);

        hidden_key = (TextView) view.findViewById(R.id.hidden_key);
        hidden_value = (TextView) view.findViewById(R.id.hidden_value);
        hidden_time = (TextView) view.findViewById(R.id.hidden_time);

        ThemeUtils themeutil = new ThemeUtils();

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
                hidden_key.setText(cmsQuestion.getId() + "");
                themeutil.getThemeQuestion(cmsQuestion, question);
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {

                        themeutil.getThemeMultipleOption(cmsQuestion, option1, checkbtn1, cmsOption);
                    }
                    if (temp == 1) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option2, checkbtn2, cmsOption);
                    }
                    if (temp == 2) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option3, checkbtn3, cmsOption);
                    }
                    if (temp == 3) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option4, checkbtn4, cmsOption);
                    }
                    if (temp == 4) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option5, checkbtn5, cmsOption);
                    }
                    temp++;
                }
            }

            if (cmsQuestion.getTheme() != null) {

                cv.setBackgroundColor(Color.parseColor(cmsQuestion.getTheme().getBackgroundColor()));
            }

        }

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

        hidden_value.setText(result + "");
    }


}
