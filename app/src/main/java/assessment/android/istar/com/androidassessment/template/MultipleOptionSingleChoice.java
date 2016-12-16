package assessment.android.istar.com.androidassessment.template;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
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
    private int position;
    private CMSQuestion cmsQuestion;
    private Button submitbtn;
    public RadioGroup Rgroup;
    private RadioButton radioButton;
    private long start_time, end_time;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.multipleoption_singlechoice, container, false);
        start_time = System.currentTimeMillis();
        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);
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
                question.loadDataWithBaseURL(null, cmsQuestion.getQuestionText(), "text/html", "utf-8", null);
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {
                        option1.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        option1.setTag(cmsOption.getId());
                        option1.setVisibility(View.VISIBLE);
                    }
                    if (temp == 1) {
                        option2.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        option2.setTag(cmsOption.getId());
                        option2.setVisibility(View.VISIBLE);
                    }
                    if (temp == 2) {
                        option3.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        option3.setTag(cmsOption.getId());
                        option3.setVisibility(View.VISIBLE);
                    }
                    if (temp == 3) {
                        option4.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        option4.setTag(cmsOption.getId());
                        option4.setVisibility(View.VISIBLE);
                    }
                    if (temp == 4) {
                        option5.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        option5.setTag(cmsOption.getId());
                        option5.setVisibility(View.VISIBLE);
                    }
                    temp++;
                }
            }

        }

        submitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = Rgroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) view.findViewById(selectedId);
                end_time = System.currentTimeMillis();

                if (radioButton != null && radioButton.getTag() != null) {
                    CMSAssessmentFragment.nextViewpager(cmsQuestion.getId() + "", radioButton.getTag().toString(), (end_time - start_time) / 1000 + "");
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
