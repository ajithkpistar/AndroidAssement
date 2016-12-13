/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * @author vaibhav
 *
 */
@Root(name="classmate")
public class XMLBatchMember {
	@Attribute(required =false)
	private int studentId;
	@Attribute(required =false)
	private String studentName;
	@Attribute(required =false)
	private String imageUrl;

	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		studentName = studentName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public XMLBatchMember() {
		super();
	}
	public XMLBatchMember(int studentId, String studentName, String imageUrl) {
		super();
		this.studentId = studentId;
		studentName = studentName;
		this.imageUrl = imageUrl;
	}
	
	
	
	
}
