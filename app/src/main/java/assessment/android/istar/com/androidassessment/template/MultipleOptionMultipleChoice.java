package assessment.android.istar.com.androidassessment.template;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;


public class MultipleOptionMultipleChoice extends AssessmentCard {

    private WebView question, option1, option2, option3, option4, option5;
    private int position;
    private CMSQuestion cmsQuestion;
    private long start_time;
    private ScrollView mainLayout;
    private AppCompatCheckBox checkbtn1, checkbtn2, checkbtn3, checkbtn4, checkbtn5;
    private View view;
    private TextView hidden_key, hidden_value, hidden_time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_multiple_option_multiple_choice, container, false);
        mainLayout = (ScrollView) view.findViewById(R.id.mainLayout);
        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);

        checkbtn1 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn1);
        checkbtn2 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn2);
        checkbtn3 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn3);
        checkbtn4 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn4);
        checkbtn5 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn5);

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
                mainLayout.setBackgroundColor(Color.parseColor(cmsQuestion.getTheme().getBackgroundColor()));
                setColorforCheckBox();
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

    private void setColorforCheckBox() {
        try {
            String color = "#000000";
            try {
                color = cmsQuestion.getTheme().getListitemFontColor();
            } catch (Exception e) {
            }
            int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
            int[] colors = new int[]{Color.parseColor(color), Color.parseColor(color)};
            ColorStateList colorStateList = new ColorStateList(states, colors);
            checkbtn1.setSupportButtonTintList(colorStateList);
            checkbtn2.setSupportButtonTintList(colorStateList);
            checkbtn3.setSupportButtonTintList(colorStateList);
            checkbtn4.setSupportButtonTintList(colorStateList);
            checkbtn5.setSupportButtonTintList(colorStateList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
