package solution;

import static solution.maps.SMap.argErr;
import static solution.maps.SMap.getArgString;

import blch.framework.annotations.Solution;
import blch.framework.external.SolutionBase;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import solution.maps.PersonSMap;
import solution.maps.SMap;

@Solution
public class MySolution extends SolutionBase {

  private SMap getEntityMap(String entity) {
    return switch (entity) {
      case "person" -> PersonSMap.getInstance();
      default -> argErr("Unknown entity", null);
    };
  }

  private void checkArgs(List<Object> largs) {
    int size = largs.size();

    if (size < 1) {
      argErr("No arguments");
    } else if (size < 2) {
      argErr("Missing entity");
    }
  }

  @Override
  public Object execute(Object... args) {
    String command, entity;
    SMap map;
    List<Object> largs = Arrays.stream(args).collect(Collectors.toList());

    // Check if we have enough args
    checkArgs(largs);

    // Get the command and entity
    command = getArgString(largs.removeFirst());
    entity = getArgString(largs.removeFirst());

    // Get the right entity map
    map = getEntityMap(entity);

    // Execute the command
    return switch (command) {
      case "COUNT", "SIZE" -> map.count();
      case "PUT" -> map.put(largs);
      case "GET" -> map.get(largs);
      case "REMOVE" -> map.remove(largs);
      default -> argErr("Unknown command", null);
    };
  }
}
