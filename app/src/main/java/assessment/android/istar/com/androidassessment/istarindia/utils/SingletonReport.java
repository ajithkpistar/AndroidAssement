package assessment.android.istar.com.androidassessment.istarindia.utils;


import assessment.android.istar.com.androidassessment.istarindia.complexobject.report.XMLStudentReport;

/**
 * Created by Mayank on 13/08/2016.
 */
public class SingletonReport {

    private XMLStudentReport studentReport;

    public XMLStudentReport getStudentReport() {
        return studentReport;
    }

    public void setStudentReport(XMLStudentReport studentReport) {
        this.studentReport = studentReport;
    }

    public static void setInstance(SingletonReport instance) {
        SingletonReport.instance = instance;
    }

    private static SingletonReport instance;
    private SingletonReport(){};

    public static SingletonReport getInstance(){
        if(instance == null){
            synchronized (SingletonReport.class) {
                if(instance == null){
                    instance = new SingletonReport();
                }
            }
        }
        return instance;
    }


}
