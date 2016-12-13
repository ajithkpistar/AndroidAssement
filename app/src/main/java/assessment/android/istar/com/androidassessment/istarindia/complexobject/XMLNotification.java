package assessment.android.istar.com.androidassessment.istarindia.complexobject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Mayank on 15/08/2016.
 */
@Root(name="notification")
public class XMLNotification {

    @Attribute
    private String id;
    @Attribute
    private String date;
    @Attribute
    private String time;
    @Attribute
    private String description;
    @Attribute
    private String title;
    @Attribute
    private String type;
    @Attribute
    private String sender;
    @Attribute
    private String receivedAt;
    @Attribute
    private String eventID ;


    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    public XMLNotification(String id, String date, String time, String description, String title, String type, String sender, String eventId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.description = description;
        this.title = title;
        this.type = type;
        this.sender = sender;
        this.eventID=eventId;
    }

    public XMLNotification() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
