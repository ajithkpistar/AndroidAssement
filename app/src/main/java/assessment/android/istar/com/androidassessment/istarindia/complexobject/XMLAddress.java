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
public class XMLAddress {

	@Attribute(required = false)
	private Integer id;

	@Element(required = false)
	private XMLPincode pincode;
	@Attribute(required = false)

	private String addressline1;
	@Attribute(required = false)

	private String addressline2;
	@Attribute(required = false)

	private Double addressGeoLongitude;

	@Attribute(required = false)

	private Double addressGeoLatitude;

	public XMLAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XMLAddress(Integer id, XMLPincode pincode, String addressline1, String addressline2, Double addressGeoLongitude,
					  Double addressGeoLatitude) {
		super();
		this.id = id;
		this.pincode = pincode;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.addressGeoLongitude = addressGeoLongitude;
		this.addressGeoLatitude = addressGeoLatitude;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public XMLPincode getPincode() {
		return pincode;
	}
	
	public void setPincode(XMLPincode pincode) {
		this.pincode = pincode;
	}
	public String getAddressline1() {
		return addressline1;
	}
	
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public Double getAddressGeoLongitude() {
		return addressGeoLongitude;
	}
	
	public void setAddressGeoLongitude(Double addressGeoLongitude) {
		this.addressGeoLongitude = addressGeoLongitude;
	}
	public Double getAddressGeoLatitude() {
		return addressGeoLatitude;
	}
	
	public void setAddressGeoLatitude(Double addressGeoLatitude) {
		this.addressGeoLatitude = addressGeoLatitude;
	}
	
	

}
