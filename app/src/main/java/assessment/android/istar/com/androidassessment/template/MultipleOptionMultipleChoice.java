package assessment.android.istar.com.androidassessment.template;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;


public class MultipleOptionMultipleChoice extends AssessmentCard {

    private WebView question, option1, option2, option3, option4, option5;
    private int position;
    private CMSQuestion cmsQuestion;
    private long start_time;
    private ScrollView mainLayout;
    private boolean chck_1 = false, chck_2 = false, chck_3 = false, chck_4 = false, chck_5 = false;
    private AppCompatCheckBox checkbtn1, checkbtn2, checkbtn3, checkbtn4, checkbtn5;
    private View view;
    private String selectedVal = "";
    private TextView hidden_key, hidden_value, hidden_time;
    private ThemeUtils themeutil;
    private RelativeLayout label_view;
    private CardView layoutBtn1, layoutBtn2, layoutBtn3, layoutBtn4, layoutBtn5;
    private String optionCardColor = "#ffffff";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_fragment_multiple_option_multiple_choice, container, false);

        //hardware acceleration disable
        try {
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (Exception e) {
        }

        label_view = (RelativeLayout) view.findViewById(R.id.label_view);

        mainLayout = (ScrollView) view.findViewById(R.id.mainLayout);
        question = (WebView) view.findViewById(R.id.question);
        option1 = (WebView) view.findViewById(R.id.option1);
        option2 = (WebView) view.findViewById(R.id.option2);
        option3 = (WebView) view.findViewById(R.id.option3);
        option4 = (WebView) view.findViewById(R.id.option4);
        option5 = (WebView) view.findViewById(R.id.option5);

        checkbtn1 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn1);
        checkbtn2 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn2);
        checkbtn3 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn3);
        checkbtn4 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn4);
        checkbtn5 = (AppCompatCheckBox) view.findViewById(R.id.checkbtn5);

        layoutBtn1 = (CardView) view.findViewById(R.id.layoutBtn1);
        layoutBtn2 = (CardView) view.findViewById(R.id.layoutBtn2);
        layoutBtn3 = (CardView) view.findViewById(R.id.layoutBtn3);
        layoutBtn4 = (CardView) view.findViewById(R.id.layoutBtn4);
        layoutBtn5 = (CardView) view.findViewById(R.id.layoutBtn5);

        hidden_key = (TextView) view.findViewById(R.id.hidden_key);
        hidden_value = (TextView) view.findViewById(R.id.hidden_value);
        hidden_time = (TextView) view.findViewById(R.id.hidden_time);
        Boolean externalReadable = ImageSaver.isExternalStorageReadable();

      themeutil = new ThemeUtils();

        if (getArguments() != null) {
            if (getArguments().getSerializable(AssessmentCard.CMSASSESSMENT) != null) {
                cmsQuestion = (CMSQuestion) getArguments().getSerializable(AssessmentCard.CMSASSESSMENT);
                if (getArguments().getInt(AssessmentCard.POSITION, -1) != -1)
                    position = getArguments().getInt(AssessmentCard.POSITION, -1);
            }
        }

        if (cmsQuestion != null) {
            if (cmsQuestion.getQuestionText() != null) {
                hidden_key.setText(cmsQuestion.getId() + "");
                themeutil.getThemeQuestion(cmsQuestion, question, getActivity(), externalReadable);
            }
            if (cmsQuestion.getOptions() != null) {
                int temp = 0;
                for (CMSOption cmsOption : cmsQuestion.getOptions()) {
                    if (temp == 0) {

                        themeutil.getThemeMultipleOption(cmsQuestion, option1, checkbtn1,layoutBtn1, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 1) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option2, checkbtn2,layoutBtn2, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 2) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option3, checkbtn3,layoutBtn3, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 3) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option4, checkbtn4,layoutBtn4, cmsOption, getActivity(), externalReadable);
                    }
                    if (temp == 4) {
                        themeutil.getThemeMultipleOption(cmsQuestion, option5, checkbtn5,layoutBtn5, cmsOption, getActivity(), externalReadable);
                    }
                    temp++;
                }
            }

            if (cmsQuestion.getTheme() != null) {
                mainLayout.setBackgroundColor(Color.parseColor("#0097a7"));
                label_view.setBackgroundColor(Color.parseColor("#0097a7"));
               // setColorforCheckBox();
                selectUnselect(0);
            }

        }

        checkbtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        checkbtn5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateValues();
            }
        });
        webviewSetup();
        return view;
    }

    private void updateValues() {
        String result = "";
        boolean checkbox1 = ((CheckBox) view.findViewById(R.id.checkbtn1)).isChecked();
        if (checkbox1 == true) {
            result = result + "," + checkbtn1.getTag();
        }
        boolean checkbox2 = ((CheckBox) view.findViewById(R.id.checkbtn2)).isChecked();
        if (checkbox2 == true) {
            result = result + "," + checkbtn2.getTag();
        }
        boolean checkbox3 = ((CheckBox) view.findViewById(R.id.checkbtn3)).isChecked();
        if (checkbox3 == true) {
            result = result + "," + checkbtn3.getTag();
        }
        boolean checkbox4 = ((CheckBox) view.findViewById(R.id.checkbtn4)).isChecked();
        if (checkbox4 == true) {
            result = result + "," + checkbtn4.getTag();
        }
        boolean checkbox5 = ((CheckBox) view.findViewById(R.id.checkbtn5)).isChecked();
        if (checkbox5 == true) {
            result = result + "," + checkbtn5.getTag();
        }
        result = result.replaceFirst("^,", "");

        hidden_value.setText(result + "");
    }

    /*private void setColorforCheckBox() {
        try {
            String color = "#000000";
            try {
                color = cmsQuestion.getTheme().getListitemFontColor();
            } catch (Exception e) {
            }
            int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
            int[] colors = new int[]{Color.parseColor(color), Color.parseColor(color)};
            ColorStateList colorStateList = new ColorStateList(states, colors);

            CompoundButtonCompat.setButtonTintList(checkbtn1, colorStateList);
            CompoundButtonCompat.setButtonTintList(checkbtn2, colorStateList);
            CompoundButtonCompat.setButtonTintList(checkbtn3, colorStateList);
            CompoundButtonCompat.setButtonTintList(checkbtn4, colorStateList);
            CompoundButtonCompat.setButtonTintList(checkbtn5, colorStateList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void webviewSetup() {
        forceWebViewRedraw(question);
        forceWebViewRedraw(option1);
        forceWebViewRedraw(option2);
        forceWebViewRedraw(option3);
        forceWebViewRedraw(option4);
        forceWebViewRedraw(option5);
    }
    public void selectUnselect(int position) {
        switch (position) {
            case 1:
                selectedVal = checkbtn1.getTag().toString();
                chck_1 = true;
                layoutBtn1.setCardBackgroundColor(getResources().getColor(R.color.SelectedOption));


                break;
            case 2:
                selectedVal = checkbtn2.getTag().toString();
                chck_2 = true;

                layoutBtn2.setCardBackgroundColor(getResources().getColor(R.color.SelectedOption));

                break;
            case 3:
                selectedVal = checkbtn3.getTag().toString();
                chck_3 = true;

                layoutBtn3.setCardBackgroundColor(getResources().getColor(R.color.SelectedOption));

                break;
            case 4:
                selectedVal = checkbtn4.getTag().toString();
                chck_4 = true;

                layoutBtn4.setCardBackgroundColor(getResources().getColor(R.color.SelectedOption));

                break;
            case 5:
                selectedVal = checkbtn5.getTag().toString();
                chck_5 = true;

                layoutBtn5.setCardBackgroundColor(getResources().getColor(R.color.SelectedOption));

                break;
            default:
                selectedVal = "";
                layoutBtn1.setCardBackgroundColor(Color.parseColor(optionCardColor));
                layoutBtn1.setCardBackgroundColor(Color.parseColor(optionCardColor));
                layoutBtn2.setCardBackgroundColor(Color.parseColor(optionCardColor));
                layoutBtn3.setCardBackgroundColor(Color.parseColor(optionCardColor));
                layoutBtn4.setCardBackgroundColor(Color.parseColor(optionCardColor));
                layoutBtn5.setCardBackgroundColor(Color.parseColor(optionCardColor));
                break;
        }
        hidden_value.setText(selectedVal + "");
    }
    private void forceWebViewRedraw(final WebView mWebView) {

        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        mWebView.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;


            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (fingerState == FINGER_RELEASED) fingerState = FINGER_TOUCHED;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;

                            if (view.getId() == R.id.option1) {
                                if (chck_1) {
                                    selectUnselect(0);
                                    chck_1 = false;
                                } else {
                                    selectUnselect(1);
                                }                            }
                            if (view.getId() == R.id.option2) {
                                if (chck_1) {
                                    selectUnselect(0);
                                    chck_1 = false;
                                } else {
                                    selectUnselect(2);
                                }                            }
                            if (view.getId() == R.id.option3) {
                                if (chck_1) {
                                    selectUnselect(0);
                                    chck_1 = false;
                                } else {
                                    selectUnselect(3);
                                }                            }
                            if (view.getId() == R.id.option4) {
                                if (chck_1) {
                                    selectUnselect(0);
                                    chck_1 = false;
                                } else {
                                    selectUnselect(4);
                                }                            }
                            if (view.getId() == R.id.option5) {
                                if (chck_1) {
                                    selectUnselect(0);
                                    chck_1 = false;
                                } else {
                                    selectUnselect(5);
                                }                            }


                        } else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;
                    default:
                        fingerState = FINGER_UNDEFINED;
                        break;
                }
                return false;
            }
        });
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
