/**
 *
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * @author vaibhav
 *
 */
public class XMLOrganization {
	@Attribute(required=false)
	private String name;
	@Element(required=false)
	private XMLAddress address;


	public XMLOrganization() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XMLOrganization(String name, XMLAddress address) {
		super();
		this.name = name;
		this.address = address;
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




}
