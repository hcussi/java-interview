package org.hernan.cussi.findindexes;

import java.util.List;

import static java.util.stream.Collectors.toMap;


public class Main {

  record Result(Integer index1, Integer index2){}

  /**
   * Given a list of integers nums and an integer target, return indices of the two numbers such that they add up to target.
   * You may assume that each input would have exactly one solution, and you may not use the same element twice.
   * You can return the answer in any order.
   * nums = [2, 8, 6, 3, 5, 1, 4]
   * target = 5
   * output = [0, 3]
   */
  public static void main(String[] args) throws Exception {

    var target = 5;

    List<Integer> numbers = List.of(2, 8, 6, 3, 5, 1, 4);

    var result = findIndexes(numbers, target);
    System.out.println(result);
  }

  public static Result findIndexes(List<Integer> numbers, Integer target) throws Exception {

    var numbersMap = numbers.stream().collect(toMap((number) -> number, (i) -> i));

    for (int i = 0; i < numbers.size(); i++) {
      var n = numbers.get(i);
      if (n > target) {
        continue;
      }

      var j = numbersMap.get(target - n);

      if (j != null) {
        return new Result(i, j);
      }
    }

    throw new Exception("No number found");
  }

}
