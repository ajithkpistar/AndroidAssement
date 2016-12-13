package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;

@Root(name="question")
public class XMLQuestion implements Serializable {
	
	@Attribute(required =false)
	private String questionText;
	@Attribute(required =false)
	private String questionType;
	@Attribute(required =false)
	private Integer durationInSec;
	@Attribute(required =false)
	private int queId;
	@Attribute(required =false)
	private int orderId;
	@ElementList(name="options", required = false)
	private ArrayList<XMLOption> options;
	
	
	
	
	public XMLQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public Integer getDurationInSec() {
		return durationInSec;
	}
	public void setDurationInSec(Integer durationInSec) {
		this.durationInSec = durationInSec;
	}
	public int getQueId() {
		return queId;
	}
	public void setQueId(int queId) {
		this.queId = queId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public ArrayList<XMLOption> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<XMLOption> options) {
		this.options = options;
	}
	
	
}
