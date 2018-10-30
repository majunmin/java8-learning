package com.mjm;

import com.mjm.stream.entity.Dish;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

/**
 * @author majun
 * @date 2018/10/25 20:20
 * <p>
 * 流                从支持数据处理操作的源生成的元素序列
 * 元素序列
 * 源               从有序集合生成流时会保留原有的顺序。由列表生成的流，其元素顺序与列表一致
 * 数据处理操作       filter 、 map 、 reduce 、 find 、 match 、 sort etc. 流操作可以顺序执行，也可并行执行
 * 流水线          很多流操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大的流水线(延迟与短路)流水线的操作可以看作对数据源进行数据库式查询
 * 内部迭代         与使用迭代器显式迭代的集合不同，流的迭代操作是在背后进行的 (foreach 属于外部迭代)
 * <p>
 * 和迭代器类似，流只能遍历一次
 * <p>
 * 流的使用
 *
 * 获取流
 * Collection.stream()  Collection.parallelStream()
 * Arrays.stream(T[])
 * Stream.of(T... values)
 *
 *
 * 中间操作
 * 终端操作
 * <p>
 *   一个数据源（如集合）来执行一个查询；
 *   一个中间操作链，形成一条流的流水线；
 *   一个终端操作，执行流水线，并能生成结果。
 */
public class StreamTest {

    List<Dish> menu = asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void test(){

    }

    @Test
    public void test1() {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .limit(3)
                .map(Dish::getName)
                //.forEach(System.out::println);
                .collect(Collectors.toList());

        List<String> threeHighCaloricDishNames1 = menu.stream()
                .filter(dish -> {
                    System.out.println("filter : " + dish.getCalories());
                    return dish.getCalories() > 500;
                })
                .limit(3)
                .map(dish -> {
                    System.out.println("map" + dish.getName());
                    return dish.getName();
                })
                .collect(Collectors.toList());

        //流的扁平化

        String[] words = new String[]{"Hello", "World"};
        stream(words).map(str -> str.split(""))
                .forEach(System.out::println);

        stream(words)
                .map(str -> str.split(""))
                .flatMap(strings -> stream(strings))
                .distinct()
                .forEach(System.out::println);

        // 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
        asList(1, 2, 3, 4, 5).stream()
                .map(i -> i * i)
                .forEach(System.out::println);

        //给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
        //该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
        List<Integer> list1 = asList(1, 2, 3, 4);
        List<Integer> list2 = asList(3, 4);
        List<int[]> res = list1.stream()
                .flatMap(
                        i -> list2.stream().map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());
        System.out.println(res);

        //) 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的
        //1
        List<int[]> res2 = list1.stream()
                .flatMap(
                        i -> list2.stream().map(j -> new int[]{i, j})
                )
                .filter(arr -> (arr[0] + arr[1]) % 3 == 0)
                .collect(Collectors.toList());
        //2
        List<int[]> res3 = list1.stream()
                .flatMap(
                        i -> list2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());
    }

    /**
     * 查找元素 匹配元素
     * findAny
     *
     */
    @Test
    public void test2(){
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .filter(dish1 -> dish1.getCalories() > 1000)
                .findAny();
        if (dish.isPresent()){
            System.out.println(dish.get());
        }

        //查找第一个元素  1
        Optional<Integer> res = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(i -> i * i)
                .findFirst();
        System.out.println(res.orElse(0));
    }

    /**
     * 归约操作 reduce
     */
    @Test
    public void test3(){
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .filter(dish1 -> dish1.getCalories() > 1000)
                .findAny();

//        if (dish.isPresent()){
//            System.out.println(dish.get());
//        } 可以简化为 如下表达
        dish.ifPresent(System.out::println);

        //查找第一个元素  1
        Optional<Integer> res = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(i -> i * i)
                .findFirst();
        System.out.println(res.orElse(0));


        /**
         *  下面两种情况
         *  为什么resuce  是 Optional<Integer> ??
         *
         */
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer result = integers.stream().reduce(0, (x, y) -> x + y);
        System.out.println(result);

        Optional<Integer> reduce = menu.stream()
                .filter(Dish::isVegetarian)
                .filter(dish1 -> dish1.getCalories() > 1000)
                .map(Dish::getCalories)
                .reduce(Integer::sum);


    }

    /**
     * collect 江流转化为其他形式，。接受一个Colllector接口实现，用于给六种元素做汇总操作
     */
    public void testCollect(){
        Set<String> collect = menu.stream().map(Dish::getName)
                .collect(Collectors.toSet());

        List<Integer> collect1 = menu.stream().map(Dish::getCalories)
                .collect(Collectors.toList());

        /**
         * 放入特殊的集合中  Collectors.toCollection
         */
        HashSet<String> collect2 = menu.stream().map(Dish::getName)
                .collect(Collectors.toCollection(HashSet::new));

        //平均数  最大值  最小值 总和  总数
        Double collect3 = menu.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories));
        Optional<Dish> collect4 = menu.stream()
                .collect(Collectors
                        .maxBy(Comparator.comparingInt(Dish::getCalories)));
    }

    /**
     * 分组 groupingBy
     * 分区 partitioningBy
     */
    public void testGrouping(){
        Map<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType));

        /**
         * 先按 Type 分组  再按  卡路里 分区段分组
         */
        menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() > 2000){
                return "high";
            } else if (dish.getCalories() > 1000){
                return "middle";
            } else {
                return "low";
            }
        })));
    }

    /**
     * 统计
     */
    public void testSummarizing(){
        DoubleSummaryStatistics dss = menu.stream()
                .collect(Collectors.summarizingDouble(Dish::getCalories));
        System.out.println(dss.getMax());
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getMin());
        System.out.println(dss.getSum());
    }


}
