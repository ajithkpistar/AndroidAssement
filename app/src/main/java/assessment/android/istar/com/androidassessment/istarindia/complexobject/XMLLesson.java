/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author vaibhav
 *
 */
@Root(name="lesson")
public class XMLLesson  implements Serializable {

	@Attribute(required =false)
	private Integer id;
	@Attribute(required =false)
	private String title;
	@ElementList(name="llos", required = false)
	private ArrayList<XMLLO> los;
	@ElementList(name="slides", required = false)
	private ArrayList<XMLSlide> slides;
	@Attribute(required =false)
	private String type;
	@Element(required =false)
	private XMLAssessment assessment;
	@Attribute(required =false)
	private Integer concreteId;
	@Attribute(required =false)
	private String status;
	@Attribute(required =false)
	private Integer lOrderId;
    @Attribute(required =false)
    private Integer lastSlidePointer;

    public XMLLesson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XMLLesson(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public XMLAssessment getAssessment() {
		return assessment;
	}
	
	public void setAssessment(XMLAssessment assessment) {
		this.assessment = assessment;
	}
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<XMLSlide> getSlides() {
		return slides;
	}

	public void setSlides(ArrayList<XMLSlide> slides) {
		this.slides = slides;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<XMLLO> getLos() {
		return los;
	}
	public void setLos(ArrayList<XMLLO> los) {
		this.los = los;
	}

	public Integer getConcreteId() {
		return concreteId;
	}

	public void setConcreteId(Integer concreteId) {
		this.concreteId = concreteId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getlOrderId() {
		return lOrderId;
	}

	public void setlOrderId(Integer lOrderId) {
		this.lOrderId = lOrderId;
	}

    public Integer getLastSlidePointer() {
        return lastSlidePointer;
    }

    public void setLastSlidePointer(Integer lastSlidePointer) {
        this.lastSlidePointer = lastSlidePointer;
    }
}
