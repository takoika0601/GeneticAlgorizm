package akiguchi.co.jp.geneticalgorizm;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class ResultActivity extends Activity {
    private int number_person;
    private int[] gane;
    private ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        View TeamA_Param1 = findViewById(R.id.para_a1);
        View TeamA_Param2 = findViewById(R.id.para_a2);
        View TeamA_Param3 = findViewById(R.id.para_a3);
        View TeamA_Param4 = findViewById(R.id.para_a4);
        View TeamA_Param5 = findViewById(R.id.para_a5);
        View TeamB_Param1 = findViewById(R.id.para_b1);
        View TeamB_Param2 = findViewById(R.id.para_b2);
        View TeamB_Param3 = findViewById(R.id.para_b3);
        View TeamB_Param4 = findViewById(R.id.para_b4);
        View TeamB_Param5 = findViewById(R.id.para_b5);
        View TeamA_Total = findViewById(R.id.para_a6);
        View TeamB_Total = findViewById(R.id.para_b6);

        number_person = getIntent().getIntExtra("number_person", 0);
        gane = getIntent().getIntArrayExtra("Gane");
        persons = (ArrayList<Person>) getIntent().getSerializableExtra("Person");

        int TeamA_Param1_Total = 0;
        int TeamA_Param2_Total = 0;
        int TeamA_Param3_Total = 0;
        int TeamA_Param4_Total = 0;
        int TeamA_Param5_Total = 0;
        int TeamB_Param1_Total = 0;
        int TeamB_Param2_Total = 0;
        int TeamB_Param3_Total = 0;
        int TeamB_Param4_Total = 0;
        int TeamB_Param5_Total = 0;
        int TeamA;
        int TeamB;

        for (int i = 0; i < number_person; i++) {
            if (i < number_person / 2) {
                TeamA_Param1_Total += persons.get(gane[i]).getParam1();
                TeamA_Param2_Total += persons.get(gane[i]).getParam2();
                TeamA_Param3_Total += persons.get(gane[i]).getParam3();
                TeamA_Param4_Total += persons.get(gane[i]).getParam4();
                TeamA_Param5_Total += persons.get(gane[i]).getParam5();
            } else {
                TeamB_Param1_Total += persons.get(gane[i]).getParam1();
                TeamB_Param2_Total += persons.get(gane[i]).getParam2();
                TeamB_Param3_Total += persons.get(gane[i]).getParam3();
                TeamB_Param4_Total += persons.get(gane[i]).getParam4();
                TeamB_Param5_Total += persons.get(gane[i]).getParam5();
            }
            Log.d(persons.get(gane[i]).getName() + "", "TAG");
        }
        TeamA = TeamA_Param1_Total + TeamA_Param2_Total + TeamA_Param3_Total + TeamA_Param4_Total + TeamA_Param5_Total;
        TeamB = TeamB_Param1_Total + TeamB_Param2_Total + TeamB_Param3_Total + TeamB_Param4_Total + TeamB_Param5_Total;

        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams.weight = TeamB_Param1_Total;
        TeamA_Param1.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams1.weight = TeamA_Param1_Total;
        TeamB_Param1.setLayoutParams(layoutParams1);

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams2.weight = TeamB_Param2_Total;
        TeamA_Param2.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams3.weight = TeamA_Param2_Total;
        TeamB_Param2.setLayoutParams(layoutParams3);

        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams4.weight = TeamB_Param3_Total;
        TeamA_Param3.setLayoutParams(layoutParams4);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams5.weight = TeamA_Param3_Total;
        TeamB_Param3.setLayoutParams(layoutParams5);

        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams6.weight = TeamB_Param4_Total;
        TeamA_Param4.setLayoutParams(layoutParams6);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams7.weight = TeamA_Param4_Total;
        TeamB_Param4.setLayoutParams(layoutParams7);

        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams8.weight = TeamB_Param5_Total;
        TeamA_Param5.setLayoutParams(layoutParams8);
        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (25 * metrics.scaledDensity));
        layoutParams9.weight = TeamA_Param5_Total;
        TeamB_Param5.setLayoutParams(layoutParams9);

        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (30 * metrics.scaledDensity));
        layoutParams10.weight = TeamB;
        TeamA_Total.setLayoutParams(layoutParams10);
        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (30 * metrics.scaledDensity));
        layoutParams11.weight = TeamA;
        TeamB_Total.setLayoutParams(layoutParams11);
    }

    public void PopupA(View view) {
        int popup_count = 0;

        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.team_a));

        for (int i = 0; i < number_person / 2; i++) {
            popupMenu.getMenu().add(1, popup_count, popup_count, persons.get(gane[i]).getName());
            popup_count++;
        }
        popupMenu.show();
    }

    public void PopupB(View view) {
        int popup_count = 0;

        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.team_b));

        for (int i = number_person / 2; i < number_person; i++) {
            popupMenu.getMenu().add(2, popup_count, popup_count, persons.get(gane[i]).getName());
            popup_count++;
        }
        popupMenu.show();
    }
}
