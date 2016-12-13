/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import com.istarindia.utils.SingletonStudent;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Transient;

import java.util.ArrayList;

/**
 * @author vaibhav
 *
 */
public class XMLCourse {
	
	@Attribute(required =false)
	private int courseId;
	@Attribute(required =false)
	private String courseName;



	@Attribute(required =false)

	private String coursedescription;
	@ElementList(name="cmsessions", required = false)
	private ArrayList<XMLSession> cmsessions;
	
	
	public XMLCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XMLCourse(int courseId, String courseName, ArrayList<XMLSession> cmsessions) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.cmsessions = cmsessions;
	}
	public int getCourseId() {
		return courseId;
	}
	
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public ArrayList<XMLSession> getCmsessions() {
		return cmsessions;
	}
	
	public void setCmsessions(ArrayList<XMLSession> cmsessions) {
		this.cmsessions = cmsessions;
	}

	public String getCoursedescription() {
		return coursedescription;
	}

	public void setCoursedescription(String coursedescription) {
		this.coursedescription = coursedescription;
	}

	@Transient
	public ArrayList<XMLSession> getSessionsWithNotes()
	{
		ArrayList<XMLSession> data = new ArrayList<>();

		for(XMLSession s: getCmsessions())
		{
			for(XMLNote note : SingletonStudent.getInstance().getStudent().getNotes())
			{
				if(!data.contains(s) && s.getCmsession_id()== note.getSessionId())
				{
					data.add(s);
				}
			}
		}

		return data;
	}

}
