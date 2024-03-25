package org.hernan.cussi.salarymap;

import java.util.Map;


public class Main {

  public record Student(String name, Double salary){}

  /**
   * Find Nth salary in given map . Only Java 8 stream solution will be accepted
   * e.g Student has name and salary
   * Map will be like Map<String , Student >
   */
  public static void main(String[] args) throws Exception {

    var target = 2;

    Map<String, Student> studentMap = Map.of(
      "juan", new Student("juan", 30.5),
      "pedro", new Student("pedro", 3.5),
      "luis", new Student("luis", 10.5)
    );

    var result = findStudent(studentMap, target);
    System.out.println(result);
  }

  public static Student findStudent(Map<String, Student> studentMap, Integer target) throws Exception {
    if (target > studentMap.size()) {
      throw new IllegalArgumentException("Illegal target value");
    }
    return studentMap.values().stream()
      .sorted((o1, o2) -> (int) (o1.salary - o2.salary))
      .toList().get(target - 1);
  }

}
