package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(name="test")
public class XMLTest {

    @Attribute(required =false)
    private String companyName;
    @Attribute(required =false)
    private String imageUrl;
    @Attribute(required =false)
    private String jobTitle;
    @Attribute(required =false)
    private String location;
    @Attribute(required =false)
    private String studentInviteId;
    @Attribute(required =false)
    private String assessmentTitle;
    @Attribute(required =false)
    private int assessmentId;
    @Attribute(required =false)
    private int vacancyId;
    @Attribute(required =false)
    private String postedHoursAgo;
    @Attribute(required =false)
    private int numberOfQuestions;
    @Attribute(required =false)
    private int duration;
    @Attribute(required =false)
    private String testDate;
    @Attribute(required =false)
    private String testTime;

    public XMLTest() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStudentInviteId() {
        return studentInviteId;
    }

    public void setStudentInviteId(String studentInviteId) {
        this.studentInviteId = studentInviteId;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getPostedHoursAgo() {
        return postedHoursAgo;
    }

    public void setPostedHoursAgo(String postedHoursAgo) {
        this.postedHoursAgo = postedHoursAgo;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }
}
