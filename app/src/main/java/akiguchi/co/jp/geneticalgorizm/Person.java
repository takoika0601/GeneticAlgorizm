package akiguchi.co.jp.geneticalgorizm;

import java.io.Serializable;

/**
 * Created by i09324 on 2014/10/28.
 */
public class Person implements Serializable {

    // 名前の格納変数
    String name;
    // 各パラメータの合計値
    int ParamTotal;
    int param1, param2, param3, param4, param5;

    public Person(String name, int param1, int param2, int param3, int param5, int param4) {
        this.name = name;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param5 = param5;
        this.param4 = param4;
        ParamTotal = param1 + param2 + param3 + param4 + param5;
    }

    public String getName() {
        return name;
    }

    public int getParam1() {
        return param1;
    }

    public int getParam2() {
        return param2;
    }

    public int getParam3() {
        return param3;
    }

    public int getParam4() {
        return param4;
    }

    public int getParam5() {
        return param5;
    }

    public int getParamTotal() {
        return ParamTotal;
    }
}
