package assessment.android.istar.com.androidassessment.template;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

/**
 * Created by Feroz on 14-12-2016.
 */

public class MultipleOptionSingleChoice extends AssessmentCard {

    private WebView question, option1, option2, option3, option4, option5;
    private AppCompatRadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;
    private int position;
    private CMSQuestion cmsQuestion;
    private RadioGroup Rgroup;
    private View view;
    private String selectedVal = "";
    private long start_time;
    private boolean chck_1 = false, chck_2 = false, chck_3 = false, chck_4 = false, chck_5 = false;
    private TextView hidden_key, hidden_value, hidden_time;
    private ScrollView mainLayout;
    private LinearLayout layoutBtn1,layoutBtn2,layoutBtn3,layoutBtn4,layoutBtn5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.multipleoption_singlechoice, container, false);

        //hardware acceleration disable
        try {
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (Exception e) {
        }

        mainLayout = (ScrollView) view.findViewById(R.id.mainLayout);
        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);

        rbtn1 = (AppCompatRadioButton) view.findViewById(R.id.rbtn1);
        rbtn2 = (AppCompatRadioButton) view.findViewById(R.id.rbtn2);
        rbtn3 = (AppCompatRadioButton) view.findViewById(R.id.rbtn3);
        rbtn4 = (AppCompatRadioButton) view.findViewById(R.id.rbtn4);
        rbtn5 = (AppCompatRadioButton) view.findViewById(R.id.rbtn5);

        layoutBtn1 = (LinearLayout)view.findViewById(R.id.layoutBtn1);
        layoutBtn2 = (LinearLayout)view.findViewById(R.id.layoutBtn2);
        layoutBtn3 = (LinearLayout)view.findViewById(R.id.layoutBtn3);
        layoutBtn4 = (LinearLayout)view.findViewById(R.id.layoutBtn4);
        layoutBtn5 = (LinearLayout)view.findViewById(R.id.layoutBtn5);



        Boolean externalReadable = ImageSaver.isExternalStorageReadable();
        ThemeUtils themeutil = new ThemeUtils();

        Rgroup = (RadioGroup) view.findViewById(R.id.options);

        hidden_key = (TextView) view.findViewById(R.id.hidden_key);
        hidden_value = (TextView) view.findViewById(R.id.hidden_value);
        hidden_time = (TextView) view.findViewById(R.id.hidden_time);
        if (getArguments() != null) {
            if (getArguments().getSerializable(AssessmentCard.CMSASSESSMENT) != null) {
                cmsQuestion = (CMSQuestion) getArguments().getSerializable(AssessmentCard.CMSASSESSMENT);
                if (getArguments().getInt(AssessmentCard.POSITION, -1) != -1)
                    position = getArguments().getInt(AssessmentCard.POSITION, -1);
            }
        }

        if (cmsQuestion != null) {
            if (cmsQuestion.getQuestionText() != null) {
                themeutil.getThemeQuestion(cmsQuestion, question, getActivity(), externalReadable);
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                hidden_key.setText(cmsQuestion.getId() + "");

                selectedVal = "";
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {

                        themeutil.getThemeSingleOption(cmsQuestion, option1, rbtn1, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 1) {
                        themeutil.getThemeSingleOption(cmsQuestion, option2, rbtn2, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 2) {
                        themeutil.getThemeSingleOption(cmsQuestion, option3, rbtn3, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 3) {
                        themeutil.getThemeSingleOption(cmsQuestion, option4, rbtn4, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 4) {
                        themeutil.getThemeSingleOption(cmsQuestion, option5, rbtn5, cmsOption, getActivity(), externalReadable);
                    }
                    temp++;
                }
            }
            if (cmsQuestion.getTheme() != null) {
                mainLayout.setBackgroundColor(Color.parseColor(cmsQuestion.getTheme().getBackgroundColor()));
                setColorforRadioButton();
            }

        }

        rbtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (chck_1) {
                    selectUnselect(0);
                    chck_1 = false;
                } else {
                    selectUnselect(1);
                }
            }
        });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (chck_2) {
                    selectUnselect(0);
                    chck_2 = false;
                } else {
                    selectUnselect(2);
                }

            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (chck_3) {
                    selectUnselect(0);
                    chck_3 = false;
                } else {
                    selectUnselect(3);
                }

            }
        });
        rbtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (chck_4) {
                    selectUnselect(0);
                    chck_4 = false;
                } else {
                    selectUnselect(4);
                }

            }
        });
        rbtn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (chck_5) {
                    selectUnselect(0);
                    chck_5 = false;
                } else {
                    selectUnselect(5);
                }
            }
        });
        webviewSetup();
        return view;
    }

    private void setColorforRadioButton() {
        try {
            String color = "#000000";
            try {
                color = cmsQuestion.getTheme().getListitemFontColor();
            } catch (Exception e) {
            }
            int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
            int[] colors = new int[]{Color.parseColor(color), Color.parseColor(color)};
            ColorStateList colorStateList = new ColorStateList(states, colors);
            CompoundButtonCompat.setButtonTintList(rbtn1, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn2, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn3, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn4, colorStateList);
            CompoundButtonCompat.setButtonTintList(rbtn5, colorStateList);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void selectUnselect(int position) {
        switch (position) {
            case 1:
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn1.getTag().toString();
                chck_1 = true;
                chck_2 = false;
                chck_3 = false;
                chck_4 = false;
                chck_5 = false;
                layoutBtn1.setBackgroundColor(getResources().getColor(R.color.SelectedOption));
                layoutBtn2.setBackgroundColor(0);
                layoutBtn3.setBackgroundColor(0);
                layoutBtn4.setBackgroundColor(0);
                layoutBtn5.setBackgroundColor(0);

                break;
            case 2:
                rbtn1.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn2.getTag().toString();
                chck_2 = true;
                chck_1 = false;
                chck_3 = false;
                chck_4 = false;
                chck_5 = false;
                layoutBtn2.setBackgroundColor(getResources().getColor(R.color.SelectedOption));
                layoutBtn1.setBackgroundColor(0);
                layoutBtn3.setBackgroundColor(0);
                layoutBtn4.setBackgroundColor(0);
                layoutBtn5.setBackgroundColor(0);
                break;
            case 3:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn3.getTag().toString();
                chck_3 = true;
                chck_1 = false;
                chck_2 = false;
                chck_4 = false;
                chck_5 = false;
                layoutBtn3.setBackgroundColor(getResources().getColor(R.color.SelectedOption));
                layoutBtn2.setBackgroundColor(0);
                layoutBtn1.setBackgroundColor(0);
                layoutBtn4.setBackgroundColor(0);
                layoutBtn5.setBackgroundColor(0);
                break;
            case 4:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = rbtn4.getTag().toString();
                chck_4 = true;
                chck_1 = false;
                chck_2 = false;
                chck_3 = false;
                chck_5 = false;
                layoutBtn4.setBackgroundColor(getResources().getColor(R.color.SelectedOption));
                layoutBtn2.setBackgroundColor(0);
                layoutBtn3.setBackgroundColor(0);
                layoutBtn1.setBackgroundColor(0);
                layoutBtn5.setBackgroundColor(0);
                break;
            case 5:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                selectedVal = rbtn5.getTag().toString();
                chck_5 = true;
                chck_1 = false;
                chck_2 = false;
                chck_3 = false;
                chck_4 = false;
                layoutBtn5.setBackgroundColor(getResources().getColor(R.color.SelectedOption));
                layoutBtn2.setBackgroundColor(0);
                layoutBtn3.setBackgroundColor(0);
                layoutBtn4.setBackgroundColor(0);
                layoutBtn1.setBackgroundColor(0);
                break;
            default:
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                rbtn5.setChecked(false);
                selectedVal = "";
                layoutBtn1.setBackgroundColor(0);
                layoutBtn1.setBackgroundColor(0);
                layoutBtn2.setBackgroundColor(0);
                layoutBtn3.setBackgroundColor(0);
                layoutBtn4.setBackgroundColor(0);
                layoutBtn5.setBackgroundColor(0);
                break;
        }
        hidden_value.setText(selectedVal + "");
    }

    private void webviewSetup() {
        forceWebViewRedraw(question);
        forceWebViewRedraw(option1);
        forceWebViewRedraw(option2);
        forceWebViewRedraw(option3);
        forceWebViewRedraw(option4);
        forceWebViewRedraw(option5);
    }

    private void forceWebViewRedraw(final WebView mWebView) {

        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (view.getId() == R.id.option1) {
                            if (chck_1) {
                                selectUnselect(0);
                                chck_1 = false;
                            } else {
                                rbtn1.setChecked(true);
                                selectUnselect(1);
                            }
                        } else if (view.getId() == R.id.option2) {
                            if (chck_2) {
                                selectUnselect(0);
                                chck_2 = false;
                            } else {
                                rbtn2.setChecked(true);
                                selectUnselect(2);
                            }
                        } else if (view.getId() == R.id.option3) {
                            if (chck_3) {
                                selectUnselect(0);
                                chck_3 = false;
                            } else {
                                rbtn3.setChecked(true);
                                selectUnselect(3);
                            }
                        } else if (view.getId() == R.id.option4) {
                            if (chck_4) {
                                selectUnselect(0);
                                chck_4 = false;
                            } else {
                                rbtn4.setChecked(true);
                                selectUnselect(4);
                            }
                        } else if (view.getId() == R.id.option5) {
                            if (chck_5) {
                                selectUnselect(0);
                                chck_5 = false;
                            } else {
                                rbtn2.setChecked(true);
                                selectUnselect(5);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return false;
            }
        });

        //for future problem in webview try this
        /*final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mWebView.invalidate();
            }
        };
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                view.invalidate();

            }
        });*/
        // mWebView.postDelayed(runnable, 0);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            if (isVisibleToUser) {
                start_time = System.currentTimeMillis();
                hidden_time.setText(start_time + "");
            }
        } catch (Exception e) {
        }
    }
}
