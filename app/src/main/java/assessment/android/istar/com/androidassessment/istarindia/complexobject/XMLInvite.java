/**
 * 
 */
package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


/**
 * @author vaibhav
 *
 */
@Root(name="invite")
public class XMLInvite {

    @Attribute(required =false)
    private String companyName;
    @Attribute(required =false)
    private String imageUrl;
    @Attribute(required =false)
    private String vacancyJobTitle;
    @Attribute(required =false)
    private String vacancyLocation;
    @Attribute(required =false)
    private String vacancyDescription;
    @Attribute(required =false)
    private String eventId;
    @Attribute(required =false)
    private String postedHoursAgo;
    @Attribute(required =false)
    private String eventAction;


    public XMLInvite() {
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

    public String getVacancyJobTitle() {
        return vacancyJobTitle;
    }

    public void setVacancyJobTitle(String vacancyJobTitle) {
        this.vacancyJobTitle = vacancyJobTitle;
    }

    public String getVacancyLocation() {
        return vacancyLocation;
    }

    public void setVacancyLocation(String vacancyLocation) {
        this.vacancyLocation = vacancyLocation;
    }

    public String getVacancyDescription() {
        return vacancyDescription;
    }

    public void setVacancyDescription(String vacancyDescription) {
        this.vacancyDescription = vacancyDescription;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPostedHoursAgo() {
        return postedHoursAgo;
    }

    public void setPostedHoursAgo(String postedHoursAgo) {
        this.postedHoursAgo = postedHoursAgo;
    }

    public String getEventAction() {
        return eventAction;
    }

    public void setEventAction(String eventAction) {
        this.eventAction = eventAction;
    }
}
