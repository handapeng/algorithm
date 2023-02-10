package com.hdp.algorithm.hw;

/**
 * @author HDP
 * @ClassName: fiveKeyboard
 * @Description:5键键盘的输出
 * 有一个特殊的5键键盘，上面有a，ctrl-c，ctrl-x，ctrl-v，ctrl-a五个键。a键在屏幕上输出一个字母a；
 * ctrl-c将当前选择的字母复制到剪贴板；ctrl-x将当前选择的字母复制到剪贴板，并清空选择的字母；ctrl-v将当前剪贴板里的字母输出到屏幕；ctrl-a选择当前屏幕上的所有字母。注意：
 * 1 剪贴板初始为空，新的内容被复制到剪贴板时会覆盖原来的内容
 * 2 当屏幕上没有字母时，ctrl-a无效
 * 3 当没有选择字母时，ctrl-c和ctrl-x无效
 * 4 当有字母被选择时，a和ctrl-v这两个有输出功能的键会先清空选择的字母，再进行输出
 * <p>
 * 给定一系列键盘输入，输出最终屏幕上字母的数量。
 * <p>
 * 输入描述:
 * 输入为一行，为简化解析，用数字1 2 3 4 5代表a，ctrl-c，ctrl-x，ctrl-v，ctrl-a五个键的输入，数字用空格分隔
 * 输出描述:
 * 输出一个数字，为最终屏幕上字母的数量
 * <p>
 * 示例1：
 * 输入
 * 1 1 1
 * 输出
 * 3
 * 说明
 * 连续键入3个a，故屏幕上字母的长度为3
 * <p>
 * 示例2：
 * 输入
 * 1 1 5 1 5 2 4 4
 * 输出
 * 2
 * 说明
 * 输入两个a后ctrl-a选择这两个a，再输入a时选择的两个a先被清空，所以此时屏幕只有一个a，后续的ctrl-a，ctrl-c选择并复制了这一个a，最后两个ctrl-v在屏幕上输出两个a，
 * 故屏幕上字母的长度为2（第一个ctrl-v清空了屏幕上的那个a）
 * @date 2023/2/9 18:02
 */

import java.util.Scanner;

public class FiveKeyboard {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String totalStr = in.nextLine();
            int count = 0;
            int copyCount = 0;
            int selCount = 0;
            boolean overOp = false;
            for (char op : totalStr.toCharArray()) {
                if (op == '1') {
                    //a
                    count = selCount > 0 ? 1 : count + 1;
                    selCount = 0;
                } else if (op == '2') {
                    //复制
                    copyCount = selCount;
                } else if (op == '3') {
                    //剪切
                    count -= selCount;
                    copyCount = selCount;
                    selCount = 0;
                } else if (op == '4') {
                    //粘贴
                    count -= selCount;
                    selCount = 0;
                    count += copyCount;
                } else if (op == '5') {
                    //全选
                    selCount = count;
                }
            }
            System.out.println(count);
        }
    }

}
