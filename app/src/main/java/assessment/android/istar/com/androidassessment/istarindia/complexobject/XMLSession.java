/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import com.istarindia.utils.SingletonStudent;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;

import java.util.ArrayList;


/**
 * @author vaibhav
 *
 */
@Root(name="cmsession")
public class XMLSession {
	
	@Attribute(required =false)
	private int cmsessionId;
	@Attribute(required =false)
	private String cmsessionName;
	@Attribute(required =false)
	private String cmsessionDescription;
	@ElementList(name="lessons", required = false)
	private ArrayList<XMLLesson> lessons;
	@Attribute(required =false)
	private boolean readable;
	@Attribute(required =false)
	private int orderId;
	@Attribute(required =false)
	private int lastOrderId;
	@Attribute(required =false)
	private int currentmoduleOrderId;
	@Attribute(required =false)
	private int lastModuleOrderId;


	public int getCurrentmoduleOrderId() {
		return currentmoduleOrderId;
	}

	public void setCurrentmoduleOrderId(int currentmoduleOrderId) {
		this.currentmoduleOrderId = currentmoduleOrderId;
	}

	public int getLastModuleOrderId() {
		return lastModuleOrderId;
	}

	public void setLastModuleOrderId(int lastModuleOrderId) {
		this.lastModuleOrderId = lastModuleOrderId;
	}

	public XMLSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XMLSession(int cmsession_id, String cmsessionName, ArrayList<XMLLesson> lessons) {
		super();
		this.cmsessionId = cmsession_id;
		this.cmsessionName = cmsessionName;
		this.lessons = lessons;
	}

	public int getLastOrderId() {
		return lastOrderId;
	}

	public void setLastOrderId(int lastOrderId) {
		this.lastOrderId = lastOrderId;
	}

	public boolean isReadable() {
		return readable;
	}

	public void setReadable(boolean readable) {
		this.readable = readable;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCmsession_id() {
		return cmsessionId;
	}

	public String getCmsessionDescription() {
		return cmsessionDescription;
	}

	public void setCmsessionDescription(String cmsessionDescription) {
		this.cmsessionDescription = cmsessionDescription;
	}

	public void setCmsession_id(int cmsession_id) {
		this.cmsessionId = cmsession_id;
	}
	public String getCmsessionName() {
		return cmsessionName;
	}

	public void setCmsessionName(String cmsessionName) {
		this.cmsessionName = cmsessionName;
	}
	public ArrayList<XMLLesson> getLessons() {
		return lessons;
	}
	
	public void setLessons(ArrayList<XMLLesson> lessons) {
		this.lessons = lessons;
	}


	@Transient
	public boolean getNotesValidation() {
		boolean res = false;
		for(XMLNote note : SingletonStudent.getInstance().getStudent().getNotes())
		{
			if(note.getSessionId()==getCmsession_id())
			{
				res = true;
				break;
			}
		}
		return res;
	}


}
