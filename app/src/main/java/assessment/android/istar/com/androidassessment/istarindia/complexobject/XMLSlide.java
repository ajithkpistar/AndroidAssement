/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author vaibhav
 *
 */
@Root(name="slide")
public class XMLSlide  implements Serializable {
	@Attribute(required =false)
	private Integer id;
	@Attribute(required =false)
	private String title;
	
	
	
	
	public XMLSlide() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XMLSlide(Integer id, String title, ArrayList<XMLNote> slideNotes) {
		super();
		this.id = id;
		this.title = title;
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
	
	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
