package assessment.android.istar.com.androidassessment.assessment_util;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import assessment.android.istar.com.androidassessment.EndAssessmentFragment;
import assessment.android.istar.com.androidassessment.assessment_pojo.CMSAssessment;
import assessment.android.istar.com.androidassessment.template.AssessmentCard;
import assessment.android.istar.com.androidassessment.template.AssessmentFactory;

/**
 * Created by Feroz on 14-12-2016.
 */

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    private CMSAssessment cmsAssessment;

    public ViewpagerAdapter(android.support.v4.app.FragmentManager fm,CMSAssessment cmsAssessment) {
        super(fm);
        this.cmsAssessment = cmsAssessment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        final Bundle bundle = new Bundle();
        if(position !=cmsAssessment.getQuestions().size()) {
            bundle.putInt(AssessmentCard.POSITION,position);
            bundle.putSerializable(AssessmentCard.CMSASSESSMENT,cmsAssessment.getQuestions().get(position));
            fragment = AssessmentFactory.getCard(cmsAssessment.getQuestions().get(position).getTemplate());
        }else
        fragment = new EndAssessmentFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return cmsAssessment.getQuestions().size()+1;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    @Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View)o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }


    /*@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub


    }*/
}