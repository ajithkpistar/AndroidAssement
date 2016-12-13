/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;


import org.simpleframework.xml.Attribute;

/**
 * Created by AJITH on 27-09-2016.
 */


public class XMLStudentProfile {

	@Attribute(name="userId", required = false)
	Integer user_id;
	@Attribute(name="firstname", required = false)
	String firstname;
	@Attribute(name="lastname", required = false)
	String lastname;
	@Attribute(name="dob", required = false)
	String dob;
	@Attribute(name="mobileno", required = false)
	Long mobileno;
	@Attribute(name="gender", required = false)
	String gender;
	@Attribute(name="pincode", required = false)
	Integer pincode;
	@Attribute(name="aadharno", required = false)
	Long aadharno;
	@Attribute(name="email", required = false)
	String email;
	@Attribute(name="yop10", required = false)
	Integer yop_10;
	@Attribute(name="marks10", required = false)
	Float marks_10;
	@Attribute(name="yop12", required = false)
	Integer yop_12;
	@Attribute(name="marks12", required = false)
	Float marks_12;
	@Attribute(name="hasUnderGraduation", required = false)
	Boolean has_under_graduation;
	@Attribute(name="underGraduationSpecializationName", required = false)
	String under_graduation_specialization_name;
	@Attribute(name="underGradutionMarks", required = false)
	Float under_gradution_marks;
	@Attribute(name="hasPostGraduation", required = false)
	Boolean has_post_graduation;
	@Attribute(name="postGraduationSpecializationName", required = false)
	String post_graduation_specialization_name;
	@Attribute(name="postGradutionMarks", required = false)
	Float post_gradution_marks;
	@Attribute(name="isStudyingFurtherAfterDegree", required = false)
	Boolean is_studying_further_after_degree;
	@Attribute(name="jobSector", required = false)
	String job_sector;
	@Attribute(name="preferredLocation", required = false)
	String preferred_location;
	@Attribute(name="companyName", required = false)
	String company_name;
	@Attribute(name="position", required = false)
	String position;
	@Attribute(name="duration", required = false)
	String duration;
	@Attribute(name="description", required = false)
	String description;
	@Attribute(name="interestedInTypeOfCourse", required = false)
	String interested_in_type_of_course;
	@Attribute(name="areaOfInterest", required = false)
	String area_of_interest;
	@Attribute(name="imageUrl10", required = false)
	String image_url_10;
	@Attribute(name="imageUrl12", required = false)
	String image_url_12;
	@Attribute(name="profileImage", required = false)
	String profile_image;
	@Attribute(name="underGraduateDegreeName", required = false)
	String underGraduateDegreeName;
	@Attribute(name="pgDegreeName", required = false)
	String pgDegreeName;
	@Attribute(name="resumeUrl", required = false)
	String resumeUrl;
	@Attribute(name="userCategory", required = false)
	String user_category;
	public XMLStudentProfile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getUser_id() {
		return user_id;
	}



	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}

	public Long getMobileno() {
		return mobileno;
	}

	public void setMobileno(Long mobileno) {
		this.mobileno = mobileno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Long getAadharno() {
		return aadharno;
	}

	public void setAadharno(Long aadharno) {
		this.aadharno = aadharno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getYop_10() {
		return yop_10;
	}

	public void setYop_10(Integer yop_10) {
		this.yop_10 = yop_10;
	}

	public Float getMarks_10() {
		return marks_10;
	}

	public void setMarks_10(Float marks_10) {
		this.marks_10 = marks_10;
	}

	public Integer getYop_12() {
		return yop_12;
	}

	public void setYop_12(Integer yop_12) {
		this.yop_12 = yop_12;
	}

	public Float getMarks_12() {
		return marks_12;
	}

	public void setMarks_12(Float marks_12) {
		this.marks_12 = marks_12;
	}

	public Boolean getHas_under_graduation() {
		return has_under_graduation;
	}

	public void setHas_under_graduation(Boolean has_under_graduation) {
		this.has_under_graduation = has_under_graduation;
	}

	public String getUnder_graduation_specialization_name() {
		return under_graduation_specialization_name;
	}

	public void setUnder_graduation_specialization_name(String under_graduation_specialization_name) {
		this.under_graduation_specialization_name = under_graduation_specialization_name;
	}

	public Float getUnder_gradution_marks() {
		return under_gradution_marks;
	}

	public void setUnder_gradution_marks(Float under_gradution_marks) {
		this.under_gradution_marks = under_gradution_marks;
	}

	public Boolean getHas_post_graduation() {
		return has_post_graduation;
	}

	public void setHas_post_graduation(Boolean has_post_graduation) {
		this.has_post_graduation = has_post_graduation;
	}

	public String getPost_graduation_specialization_name() {
		return post_graduation_specialization_name;
	}

	public void setPost_graduation_specialization_name(String post_graduation_specialization_name) {
		this.post_graduation_specialization_name = post_graduation_specialization_name;
	}

	public Float getPost_gradution_marks() {
		return post_gradution_marks;
	}

	public void setPost_gradution_marks(Float post_gradution_marks) {
		this.post_gradution_marks = post_gradution_marks;
	}

	public Boolean getIs_studying_further_after_degree() {
		return is_studying_further_after_degree;
	}

	public void setIs_studying_further_after_degree(Boolean is_studying_further_after_degree) {
		this.is_studying_further_after_degree = is_studying_further_after_degree;
	}

	public String getJob_sector() {
		return job_sector;
	}

	public void setJob_sector(String job_sector) {
		this.job_sector = job_sector;
	}

	public String getPreferred_location() {
		return preferred_location;
	}

	public void setPreferred_location(String preferred_location) {
		this.preferred_location = preferred_location;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInterested_in_type_of_course() {
		return interested_in_type_of_course;
	}

	public void setInterested_in_type_of_course(String interested_in_type_of_course) {
		this.interested_in_type_of_course = interested_in_type_of_course;
	}

	public String getArea_of_interest() {
		return area_of_interest;
	}

	public void setArea_of_interest(String area_of_interest) {
		this.area_of_interest = area_of_interest;
	}

	public String getImage_url_10() {
		return image_url_10;
	}

	public void setImage_url_10(String image_url_10) {
		this.image_url_10 = image_url_10;
	}

	public String getImage_url_12() {
		return image_url_12;
	}

	public void setImage_url_12(String image_url_12) {
		this.image_url_12 = image_url_12;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getUnderGraduateDegreeName() {
		return underGraduateDegreeName;
	}

	public void setUnderGraduateDegreeName(String underGraduateDegreeName) {
		this.underGraduateDegreeName = underGraduateDegreeName;
	}

	public String getPgDegreeName() {
		return pgDegreeName;
	}

	public void setPgDegreeName(String pgDegreeName) {
		this.pgDegreeName = pgDegreeName;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

    public String getUser_category() {
        return user_category;
    }

    public void setUser_category(String user_category) {
        this.user_category = user_category;
    }
}

