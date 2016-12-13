/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;

/**
 * @author vaibhav
 *
 */
public class XMLPincode {
	
	@Attribute(required =false)
	private int id;
	@Attribute(required =false)
	private String state;
	@Attribute(required =false)
	private String city;
	@Attribute(required =false)
	private String country;
	@Attribute(required =false)
	private int pin;
	
	
	
	public XMLPincode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XMLPincode(int id, String state, String city, String country, int pin) {
		super();
		this.id = id;
		this.state = state;
		this.city = city;
		this.country = country;
		this.pin = pin;
	}
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPin() {
		return pin;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	

}
