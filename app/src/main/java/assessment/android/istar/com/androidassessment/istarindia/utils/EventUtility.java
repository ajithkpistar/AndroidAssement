/*
package assessment.android.istar.com.androidassessment.istarindia.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import assessment.android.istar.com.androidassessment.istarindia.complexobject.XMLEvents;

*/
/**
 * Created by Mayank on 13/08/2016.
 *//*

public class EventUtility {

    public ArrayList<XMLEvents> getSingleDayEvent(String date)
    {
        ArrayList<XMLEvents> events = new ArrayList<>();
        //System.out.println("new size--------------"+SingletonStudent.getInstance().getStudent().getEvents());
        for(XMLEvents event : SingletonStudent.getInstance().getStudent().getEvents())
        {
            if(event.getEventdate().contains(date))
            {
              events.add(event);
            }
        }

        return events;
    }

    public XMLEvents getClosestEventId()
    {
        XMLEvents eventId =null;
        boolean has_event=false;
        for(XMLEvents e : SingletonStudent.getInstance().getStudent().getEvents())
        {

            try {
            SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date  = (Date)timeformat.parse(e.getEventdate());

                //DateUtils.isToday(date.getTime())
            if( e.getEventType().contains("BATCH_SCHEDULE") && !e.getEventName().contains("TESTING"))
            {

                     System.out.println();
                    Calendar cal = Calendar.getInstance();

                    int duration =  e.getEventMin();
                    int hours = e.getEventHour();
                    duration = hours*60+duration;
                    Date startTime = timeformat.parse(e.getEventdate());
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(startTime);
                    cal1.add(Calendar.MINUTE, duration);
                   //System.out.println("actual date"+date+"duration ="+duration+" start time "+ startTime+" end time = "+cal1.getTime() );
                    has_event = cal.getTime().after(startTime) && cal.getTime().before(cal1.getTime());

                    if(has_event) {
                        eventId = e;
                        break;
                    }


            }

            } catch (ParseException e1) {
                // e1.printStackTrace();
            }
            //2016-07-14 10:00:00.0
        }

        return eventId;
    }


}
*/
