package com.example.study.serialize;

import java.io.*;

/**
 * 测试序列化和反序列化以及 serialVersionUID 的作用
 * 序列化的目的是为了将内存中的 Java 对象转换为易于磁盘存储或网络传输的编码格式
 * 反序列化则是为了将磁盘或网络中读取的序列化数据重新转换为 Java 对象到内存中
 *
 * serialVersionUID 的作用是指明当前对象和序列化数据所对应的版本，以方便在反序列化时找到将两者正确匹配，常用于【项目升级】
 * 原理：
 * 在定义类的时候指定一个 serialVersionUID，这样在序列化的时候就会将这个 serialVersionUID 标记在对象的序列化数据中
 * 在反序列化时先找到要转换的类型，然后再根据 serialVersionUID 去验证当前类是否有过更新
 * ID 不一致则说明有过更新，反序列化失败，抛出异常
 * ID 一致则反序列化成功
 *
 * 即 serialVersionUID 的本质就是保证数据的一致性
 */
class SerializationExample {
    private static final String FILE_NAME = "D:\\STUDY\\java\\serialization\\person.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 定义一个类，实现了 Serializable 接口
        class Person implements Serializable {
            private static final long serialVersionUID = 9001383854419957096L;
            private String name;
            private int age;
            private String address;

//            public Person(String name, int age) {
//                this.name = name;
//                this.age = age;
//            }
            public Person(String name, int age, String address) {
                this.name = name;
                this.age = age;
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }

            public String getAddress() {
                return address;
            }


        }

        // 创建一个 Person 对象，并将其序列化到文件中
//        Person person = new Person("Alice", 30);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
//        objectOutputStream.writeObject(person);
//        objectOutputStream.close();


        // 从文件中反序列化 Person 对象
        // 由于修改了 Person 类的定义，但是没有更新 serialVersionUID，
        // 反序列化时会抛出 InvalidClassException 异常，导致反序列化失败
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
        Person deserializedPerson = (Person) objectInputStream.readObject();
        System.out.println(deserializedPerson.getName() + ", " + deserializedPerson.getAge() + ", " + deserializedPerson.getAddress());
        objectInputStream.close();

    }
}


