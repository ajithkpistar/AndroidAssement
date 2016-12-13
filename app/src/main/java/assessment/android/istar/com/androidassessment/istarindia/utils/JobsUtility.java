package assessment.android.istar.com.androidassessment.istarindia.utils;

import com.istarindia.complexobject.XMLInterview;
import com.istarindia.complexobject.XMLInvite;
import com.istarindia.complexobject.XMLOffer;
import com.istarindia.complexobject.XMLStudent;
import com.istarindia.complexobject.XMLTest;

import java.util.ArrayList;

import studenttrainer.istar.studenttrainerapp.jobs.job_bean.JobsNotification;
import studenttrainer.istar.studenttrainerapp.jobs.job_bean.interview.InterviewChildUI;
import studenttrainer.istar.studenttrainerapp.jobs.job_bean.interview.InterviewParentUI;
import studenttrainer.istar.studenttrainerapp.jobs.job_bean.invites.JobInviteChild;
import studenttrainer.istar.studenttrainerapp.jobs.job_bean.invites.JobInviteParent;
import studenttrainer.istar.studenttrainerapp.jobs.job_bean.offers.OfferChildUI;
import studenttrainer.istar.studenttrainerapp.jobs.job_bean.offers.OfferParentUI;
import studenttrainer.istar.studenttrainerapp.jobs.job_bean.tests.JobTestUI;

/**
 * Created by Mayank on 30/08/2016.
 */
public class JobsUtility {

    public ArrayList<JobsNotification> getNotification() {
        ArrayList<JobsNotification> data = new ArrayList<>();
        XMLStudent s = SingletonStudent.getInstance().getStudent();
        int i = 1;
        if (s.getTests() != null && s.getTests().size() > 0) {
            for (XMLTest test : s.getTests()) {
                if (i <= 9) {
                    JobsNotification jn = new JobsNotification();
                    jn.setTime(test.getTestTime());
                    jn.setDescription("Recruitement Assessment At " + test.getCompanyName() + " for profile " + test.getJobTitle());
                    data.add(jn);
                    i++;
                }
            }
        }


        return data;
    }


    public ArrayList<JobInviteParent> getJobInvites() {
        XMLStudent s = SingletonStudent.getInstance().getStudent();
        ArrayList<JobInviteParent> data = new ArrayList<>();
        for (XMLInvite invite : s.getInvites()) {
            JobInviteParent p = new JobInviteParent();
            p.setCompanyName(invite.getCompanyName());
            p.setImageUrl(invite.getImageUrl());
            p.setJobTitle(invite.getVacancyJobTitle());
            p.setLocation(invite.getVacancyLocation());
            p.setHoursAgo(invite.getPostedHoursAgo());

            JobInviteChild c = new JobInviteChild();

            c.setJobTitle(invite.getVacancyJobTitle());
            c.setLocation(invite.getVacancyLocation());
            c.setJobDescription(invite.getVacancyDescription());
            c.setStudent_invite_id(invite.getEventId());

            ArrayList<JobInviteChild> cdata = new ArrayList<>();
            cdata.add(c);

            p.setChildren(cdata);
            data.add(p);
        }

        return data;
    }

    public ArrayList<InterviewParentUI> getJobInterview() {
        XMLStudent s = SingletonStudent.getInstance().getStudent();
        ArrayList<InterviewParentUI> data = new ArrayList<>();
        for (XMLInterview interview : s.getInterviews()) {

            InterviewParentUI p = new InterviewParentUI();
            p.setJobTitle(interview.getJobTitle());
            p.setLocation(interview.getLocation());
            p.setCompanyImageUrl(interview.getImageUrl());
            p.setCompanyName(interview.getCompanyName());
            p.setHoursAgo(interview.getPostedHoursAgo());

            InterviewChildUI c = new InterviewChildUI();

            c.setStudent_invite_id(interview.getEventId());
            c.setPanelList(interview.getRecruiterName());
            c.setDesignation("Recruiter");
            c.setInterviewType(interview.getInterviwType());
            c.setInterviewDate(interview.getInterviewDate());
            c.setInterviewTime(interview.getInterviewTime());


            c.setJoinUrl(interview.getJoinUrl());
            c.setPanelist(interview.getPanelist());
            c.setMeetingId(interview.getMeetingId());
            c.setMeetingPassword(interview.getMeetingPassword());
            c.setHostUrl(interview.getHostUrl());

            ArrayList<InterviewChildUI> cdata = new ArrayList<>();
            cdata.add(c);

            p.setChildren(cdata);
            data.add(p);
        }

        return data;
    }


    public ArrayList<OfferParentUI> getJobOfferUI() {
        XMLStudent s = SingletonStudent.getInstance().getStudent();
        ArrayList<OfferParentUI> data = new ArrayList<>();
        for (XMLOffer offer : s.getOffers()) {


            OfferParentUI p = new OfferParentUI();
            p.setCompanyName(offer.getCompanyName());
            p.setImageUrl(offer.getImageUrl());
            p.setLocation(offer.getLocation());
            p.setJobTitle(offer.getJobTitle());
            p.setHoursAgo(offer.getPostedHoursAgo());


            OfferChildUI c = new OfferChildUI();
            c.setOfferLetter(offer.getOfferLetterUrl());
            c.setJobDescription(offer.getDescription());
            c.setStudentOfferId(offer.getEventId());

            ArrayList<OfferChildUI> cdata = new ArrayList<>();
            cdata.add(c);

            p.setChildren(cdata);
            data.add(p);
        }

        return data;
    }

    public ArrayList<JobTestUI> getJobTestUI() {
        XMLStudent s = SingletonStudent.getInstance().getStudent();
        ArrayList<JobTestUI> data = new ArrayList<>();
        for (XMLTest test : s.getTests()) {


            JobTestUI t = new JobTestUI();
            t.setJobTitle(test.getJobTitle());
            t.setCompanyName(test.getCompanyName());
            t.setLocation(test.getLocation());
            t.setTestDate(test.getTestDate());
            t.setImageUrl(test.getImageUrl());
            t.setTestTime(test.getTestTime());
            t.setTestAssesmentDuration(test.getDuration());
            t.setTestAssesmentName(test.getAssessmentTitle());
            t.setAssessmentId(test.getAssessmentId());
            t.setStudentInviteId(test.getStudentInviteId());
            t.setTestNoOfQues(test.getNumberOfQuestions());

            data.add(t);


        }

        return data;
    }

}
