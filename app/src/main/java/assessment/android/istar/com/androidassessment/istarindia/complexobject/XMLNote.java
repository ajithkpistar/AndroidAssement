/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;


/**
 * @author vaibhav
 *
 */
@Root
public class XMLNote {

	@Attribute(required =false)
	private int noteId;
	@Element(required =false)
	private String noteText;
	@Attribute(required =false)
	private String slideUrl;
	@Attribute(required =false)
	private String createdAt;
	@Attribute(required =false)
	private boolean myNote;
	@Attribute(required =false)
	private String sharedBy;
	@Attribute(required =false)
	private String cmSession;
	@Attribute(required =false)
	private int sessionId;
	@Attribute(required =false)
	private String lesson;
	@Attribute(required =false)
	private int lessonId;
	@Attribute(required =false)
	private String course;
	@Attribute(required =false)
	private int courseId;
	@Attribute(required =false)
	private int slideId;


	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCMSession() {
		return cmSession;
	}
	public void setCMSession(String cMSession) {
		cmSession = cMSession;
	}
	public String getLesson() {
		return lesson;
	}
	public void setLesson(String lesson) {
		lesson = lesson;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		course = course;
	}
	public int getSlideId() {
		return slideId;
	}
	public void setSlideId(int slideId) {
		this.slideId = slideId;
	}



	public int getNote_id() {
		return noteId;
	}
	public void setNote_id(int note_id) {
		this.noteId = note_id;
	}
	public String getNoteText() {
		return noteText;
	}
	
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public String getSlideUrl() {
		return slideUrl;
	}
	
	public void setSlideUrl(String slideUrl) {
		this.slideUrl = slideUrl;
	}
	public String getCreated_at() {
		return createdAt;
	}
	

	public void setCreated_at(String created_at) {
		this.createdAt = created_at;
	}
	public boolean isMyNote() {
		return myNote;
	}
	
	
	public void setMyNote(boolean isMyNote) {
		this.myNote = isMyNote;
	}
	public String getSharedBy() {
		return sharedBy;
	}
	

	public void setSharedBy(String sharedBy) {
		this.sharedBy = sharedBy;
	}
	public XMLNote(int note_id, String noteText, String slideUrl, Date created_at, boolean isMyNote, String sharedBy) {
		super();
		this.noteId = note_id;
		this.noteText = noteText;
		this.slideUrl = slideUrl;

		this.sharedBy = sharedBy;
	}
	
	public XMLNote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
