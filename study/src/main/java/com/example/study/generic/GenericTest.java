package com.example.study.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GenericTest {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("string");
        list.add(2);
        list.add(true);

//        consumeAnyElement(list);

        List<Fruit> fruits = new ArrayList<>() {{add(new Fruit());} {add(new Apple());}};
        List<Apple> apples = new ArrayList<>() {{add(new Apple());}};
//        fruits = apples;
        List<?> anyFruits= new ArrayList<>();
        List<? extends Fruit> extFruits= new ArrayList<>();
        List<? super Fruit> supFruits= new ArrayList<>();

        fruits.stream().forEach(GenericTest::consumeFruit);
//        apples.stream().forEach(GenericTest::consumeApple);
//        apples.stream().forEach(GenericTest::consumeFruit);

        Stream<Fruit> fruitSteam = Stream.generate(GenericTest::produceFruit);
        Stream<Fruit> appleSteam = Stream.generate(GenericTest::produceApple);
//        List<Apple> appleFruits = appleSteam.collect(Collectors.toList());
//        appleFruits.add(new Orange());

//        //普通泛型
//        fruits.add(new Apple());//可以赋值
//        fruits = apples;//无法泛型协变
//        consumeFruitElement(fruits);//可以充当指定泛型的消费者
//        consumeFruitElement(apples);//无法充当指定子类泛型的消费者
//
//        //无限通配符
//        anyFruits.addAll(apples);//无法赋值
//        anyFruits = apples;//可以泛型协变
//        consumeAnyElement(fruits);
//        consumeAnyElement(apples);//可以充当消费者，但是只能以Object类型使用，没有意义
//        List<?> anyList = produceAnyFruit();//可以充当生产者
//        Object obj = anyList.get(0);//但是消费者只能以Object类型使用，没有意义
//        Fruit anyFruit = anyList.get(0);//无法获取为具体类型进行消费
//
//        //上界通配符
//        extFruits.addAll(apples);//无法赋值
//        extFruits = apples;//可以泛型协变
//        consumeExtElement(fruits);
//        consumeExtElement(apples);//可以充当消费者
//        List<? extends Fruit> extList = produceExtFruit();//可以充当生产者
//        Fruit extFruit = extList.get(0);//消费者可以获取具体类型进行消费
//
//        //下界通配符
//        supFruits.addAll(apples);//可以赋值
//        supFruits = apples;//无法泛型协变
//        consumeSupElement(apples);//无法充当消费者
//        List<? super Fruit> supList = produceSupFruit();//可以充当生产者
//        Fruit supFruit = supList.get(0);//消费者可以获取具体类型进行消费


        CustomeGeneric<Fruit> genericFruit = new CustomeGeneric<>(new Fruit());
        CustomeGeneric<Apple> genericApple = new CustomeGeneric<>(new Apple());

//        genericFruit = genericApple;

        CustomeGeneric<Fruit> cusFruit = CustomeGeneric.produce(GenericTest::produceFruit);
//        cusFruit.consume(GenericTest::consumeFruit);
        CustomeGeneric<Apple> cusApple = CustomeGeneric.produce(GenericTest::produceApple);

        genericFruit = CustomeGeneric.produce(GenericTest::produceApple);
//        genericFruit.consume(GenericTest::consumeFruit);
//        cusApple.consume(GenericTest::consumeFruit);
//        cusApple.consume(GenericTest::consumeApple);
        Supplier<Fruit> sf = new Supplier<Fruit>() {
            @Override
            public Fruit get() {
                return produceFruit();
            }
        };

        Supplier<Apple> sa = new Supplier<Apple>() {
            @Override
            public Apple get() {
                return produceApple();
            }
        };
//        CustomeGeneric.testPE2(GenericTest::produceFruit);
        CustomeGeneric.testPE(() -> new Apple());
//        CustomeGeneric.testPE(sa);
//        CustomeGeneric.testPE1(sa);

//        CustomeGeneric.testListPE(new ArrayList<Apple>());
//        CustomeGeneric.testListPE1(new ArrayList<Apple>());
//        sa = sf;

        CustomeGeneric.produce1(sa);


    }

    private static int produceInt() {
        return 1;
    }

    private static Fruit produceFruit() {
        //可以充当指定泛型的生产者
        return new Fruit();
    }

    private static Apple produceApple() {
        //无法充当子类泛型的生产者
        return new Apple();
    }

    private static List<Apple> produceAppleList() {
        //无法充当子类泛型的生产者
        return new ArrayList<Apple>();
    }
    private static List<?> produceAnyFruit() {
        return new ArrayList<Apple>();
    }

    private static List<? extends Fruit> produceExtFruit() {
        return new ArrayList<Apple>();
    }

//    private static List<? super Fruit> produceSupFruit() {
//        return new ArrayList<Apple>();
//    }


    public static void consumeFruit(Fruit fruit) {
        System.out.println(fruit.name);
    }

    public static void consumeApple(Apple apple) {
        System.out.println(apple.name);
    }

    public static void consumeFruitElement(List<Fruit> list) {
        list.stream().forEach(e -> {Fruit fruit = e;});
    }

    public static void consumeAnyElement(List<?> list) {
        //因为不知道list中元素具体类型，这里只能作为一个Object类型拿出来
        //一般来说对于一个消费者没有意义，消费者需要知道的是对象中携带的具体信息
        list.stream().forEach(e -> {Object obj = e;});

    }

    public static void consumeExtElement(List<? extends Fruit> list) {
        list.stream().forEach(e -> {Fruit fruit = e;});

    }

//    public static void consumeSupElement(List<? super Fruit> list) {
//        list.stream().forEach(e -> {Fruit fruit = e;});
//
//    }
}
