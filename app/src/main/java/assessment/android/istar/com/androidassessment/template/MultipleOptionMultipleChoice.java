package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import assessment.android.istar.com.androidassessment.CMSAssessmentFragment;
import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;


public class MultipleOptionMultipleChoice extends AssessmentCard {

    private TextView question, option1, option2, option3, option4, option5;
    private int position;
    private CMSQuestion cmsQuestion;
    private Button submitbtn;
    private long start_time, end_time;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_multiple_option_multiple_choice, container, false);
        start_time = System.nanoTime();
        question = (TextView) view.findViewById(R.id.question);
        option1 = (TextView) view.findViewById(R.id.option1);
        option2 = (TextView) view.findViewById(R.id.option2);
        option3 = (TextView) view.findViewById(R.id.option3);
        option4 = (TextView) view.findViewById(R.id.option4);
        option5 = (TextView) view.findViewById(R.id.option5);
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
                question.setText(cmsQuestion.getQuestionText());
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {
                        option1.setText(cmsOption.getOptionText());
                        option1.setTag(cmsOption.getId());
                        option1.setVisibility(View.VISIBLE);
                    }

                    if (temp == 1) {
                        option2.setText(cmsOption.getOptionText());
                        option2.setTag(cmsOption.getId());
                        option2.setVisibility(View.VISIBLE);


                    }
                    if (temp == 2) {
                        option3.setText(cmsOption.getOptionText());
                        option3.setTag(cmsOption.getId());
                        option3.setVisibility(View.VISIBLE);

                    }
                    if (temp == 3) {
                        option4.setText(cmsOption.getOptionText());
                        option4.setTag(cmsOption.getId());
                        option4.setVisibility(View.VISIBLE);

                    }
                    if (temp == 4) {
                        option5.setText(cmsOption.getOptionText());
                        option5.setTag(cmsOption.getId());
                        option5.setVisibility(View.VISIBLE);

                    }
                    temp++;
                }
            }

        }

        submitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String result="";
                boolean checkbox1 = ((CheckBox) view.findViewById(R.id.option1)).isChecked();
                if(checkbox1 == true){
                    System.out.println("---option1--");
                    result = result +","+option1.getTag();
                }
                boolean checkbox2 = ((CheckBox) view.findViewById(R.id.option2)).isChecked();
                if(checkbox2 == true){
                    System.out.println("---option2--");
                    result = result +","+"option2";
                }
                boolean checkbox3 = ((CheckBox) view.findViewById(R.id.option3)).isChecked();
                if(checkbox3 == true){
                    System.out.println("---option3--");
                    result = result +","+"option3";
                }
                boolean checkbox4 = ((CheckBox) view.findViewById(R.id.option4)).isChecked();
                if(checkbox4 == true){
                    System.out.println("---option4--");
                    result = result +","+"option4";
                }
                boolean checkbox5 = ((CheckBox) view.findViewById(R.id.option5)).isChecked();
                if(checkbox5 == true){
                    System.out.println("---option5--");
                    result = result +","+"option5";
                }

                System.out.println("---result--"+result.replaceFirst("^,", ""));



            }
        });

        return view;
    }

}
