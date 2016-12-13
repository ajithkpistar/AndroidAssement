package assessment.android.istar.com.androidassessment.istarindia.complexobject.report;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="options")
public class XMLReportOption {

	
	@Attribute(name="optionId", required = false)
	Integer optionId;
	@Element(data=true,required = false)
	String optionText;
	@Attribute(name="markingScheme", required = false)
	Integer markingScheme;
	public XMLReportOption() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getOptionId() {
		return optionId;
	}
	
	
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public String getOptionText() {
		return optionText;
	}
	
	
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	public Integer getMarkingScheme() {
		return markingScheme;
	}
	
	
	public void setMarkingScheme(Integer markingScheme) {
		this.markingScheme = markingScheme;
	}
	
	
	
}
