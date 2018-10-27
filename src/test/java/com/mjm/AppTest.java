package com.mjm;

import static org.junit.Assert.assertTrue;

import com.mjm.apple.filter.Apple;
import org.junit.Test;

import java.util.*;
import java.util.function.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    List<Apple> appleList = Arrays.asList(
            new Apple("green", 9.56),
            new Apple("red", 8.99),
            new Apple("green", 4.67),
            new Apple("yellow", 9.56),
            new Apple("red", 6.53),
            new Apple("green", 4.37)

    );

    @Test
    public void TreeMapTest() {
        Map<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(21,67);
        treeMap.put(1,3);
        treeMap.put(10,15);
        treeMap.put(5,8);

        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " " +entry.getValue());
        }
    }

    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);

        //Lambda表达式
        TreeSet<Integer> treeSet1 = new TreeSet<>((o1, o2) -> Integer.compare(o1, o2));

        //方法引用
        TreeSet<Integer> treeSet2 = new TreeSet<>(Integer::compare);
    }

    @Test
    public void test2() {
        List<Apple> apples = filterWeight(filterRed(appleList));
        for (Apple apple : apples) {
            System.out.println(apple);
        }

        //lambda表达式
        System.out.println("lambda: ");
        List<Apple> applesl = filterApple(appleList, apple -> apple.getColor().equals("red"));
        for (Apple apple : apples) {
            System.out.println(apple);
        }

        //方法引用

        //流的方式
        System.out.println("stream: ");
        appleList.stream().filter(apple -> apple.getWeight() > 5)
                .limit(2)
                //.map(apple -> apple.getWeight())
                .map(Apple::getWeight)
                .forEach(System.out::println);

    }

    public List<Apple> filterRed(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if ("red".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public List<Apple> filterWeight(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getWeight() != null && apple.getWeight() > 5) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 应用策略模式 将以上两种方法 当成策略 合并为以下一个方法
     * 将行为传递进去
     *
     * @param apples
     * @param predicate
     * @return
     */
    public List<Apple> filterApple(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test4() {
        /**
         * 类型推断
         * String[] strs = {"aaa","bbb","ccc"};
         *
         * HashMap<String.Integer> hmap = new HashMap<>();
         */
//        String[] strs ;
//        strs = {"aaa","bbb","ccc"};

    }

    /**
     * 类型推断
     */
    @Test
    public void test5() {
        Comparator<Integer> comparator = Integer::compare;

        /**
         * 下面这段代码 编译不过
         * Variable used in lambda expression should be final or effectively final
         *
         * lambda 内部相当于 匿名函数
         * 内部医用局部变量 需要局部变量是 final的
         */
//        int portNum = 8080;
//        Runnable r = ()-> System.out.print(portNum);
//        portNum = 5672;

    }

    /**
     * 方法引用
     */
    @Test
    public void test6() {
        //1
        appleList.sort((apple1, apple2) -> Double.compare(apple1.getWeight(), apple2.getWeight()));

        appleList.sort(Comparator.comparingDouble(Apple::getWeight));


        //2
        Function<String, Integer> function = (str) -> Integer.parseInt(str);
        Function<String, Integer> function1 = Integer::parseInt;

        //3
        BiPredicate<List<String>, String> predicate1 = (list, string) -> list.contains(string);
        BiPredicate<List<String>, String> predicate2 = List::contains;

        //4 构造函数方法引用 apple = appleS.get() =apple1S.get()
        // 构造器的参数列表 与 接口的抽象方法 参数列表一致  无参
        Supplier<Apple> appleS = () -> new Apple();
        Supplier<Apple> apple1S = Apple::new;
        Apple apple = apple1S.get();

        //4.2
        // 构造器的参数列表 与 接口的抽象方法 参数列表一致  无参
        Function<String, Apple> appleFunction = aColor -> new Apple(aColor);
        Function<String, Apple> appleFunction1 = Apple::new;

        //4.3  -- 行为参数化
        List<String> weights = Arrays.asList("green", "yellow", "red");
        List<Apple> apples = map(weights, Apple::new);

        //4.4
        BiFunction<String, Double, Apple> function2 = (c, w) -> new Apple(c, w);
        BiFunction<String, Double, Apple> function3 = Apple::new;


        //5
        /**
         * 1.这里一定要知道 类名::实例方法名  这种语法的使用条件：
         *   说明：a，b是所有方法引用的必要条件，c是使用 类名::实例方法名的特殊前提
         *
         *     a.方法引用所引用的方法的参数列表必须要和函数式接口中抽象方法的参数列表相同（完全一致）
         *
         *     b.方法引用所引用的方法的的返回值必须要和函数式接口中抽象方法的返回值相同（完全一致）
         *
         *     c.若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： 类名::实例方法名
         *
         * 以下代码说明:: equals方法的调用者是x，equals方法参数是y，这符合我们上面的c条件；所以能使用
         */
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        BiPredicate<String,String> bp1 = String::equals;


        //6 数组引用
        Function<Integer,String[]> fun = x->new String[x];
        Function<Integer,String[]> fun1 = String[]::new;
        String[] strArr = fun.apply(10);
        System.out.println(strArr.length);

    }

    private List<Apple> map(List<String> list,
                            Function<String, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (String color : list) {
            result.add(f.apply(color));
        }
        return result;
    }


    /**
     * lambda表达式 复合
     */
    @Test
    public void test7(){

        /**
         * 比较器复合
         */


        /**
         * 谓词复合
         */
        Predicate<Apple> redApple = (apple) -> "red".equals(apple.getColor());
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(apple->apple.getWeight() > 5);


        Function<Integer,Integer> f = x -> x+1;
        Function<Integer,Integer> g = x -> x*2;
        Function<Integer, Integer> gf = f.andThen(g);
        Function<Integer, Integer> fg = f.compose(g);
        System.out.println(gf.apply(1)); //4
        System.out.println(fg.apply(1)); //3
    }
}
