package org.hernan.cussi.leftrightorder;

import java.util.List;

public class Main {

  /**
   * You have an arrays of zeros (0) and (1) keep all zeros at the left hand side and all 1 to right hand side
   * e.g Integer array[] =[1,1,0,1,0] -> output : [0,0,1,1,1]
   */
  public static void main(String[] args) throws Exception {

    var res = leftRightOrder(List.of(1, 1, 0, 1, 0));
    System.out.println(res);
  }

  private static List<Integer> leftRightOrder(final List<Integer> input) {

    return input.stream().sorted().toList();
  }

}
