package assessment.android.istar.com.androidassessment.istarindia.utils;

import com.istarindia.complexobject.XMLStudent;

/**
 * Created by Mayank on 13/08/2016.
 */
public class SingletonStudent {

    private XMLStudent student;

    public void setStudent(XMLStudent student) {
        this.student = student;
    }

    public XMLStudent getStudent() {
        return student;
    }

    private static SingletonStudent instance;
    private SingletonStudent(){};

    public static SingletonStudent getInstance(){
        if(instance == null){
            synchronized (SingletonStudent.class) {
                if(instance == null){
                    instance = new SingletonStudent();
                }
            }
        }
        return instance;
    }


}
