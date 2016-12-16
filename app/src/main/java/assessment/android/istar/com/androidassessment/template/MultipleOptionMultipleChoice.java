package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
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
    private Button submitbtn;
    private long start_time, end_time;
    CheckBox checkbtn1,checkbtn2,checkbtn3,checkbtn4,checkbtn5;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_multiple_option_multiple_choice, container, false);
        start_time = System.nanoTime();

        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);

        checkbtn1 = (CheckBox)view.findViewById(R.id.checkbtn1);
        checkbtn2 = (CheckBox)view.findViewById(R.id.checkbtn2);
        checkbtn3 = (CheckBox)view.findViewById(R.id.checkbtn3);
        checkbtn4 = (CheckBox)view.findViewById(R.id.checkbtn4);
        checkbtn5 = (CheckBox)view.findViewById(R.id.checkbtn5);




        submitbtn = (Button) view.findViewById(R.id.submitbtn);

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
                        checkbtn1.setTag(cmsOption.getId());
                        option1.setVisibility(View.VISIBLE);
                        checkbtn1.setVisibility(View.VISIBLE);
                    }
                    if (temp == 1) {
                        option2.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        checkbtn2.setTag(cmsOption.getId());
                        option2.setVisibility(View.VISIBLE);
                        checkbtn2.setVisibility(View.VISIBLE);
                    }
                    if (temp == 2) {
                        option3.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        checkbtn3.setTag(cmsOption.getId());
                        option3.setVisibility(View.VISIBLE);
                        checkbtn3.setVisibility(View.VISIBLE);
                    }
                    if (temp == 3) {
                        option4.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        checkbtn4.setTag(cmsOption.getId());
                        option4.setVisibility(View.VISIBLE);
                        checkbtn4.setVisibility(View.VISIBLE);
                    }
                    if (temp == 4) {
                        option5.loadDataWithBaseURL(null, cmsOption.getOptionText(), "text/html", "utf-8", null);
                        checkbtn5.setTag(cmsOption.getId());
                        option5.setVisibility(View.VISIBLE);
                        checkbtn5.setVisibility(View.VISIBLE);
                    }
                    temp++;
                }
            }

        }

        submitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String result="";
                boolean checkbox1 = ((CheckBox) view.findViewById(R.id.checkbtn1)).isChecked();
                if(checkbox1 == true){
                    result = result +","+checkbtn1.getTag();
                }
                boolean checkbox2 = ((CheckBox) view.findViewById(R.id.checkbtn2)).isChecked();
                if(checkbox2 == true){
                    result = result +","+checkbtn2.getTag();
                }
                boolean checkbox3 = ((CheckBox) view.findViewById(R.id.checkbtn3)).isChecked();
                if(checkbox3 == true){
                    result = result +","+checkbtn3.getTag();
                }
                boolean checkbox4 = ((CheckBox) view.findViewById(R.id.checkbtn4)).isChecked();
                if(checkbox4 == true){
                    result = result +","+checkbtn4.getTag();
                }
                boolean checkbox5 = ((CheckBox) view.findViewById(R.id.checkbtn5)).isChecked();
                if(checkbox5 == true){
                    result = result +","+checkbtn5.getTag();
                }

                System.out.println("---result--"+result.replaceFirst("^,", ""));

                end_time = System.currentTimeMillis();
                if (!result.equalsIgnoreCase("") ) {
                    CMSAssessmentFragment.nextViewpager(cmsQuestion.getId() + "", result.replaceFirst("^,", ""), (end_time - start_time) / 1000 + "");
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
