package assessment.android.istar.com.androidassessment.istarindia.utils;

import com.istarindia.complexobject.XMLBatch;
import com.istarindia.complexobject.XMLBatchGroup;
import com.istarindia.complexobject.XMLCourse;
import com.istarindia.complexobject.XMLLesson;
import com.istarindia.complexobject.XMLNote;
import com.istarindia.complexobject.XMLSession;
import com.istarindia.complexobject.XMLStudent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import studenttrainer.istar.studenttrainerapp.learn.pojo.AndroidCourse;

/**
 * Created by Mayank on 14/08/2016.
 */
public class CourseUtils {

    public TreeMap<Integer, AndroidCourse> getOrderedCourse() {
        ArrayList<Integer> course_added = new ArrayList<>();
        TreeMap<Integer, AndroidCourse> data = new TreeMap<>();
        TreeMap<Integer, XMLBatch> orderedBatch = new TreeMap<>();
        XMLStudent student = SingletonStudent.getInstance().getStudent();
        for (XMLBatchGroup bg : student.getBatchGroups()) {
            for (XMLBatch b : bg.getBtaches()) {
                orderedBatch.put(b.getOrderId(), b);
            }
        }
        System.out.print(orderedBatch.size() + ":size ");
        Set set = orderedBatch.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();

            XMLBatch bb = (XMLBatch) me.getValue();
            //System.out.println(me.getKey() + ": "+bb.getCourse().getCourseName());
            XMLCourse c = bb.getCourse();
            AndroidCourse course0 = new AndroidCourse();
            course0.setId(c.getCourseId());
            course0.setName(c.getCourseName());
            course0.setCourseDescription(c.getCoursedescription());
            if (!course_added.contains(course0.getId())) {
                course_added.add(course0.getId());
                data.put((int) me.getKey(), course0);
            }

        }
        return data;
    }


    public XMLSession getSession(XMLCourse course, int sesssion_id) {
        XMLSession ss = new XMLSession();
        if (null != course.getCmsessions() && course.getCmsessions().size() > 0) {
            for (XMLSession s : course.getCmsessions()) {
                if (s.getCmsession_id() == sesssion_id) {
                    ss = s;
                    break;
                }
            }
        }

        return ss;
    }

    public XMLCourse getCourse(int course_id) {
        XMLCourse course = new XMLCourse();
        boolean having_note = false;
        for (XMLNote note : SingletonStudent.getInstance().getStudent().getNotes()) {
            if (note.getCourseId() == course_id) {
                having_note = true;
            }
        }
        System.out.println("check course" + course_id);
        if (having_note) {
            for (XMLBatchGroup bg : SingletonStudent.getInstance().getStudent().getBatchGroups()) {
                for (XMLBatch b : bg.getBtaches()) {
                    if (b.getCourse().getCourseId() == course_id) {
                        course = b.getCourse();
                        break;
                    }
                }
            }
            System.out.println("found course" + course.getCourseName());
        }
        return course;
    }

    public ArrayList<XMLNote> getNotes(XMLCourse course, XMLSession session) {
        ArrayList<XMLNote> notes = new ArrayList<>();
        for (XMLNote note : SingletonStudent.getInstance().getStudent().getNotes()) {
            if (note.getSessionId() == session.getCmsession_id()) {
                notes.add(note);
            }
        }
        return notes;
    }

    public ArrayList<XMLNote> getNotes(int course_id, int session_id) {
        XMLCourse course = getCourse(course_id);
        XMLSession session = new XMLSession();
        for (XMLSession s : course.getCmsessions()) {
            if (s.getCmsession_id() == session_id) {
                session = s;
                break;
            }
        }

        return getNotes(course, session);

    }

    public TreeMap<Integer, XMLLesson> getAllCoursePlayLessons(Integer course_id) {
        TreeMap<Integer, XMLLesson> xmlLessons = new TreeMap<>();
        try {
            XMLStudent student = SingletonStudent.getInstance().getStudent();

            for (XMLBatchGroup bg : student.getBatchGroups()) {
                for (XMLBatch b : bg.getBtaches()) {
                    if (b != null && b.getCourse() != null && b.getCourse().getCourseId() == course_id && b.getCourse().getCmsessions() != null) {
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

    public TreeMap<Integer, XMLLesson> getAllSessionPlayLessons(Integer course_id, Integer sessionId) {
        TreeMap<Integer, XMLLesson> xmlLessons = new TreeMap<>();
        try {
            XMLStudent student = SingletonStudent.getInstance().getStudent();

            for (XMLBatchGroup bg : student.getBatchGroups()) {
                for (XMLBatch b : bg.getBtaches()) {
                    if (b != null && b.getCourse() != null && b.getCourse().getCourseId() == course_id && b.getCourse().getCmsessions() != null) {
                        for (XMLSession s : b.getCourse().getCmsessions()) {
                            if (s != null && s.getLessons() != null && s.getLessons().size() > 0 && s.getCmsession_id() == sessionId) {
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

    //get all lessons of all courses.
    public TreeMap<Integer, XMLLesson> getAllLessons() {
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
