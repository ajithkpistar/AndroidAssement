package assessment.android.istar.com.androidassessment.assessment_pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Feroz on 14-12-2016.
 */

@Root(name = "assessment")
public class CMSAssessment implements java.io.Serializable {
    @Attribute(name = "assessmentId", required = false)
    private int assessmentId;
    @Attribute(name = "assessmentDurationMinutes", required = false)
    private Integer assessmentDurationMinutes;
    @Attribute(name = "number_of_questions", required = false)
    private Integer number_of_questions;
    @Attribute(name = "assessmentTitle", required = false)
    private String assessmentTitle;
    @Attribute(name = "isRetryAble", required = false)
    private Boolean isRetryAble;
    @Attribute(name = "category", required = false)
    private String category;
    @Attribute(name = "assessmentType", required = false)
    private String assessmentType;
    @ElementList(name = "questions", required = false)
    private List<CMSQuestion> questions;
    @Element(name = "theme", required = false)
    private UiTheme theme;

    public CMSAssessment() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }
    public Integer getAssessmentDurationMinutes() {
        return assessmentDurationMinutes;
    }

    public void setAssessmentDurationMinutes(Integer assessmentDurationMinutes) {
        this.assessmentDurationMinutes = assessmentDurationMinutes;
    }
    public Integer getNumber_of_questions() {
        return number_of_questions;
    }

    public void setNumber_of_questions(Integer number_of_questions) {
        this.number_of_questions = number_of_questions;
    }
    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }
    public Boolean getIsRetryAble() {
        return isRetryAble;
    }

    public void setIsRetryAble(Boolean isRetryAble) {
        this.isRetryAble = isRetryAble;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<CMSQuestion> getQuestions() {
        return questions;
    }


    public void setQuestions(List<CMSQuestion> questions) {
        this.questions = questions;
    }


    public String getAssessmentType() {
        return assessmentType;
    }
    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }



    public UiTheme getTheme() {
        return theme;
    }

    public void setTheme(UiTheme theme) {
        this.theme = theme;
    }




}
