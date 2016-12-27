package assessment.android.istar.com.androidassessment.template;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.andexert.library.RippleView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assessment.android.istar.com.androidassessment.R;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSOption;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSQuestion;

/**
 * Created by Sumanth on 12/16/2016.
 */

public class ThemeUtils {

    private String questionFontColor = "#ffffff";
    private String questionBackgroundColor = "#00000000";
    private int quetionFontSize = 22;

    private String fontFamily = "Raleway-Regular.ttf";

    private int optionFontSize = 18;
    private String optionBackgroundColor = "#00000000";
    private String optionFontColor = "#000000";

    public void getThemeQuestion(final CMSQuestion cmsQuestion, final TextView textView, final Context context, final Boolean externalReadable) {



        String text = cmsQuestion.getQuestionText();

        if (cmsQuestion.getQuestionText().contains("<img")) {
            int qId = cmsQuestion.getId();

            text = getImageUrl(text, qId, context, externalReadable, textView);
        }

        String finaltext = "<html><head>"
                + "<style type=\"text/css\">body{color: " + questionFontColor
                + "; background-color:" + questionBackgroundColor + " ;}"
                + "; font-family:" + fontFamily + " ;}"
                + "; font-size:" + quetionFontSize + " ;}"
                + "</style></head>"
                + "<body>"
                + text
                + "</body></html>";
        textView.setText(Html.fromHtml(text));

    }


    public void getThemePassage(final CMSQuestion cmsQuestion, final TextView textView, final Context context, final Boolean externalReadable) {

        String text = cmsQuestion.getComprehensive_passage();

        if (cmsQuestion.getComprehensive_passage().contains("<img")) {
            int qId = cmsQuestion.getId();

            text = getImageUrl(text, qId, context, externalReadable, textView);
        }

        String finaltext = "<html><head>"
                + "<style type=\"text/css\">body{color: " + questionFontColor
                + "; background-color:" + questionBackgroundColor + " ;}"
                + "; font-family:" + fontFamily + " ;}"
                + "; font-size:" + quetionFontSize + " ;}"
                + "</style></head>"
                + "<body>"
                + text
                + "</body></html>";

        textView.setText(Html.fromHtml(text));
        textView.setVisibility(View.VISIBLE);
    }


    public void getThemeSingleOption(final CMSQuestion cmsQuestion, final TextView textView, final RadioButton radioButton, final RippleView layout, final CMSOption cmsOption, final Context context, final Boolean externalReadable) {
        if (cmsOption != null && cmsOption.getId() != null && cmsOption.getOptionText() != null) {

            String text = cmsOption.getOptionText();
            if (cmsOption.getOptionText().contains("<img")) {
                int qId = cmsOption.getId();

                text = getImageUrl(text, qId, context, externalReadable, textView);
            }


            String finaltext = "<html><head>"
                    + "<style type=\"text/css\">body{color: " + optionFontColor
                    + "; background-color:" + optionBackgroundColor + " ;}"
                    + "; font-family:" + fontFamily + " ;}"
                    + "; font-size:" + optionFontSize + " ;}"
                    + "p{text-align:center;}"
                    + "</style></head>"
                    + "<body><center><b>"
                    + text
                    + "</b></center></body></html>";

            textView.setText(Html.fromHtml(text));
            radioButton.setTag(cmsOption.getId());
            textView.setVisibility(View.VISIBLE);
            radioButton.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }

    }

    public void getThemeMultipleOption(final CMSQuestion cmsQuestion, final TextView textView, final CheckBox checkBox, final RippleView layout, final CMSOption cmsOption, final Context context, final Boolean externalReadable) {

        if (cmsOption != null && cmsOption.getId() != null && cmsOption.getOptionText() != null) {


            String text = cmsOption.getOptionText();
            if (cmsOption.getOptionText().contains("<img")) {
                int qId = cmsOption.getId();

                text = getImageUrl(text, qId, context, externalReadable, textView);
            }

            String finaltext = "<html><head>"
                    + "<style type=\"text/css\">body{color: " + optionFontColor
                    + "; background-color:" + optionBackgroundColor + " ;}"
                    + "; font-family:" + fontFamily + " ;}"
                    + "; font-size:" + optionFontSize + " ;}"
                    + "</style></head>"
                    + "<body><center><b>"
                    + text
                    + "</b></center></body></html>";

            checkBox.setTag(cmsOption.getId());
            textView.setText(Html.fromHtml(text));
            textView.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);


        }

    }

    public String getImageUrl(final String text, final int qId, final Context context, final Boolean externalReadable, final TextView webView) {
        String url = "";
        String returingText = "";
        try {
            String pattern = "/video(.*?)\"";
            // Create a Pattern object  "/video(.*?)\"";
            Pattern r = Pattern.compile(pattern);

            // Now create matcher object.
            Matcher m = r.matcher(text);
            System.out.println(m.groupCount());

            while (m.find()) {

                url = m.group().replaceAll(" ", "").replaceAll("\"", "");

            }

            int index = url.lastIndexOf("/");
            String as_image_name = url.substring(index, url.length()).replace("/", "");
            ImageSaver imageSaver = new ImageSaver(context).
                    setParentDirectoryName("" + qId).
                    setFileName(as_image_name).
                    setExternal(externalReadable);
            Boolean file_exist = imageSaver.checkFile();
            if (file_exist) {
                //webView.getSettings().setAllowFileAccess(true);
                //webView.getSettings().setJavaScriptEnabled(true);
                returingText = text.replaceAll(url, "file://" + imageSaver.pathFile().getAbsolutePath().toString());


            } else {
                String finalurl = context.getResources().getString(R.string.resources_fetch_ip) + url;
                returingText = text.replaceAll(url, finalurl);
                new SaveImageAsync(imageSaver).execute(finalurl);
            }
        } catch (Exception e) {
        }

        return returingText;
    }
}
