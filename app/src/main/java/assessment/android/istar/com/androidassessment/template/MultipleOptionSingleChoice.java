package assessment.android.istar.com.androidassessment.template;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

/**
 * Created by Feroz on 14-12-2016.
 */

public class MultipleOptionSingleChoice extends AssessmentCard {

    private TextView question,option1,option2,option3,option4,option5;
    private int position;
    private CMSQuestion cmsQuestion;
    private Button submitbtn;
    public RadioGroup Rgroup;
    private RadioButton radioButton;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.multipleoption_singlechoice, container, false);
        question = (TextView) view.findViewById(R.id.question);
        option1 = (TextView) view.findViewById(R.id.option1);
        option2 = (TextView) view.findViewById(R.id.option2);
        option3 = (TextView) view.findViewById(R.id.option3);
        option4 = (TextView) view.findViewById(R.id.option4);
        option5 = (TextView) view.findViewById(R.id.option5);
        submitbtn = (Button) view.findViewById(R.id.submitbtn);
        Rgroup = (RadioGroup)view.findViewById(R.id.options);

        if(getArguments() != null){
            if(getArguments().getSerializable(AssessmentCard.CMSASSESSMENT) != null){
                cmsQuestion = (CMSQuestion)getArguments().getSerializable(AssessmentCard.CMSASSESSMENT);
                if(getArguments().getInt(AssessmentCard.POSITION,-1) !=-1)
                    position = getArguments().getInt(AssessmentCard.POSITION,-1);
            }
        }

        if(cmsQuestion != null){
            if(cmsQuestion.getQuestionText() != null){
                question.setText(cmsQuestion.getQuestionText());
            }
            if(cmsQuestion.getOptions() != null){
                int temp = 0;
                for(CMSOption cmsOption : cmsQuestion.getOptions()){
                    if(temp ==0)
                        option1.setText(cmsOption.getOptionText());
                    if(temp ==1)
                        option2.setText(cmsOption.getOptionText());
                    if(temp ==2)
                        option3.setText(cmsOption.getOptionText());
                    if(temp ==3)
                        option4.setText(cmsOption.getOptionText());
                    if(temp ==4)
                        option5.setText(cmsOption.getOptionText());
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

                Toast.makeText(getActivity(),radioButton.getText(), Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }

}
