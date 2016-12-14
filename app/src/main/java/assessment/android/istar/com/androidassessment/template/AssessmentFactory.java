package assessment.android.istar.com.androidassessment.template;

import android.support.v4.app.Fragment;

/**
 * Created by Feroz on 14-12-2016.
 */

public class AssessmentFactory  {


    public static AssessmentCard getCard(String type) {

        if(type.equalsIgnoreCase("Basic")){
            return BasicFrag();
        }else{
            return DefaultFragment();
        }

    }
}
