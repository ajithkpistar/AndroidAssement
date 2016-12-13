package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name="option")
public class XMLOption implements Serializable {

	@Attribute(required =false)
	private int optionId;
	@Attribute(required =false)
	private String optionText;
	@Attribute(required =false)
	private boolean correct;
	
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getOptionText() {
		return optionText;
	}
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean isCorrect) {
		this.correct = isCorrect;
	}
	public XMLOption() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}
