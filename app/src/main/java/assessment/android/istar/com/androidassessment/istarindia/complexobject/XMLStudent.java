package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vaibhav
 *
 */

public class XMLStudent {


	@Attribute(required =false)
	private String fatherName;
	@Attribute(required = false)
	private Long phone;
	@Attribute(required =false)
	private String motherName;
	@Attribute(required =false)
	private int id;
	@Attribute(required =false)
	private String name;
	@Element(required = false)
	private XMLAddress address;
	@Attribute(required =false)
	private String email;
	@Attribute(required =false)
	private String imageUrl;
	@Attribute(required =false)
	private String userType;
	@ElementList(name="notifications", required = false)
	private ArrayList<XMLNotification> notifications;
	@Element(required = false)
	private XMLStudentProfile student_profile;

	public XMLStudentProfile getStudentProfile() {
		return student_profile;
	}

	public void setStudentProfile(XMLStudentProfile studentProfile) {
		this.student_profile = studentProfile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@ElementList(name="batch_groups", required = false)
	ArrayList<XMLBatchGroup> batchGroups;
	@Element(required = false)
	private XMLOrganization organization;

	public XMLOrganization getOrganization() {
		return organization;
	}

	@ElementList(name="invites", required = false)
	ArrayList<XMLInvite> invites;
	@ElementList(name="offers", required = false)
	ArrayList<XMLOffer> offers;
	@ElementList(name="interviews", required = false)
	ArrayList<XMLInterview> interviews;
	@ElementList(name="tests", required = false)
	ArrayList<XMLTest> tests;
	@ElementList(name="events", required = false)
	ArrayList<XMLEvents> events;
	@ElementList(name="mynotes", required = false)
	ArrayList<XMLNote> notes;
	@ElementList(name="shared_notes", required = false)
	ArrayList<XMLNote> sharedNotes;

	@ElementList(name="coach_students", required = false)
	ArrayList<XMLCoachStudent> coachStudents;


	public ArrayList<XMLCoachStudent> getCoachStudents() {
		return coachStudents;
	}

	public void setCoachStudents(ArrayList<XMLCoachStudent> coachStudents) {
		this.coachStudents = coachStudents;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XMLAddress getAddress() {
		return address;
	}

	public void setAddress(XMLAddress address) {
		this.address = address;
	}
	public void setOrganization(XMLOrganization organization) {
		this.organization = organization;
	}



	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public Long getPhone() {
		return phone;
	}


	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getMotherName() {
		return motherName;
	}


	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public List<XMLBatchGroup> getBatchGroups() {
		return batchGroups;
	}

	public void setBatchGroups(ArrayList<XMLBatchGroup> batchGroups) {
		this.batchGroups = batchGroups;
	}
	
	public ArrayList<XMLInvite> getInvites() {
		return invites;
	}

	public void setInvites(ArrayList<XMLInvite> invites) {
		this.invites = invites;
	}
	public ArrayList<XMLOffer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<XMLOffer> offers) {
		this.offers = offers;
	}
	public ArrayList<XMLInterview> getInterviews() {
		return interviews;
	}

	public void setInterviews(ArrayList<XMLInterview> interviews) {
		this.interviews = interviews;
	}
	
	public ArrayList<XMLEvents> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<XMLEvents> events) {
		this.events = events;
	}
	public ArrayList<XMLNote> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<XMLNote> notes) {
		this.notes = notes;
	}
	public ArrayList<XMLNote> getSharedNotes() {
		return sharedNotes;
	}

	public void setSharedNotes(ArrayList<XMLNote> sharedNotes) {
		this.sharedNotes = sharedNotes;
	}

	public ArrayList<XMLNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<XMLNotification> notifications) {
		this.notifications = notifications;
	}

	public ArrayList<XMLTest> getTests() {
		return tests;
	}

	public void setTests(ArrayList<XMLTest> tests) {
		this.tests = tests;
	}
}
