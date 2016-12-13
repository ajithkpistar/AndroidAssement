package assessment.android.istar.com.androidassessment.istarindia.complexobject.report;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;


@Root(name="report")
public class XMLStudentReport {

	
	@Attribute(name="studentId", required = false)
	Integer studentId;
	@Attribute(name="batchRank", required = false)
	Integer batchRank;
	@Attribute(name="totalBatchStudents", required = false)
	Integer totalBatchStudents;
	@Attribute(name="pointsEarned", required = false)
	Integer pointsEarned;
	@Attribute(name="totalPoints", required = false)
	Integer totalPoints;
	@Attribute(name="totalScore", required = false)
	Integer totalScore;
	@Element(name="skill_la_data", required = false)
	XMLSkillReportLAData overAllData;
	
    @ElementList(name="tests", required = false)
	ArrayList<XMLReportTest> testList;
	
	public XMLStudentReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getStudentId() {
		return studentId;
	}
	
	public Integer getTotalScore() {
		return totalScore;
	}
	
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
	
	
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getBatchRank() {
		return batchRank;
	}
	
	
	public void setBatchRank(Integer batchRank) {
		this.batchRank = batchRank;
	}
	public Integer getTotalBatchStudents() {
		return totalBatchStudents;
	}
	
	
	public void setTotalBatchStudents(Integer totalBatchStudents) {
		this.totalBatchStudents = totalBatchStudents;
	}
	public Integer getPointsEarned() {
		return pointsEarned;
	}
	
	public void setPointsEarned(Integer pointsEarned) {
		this.pointsEarned = pointsEarned;
	}
	public Integer getTotalPoints() {
		return totalPoints;
	}
	
	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	
	public XMLSkillReportLAData getOverAllData() {
		return overAllData;
	}
	
	public void setOverAllData(XMLSkillReportLAData overAllData) {
		this.overAllData = overAllData;
	}
	
	
	public ArrayList<XMLReportTest> getTestList() {
		return testList;
	}
	
	
	
	public void setTestList(ArrayList<XMLReportTest> testList) {
		this.testList = testList;
	}
	
	
	
	
}
