package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(name="interview")
public class XMLInterview {

    @Attribute(required =false)
    private String companyName;
    @Attribute(required =false)
    private String imageUrl;
    @Attribute(required =false)
    private String jobTitle;
    @Attribute(required =false)
    private String location;
    @Attribute(required =false)
    private String interviewDate;
    @Attribute(required =false)
    private String interviewTime;
    @Attribute(required =false)
    private String description;
    @Attribute(required =false)
    private String eventId;
    @Attribute(required =false)
    private int vacancyId;
    @Attribute(required =false)
    private String recruiterName;
    @Attribute(required =false)
    private int durationInMinutes;
    @Attribute(required =false)
    private String interviewDay;
    @Attribute(required =false)
    private String postedHoursAgo;
    @Attribute(required =false)
    private String interviwType;

    @Attribute(required =false)
    private String panelist;
    @Attribute(required =false)
    private String hostUrl;
    @Attribute(required =false)
    private String joinUrl;
    @Attribute(required =false)
    private String meetingId;
    @Attribute(required =false)
    private String meetingPasword;

    public String getInterviwType() {
        return interviwType;
    }

    public void setInterviwType(String interviwType) {
        this.interviwType = interviwType;
    }

    public XMLInterview() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getInterviewDay() {
        return interviewDay;
    }

    public void setInterviewDay(String interviewDay) {
        this.interviewDay = interviewDay;
    }

    public String getPostedHoursAgo() {
        return postedHoursAgo;
    }

    public void setPostedHoursAgo(String postedHoursAgo) {
        this.postedHoursAgo = postedHoursAgo;
    }

    public String getPanelist() {
        return panelist;
    }

    public void setPanelist(String panelist) {
        this.panelist = panelist;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getJoinUrl() {
        return joinUrl;
    }

    public void setJoinUrl(String joinUrl) {
        this.joinUrl = joinUrl;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingPassword() {
        return meetingPasword;
    }

    public void setMeetingPassword(String meetingPasword) {
        this.meetingPasword = meetingPasword;
    }
}
