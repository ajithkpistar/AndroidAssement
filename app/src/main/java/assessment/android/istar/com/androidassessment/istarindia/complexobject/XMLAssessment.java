/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.ArrayList;

public class XMLAssessment implements Serializable {
	
	@Attribute(required =false)
	private int assessmentId;
	@Attribute(required =false)
	private Integer assessmentDurationMinutes;
	@ElementList(name="questions", required = false)
	private ArrayList<XMLQuestion> questions;
	@Attribute(required =false)
	private Integer numberOfQuestions;
	@Attribute(required =false)
	private String assessmentTitle;
	
	
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
	public ArrayList<XMLQuestion> getQuestions() {
		return questions;
	}
	
	public void setQuestions(ArrayList<XMLQuestion> questions) {
		this.questions = questions;
	}
	public Integer getNumber_of_questions() {
		return numberOfQuestions;
	}
	
	public void setNumber_of_questions(Integer number_of_questions) {
		this.numberOfQuestions = number_of_questions;
	}
	
	public String getAssessmentTitle() {
		return assessmentTitle;
	}
	
	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}
	public XMLAssessment(int assessmentId, Integer assessmentDurationMinutes, ArrayList<XMLQuestion> questions,
						 Integer number_of_questions, String assessmentTitle) {
		super();
		this.assessmentId = assessmentId;
		this.assessmentDurationMinutes = assessmentDurationMinutes;
		this.questions = questions;
		this.numberOfQuestions = number_of_questions;
		this.assessmentTitle = assessmentTitle;
	}
	public XMLAssessment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
