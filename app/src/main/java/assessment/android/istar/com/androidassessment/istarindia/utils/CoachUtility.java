package assessment.android.istar.com.androidassessment.istarindia.utils;

import com.istarindia.complexobject.XMLCoachStudent;
import com.istarindia.complexobject.XMLCourseRating;
import com.istarindia.complexobject.XMLEvents;

import java.util.ArrayList;
import java.util.HashMap;

import studenttrainer.istar.studenttrainerapp.coach.pojo.BatchStudentDetail;
import studenttrainer.istar.studenttrainerapp.coach.pojo.FavouriteStudent;
import studenttrainer.istar.studenttrainerapp.coach.pojo.ReportData;
import studenttrainer.istar.studenttrainerapp.event_workflow.pojo.Student;

/**
 * Created by Mayank on 13/08/2016.
 */
public class CoachUtility {

    public ArrayList<FavouriteStudent> getAllStudents()
    {
        ArrayList<FavouriteStudent> data = new ArrayList<>();
        ArrayList<Integer> alreday_added= new ArrayList<>();
        for(XMLCoachStudent cst : SingletonStudent.getInstance().getStudent().getCoachStudents())
        {
            if(!alreday_added.contains(cst.getStudentId()) && SingletonStudent.getInstance().getStudent().getId()!= cst.getStudentId())
            {
                alreday_added.add(cst.getStudentId());
                FavouriteStudent fs = new FavouriteStudent();
                fs.setId(cst.getStudentId());
                fs.setCollegeName(cst.getOrganizationName());
                fs.setContactNum(cst.getMobile());
                fs.setEmail("null");
                fs.setImageUrl(cst.getImageUrl());

                fs.setName(cst.getStudentName());
                fs.setFav(cst.isIsfavoutite());
                ArrayList<ReportData> reportData = new ArrayList<>();
                for(XMLCourseRating cr: cst.getCourseRating())
                {

                    ReportData reportData1 = new ReportData(cr.getCourseName(),cr.getRating()+"",cr.getCourse_id());
                    reportData.add(reportData1);
                }
                fs.setReportData(reportData);
                data.add(fs);
            }

        }

        return data;
    }

    public ArrayList<BatchStudentDetail> getBatchStudent()
    {
        ArrayList<BatchStudentDetail> data = new ArrayList<>();

        HashMap<String,ArrayList<XMLCoachStudent> > inside = new HashMap<>();
        HashMap<String,String> batch_org = new HashMap<>();
        for(XMLCoachStudent cs : SingletonStudent.getInstance().getStudent().getCoachStudents())
        {
            if(SingletonStudent.getInstance().getStudent().getId()!= cs.getStudentId())
            {
                if(inside.containsKey(cs.getBatchGroup()) )
                {
                    ArrayList<XMLCoachStudent> stu = inside.get(cs.getBatchGroup());
                    stu.add(cs);
                    inside.put(cs.getBatchGroup(),stu);
                }
                else
                {
                    ArrayList<XMLCoachStudent> stu = new ArrayList<>();
                    stu.add(cs);
                    inside.put(cs.getBatchGroup(),stu);
                    batch_org.put(cs.getBatchGroup(), cs.getOrganizationName());
                }
            }


        }

        for(String str : inside.keySet())
        {
            String bg_name = str;
            ArrayList<FavouriteStudent> favouriteStudents= new ArrayList<>();
            ArrayList<XMLCoachStudent> stus = inside.get(str);
            for(XMLCoachStudent cst : stus)
            {

                FavouriteStudent fs = new FavouriteStudent();
                fs.setId(cst.getStudentId());
                fs.setCollegeName(cst.getOrganizationName());
                fs.setContactNum(cst.getMobile());
                fs.setEmail("null");
                fs.setName(cst.getStudentName());
                fs.setImageUrl(cst.getImageUrl());

                fs.setFav(cst.isIsfavoutite());
                ArrayList<ReportData> reportData = new ArrayList<>();
                for(XMLCourseRating cr: cst.getCourseRating())
                {

                    ReportData reportData1 = new ReportData(cr.getCourseName(),cr.getRating()+"",cr.getCourse_id());
                    reportData.add(reportData1);
                }
                fs.setReportData(reportData);
                favouriteStudents.add(fs);
            }
            BatchStudentDetail bd = new BatchStudentDetail();
            bd.setBatch_name(str);
            bd.setOrganization_name(batch_org.get(str));
            bd.setStudentDetails(favouriteStudents);
            data.add(bd);

        }

        return data;
    }

    public ArrayList<FavouriteStudent> getFavoutite()
    {
        ArrayList<FavouriteStudent> data = new ArrayList<>();
        ArrayList<Integer> alreday_added= new ArrayList<>();
        for(XMLCoachStudent cs : SingletonStudent.getInstance().getStudent().getCoachStudents())
        {
            if(!alreday_added.contains(cs.getStudentId()) && SingletonStudent.getInstance().getStudent().getId()!= cs.getStudentId())
            {
                alreday_added.add(cs.getStudentId());
                if(cs.isIsfavoutite())
                {
                    FavouriteStudent fs = new FavouriteStudent();
                    fs.setId(cs.getStudentId());
                    fs.setCollegeName(cs.getOrganizationName());
                    fs.setContactNum(cs.getMobile());
                    fs.setEmail("null");
                    fs.setName(cs.getStudentName());
                    fs.setFav(cs.isIsfavoutite());
                    fs.setImageUrl(cs.getImageUrl());
                    ArrayList<ReportData> reportData = new ArrayList<>();
                    for(XMLCourseRating cr: cs.getCourseRating())
                    {

                        ReportData reportData1 = new ReportData(cr.getCourseName(),cr.getRating()+"",cr.getCourse_id());
                        reportData.add(reportData1);
                    }
                    fs.setReportData(reportData);
                    data.add(fs);
                }
            }

        }


        return data;
    }

    public ArrayList<Student> getStuForAttendance(String eventId) {
       // System.out.println("event id "+eventId);
        ArrayList<Student> stu = new ArrayList<>();
          int batchGroupId=0;
        for(XMLEvents e : SingletonStudent.getInstance().getStudent().getEvents())
        {
            if(e.getId().equalsIgnoreCase(eventId))
            {
                batchGroupId= e.getBgroupId();
            }
        }

        //System.out.println("batchId---"+batchGroupId);


        ArrayList<Integer> already_added = new ArrayList<>();
        for(XMLCoachStudent cst : SingletonStudent.getInstance().getStudent().getCoachStudents())
        {
           // System.out.println("stu batchgrp id----"+cst.getBatchId());
            if(!already_added.contains(cst.getStudentId()) && cst.getBatchId()==batchGroupId)
            {
                already_added.add(cst.getStudentId());
                Student s = new Student();
                s.setImageUrl(cst.getImageUrl());
                s.setName(cst.getImageUrl());
                s.setCollegeName(cst.getOrganizationName());
                s.setName(cst.getStudentName());
                s.setContactNum(cst.getMobile());
                s.setId(cst.getStudentId());
                s.setMarked(false);
                stu.add(s);
            }

        }
        return stu;
    }
}
