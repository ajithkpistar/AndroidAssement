package assessment.android.istar.com.androidassessment.template;

/**
 * Created by Feroz on 14-12-2016.
 */

public class AssessmentFactory  {


    public static AssessmentCard getCard(String type) {

        if(type.equalsIgnoreCase("1") || type.equalsIgnoreCase("3")){
            return new MultipleOptionSingleChoice();
        }if(type.equalsIgnoreCase("2") || type.equalsIgnoreCase("4")){
            return new MultipleOptionMultipleChoice();
        }else{
            return new DefaultFragment();
        }

    }
}
