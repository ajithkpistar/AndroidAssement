package assessment.android.istar.com.androidassessment.assessment_pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Feroz on 14-12-2016.
 */

@Root(name = "question")
public class CMSQuestion implements java.io.Serializable{

    @Attribute(name = "id", required = false)
    private Integer id;
    @Element(data=true,required = false)
    private String questionText;
    @Attribute(name = "durationInSec", required = false)
    private Integer durationInSec;
    @Attribute(name = "orderId", required = false)
    private int orderId;
    @Attribute(name = "template", required = false)
    private String template;
    @Attribute(name = "difficultyLevel", required = false)
    private Integer difficultyLevel;
    @Element(data=true, required = false)
    private String comprehensive_passage;
    @Element(name = "theme", required = false)
    private UiTheme theme;
    @ElementList(name = "options", required = false)
    private List<CMSOption> options;

    public CMSQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }


    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getDurationInSec() {
        return durationInSec;
    }

    public void setDurationInSec(Integer durationInSec) {
        this.durationInSec = durationInSec;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }



    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }



    public String getComprehensive_passage() {
        return comprehensive_passage;
    }


    public void setComprehensive_passage(String comprehensive_passage) {
        this.comprehensive_passage = comprehensive_passage;
    }



    public List<CMSOption> getOptions() {
        return options;
    }

    public void setOptions(List<CMSOption> options) {
        this.options = options;
    }

    public UiTheme getTheme() {
        return theme;
    }

    public void setTheme(UiTheme theme) {
        this.theme = theme;
    }


}

