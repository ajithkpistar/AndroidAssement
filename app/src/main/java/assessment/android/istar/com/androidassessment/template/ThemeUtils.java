package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

import static assessment.android.istar.com.androidassessment.R.id.options;

/**
 * Created by Sumanth on 12/16/2016.
 */

public class ThemeUtils {


    public void getThemeQuestion(final CMSQuestion cmsQuestion, final WebView webView,Context context) {

if(cmsQuestion != null && cmsQuestion.getId() != null){

        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: " + cmsQuestion.getTheme().getListitemFontColor()
                + "; background-color:" + cmsQuestion.getTheme().getBackgroundColor() + " ;}"
                + "; font-family:" + cmsQuestion.getTheme().getListitemFontFamily() + " ;}"
                + "; font-size:" + cmsQuestion.getTheme().getListitemFontSize() + " ;}"
                + "</style></head>"
                + "<body>"
                + cmsQuestion.getQuestionText()
                + "</body></html>";
    //http://beta.talentify.in:8339/video/assessment_media/graph.png
   /* if(cmsQuestion.getQuestionText().contains("<img")){
       String url = context.getResources().getString(R.string.resources_fetch_ip) + url;

    }*/
        webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
}

    }

    public void getThemeSingleOption(final CMSQuestion cmsQuestion, final WebView webView, final RadioButton radioButton, final CMSOption cmsOption) {

if(cmsOption != null && cmsOption.getId() !=null){
        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: " + cmsQuestion.getTheme().getListitemFontColor()
                + "; background-color:" + cmsQuestion.getTheme().getBackgroundColor() + " ;}"
                + "; font-family:" + cmsQuestion.getTheme().getListitemFontFamily() + " ;}"
                + "; font-size:" + cmsQuestion.getTheme().getListitemFontSize() + " ;}"
                + "</style></head>"
                + "<body>"
                + cmsOption.getOptionText()
                + "</body></html>";
        webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
        radioButton.setTag(cmsOption.getId());
        webView.setVisibility(View.VISIBLE);
        radioButton.setVisibility(View.VISIBLE);

}

    }

    public void getThemeMultipleOption(final CMSQuestion cmsQuestion, final WebView webView, final CheckBox checkBox, final CMSOption cmsOption) {

        if(cmsOption != null && cmsOption.getId() !=null){
            String text = "<html><head>"
                    + "<style type=\"text/css\">body{color: " + cmsQuestion.getTheme().getListitemFontColor()
                    + "; background-color:" + cmsQuestion.getTheme().getBackgroundColor() + " ;}"
                    + "; font-family:" + cmsQuestion.getTheme().getListitemFontFamily() + " ;}"
                    + "; font-size:" + cmsQuestion.getTheme().getListitemFontSize() + " ;}"
                    + "</style></head>"
                    + "<body>"
                    + cmsOption.getOptionText()
                    + "</body></html>";
            webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
            checkBox.setTag(cmsOption.getId());
            webView.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);

        }

    }
}
