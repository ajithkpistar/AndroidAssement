package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import assessment.android.istar.com.androidassessment.R;


public class BasicFragment extends AssessmentCard {

    private TextView question,timer;
    private Toast mToastToShow;
    int delay =60000;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        mToastToShow = Toast.makeText(view.getContext(), "Hurry Up.", Toast.LENGTH_LONG);
        timer = (TextView) view.findViewById(R.id.timer);
        new CountDownTimer(delay, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timer.setText(""+String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                if(millisUntilFinished < 400000 && millisUntilFinished < 20000){mToastToShow.show();}

                System.out.println("--------timer------>"+millisUntilFinished);
                // mToastToShow.show();

            }

            public void onFinish() {

                timer.setText("TIME OVER!");
               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new EndAssessmentFragment()).commit();
            }
        }.start();


        return view;
    }




}
