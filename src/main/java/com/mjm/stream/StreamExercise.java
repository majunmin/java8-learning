package com.mjm.stream;

import com.mjm.stream.entity.Trader;
import com.mjm.stream.entity.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author majun
 * @date 2018/10/26 10:36
 * <p>
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 */
public class StreamExercise {

    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {

        List<Transaction> transactionList1 = findTranscationAndSort();
        System.out.println(transactionList1);

        List<String> countryList = findDisCountry();
        System.out.println(countryList);

        List<Trader> traderList = findTradeFromJQ();
        System.out.println(traderList);

        List<String> allName = findName();
        System.out.println(allName);

        System.out.println(hasMiLan());

        System.out.println(findValueSum());

        System.out.println(findMaxValue());

        System.out.println(findMinValue());




    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     *
     * @return
     */
    public static List<Transaction> findTranscationAndSort() {
        List<Transaction> transactionList = transactions.stream()
                .filter(transaction -> transaction.getYear() >= 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        return transactionList;
    }

    /**
     * 交易员都在哪些不同的城市工作过？
     * @return
     */
    private static List<String> findDisCountry() {
        List<String> countryList = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct().collect(Collectors.toList());
        return countryList;
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序。
     */
    private static List<Trader> findTradeFromJQ() {
        List<Trader> traderList = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        return traderList;
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序。
     */
    private static List<String> findName() {
        List<String> nameList = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        return nameList;
    }

    /**
     * 有没有交易员是在米兰工作的？
     * @return
     */
    private static boolean hasMiLan(){
        List<Trader> traders = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .collect(toList());
        return traders.size() > 0;
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额。
     */
    public static int findValueSum(){
        Integer valueSum = transactions.stream()
                .filter(transaction -> {
                    Trader trader = transaction.getTrader();
                    return trader.getCity().equals("Cambridge");
                })
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        return valueSum;
    }

    /**
     * 所有交易中，最高的交易额是多少？
     */
    public static int findMaxValue(){
        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue)
                .reduce(Integer::max);
        return reduce.orElse(0);
    }

    /**
     * 找到交易额最小的交易。
     */
    public static int findMinValue(){
        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue)
                .reduce(Integer::min);
        return reduce.orElse(0);
    }




}
