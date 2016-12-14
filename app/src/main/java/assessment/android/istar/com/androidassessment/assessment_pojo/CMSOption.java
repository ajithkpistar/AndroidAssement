package assessment.android.istar.com.androidassessment.assessment_pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Feroz on 14-12-2016.
 */
@Root(name = "option")
public class CMSOption implements java.io.Serializable{


    @Attribute(name = "option_id", required = false)
    private Integer id;
    @Element(data=true, required = false)
    private String option_text;
    @Attribute(name="marking_scheme",required = false)
    private Integer markingScheme;
    @Attribute(name="option_order",required = false)
    private Integer optionOrder;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getOptionText() {
        return option_text;
    }


    public void setOptionText(String optionText) {
        this.option_text = optionText;
    }
    public Integer getMarkingScheme() {
        return markingScheme;
    }

    public void setMarkingScheme(Integer markingScheme) {
        this.markingScheme = markingScheme;
    }
    public Integer getOptionOrder() {
        return optionOrder;
    }

    public void setOptionOrder(Integer optionOrder) {
        this.optionOrder = optionOrder;
    }
    public CMSOption() {
        super();
        // TODO Auto-generated constructor stub
    }



}

