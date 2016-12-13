package assessment.android.istar.com.androidassessment.istarindia.complexobject.report;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(name="graph_point")
public class XMLGraphPoints {
	@Attribute(name="date", required = false)
	String date;
	@Attribute(name="percentileCountry", required = false)
	Integer percentileCountry;
	@Attribute(name="percentileglobe", required = false)
	Integer percentileglobe;
	@Attribute(name="percentileBatch", required = false)
	Integer percentileBatch;
	@Attribute(name="percentileOrganozation", required = false)
	Integer percentileOrganozation;
	public XMLGraphPoints() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getPercentileCountry() {
		return percentileCountry;
	}
	
	
	public void setPercentileCountry(Integer percentileCountry) {
		this.percentileCountry = percentileCountry;
	}
	public Integer getPercentileglobe() {
		return percentileglobe;
	}
	
	
	public void setPercentileglobe(Integer percentileglobe) {
		this.percentileglobe = percentileglobe;
	}
	public Integer getPercentileBatch() {
		return percentileBatch;
	}
	
	
	public void setPercentileBatch(Integer percentileBatch) {
		this.percentileBatch = percentileBatch;
	}
	public Integer getPercentileOrganozation() {
		return percentileOrganozation;
	}
	
	
	public void setPercentileOrganozation(Integer percentileOrganozation) {
		this.percentileOrganozation = percentileOrganozation;
	}
	
	
	
}
