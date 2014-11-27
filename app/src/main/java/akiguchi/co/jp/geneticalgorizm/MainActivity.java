package akiguchi.co.jp.geneticalgorizm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends Activity implements View.OnClickListener, Serializable {
    private static String TAG = MainActivity.class.getSimpleName();

    private static int GANE_SIZE = 100;
    private static int CROSS = 75;
    private static int MUTATION = 2;

    // 人数カウント用変数
    private int number_person = 0;
    // エリートである配列の要素数格納変数
    private GaneData elite;

    // ランダム整数の生成変数
    private Random rand = new Random(System.currentTimeMillis());

    private ArrayList<GaneData> ganeDatas = new ArrayList<GaneData>();
    private ArrayList<Person> persons = new ArrayList<Person>();

    private static final long serialVersionUID = 1L;

    EditText editName;
    Button nextperson;
    Button start;
    Button clear_all;
    RatingBar Param1;
    RatingBar Param2;
    RatingBar Param3;
    RatingBar Param4;
    RatingBar Param5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.name);
        nextperson = (Button) findViewById(R.id.nextperson);
        start = (Button) findViewById(R.id.start);
        clear_all = (Button) findViewById(R.id.clearall_button);
        Param1 = (RatingBar) findViewById(R.id.ratingBar1);
        Param2 = (RatingBar) findViewById(R.id.ratingBar2);
        Param3 = (RatingBar) findViewById(R.id.ratingBar3);
        Param4 = (RatingBar) findViewById(R.id.ratingBar4);
        Param5 = (RatingBar) findViewById(R.id.ratingBar5);

        nextperson.setOnClickListener(this);
        start.setOnClickListener(this);
        clear_all.setOnClickListener(this);
    }

    public void GeneticAlgorizm() {
        Generate();

        for (int i = 0; i < 10; i++) {
            CalcGap();

            // GaneDataを昇順で並び替え
            Collections.sort(ganeDatas, new ArrayListComparator());

            Selection();

            Cross();

            // エリートをランダムに代入
            int random = rand.nextInt(GANE_SIZE);
            ganeDatas.add(random, elite);
        }

        CalcGap();
        Collections.sort(ganeDatas, new ArrayListComparator());

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("number_person", number_person);
        intent.putExtra("Gane", ganeDatas.get(0).getGanes());
        intent.putExtra("Person", persons);
        startActivity(intent);
    }

    // 遺伝子の生成
    public void Generate() {
        // 重複しない配列を生成
        int[] random = new int[number_person];
        int randnum, randkey;

        for (int i = 0; i < GANE_SIZE; i++) {
            GaneData ganeData = new GaneData(number_person);
            for (int j = 0; j < number_person; j++) {
                ganeData.setGane(0, j);
                random[j] = j;
            }

            randnum = number_person;

            for (int j = 0; j < number_person; j++) {
                randkey = rand.nextInt(randnum);
                ganeData.setGane(random[randkey], j);
                random[randkey] = random[randnum - 1];
                randnum--;
            }
            ganeDatas.add(ganeData);
        }
    }

    // ランキング選択
    public void Selection() {
        int[] rank = new int[GANE_SIZE];
        int Ch1 = 0;
        int Ch2 = 0;

        for (int i = 0; i < GANE_SIZE; i++) {
            if (i == 0) {
                rank[i] = GANE_SIZE;
            } else {
                rank[i] = rank[i - 1] + GANE_SIZE - i;
            }
        }

        for (int i = 0; i < GANE_SIZE; i += 2) {
            for (int j = 0; j < 2; j++) {
                int Choose = rand.nextInt(rank[GANE_SIZE - 1]);

                for (int k = 0; k < GANE_SIZE; k++) {
                    if (Choose < rank[k]) {
                        if (j == 0) {
                            Ch1 = k;
                            break;
                        } else {
                            Ch2 = k;
                            break;
                        }
                    }
                }
            }
            for (int j = 0; j < number_person; j++) {
                ganeDatas.get(i).setNeogane(ganeDatas.get(Ch1).getGane(j), j);
                ganeDatas.get(i + 1).setNeogane(ganeDatas.get(Ch2).getGane(j), j);
            }
        }

        for (int i = 0; i < GANE_SIZE; i++) {
            for (int j = 0; j < number_person; j++) {
                ganeDatas.get(i).setGane(ganeDatas.get(i).getNeogane(j), j);
            }
        }
    }

    // 部分一致交差
    public void Cross() {
        int[] CH1 = new int[GANE_SIZE / 2];
        int[] CH2 = new int[GANE_SIZE / 2];
        int flag = 0;
        for (int i = 0; i < GANE_SIZE; i += 2) {
            if (rand.nextInt(GANE_SIZE) < CROSS) {
                int Ch1 = rand.nextInt(number_person);
                int Ch2 = rand.nextInt(number_person);
                for (int j = Ch1; j < Ch2; j++) {
                    CH1[j - Ch1] = ganeDatas.get(i).getGane(j);
                    CH2[j - Ch1] = ganeDatas.get(i + 1).getGane(j);
                    ganeDatas.get(i).setGane(CH2[j - Ch1], j);
                    ganeDatas.get(i + 1).setGane(CH1[j - Ch1], j);
                }
                for (int j = 0; j < Ch1; j++) {
                    while (true) {
                        flag = 0;
                        for (int k = Ch1; k < Ch2; k++) {
                            if (ganeDatas.get(i).getGane(j) == ganeDatas.get(i).getGane(k)) {
                                ganeDatas.get(i).setGane(ganeDatas.get(i + 1).getGane(k), j);
                                flag = 1;
                            }
                        }
                        if (flag == 0)
                            break;
                    }
                }
                for (int j = Ch2; j < number_person; j++) {
                    while (true) {
                        flag = 0;
                        for (int k = Ch1; k < Ch2; k++) {
                            if (ganeDatas.get(i).getGane(j) == ganeDatas.get(i).getGane(k)) {
                                ganeDatas.get(i).setGane(ganeDatas.get(i + 1).getGane(k), j);
                                flag = 1;
                            }
                        }
                        if (flag == 0)
                            break;
                    }
                }
                for (int j = 0; j < Ch1; j++) {
                    while (true) {
                        flag = 0;
                        for (int k = Ch1; k < Ch2; k++) {
                            if (ganeDatas.get(i + 1).getGane(j) == ganeDatas.get(i + 1).getGane(k)) {
                                ganeDatas.get(i + 1).setGane(ganeDatas.get(i).getGane(k), j);
                                flag = 1;
                            }
                        }
                        if (flag == 0)
                            break;
                    }
                }
                for (int j = Ch2; j < number_person; j++) {
                    while (true) {
                        flag = 0;
                        for (int k = Ch1; k < Ch2; k++) {
                            if (ganeDatas.get(i + 1).getGane(j) == ganeDatas.get(i + 1).getGane(k)) {
                                ganeDatas.get(i + 1).setGane(ganeDatas.get(i).getGane(k), j);
                                flag = 1;
                            }
                        }
                        if (flag == 0)
                            break;
                    }
                }
            }
        }

        // 突然変異
        for (int i = 0; i < GANE_SIZE; i++) {
            for (int j = 1; j < number_person; j++) {
                if (rand.nextInt(GANE_SIZE) < MUTATION) {
                    int k = rand.nextInt(number_person);
                    int Ch1 = ganeDatas.get(i).getGane(j);
                    int Ch2 = ganeDatas.get(i).getGane(k);
                    ganeDatas.get(i).setGane(Ch2, j);
                    ganeDatas.get(i).setGane(Ch1, k);
                }
            }
        }
    }

    // ギャップの計算
    public void CalcGap() {
        int elite_gap = 10000;

        for (int i = 0; i < GANE_SIZE; i++) {
            int teamTotal1 = 0;
            int teamTotal2 = 0;

            for (int j = 0; j < number_person; j++) {
                if (j < number_person / 2) {
                    teamTotal1 += persons.get(ganeDatas.get(i).getGane(j)).getParamTotal();
                } else {
                    teamTotal2 += persons.get(ganeDatas.get(i).getGane(j)).getParamTotal();
                }
            }
            ganeDatas.get(i).setGap(Math.abs(teamTotal1 - teamTotal2));

            // エリートの判定
            if (elite_gap > ganeDatas.get(i).getGap()) {
                elite = ganeDatas.get(i);
                elite_gap = ganeDatas.get(i).getGap();
            }
        }
    }

    // パラメータの入力
    public void ParamIN(String name, int param1, int param2, int param3, int param4, int param5) {
        Person person = new Person(name, param1, param2, param3, param4, param5);
        persons.add(person);
        number_person++;
    }

    private void Clear() {
        editName.setText("");
        Param1.setRating(0);
        Param2.setRating(0);
        Param3.setRating(0);
        Param4.setRating(0);
        Param5.setRating(0);
    }

    private void Reset() {
        number_person = 0;
        persons.clear();
        ganeDatas.clear();

        Toast.makeText(this, "これまでの入力データを消去しました", Toast.LENGTH_SHORT).show();
    }

    private void Preset_Param() {
        ParamIN("井上", 2, 5, 3, 3, 3);
        ParamIN("北", 4, 3, 6, 6, 6);
        ParamIN("小泉", 5, 6, 3, 4, 1);
        ParamIN("末広", 4, 3, 2, 2, 1);
        ParamIN("谷川", 3, 2, 4, 3, 2);
        ParamIN("坪内", 2, 6, 4, 3, 2);
        ParamIN("中脇", 6, 2, 2, 3, 6);
        ParamIN("南部", 6, 4, 3, 6, 4);
        ParamIN("古谷", 5, 5, 5, 5, 5);
        ParamIN("松井", 4, 5, 3, 3, 2);
        ParamIN("松浦", 2, 1, 1, 3, 2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextperson:
                if (editName.getText().toString().equals("") || editName.getText().toString().equals(" ")) {
                    Toast.makeText(this, "名前が入力されていません", Toast.LENGTH_LONG).show();
                } else if (Param1.getRating() == 0 || Param2.getRating() == 0 || Param3.getRating() == 0 || Param4.getRating() == 0 || Param5.getRating() == 0) {
                    Toast.makeText(this, "入力されていないパラメータがあります", Toast.LENGTH_LONG).show();
                } else {
                    ParamIN(editName.getText().toString(), (int) Param1.getRating(), (int) Param2.getRating(), (int) Param3.getRating(), (int) Param4.getRating(), (int) Param5.getRating());
                    Clear();
                }
                break;
            case R.id.start:
                if (number_person < 4) {
                    Toast.makeText(this, "最低でも4人のデータが必要です", Toast.LENGTH_LONG).show();
                } else {
                    GeneticAlgorizm();
                }
                break;
            case R.id.clearall_button:
                DialogFragment dialogFragment = new ClearAllDialogFragment();
                dialogFragment.show(getFragmentManager(), TAG);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Reset();
            Preset_Param();
            GeneticAlgorizm();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class ClearAllDialogFragment extends DialogFragment {
        private MainActivity mActivity;

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            // MyActivity 以外に所属する場合 Exception をスローし先に進ませない
            if (activity instanceof MainActivity == false) {
                throw new UnsupportedOperationException("MyActivity 以外からコールされている.");
            }
            mActivity = (MainActivity) activity;
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mActivity = null;  // Activity のリーク対策
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(mActivity)
                    .setMessage("これまでの入力データをすべて消去します")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mActivity.Reset();  // MyActivity 固有のメソッドをコールして処理を戻す
                        }
                    })
                    .setNegativeButton("キャンセル", null)  // リスナに null を入れると何もしない
                    .create();
        }
    }
}
