package org.hernan.cussi.continuoussequence;

import java.util.ArrayList;
import java.util.List;

public class Main {

  /**
   * Find the continuous sequences of numbers in given input.
   * e.g input 1 2 3 5 9 a 6 7 8 4 @ -5 -7 -3 -2 -1
   * output
   * 1 2 3
   * 6 7 8
   * -3 -2 -1
   */

  public static void main(String[] args) throws Exception {

    var res = calculateSequences(List.of("1", "2", "3", "5", "9", "a", "6", "7",  "8", "4", "@", "-5", "-7", "-3", "-2", "-1"));
    System.out.println(res);
  }

  private static List<List<Integer>> calculateSequences(final List<String> input) {
    List<List<Integer>> res = new ArrayList<>();

    List<Integer> numbers = input.stream().filter(element -> {
      try {
        Integer.valueOf(element);
      } catch(NumberFormatException ex) {
        return false;
      }
      return true;
    }).map(Integer::valueOf).toList();

    // It's a sequence if I have more than two consecutive numbers

    if (numbers.size() <= 1) {
      return res; // cannot make ay sequence
    }

    var seq = new ArrayList<Integer>();
    res.add(seq);
    var seqDetected = false;

    for (int i = 0; i < numbers.size(); i++) {
      var n1 = numbers.get(i);
      Integer n2 = i + 1 < numbers.size() ? numbers.get(i + 1) : null;

      if (n2 == null) { // sequence cannot be detected
        break;
      }

      if (n2.equals(n1 + 1)) { // detect sequence
        if (!seqDetected) {
          seq.add(n1);
        }
        seq.add(n2);
        seqDetected = true;
      } else if (seqDetected) {
        seq = new ArrayList<>();
        res.add(seq);
        seqDetected = false;
      }
    }

    return res;
  }

}
