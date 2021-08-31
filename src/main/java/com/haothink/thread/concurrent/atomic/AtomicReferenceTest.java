package com.haothink.thread.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wanghao on 2021/8/31
 **/
public class AtomicReferenceTest {

  public static void main(String[] args) {

    AtomicReference<Person> ar = new AtomicReference<>();
    Person person = new Person("SnailClimb", 22);
    ar.set(person);
    Person updatePerson = new Person("Daisy", 20);
    ar.compareAndSet(person, updatePerson);

    System.out.println(ar.get().getName());
    System.out.println(ar.get().getAge());
  }


  static class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
      super();
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }

  }
