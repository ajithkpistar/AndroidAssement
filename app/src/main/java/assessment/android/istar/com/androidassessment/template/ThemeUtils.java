package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

import static assessment.android.istar.com.androidassessment.R.id.options;

/**
 * Created by Sumanth on 12/16/2016.
 */

public class ThemeUtils {

    String FontColor = "#000000";
    String BackgroundColor = "#ffffff";
    String FontFamily = "Raleway-Regular.ttf";
    String FontSize = "14";

//#9e9e9e

    public void getThemeQuestion(final CMSQuestion cmsQuestion, final WebView webView, Context context, Boolean externalReadable) {

        if (cmsQuestion.getTheme() != null) {
            try {
                FontColor = cmsQuestion.getTheme().getListitemFontColor();
                BackgroundColor = cmsQuestion.getTheme().getBackgroundColor();
                FontFamily = cmsQuestion.getTheme().getListitemFontFamily();
                FontSize = cmsQuestion.getTheme().getListitemFontSize();
            } catch (Exception e) {

            }
        }
        String url ="";
        String text = cmsQuestion.getQuestionText();
        if (cmsQuestion.getQuestionText().contains("<img")) {

            String pattern = "/video(.*?)\"";
            // Create a Pattern object  "/video(.*?)\"";
            Pattern r = Pattern.compile(pattern);

            // Now create matcher object.
            Matcher m = r.matcher(cmsQuestion.getQuestionText());
            System.out.println(m.groupCount());
           // imageView.setVisibility(View.INVISIBLE);

            while (m.find()) {

                 url = m.group().replaceAll(" ", "").replaceAll("\"", "");

            }

            int index = url.lastIndexOf("/");
            String bg_image_name = url.substring(index, url.length()).replace("/", "");
            ImageSaver imageSaver = new ImageSaver(context).
                    setParentDirectoryName("TestImgFolderTalentify").
                    setFileName(bg_image_name).
                    setExternal(externalReadable);
            Boolean file_exist = imageSaver.checkFile();
            if (file_exist) {

                webView.getSettings().setAllowFileAccess(true);
                webView.getSettings().setJavaScriptEnabled(true);
                String base = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
                text = cmsQuestion.getQuestionText().replaceAll(url,"file://"+imageSaver.pathFile().getAbsolutePath().toString());
                System.out.println("------------------>"+imageSaver.pathFile().getAbsolutePath().toString());


            } else {
                String finalurl = "http://beta.talentify.in:8339"+url;
                 text = cmsQuestion.getQuestionText().replaceAll(url, finalurl);
                new SaveImageAsync(imageSaver).execute(finalurl);
            }



        }
        String finaltext = "<html><head>"
                + "<style type=\"text/css\">body{color: " + FontColor
                + "; background-color:" + BackgroundColor + " ;}"
                + "; font-family:" + FontFamily + " ;}"
                + "; font-size:" + FontSize + " ;}"
                + "</style></head>"
                + "<body>"
                + text
                + "</body></html>";
//<img src=\""+ imagePath + "\">
        webView.loadDataWithBaseURL(null, finaltext, "text/html", "utf-8", null);


    }

    public void getThemeSingleOption(final CMSQuestion cmsQuestion, final WebView webView, final RadioButton radioButton, final CMSOption cmsOption) {

        if (cmsQuestion.getTheme() != null) {
            try {
                FontColor = cmsQuestion.getTheme().getListitemFontColor();
                BackgroundColor = cmsQuestion.getTheme().getBackgroundColor();
                FontFamily = cmsQuestion.getTheme().getListitemFontFamily();
                FontSize = cmsQuestion.getTheme().getListitemFontSize();
            } catch (Exception e) {

            }
        }

        if (cmsOption != null && cmsOption.getId() != null) {
            String text = "<html><head>"
                    + "<style type=\"text/css\">body{color: " + FontColor
                    + "; background-color:" + BackgroundColor + " ;}"
                    + "; font-family:" + FontFamily + " ;}"
                    + "; font-size:" + FontSize + " ;}"
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

        if (cmsQuestion.getTheme() != null) {

            FontColor = cmsQuestion.getTheme().getListitemFontColor();
            BackgroundColor = cmsQuestion.getTheme().getBackgroundColor();
            FontFamily = cmsQuestion.getTheme().getListitemFontFamily();
            FontSize = cmsQuestion.getTheme().getListitemFontSize();
        }

        if (cmsOption != null && cmsOption.getId() != null) {
            String text = "<html><head>"
                    + "<style type=\"text/css\">body{color: " + FontColor
                    + "; background-color:" + BackgroundColor + " ;}"
                    + "; font-family:" + FontFamily + " ;}"
                    + "; font-size:" + FontSize + " ;}"
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
