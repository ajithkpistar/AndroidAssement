package assessment.android.istar.com.androidassessment.istarindia.utils;

import com.istarindia.complexobject.XMLNotification;

import java.util.ArrayList;

import studenttrainer.istar.studenttrainerapp.notification.pojo.AndroidNotification;

/**
 * Created by Mayank on 15/08/2016.
 */
public class NotificationUtils {
    public ArrayList<AndroidNotification> getNotifications() {

        ArrayList<AndroidNotification> notes = new ArrayList<>();
        for(XMLNotification n : SingletonStudent.getInstance().getStudent().getNotifications())
        {
            AndroidNotification nn = new AndroidNotification();
            nn.setType(n.getType());
            nn.setDate(n.getDate());
            nn.setDescription(n.getDescription());
            nn.setId(n.getId());
            nn.setTime(n.getTime());
            nn.setTitle(n.getTitle());
            nn.setReceivedAt(n.getReceivedAt());
            notes.add(nn);
        }
    return notes;
    }

}
