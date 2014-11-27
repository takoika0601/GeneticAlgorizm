package akiguchi.co.jp.geneticalgorizm;

import java.util.Comparator;

/**
 * Created by i09324 on 2014/10/29.
 */
public class ArrayListComparator implements Comparator<GaneData> {

    public int compare(GaneData a, GaneData b) {
        int gap_a = a.getGap();
        int gap_b = b.getGap();

        if (gap_a > gap_b) {
            return 1;
        } else if (gap_a == gap_b) {
            return 0;
        } else {
            return -1;
        }
    }
}
