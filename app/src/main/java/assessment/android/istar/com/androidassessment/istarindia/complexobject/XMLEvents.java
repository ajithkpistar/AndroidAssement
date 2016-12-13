/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.sql.Timestamp;

/**
 * @author vaibhav
 *
 */
@Root(name="event")
public class XMLEvents {

	@Attribute(required =false)
	private String id ;
	@Attribute(required =false)
	private String eventdate ;
	@Attribute(required =false)
	private int eventHour ;
	@Attribute(required =false)
	private int eventMin;
	@Attribute(required =false)
	private String description;
	@Attribute(required =false)
	private String eventType;
	@Attribute(required =false)
	private String status;
	@Attribute(required =false)
	private int cmsessionId;
	@Attribute(required =false)
	private String cmsessionTitle;
	@Attribute(required =false)
	private int batchId;
	@Attribute(required =false)
	private String batchName;
	@Attribute(required =false)
	private String eventName;
	@Attribute(required =false)
	private int classId;
	@Attribute(required =false)
	private String className;
	@Attribute(required =false)
	private int bgroupId;

	@Attribute(required =false)
	private String vacancyTitle;
	@Attribute(required =false)
	private String location;
	@Attribute(required =false)
	private String vacancyDescription;
	@Attribute(required =false)
	private String recruiter;
	@Attribute(required =false)
	private String company;

	@Attribute(required =false)
	private int assessmentId;
	@Attribute(required =false)
	private String assessmentTitle;
	@Attribute(required =false)
	private String sessionTitle;


	public int getBgroupId() {
		return bgroupId;
	}

	public void setBgroupId(int bgroupId) {
		this.bgroupId = bgroupId;
	}

	public int getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}
	public String getAssessmentTitle() {
		return assessmentTitle;
	}
	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}

	public String getSessionTitle() {
		return sessionTitle;
	}
	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

	public String getVacancyTitle() {
		return vacancyTitle;
	}

	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVacancyDescription() {
		return vacancyDescription;
	}

	public void setVacancyDescription(String vacancyDescription) {
		this.vacancyDescription = vacancyDescription;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCmsessionId() {
		return cmsessionId;
	}
	public void setCmsessionId(int cmsessionId) {
		this.cmsessionId = cmsessionId;
	}
	public String getCmsessionTitle() {
		return cmsessionTitle;
	}
	public void setCmsessionTitle(String cmsessionTitle) {
		this.cmsessionTitle = cmsessionTitle;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public XMLEvents() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XMLEvents(String id, Timestamp eventdate, int eventHour, int eventMin, String eventType, String status,
					 XMLSession cmsession_id, XMLBatch batch_id, String eventName, XMLClassroom classroom) {
		super();
		this.id = id;
		
		this.eventHour = eventHour;
		this.eventMin = eventMin;
		this.eventType = eventType;
		this.status = status;
		
		this.eventName = eventName;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEventdate() {
		return eventdate;
	}
	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}
	public int getEventHour() {
		return eventHour;
	}
	public void setEventHour(int eventHour) {
		this.eventHour = eventHour;
	}
	public int getEventMin() {
		return eventMin;
	}
	public void setEventMin(int eventMin) {
		this.eventMin = eventMin;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
