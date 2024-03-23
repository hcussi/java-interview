package org.hernan.cussi.userstats;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
class UserStats {

  @Getter
  private final Optional<Long> visitCount;

}

public class Main {

  /**
   * Unify map based on key and add the visit count
   * Skip invalid long key values and nulls/empty
   */
  public static void main(String[] args) throws Exception {
    List<Map<String, UserStats>> maps = new ArrayList<>();

    maps.add(Map.of("1", new UserStats(Optional.of(10L))));
    maps.add(Map.of("2", new UserStats(Optional.of(20L))));
    maps.add(Map.of("3", new UserStats(Optional.of(30L))));
    maps.add(Map.of("hola", new UserStats(Optional.of(30L))));
    maps.add(Map.of("4", new UserStats(Optional.of(40L))));
    maps.add(Map.of("5", new UserStats(Optional.empty())));
    maps.add(Map.of("1", new UserStats(Optional.of(1L))));
    maps.add(Map.of("2", new UserStats(Optional.of(2L))));
    maps.add(Map.of("2", new UserStats(Optional.of(22L))));
    maps.add(Map.of("2", new UserStats(Optional.of(224L))));

    var mapCount = count(maps);
    System.out.println(mapCount);
  }

  public static Map<Long, Long> count(List<Map<String, UserStats>> visits) {
    if (visits == null || visits.isEmpty()) {
      return Map.of();
    }

    return visits.stream()
      .filter(Objects::nonNull)
      .filter(stringUserStatsMap -> !stringUserStatsMap.isEmpty())
      .flatMap(stringUserStatsMap -> stringUserStatsMap.entrySet().stream())
      .filter(visit -> visit.getValue() != null)
      .filter(visit -> visit.getValue().getVisitCount() != null)
      .filter(visit -> {
        try {
          Long.parseLong(visit.getKey());
          return true;
        } catch(Exception e){
          return false;
        }
      })
      .filter(visit -> visit.getValue().getVisitCount().isPresent())
      .collect(
        Collectors.toMap(
          (visit) -> Long.parseLong(visit.getKey()),
          visit -> visit.getValue()
            .getVisitCount()
            .get(),
          (aCount, aCount2) -> aCount + aCount2
        )
      );
  }
}
