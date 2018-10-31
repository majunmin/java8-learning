package com.mjm.newTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * @author majun
 * @date 2018/10/31 8:13
 *
 * 新的时间与日期API 不可变， 是线程安全的
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2018,12,31,15,15,15);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt2.plus(2, ChronoUnit.YEARS);
        System.out.println(ldt3);

        /**
         * 时间戳
         */
        Instant instant = Instant.now(); //获取的是 UTC 时区，与+0:00
        System.out.println(instant); //2018-10-31T00:26:18.758Z
        OffsetDateTime odt = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt); //2018-10-31T08:26:18.758+08:00

        Instant instant1 = Instant.ofEpochMilli(1000);
        System.out.println(instant1); //1970-01-01T00:00:01Z
    }
}
