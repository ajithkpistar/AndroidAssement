package assessment.android.istar.com.androidassessment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;
import assessment.android.istar.com.androidassessment.assessment_result.CMSAssessmentResult;
import assessment.android.istar.com.androidassessment.assessment_util.SubmitAssessmentAsyncTask;
import assessment.android.istar.com.androidassessment.template.AssessmentCard;


public class EndAssessmentFragment extends Fragment {
    public static final String CMSASSESSMENTRESULT = "CMSASSESSMENTRESULT";
    private CMSAssessmentResult cmsAssessmentResult;
    private Button endBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_end_assessment, container, false);


        cmsAssessmentResult= CMSAssessmentFragment.getAllAssmentResult();

        endBtn=(Button)view.findViewById(R.id.endBtn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cmsAssessmentResult != null) {
                    new SubmitAssessmentAsyncTask(getContext().getApplicationContext(), cmsAssessmentResult).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        });




        return view;
    }
}
