package assessment.android.istar.com.androidassessment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import java.io.StringReader;
import assessment.android.istar.com.androidassessment.assessment_database.AssessmentDataHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.assessment_util.AssessmentLockableViewPager;
import assessment.android.istar.com.androidassessment.assessment_util.FetchAssessmentFromServer;
import assessment.android.istar.com.androidassessment.assessment_util.ViewpagerAdapter;

/**
 * Created by Feroz on 14-12-2016.
 */

public class CMSAssessmentFragment extends Fragment {
    public final static String ASSESSMENT_ID="ASSESSMENT_ID";
    private AssessmentLockableViewPager assessmentLockableViewPager;
    private AssessmentDataHandler assessmentDataHandler;
    private ViewpagerAdapter viewpagerAdapter;
    private int assessment_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cms_assessment_fragment, container, false);
        assessmentLockableViewPager = (AssessmentLockableViewPager) view.findViewById(R.id.assessment_viewpager);
        assessmentDataHandler = new AssessmentDataHandler(getContext());
        
        if(getArguments() != null ){
            if(getArguments().getString(ASSESSMENT_ID) != null){
                assessment_id = Integer.parseInt(getArguments().getString(ASSESSMENT_ID));
            }
        }
        
        Cursor c = assessmentDataHandler.getData(assessment_id);
        if(c.moveToFirst()){
            setupOfflineAssement(c.getString(1),viewpagerAdapter,assessmentLockableViewPager);
        }else{
            fetchAssessmentFromServer(assessment_id,assessmentDataHandler,viewpagerAdapter,assessmentLockableViewPager);
        }
        return view;
    }

    private void setupOfflineAssement(String assessment_string,ViewpagerAdapter viewpagerAdapter,AssessmentLockableViewPager viewpager) {
        StringReader reader = new StringReader(assessment_string);
        Serializer serializer = new Persister();
        try {
            CMSAssessment cmsAssessment = serializer.read(CMSAssessment.class,reader);
            viewpagerAdapter = new ViewpagerAdapter(getChildFragmentManager(),cmsAssessment);
            viewpager.setAdapter(viewpagerAdapter);
         //   viewpager.setSwipeLocked(true);
        }catch (Exception e){

        }
    }

    private void fetchAssessmentFromServer( int assessment_id, AssessmentDataHandler assessmentDataHandler,ViewpagerAdapter viewpagerAdapter,AssessmentLockableViewPager viewpager) {
    new FetchAssessmentFromServer(getContext(),viewpagerAdapter,assessmentLockableViewPager,
            assessmentDataHandler,getChildFragmentManager()).execute(assessment_id+"");
    }

}
