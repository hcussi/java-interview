package org.hernan.cussi.permutations;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws Exception {

    var result = doPermutations("ABCD");
    System.out.println(result);

    result = doPermutations("ABCDA");
    System.out.println(result);

    result = doPermutations("ABCDAAA");
    System.out.println(result);
  }

  private static List<String> doPermutations(@NonNull final String input) {
    List<String> permutations = new ArrayList<>();

    if (input.isEmpty()) {
      return permutations;
    }

    Set<Character> charPerms = new HashSet<>();

    var startIndex = 0;

    permutations.add(input);

    while (startIndex < input.length()) {

      if (charPerms.contains(input.charAt(startIndex))) {
        startIndex++;
        continue;
      }

      for (int i = startIndex + 1; i < input.length(); i++) {
        var permutation = new StringBuilder(input);
        var swapped = swap(permutation, startIndex, i);
        if (swapped) {
          permutations.add(permutation.toString());
        }
      }

      charPerms.add(input.charAt(startIndex));

      startIndex++;
    }

    return permutations;
  }

  private static boolean swap(StringBuilder input, int i, int j) {
    var chari = input.charAt(i);
    var charj = input.charAt(j);
    if (chari == charj) {
      return false;
    }
    input.setCharAt(i, charj);
    input.setCharAt(j, chari);
    return true;
  }

}
