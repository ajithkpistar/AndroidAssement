package assessment.android.istar.com.androidassessment.istarindia.complexobject.report;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="questions")
public class XMLReportQuestion {

	@Attribute(name="questionId", required = false)
	Integer questionId;
    @Element(data=true, required = false)
	String questionText;
	
    @ElementList(name="option", required = false)
	ArrayList<XMLReportOption> options;
    @Element(data=true, required = false)
	String correctAnswer;
    @Element(data=true, required = false)
	String selectedAnswer;
    @Element(data=true, required = false)
	String explanation;
	@Attribute(name="correctness", required = false)
	String correctness;
	@Attribute(name="dificultyLevel", required = false)
	String dificultyLevel;
	@Attribute(name="timeTaken", required = false)
	String timeTaken;
	
	
	public XMLReportQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getQuestionId() {
		return questionId;
	}
	
	
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public ArrayList<XMLReportOption> getOptions() {
		return options;
	}
	

	public void setOptions(ArrayList<XMLReportOption> options) {
		this.options = options;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getSelectedAnswer() {
		return selectedAnswer;
	}
	
	
	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	public String getExplanation() {
		return explanation;
	}
	
	
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getCorrectness() {
		return correctness;
	}
	
	
	public void setCorrectness(String correctness) {
		this.correctness = correctness;
	}
	public String getDificultyLevel() {
		return dificultyLevel;
	}
	
	
	public void setDificultyLevel(String dificultyLevel) {
		this.dificultyLevel = dificultyLevel;
	}
	
	public String getTimeTaken() {
		return timeTaken;
	}
	
	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	
	
	
}
