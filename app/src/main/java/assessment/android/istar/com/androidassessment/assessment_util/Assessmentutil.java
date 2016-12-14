package assessment.android.istar.com.androidassessment.assessment_util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.TreeMap;

import assessment.android.istar.com.androidassessment.istarindia.complexobject.AppResponseObject;
import assessment.android.istar.com.androidassessment.istarindia.complexobject.XMLBatch;
import assessment.android.istar.com.androidassessment.istarindia.complexobject.XMLBatchGroup;
import assessment.android.istar.com.androidassessment.istarindia.complexobject.XMLLesson;
import assessment.android.istar.com.androidassessment.istarindia.complexobject.XMLSession;
import assessment.android.istar.com.androidassessment.istarindia.complexobject.XMLStudent;
import assessment.android.istar.com.androidassessment.istarindia.utils.SingletonStudent;

/**
 * Created by ajith on 13-12-2016.
 */

public class Assessmentutil {
    private Context context;

    public Assessmentutil(Context context) {
        this.context = context;
        String xml_object = getXml("complex.xml");
        xml_object = xml_object.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        StringReader reader = new StringReader(xml_object);
        Serializer serializer = new Persister();
        try {
            AppResponseObject example = serializer.read(AppResponseObject.class, context.getAssets().open("complex.xml"));
            SingletonStudent stu = SingletonStudent.getInstance();
            stu.setStudent(example.getStudent());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    private String getXml(String fileName) {
        String xmlString = null;
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(fileName);
            int length = is.available();
            byte[] data = new byte[length];
            is.read(data);
            xmlString = new String(data);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return xmlString;
    }



    public TreeMap<Integer, XMLLesson> getAllAssessment(){
        TreeMap<Integer, XMLLesson> xmlLessons = new TreeMap<>();
        try {
            XMLStudent student = SingletonStudent.getInstance().getStudent();

            for (XMLBatchGroup bg : student.getBatchGroups()) {
                for (XMLBatch b : bg.getBtaches()) {
                    if (b != null && b.getCourse() != null && b.getCourse().getCmsessions() != null) {
                        for (XMLSession s : b.getCourse().getCmsessions()) {
                            if (s != null && s.getLessons() != null && s.getLessons().size() > 0) {
                                for (XMLLesson l : s.getLessons()) {
                                    if (l != null && l.getStatus() != null && l.getStatus().equalsIgnoreCase("PUBLISHED"))
                                        xmlLessons.put(l.getlOrderId(), l);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlLessons;
    }


}
