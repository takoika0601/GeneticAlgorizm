package akiguchi.co.jp.geneticalgorizm;

/**
 * Created by i09324 on 2014/10/28.
 */
public class GaneData {

    // 遺伝子格納変数
    int[] gane;
    int[] neogane;
    //各遺伝子の和を比較するための変数
    int gap;

    public GaneData(int number_person) {
        gane = new int[number_person];
        neogane = new int[number_person];
    }

    public int getGane(int i) {
        return gane[i];
    }

    public void setGane(int gane, int i) {
        this.gane[i] = gane;
    }

    public int getNeogane(int i) {
        return neogane[i];
    }

    public void setNeogane(int neogane, int i) {
        this.neogane[i] = neogane;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int[] getGanes() {
        return gane;
    }
}
