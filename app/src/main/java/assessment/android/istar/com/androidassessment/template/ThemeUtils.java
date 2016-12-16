package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.TextView;

import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

import static assessment.android.istar.com.androidassessment.R.id.options;

/**
 * Created by Sumanth on 12/16/2016.
 * <p>
 * <listitemFontColor>#fffef4</listitemFontColor>
 * <listitemFontFamily>Raleway-Regular.ttf</listitemFontFamily>
 * <listitemFontSize>49</listitemFontSize>
 * <listitemFontWeight>100</listitemFontWeight>
 * <listitemLineHeight>1</listitemLineHeight>
 */

public class ThemeUtils {


    public void getThemeQuestion(final CMSQuestion cmsQuestion, final WebView webView) {


        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: "+ cmsQuestion.getTheme().getListitemFontColor()
                +"; background-color:"+ cmsQuestion.getTheme().getBackgroundColor() +" ;}"
                +"; font-family:"+ cmsQuestion.getTheme().getListitemFontFamily() +" ;}"
                +"; font-size:"+ cmsQuestion.getTheme().getListitemFontSize() +" ;}"
                + "</style></head>"
                + "<body>"
                + cmsQuestion.getQuestionText()
                + "</body></html>";
        webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);

    }

    public void getThemeOptions(final CMSQuestion cmsOption,final WebView webView,final RadioButton radioButton,final String optionText) {


        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: "+ cmsOption.getTheme().getListitemFontColor()
                +"; background-color:"+ cmsOption.getTheme().getBackgroundColor() +" ;}"
                +"; font-family:"+ cmsOption.getTheme().getListitemFontFamily() +" ;}"
                +"; font-size:"+ cmsOption.getTheme().getListitemFontSize() +" ;}"
                + "</style></head>"
                + "<body>"
                + optionText
                + "</body></html>";
        webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
        radioButton.setTag(cmsOption.getId());
        webView.setVisibility(View.VISIBLE);
        radioButton.setVisibility(View.VISIBLE);

    }
}
