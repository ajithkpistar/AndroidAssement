package assessment.android.istar.com.androidassessment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class LaunchFragment extends Fragment {

    private FrameLayout frame_container;
    private Button launchBtn;
    private TextView title,text,description;
    private View line;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_launch, container, false);

        launchBtn = (Button) view.findViewById(R.id.launchBtn);
        title = (TextView) view.findViewById(R.id.title);
        text = (TextView) view.findViewById(R.id.text);
        description = (TextView) view.findViewById(R.id.description);
        line = (View)view.findViewById(R.id.line);
        launchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new AssessmentFragment()).commit();
                title.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                description.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                launchBtn.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
