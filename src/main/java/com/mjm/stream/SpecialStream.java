package com.mjm.stream;

import com.mjm.stream.entity.Dish;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * @author majun
 * @date 2018/10/26 11:13
 * <p>
 * 原始流 特化  数值流
 */
public class SpecialStream {

    static List<Dish> menu = asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {

        /**
         *  map return Stream<T>
         *  mapToInt 返回的不是 Stream<Integer> 而是  IntStream
         */
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        /**
         * 如何从 IntStream  -> Stream<Integer>
         *     .boxed()
         */
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        OptionalInt max = menu.stream().mapToInt(Dish::getCalories)
                .max();
        System.out.println(max.orElse(0));

        /**
         * 数值范围  生成勾股数
         * 条件
         * stream.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
         * .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
         * 类型推断
         */
        Stream<int[]> stream1 = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                )
                .limit(5);
        stream1.forEach(arr -> {System.out.println(arr[0] + "," + arr[1] + "," +arr[2]);});
    }
}
