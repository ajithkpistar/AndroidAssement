package assessment.android.istar.com.androidassessment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MyActivity extends Activity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    private int delay = 10000;
    private CountDownTimer countDownTimer;
    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);


        al = new ArrayList<>();
        al.add("php");

        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");
        al.add("javascript111111111111111 1111111111111 1111111111111111111 1111111111111111 1111111111111111111 11111111111111 1111111111111111 1    1111111111111111111111111 111111111111");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );


        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                try {
                    al.remove(0);
                    arrayAdapter.notifyDataSetChanged();
                }catch (Exception e){

                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(MyActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(MyActivity.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(MyActivity.this, "Clicked!");
            }
        });



        countDownTimer = new CountDownTimer(delay, delay / 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                //

            }

            @Override
            public void onFinish() {
                System.out.println("delay was. ...... ." + delay);
                flingContainer.getTopCardListener().selectRight();
                countDownTimer.start();

            }
        };
//        countDownTimer.start();

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }




}