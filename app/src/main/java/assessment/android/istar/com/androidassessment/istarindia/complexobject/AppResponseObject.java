package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root( name = "result" )
public class AppResponseObject {

	@Attribute (name="responseCode")
	String responseCode;

	@Attribute(name="responseMessage")
	String responseMessage;

	@Element(name="student", required = false)
	XMLStudent student;

	public String getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public XMLStudent getStudent() {
		return student;
	}

	public void setStudent(XMLStudent student) {
		this.student = student;
	}


	public AppResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}



}
