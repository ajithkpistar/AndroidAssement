package assessment.android.istar.com.androidassessment.istarindia.complexobject.report;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="test")
public class XMLReportTest {
	@Attribute(name="assessmentId", required = false)
	Integer assessmentId;
	@Attribute(name="testName", required = false)
	String testName;
	@Attribute(name="percentage", required = false)
	Integer percentage;
	@Attribute(name="date", required = false)
	String date;
	@Attribute(name="imageUrl", required = false)
	String imageUrl;
	@Attribute(name="pointsEarned", required = false)
	Integer pointsEarned;
	@Attribute(name="maxPoints", required = false)
	Integer maxPoints;
	@Attribute(name="rank", required = false)
	Integer rank;
    @ElementList(name="question", required = false)
	ArrayList<XMLReportQuestion> questions;

	public XMLReportTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getAssessmentId() {
		return assessmentId;
	}

	
	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getTestName() {
		return testName;
	}

	
	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getPercentage() {
		return percentage;
	}

	
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public String getDate() {
		return date;
	}

	
	public void setDate(String date) {
		this.date = date;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ArrayList<XMLReportQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<XMLReportQuestion> questions) {
		this.questions = questions;
	}

	public Integer getPointsEarned() {
		return pointsEarned;
	}
	
	public void setPointsEarned(Integer pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public Integer getMaxPoints() {
		return maxPoints;
	}
	
	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}

	public Integer getRank() {
		return rank;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	
	
}
