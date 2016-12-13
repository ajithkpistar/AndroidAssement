/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;


/**
 * @author vaibhav
 *
 */
@Root(name="batch_group")
public class XMLBatchGroup {

	@Attribute(required =false)
	private Integer id;

	@Attribute(required =false)
	private String name;

	@Attribute(name="organization_id", required =false)
	private Integer orgid;
	@Attribute(required =false)
	private String orgname;


	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrg_id() {
		return orgid;
	}
	
	public void setOrg_id(Integer org_id) {
		this.orgid = org_id;
	}

	@ElementList(name="batches", required = false)
	ArrayList<XMLBatch> btaches;
	
	public ArrayList<XMLBatch> getBtaches() {
		return btaches;
	}
	
	public void setBtaches(ArrayList<XMLBatch> btaches) {
		this.btaches = btaches;
	}
	
	@ElementList(name="classmates", required = false)
	ArrayList<XMLBatchMember> classmates;
	
	public ArrayList<XMLBatchMember> getClassmates() {
		return classmates;
	}
	
	//@XmlRootElement(name = "android_student")
	public void setClassmates(ArrayList<XMLBatchMember> classmates) {
		this.classmates = classmates;
	}
	
	public XMLBatchGroup(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public XMLBatchGroup() {
		super();
	}
	
	
	
}
