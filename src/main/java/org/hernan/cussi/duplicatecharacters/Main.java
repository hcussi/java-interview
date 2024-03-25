package org.hernan.cussi.duplicatecharacters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

  /**
   * Write a program to find the duplicate characters in string
   */

  public static void main(String[] args) throws Exception {

    var res = calculateDuplicates("abbgaaajcxxx");
    System.out.println(res);
  }

  private static List<Character> calculateDuplicates(final String input) {
    List<Character> characters = input.chars().mapToObj(c -> (char) c).toList();

    return characters.stream()
      .collect(Collectors.toMap(
        k -> k,
        v -> 1,
        Integer::sum
      )).entrySet().stream()
      .filter(entry -> entry.getValue() > 1)
      .map(Map.Entry::getKey).toList();
  }

}
