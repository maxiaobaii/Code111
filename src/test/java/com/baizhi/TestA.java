package com.baizhi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNmae: TestA @Author: yddm @DateTime: 2020/9/10 23:04 @Description: TODO
 */
public class TestA {
    @Test
    public void test() {
        short i = (short) (3 * 0.3);
        short j = (short) 0.3;
        if (i == j) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    @Test
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 0; j < res.get(i - 1).size() - 1; j++) {
                row.add(res.get(i - 1).get(j) + res.get(i - 1).get(j + 1));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }

    @Test
    public void test1() {
        List<List<Integer>> generate = generate(5);
        System.out.println("generate = " + generate);
    }
}
