package com.mjm.stream;

import com.mjm.stream.entity.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author majun
 * @date 2018/10/25 20:11
 */
public class Stream1 {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static void main(String[] args) {

        test2();
        /*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	 */
        List<String> strs = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        strs.stream().sorted().forEach(System.out::println);

        /**
         * 3. 终止操作
         * allMatch——检查是否匹配所有元素
         * 		anyMatch——检查是否至少匹配一个元素
         * 		noneMatch——检查是否没有匹配的元素
         * 		findFirst——返回第一个元素
         * 		findAny——返回当前流中的任意元素
         * 		count——返回流中元素的总个数
         * 		max——返回流中最大值
         * 		min——返回流中最小值
         */






    }

    /**
     * 筛选与切片
     * 		filter——接收 Lambda ， 从流中排除某些元素。
     * 		limit——截断流，使其元素不超过给定数量。
     * 		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * 		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    //内部迭代：迭代操作 Stream API 内部完成
    public static void test2(){
        //所有的中间操作不会做任何的处理
        Stream<Dish> stream = menu.stream()
                .filter((e) -> {
                    System.out.println("测试中间操作");
                    return e.getCalories() <= 35;
                });

        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out::println);

        test3();
    }


    /**
     * 终止操作
     *
     * 映射
     * 		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * 		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    public static void test3(){
        List<String> strs = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        strs.stream().map(String::toUpperCase).forEach(System.out::print);

        menu.stream().map(Dish::getName).forEach(System.out::print);

        Stream<Stream<Character>> streamStream = strs.stream()
                .map(Stream1::filterCharacter);

        /**
         * 流中存在流
         */
        streamStream.forEach(sm -> sm.forEach(System.out::print));

        //------------------------------- flatMap
        Stream<Character> characterStream = strs.stream().flatMap(Stream1::filterCharacter);
        characterStream.forEach(System.out::println);


    }

    public static Stream<Character> filterCharacter(String str){
        /**
         * Incompatible types.
         * Required:Stream<java.lang.Character>
         * Found:Stream<char[]>
         */
//        char[] chars = str.toCharArray();
//        return Arrays.asList(chars).stream();

        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }



}
