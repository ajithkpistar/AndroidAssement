package assessment.android.istar.com.androidassessment.template;

/**
 * Created by Feroz on 14-12-2016.
 */

public class AssessmentFactory  {


    public static AssessmentCard getCard(String type) {

        if(type.equalsIgnoreCase("1")){
            return new MultipleOptionSingleChoice();
        }else{
            return new DefaultFragment();
        }

    }
}
