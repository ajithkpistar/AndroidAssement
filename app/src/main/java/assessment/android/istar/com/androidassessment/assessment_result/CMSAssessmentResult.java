package assessment.android.istar.com.androidassessment.assessment_result;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Feroz on 14-12-2016.
 */

@Root(name = "result")
public class CMSAssessmentResult {

    @ElementList(name = "question_map", required = false)
    List<Entry> question_map;


    @ElementList(name = "question_time", required = false)
    List<Entry> question_time;


    @Attribute(name="assessment_id", required = false)
    String assessment_id;


    @Attribute(name="total_time", required = false)
    String total_time;


    @Attribute(name="user_id", required = false)
    String user_id;



    public List<Entry> getQuestion_map() { return this.question_map; }
    public void setQuestion_map(List<Entry> _value) { this.question_map = _value; }


    public List<Entry> getQuestion_time() { return this.question_time; }
    public void setQuestion_time(List<Entry> _value) { this.question_time = _value; }


    public String getAssessment_id() { return this.assessment_id; }
    public void setAssessment_id(String _value) { this.assessment_id = _value; }


    public String getTotal_time() { return this.total_time; }
    public void setTotal_time(String _value) { this.total_time = _value; }


    public String getUser_id() { return this.user_id; }
    public void setUser_id(String _value) { this.user_id = _value; }



    public static class Entry {

        @Element(name="key", required = false)
        String key;


        @Element(name="value", required = false)
        String value;



        public String getKey() { return this.key; }
        public void setKey(String _value) { this.key = _value; }


        public String getValue() { return this.value; }
        public void setValue(String _value) { this.value = _value; }


    }
}