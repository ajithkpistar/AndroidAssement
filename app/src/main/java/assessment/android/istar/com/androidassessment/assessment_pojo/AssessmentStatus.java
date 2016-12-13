package assessment.android.istar.com.androidassessment.assessment_pojo;

/**
 * Created by ajith on 13-12-2016.
 */

public class AssessmentStatus {
    private Integer id;
    private String data;
    private String status;
    private String last_pointer;

    public AssessmentStatus() {
    }

    public AssessmentStatus(Integer id, String data, String status, String last_pointer) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.last_pointer = last_pointer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLast_pointer() {
        return last_pointer;
    }

    public void setLast_pointer(String last_pointer) {
        this.last_pointer = last_pointer;
    }
}
