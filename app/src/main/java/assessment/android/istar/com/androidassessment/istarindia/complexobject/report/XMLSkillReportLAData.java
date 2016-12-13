package assessment.android.istar.com.androidassessment.istarindia.complexobject.report;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;


@Root(name="subskill")
public class XMLSkillReportLAData {

	@Attribute(name="skillId", required = false)
	Integer skillId;
	@Attribute(name="skillName", required = false)
	String skillName;
	@Attribute(name="pointsEarned", required = false)
	Integer pointsEarned;
	@Attribute(name="totalPoints", required = false)
	Integer totalPoints;
	@Attribute(name="rating", required = false)
	Integer rating;
	@Attribute(name="imageUrl", required = false)
	String imageUrl;
	@Attribute(name="percentileCountry", required = false)
	Integer percentileCountry;
	@Attribute(name="percentileglobe", required = false)
	Integer percentileglobe;
	@Attribute(name="percentileBatch", required = false)
	Integer percentileBatch;
	@Attribute(name="percentileOrganozation", required = false)
	Integer percentileOrganozation;
	@Attribute(name="rank", required = false)
	Integer rank;
    @ElementList(name="subskills", required = false)
	ArrayList<XMLSkillReportLAData> subSkills;
	@Element(name="skill_graph", required = false)
	XMLSkillGraph graphData;
	
	public XMLSkillReportLAData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ArrayList<XMLSkillReportLAData> getSubSkills() {
		return subSkills;
	}

	
	public void setSubSkills(ArrayList<XMLSkillReportLAData> subSkills) {
		this.subSkills = subSkills;
	}

	
	public XMLSkillGraph getGraphData() {
		return graphData;
	}
	public void setGraphData(XMLSkillGraph graphData) {
		this.graphData = graphData;
	}

	public Integer getPercentileCountry() {
		return percentileCountry;
	}
	
	public void setPercentileCountry(Integer percentileCountry) {
		this.percentileCountry = percentileCountry;
	}

	public Integer getPercentileglobe() {
		return percentileglobe;
	}
	
	public void setPercentileglobe(Integer percentileglobe) {
		this.percentileglobe = percentileglobe;
	}

	public Integer getPercentileBatch() {
		return percentileBatch;
	}
	
	public void setPercentileBatch(Integer percentileBatch) {
		this.percentileBatch = percentileBatch;
	}

	public Integer getPercentileOrganozation() {
		return percentileOrganozation;
	}
	
	public void setPercentileOrganozation(Integer percentileOrganozation) {
		this.percentileOrganozation = percentileOrganozation;
	}

	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	
	
}
