package com.baizhi;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.Test;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassNmae: HutoolTest
 * @Author: yddm
 * @DateTime: 2020/8/25 11:26
 * @Description: TODO
 */

public class HutoolTest {
    @Test
    public void test1() {
        //��ǰʱ��
        Date date = DateUtil.date();
        //��ǰʱ��
        Date date2 = DateUtil.date(Calendar.getInstance());
        //��ǰʱ��
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //��ǰʱ���ַ�������ʽ��yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //��ǰ�����ַ�������ʽ��yyyy-MM-dd
        String today = DateUtil.today();
        System.out.println("date = " + date);
        System.out.println("date2 = " + date2);
        System.out.println("date3 = " + date3);
        System.out.println("now = " + now);
        System.out.println("today = " + today);
    }

    @Test
    public void test2() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        System.out.println("date = " + date);
    }

    @Test
    public void test3() {
        String dateStr = "2020-08-25 00:00:01";
        Date date = DateUtil.parse(dateStr);

//һ��Ŀ�ʼ�������2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        System.out.println("beginOfDay = " + beginOfDay);

//һ��Ľ����������2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
        System.out.println("endOfDay = " + endOfDay);
    }

    @Test
    public void test4() {
        int i = DateUtil.ageOfNow("1998-03-14");
        System.out.println("i = " + i);
    }

    @Test
    public void test5() {
        String str = ClipboardUtil.getStr();
        System.out.println("clipboard = " + str);
    }

    @Test
    public void test6() {
        QrConfig config = new QrConfig(300, 300);
// ���ñ߾࣬�ȶ�ά��ͱ���֮��ı߾�
        config.setMargin(3);
// ����ǰ��ɫ���ȶ�ά����ɫ����ɫ��
        config.setForeColor(Color.PINK.getRGB());
// ���ñ���ɫ����ɫ��
        config.setBackColor(Color.GRAY.getRGB());

// ���ɶ�ά�뵽�ļ���Ҳ���Ե���
        QrCodeUtil.generate("Happy valentine's day", config, FileUtil.file("e:/qrcode.jpg"));
    }

    @Test
    public void test7() {
        String emoji = EmojiUtil.toUnicode(":smile:");
        System.out.println("emoji = " + emoji);
    }
}
