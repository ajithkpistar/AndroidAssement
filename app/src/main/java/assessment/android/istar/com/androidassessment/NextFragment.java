package assessment.android.istar.com.androidassessment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;
import assessment.android.istar.com.androidassessment.assessment_util.SubmitAssessmentAsyncTask;


public class NextFragment extends Fragment {
    private Button endBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_assessment, container, false);
        TextView text=(TextView)view.findViewById(R.id.text);
        text.setText("Successfully Submitted!");
        endBtn=(Button)view.findViewById(R.id.endBtn);
        endBtn.setText("GO BACK");
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                getContext().startActivity(intent);
            }
        });
        return view;
    }
}
