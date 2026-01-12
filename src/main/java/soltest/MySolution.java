package soltest;

import blch.framework.annotations.Solution;
import blch.framework.datamodel.VValue;
import blch.framework.datamodel.WireValue;
import blch.framework.external.SolutionBase;
import java.util.LinkedHashMap;
import java.util.Map;

@Solution
public class MySolution extends SolutionBase {

  @Override
  public Object execute(Object... args) {
    Map<String, WireValue> mymap = new LinkedHashMap<>();
    mymap.put("name", new VValue("Alice"));

    return mymap;
  }

  public Object execute1(Object... args) {
    Map<String, Object> mymap = new LinkedHashMap<>();
    mymap.put("name", "Alice");

    return mymap;
  }
}
