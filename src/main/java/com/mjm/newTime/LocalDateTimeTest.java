package com.mjm.newTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author majun
 * @date 2018/10/31 8:13
 *
 * 新的时间与日期API 不可变， 是线程安全的
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {

//        testPeriod();
//
//        testAdjust();
        
        testDateTimeFormatter();

//        testZone();
    }

    private static void testZone() {

//        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//        availableZoneIds.forEach(System.out::println);
//        availableZoneIds.stream().forEach(System.out::println);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Australia/Darwin")); //时间以 此时区的时间显示
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Australia/Darwin"));  //zdt 有时区后缀

        System.out.println(ldt);
        System.out.println(zdt);
    }

    private static void testDateTimeFormatter() {

        LocalDateTime ldt = LocalDateTime.now();

        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String res = dateTimeFormatter.format(ldt);
        System.out.println(res);

        System.out.println(LocalDateTime.parse(res, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

    }


    /**
     * TemporalAdjuster 时间矫正器
     */
    private static void testAdjust() {

        LocalDateTime ldt = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(ldt);

        /**
         * 下一个工作日
         */
        LocalDateTime date = LocalDateTime.now().with(ta -> {
            LocalDateTime ldt1 = (LocalDateTime) ta;
            DayOfWeek dayOfWeek = ldt.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt1.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt1.plusDays(2);
            } else {
                return ldt1.plusDays(1);
            }
        });
        System.out.println(date.toLocalDate());

    }

    public static void test(){
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

    public static void testPeriod(){
        LocalDate ld1 = LocalDate.of(2018,9,21);
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1, ld2);
        System.out.println(period.getDays());
    }
}
