#Lambda 可以将函数接口(只有一个抽象方法的接口)的实现类-(替换匿名函数) 简写

 一、Lambda 表达式的基础语法: Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 						    箭头操作符将 Lambda 表达式拆分成两部分:
 
 左侧: Lambda 表达式的参数列表
 右侧: Lambda 表达式中所需执行的功能， 即 Lambda 体
 
 语法格式一: 无参数，无返回值
 		() -> System.out.println("Hello Lambda!");
 
 语法格式二: 有一个参数，并且无返回值
 		(x) -> System.out.println(x)
 
 语法格式三: 若只有一个参数，小括号可以省略不写
 		x -> System.out.println(x)
 
 语法格式四: 有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
		Comparator<Integer> com = (x, y) -> {
			System.out.println("函数式接口");
			return Integer.compare(x, y);
		};

 语法格式五: 若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 
 语法格式六: Lambda 表达式的参数列表的数据类型可以省略不写
           因为JVM编译器通过上下文推断出数据类型，即**"类型推断"**
 		   (Integer x, Integer y) -> Integer.compare(x, y);
 
 上联: 左右遇一括号省
 下联: 左侧推断类型省
 横批: 能省则省
 
 二、Lambda 表达式需要"函数式接口(@FunctionalInterface)"的支持
 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解 @FunctionalInterface 修饰
 			 可以检查是否是函数式接口




### Java8 内置的四大核心函数式接口

    Consumer<T> : 消费型接口
    void accept(T t);
    
    Supplier<T> : 供给型接口
    T get(); 
    
    Function<T, R> : 函数型接口
    R apply(T t);
    
    Predicate<T> : 断言型接口
    boolean test(T t);
    

### 方法引用    
    一、方法引用: 若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
    			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
    
    1. 对象的引用 :: 实例方法名
    
    2. 类名 :: 静态方法名
    
    3. 类名  :: 实例方法名
    
    注意：
    	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
    	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
    
    二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
    
    1. 类名 :: new
    
    三、数组引用
    
    	类型[] :: new;
    

