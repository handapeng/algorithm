package com.hdp.juc.threadactiveness;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author HDP
 * @ClassName: Chopstick
 * @Description:
 * @date 2022/11/16 14:34
 */
@Data
@AllArgsConstructor
public class Chopstick {
    int number;

    @Override
    public String toString() {
        return "筷子{" + number + "}";
    }
}
