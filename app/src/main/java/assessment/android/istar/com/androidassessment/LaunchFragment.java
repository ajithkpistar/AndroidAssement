package assessment.android.istar.com.androidassessment;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;

import assessment.android.istar.com.androidassessment.assessment_database.AssessmentDataHandler;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.assessment_util.ViewpagerAdapter;


public class LaunchFragment extends Fragment {
    public final static String ASSESSMENT_ID = "ASSESSMENT_ID";
    private int assessment_id;
    private FrameLayout frame_container;
    private Button launchBtn;
    private TextView title, text, description;
    private View line;
    private AssessmentDataHandler assessmentDataHandler;
    private CMSAssessment cmsAssessment;
    RelativeLayout main_layout, progress_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_launch, container, false);
        main_layout = (RelativeLayout) view.findViewById(R.id.main_layout);
        progress_layout = (RelativeLayout) view.findViewById(R.id.progress_layout);
        launchBtn = (Button) view.findViewById(R.id.launchBtn);
        title = (TextView) view.findViewById(R.id.title);
        text = (TextView) view.findViewById(R.id.text);
        description = (TextView) view.findViewById(R.id.description);
        line = (View) view.findViewById(R.id.line);

        if (getArguments() != null) {
            if (getArguments().getString(ASSESSMENT_ID) != null) {
                assessment_id = Integer.parseInt(getArguments().getString(ASSESSMENT_ID));
                Log.v("Talentify", "Assessment Id---->" + assessment_id);
            }
        }
        assessmentDataHandler = new AssessmentDataHandler(getContext());

        progress_layout.setVisibility(View.VISIBLE);
        main_layout.setVisibility(View.GONE);
        try {
            Cursor c = assessmentDataHandler.getData(assessment_id);
            if (c.moveToFirst()) {
                setupOfflineAssement(c.getString(1));
            } else {
                new FetchAssessmentFromServer(getContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, assessment_id + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new FetchAssessmentFromServer(getContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, assessment_id + "");
        }

        launchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString(CMSAssessmentFragment.ASSESSMENT_ID, assessment_id + "");
                    Fragment fragment = new CMSAssessmentFragment();
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private void setupOfflineAssement(String assessment_string) {
        try {
            StringReader reader = new StringReader(assessment_string);
            Serializer serializer = new Persister();
            cmsAssessment = serializer.read(CMSAssessment.class, reader);

            if (cmsAssessment != null) {
                if (cmsAssessment.getAssessmentTitle() != null) {
                    text.setText(cmsAssessment.getAssessmentTitle());
                }
            }
            progress_layout.setVisibility(View.GONE);
            main_layout.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class FetchAssessmentFromServer extends AsyncTask<String, Integer, String> {

        private Context context;
        boolean response_success = true;

        public FetchAssessmentFromServer(Context context) {
            this.context = context;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            String xml_object = null;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                String BASE_URL = context.getResources().getString(R.string.server_ip) + "/get_offline_assessment?assessment_id=" + params[0];
                Log.v("Talentify", "BASE_URL " + BASE_URL);

                int timeout = 80; // seconds
                HttpParams httpParams = httpclient.getParams();
                httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
                httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);
                HttpPost httppost = new HttpPost(BASE_URL);
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                xml_object = EntityUtils.toString(entity, "UTF-8");
                assessmentDataHandler.saveContent(params[0], xml_object);
            } catch (Exception e) {
                response_success = false;
                e.printStackTrace();
            }
            return xml_object;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && !result.equalsIgnoreCase("")) {
                setupOfflineAssement(result);
            }
        }
    }
}
