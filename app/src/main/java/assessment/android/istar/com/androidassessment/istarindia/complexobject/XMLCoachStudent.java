package assessment.android.istar.com.androidassessment.istarindia.complexobject;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="coach_student")
public class XMLCoachStudent {

	@Attribute(required =false)
	private int studentId;
	@Attribute(required =false)
	private String batchGroup;
	@Attribute(required =false)
	private String studentName;
	@Attribute(required =false)
	private String imageUrl;
	@Attribute(required =false)
	private String mobile;
	@Attribute(required =false)
	private String organizationName;
	@Attribute(required =false)
	private boolean isfavoutite;
	@Attribute(required =false)
	private int batchId;
	@ElementList(name="course_ratings", required = false)
	private ArrayList<XMLCourseRating> courseRating;


	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public ArrayList<XMLCourseRating> getCourseRating() {
		return courseRating;
	}

	public void setCourseRating(ArrayList<XMLCourseRating> courseRating) {
		this.courseRating = courseRating;
	}

	public boolean isIsfavoutite() {
		return isfavoutite;
	}
	public void setIsfavoutite(boolean isfavoutite) {
		this.isfavoutite = isfavoutite;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getBatchGroup() {
		return batchGroup;
	}

	public void setBatchGroup(String batchGroup) {
		this.batchGroup = batchGroup;
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
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public XMLCoachStudent(int studentId, String batchGroup, String studentName, String imageUrl, String mobile,
						   String organizationName) {
		super();
		this.studentId = studentId;
		this.batchGroup = batchGroup;

		this.imageUrl = imageUrl;
		this.mobile = mobile;
		this.organizationName = organizationName;
	}
	public XMLCoachStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
