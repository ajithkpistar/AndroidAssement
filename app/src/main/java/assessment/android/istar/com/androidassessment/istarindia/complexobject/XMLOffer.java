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
@Root(name="offer")
public class XMLOffer {

    @Attribute(required =false)
    private String eventId;
    @Attribute(required =false)
    private String companyName;
    @Attribute(required =false)
    private String imageUrl;
    @Attribute(required =false)
    private String jobTitle;
    @Attribute(required =false)
    private String description;
    @Attribute(required =false)
    private String location;
    @Attribute(required =false)
    private String offerLetterUrl;
    @Attribute(required =false)
    private String postedHoursAgo;

    public XMLOffer() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOfferLetterUrl() {
        return offerLetterUrl;
    }

    public void setOfferLetterUrl(String offerLetterUrl) {
        this.offerLetterUrl = offerLetterUrl;
    }

    public String getPostedHoursAgo() {
        return postedHoursAgo;
    }

    public void setPostedHoursAgo(String postedHoursAgo) {
        this.postedHoursAgo = postedHoursAgo;
    }
}
