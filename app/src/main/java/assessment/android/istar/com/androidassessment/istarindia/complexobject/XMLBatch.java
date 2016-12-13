/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;



import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author vaibhav
 *
 */
@Root(name="batch")
public class XMLBatch {
	
	@Attribute(required =false)
	private int id;
	@Element(required =false)
	private XMLCourse course;
	@Attribute(required =false)
	private String batchName;
	@Attribute(required =false)
	private int orderId;
	
	
	
	
	public XMLBatch() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public XMLCourse getCourse() {
		return course;
	}
	
	public void setCourse(XMLCourse course) {
		this.course = course;
	}
	public String getBatchName() {
		return batchName;
	}
	
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
