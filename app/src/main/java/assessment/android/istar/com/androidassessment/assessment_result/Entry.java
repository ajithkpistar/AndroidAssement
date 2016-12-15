package assessment.android.istar.com.androidassessment.assessment_result;

import org.simpleframework.xml.Element;

/**
 * Created by Feroz on 14-12-2016.
 */

public class Entry {
    @Element(name="key", required = false)
    String key;


    @Element(name="value", required = false)
    String value;

    public Entry() {
    }

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() { return this.key; }
    public void setKey(String _value) { this.key = _value; }


    public String getValue() { return this.value; }
    public void setValue(String _value) { this.value = _value; }


}