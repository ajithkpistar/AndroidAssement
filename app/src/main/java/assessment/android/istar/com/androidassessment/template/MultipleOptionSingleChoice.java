package assessment.android.istar.com.androidassessment.template;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

/**
 * Created by Feroz on 14-12-2016.
 */

public class MultipleOptionSingleChoice extends AssessmentCard {

    private WebView question;
    WebView option1, option2, option3, option4, option5;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;
    private int position;
    private CMSQuestion cmsQuestion;
    private Button submitbtn;
    public RadioGroup Rgroup;
    private RadioButton radioButton;
    private long start_time, end_time;
    CardView cv;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.multipleoption_singlechoice, container, false);
        start_time = System.currentTimeMillis();
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

        submitbtn = (Button) view.findViewById(R.id.submitbtn);
      Rgroup = (RadioGroup) view.findViewById(R.id.options);

        if (getArguments() != null) {
            if (getArguments().getSerializable(AssessmentCard.CMSASSESSMENT) != null) {
                cmsQuestion = (CMSQuestion) getArguments().getSerializable(AssessmentCard.CMSASSESSMENT);
                if (getArguments().getInt(AssessmentCard.POSITION, -1) != -1)
                    position = getArguments().getInt(AssessmentCard.POSITION, -1);
            }
        }

        if (cmsQuestion != null) {
            if (cmsQuestion.getQuestionText() != null) {
                themeutil.getThemeQuestion(cmsQuestion, question,getActivity());
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
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

                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);

            } });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                rbtn1.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);

            } });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                rbtn2.setChecked(false);
                rbtn1.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);

            } });
        rbtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn1.setChecked(false);
                rbtn5.setChecked(false);

            } });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn5.setChecked(false);
                rbtn1.setChecked(false);

            } });



        submitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

String selectedVal="";
                boolean radiobtn1 = ((RadioButton) view.findViewById(R.id.rbtn1)).isChecked();
                if(radiobtn1 == true){

                    selectedVal = rbtn1.getTag().toString();
                }
                boolean radiobtn2 = ((RadioButton) view.findViewById(R.id.rbtn2)).isChecked();
                if(radiobtn2 == true){
                    selectedVal = rbtn2.getTag().toString();

                }
                boolean radiobtn3 = ((RadioButton) view.findViewById(R.id.rbtn3)).isChecked();
                if(radiobtn3 == true){
                    selectedVal = rbtn3.getTag().toString();

                }
                boolean radiobtn4 = ((RadioButton) view.findViewById(R.id.rbtn4)).isChecked();
                if(radiobtn4 == true){
                    selectedVal = rbtn4.getTag().toString();

                }
                boolean radiobtn5 = ((RadioButton) view.findViewById(R.id.rbtn5)).isChecked();
                if(radiobtn5 == true){
                    selectedVal = rbtn5.getTag().toString();

                }

                end_time = System.currentTimeMillis();

                if (selectedVal != null && !selectedVal.equalsIgnoreCase("")) {
                    System.out.println("--selectedVal-->"+selectedVal);
                    CMSAssessmentFragment.nextViewpager(cmsQuestion.getId() + "", selectedVal, (end_time - start_time) / 1000 + "");
                } else {
                    new MaterialDialog.Builder(getContext())
                            .title(R.string.app_name)
                            .content(R.string.content_for_skip)
                            .positiveText(R.string.agree)
                            .negativeText(R.string.disagree)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    CMSAssessmentFragment.nextViewpager(cmsQuestion.getId() + "", -1 + "", (end_time - start_time) / 1000 + "");
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
            }
        });

        return view;
    }

}
