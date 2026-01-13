package soltest;

import static blch.framework.dsl.WireDsl.obj;
import static blch.framework.dsl.WireDsl.v;
import static soltest.ArrObj.arr;
import static soltest.MapObj.map;

import java.util.LinkedHashMap;
import java.util.Map;

import blch.framework.annotations.Solution;
import blch.framework.external.SolutionBase;

@Solution
public class MySolution extends SolutionBase {

  public Object execute(Object... args) {
      return execute6();
  }
  
  public Object execute6(Object... args) {
      return arr(1,"aa",3,4,5).addf("000","001").addl(false,false).rf().rl().set(2,arr()
	      .addl(1,2,3,4,5,6)
	      );
  }
  
  public Object execute5(Object... args) {
      return map("name","Eve")
	      .kv("age", 22)
	      .kv("email", "eve@example.com")
	      .kv("map", map()
		      .kv("a",1)
		      .kv("b",2)
		      
		      );
  }
  
  public Object execute4(Object... args) {
      return map("name","Alice");
  }  
    
  public Object execute3(Object... args) {
    return obj("name", v("Alice"));
  }
  
  public Object execute2(Object... args) {
      Map<String, String> mymap = new LinkedHashMap<>();
      mymap.put("name", "Alice");

      return mymap;
    }

  public Object execute1(Object... args) {
    Map<String, Object> mymap = new LinkedHashMap<>();
    mymap.put("name", "Alice");

    return mymap;
  }
}
