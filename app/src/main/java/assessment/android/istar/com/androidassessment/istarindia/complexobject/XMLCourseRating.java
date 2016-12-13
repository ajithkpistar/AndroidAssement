package assessment.android.istar.com.androidassessment.istarindia.complexobject;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="course_rating")
public class XMLCourseRating {

	@Attribute
	private String courseName;
	@Attribute
	private int rating;
	@Attribute
	private int courseId;
	
	
	public XMLCourseRating() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getCourse_id() {
		return courseId;
	}

	public void setCourse_id(int course_id) {
		this.courseId = course_id;
	}
}
