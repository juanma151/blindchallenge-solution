package solution.maps;

import static dsl.MapObj.map;

import dsl.MapObj;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public abstract class SMap {
  private final String mainkey;
  private final MapObj map = map();

  public SMap(String mk) {
    mainkey = mk;
  }

  public static <T> T argErr(String msg, T ret) {
    argErr(msg);
    return ret;
  }

  public static void argErr(String msg) {
    throw new IllegalArgumentException(msg);
  }

  public static <T> T argErr(Object condition, String msg, T ret) {
    argErrCondition(condition, msg);
    return ret;
  }

  public static boolean checkFound(boolean b) {
    return (Boolean) checkFound(Boolean.valueOf(b));
  }

  public static Object checkFound(Object condition) {
    return argErr(condition, "Not found", condition);
  }

  public static String getArgString(Object obj) {
    if (obj instanceof String str) {
      return str;
    }

    argErr("String expected");
    return null;
  }

  public int count() {
    return this.map.size();
  }

  public boolean put(List<Object> largs) {
    boolean ret = false;
    String mainval;
    MapObj innerMap;

    // check if the mainkey appears
    checkArgsHasMainKey(largs);

    mainval = getArgString(largs.get(0));

    if (this.map.containsKey(mainval)) {
      argErr("Already exists");
    } else {
      // Add the mainkey to the map
      largs.addFirst(mainkey);

      // save all the object properties under the mainvalue
      // mainkey: mainvalue will be a valid pair
      innerMap = map(largs);
      checkInnerMap(innerMap);

      this.map.put(mainval, innerMap);
      ret = true;
    }

    return ret;
  }

  public Object get(List<Object> largs) {
    String mainval;
    Object ret;

    // check if the mainkey appears and get it if it does
    checkArgsHasMainKey(largs);

    mainval = getArgString(largs.removeFirst());
    ret = map.get(mainval);

    return checkFound(ret);
  }

  public boolean remove(List<Object> largs) {
    boolean ret = false;
    String mainval;

    // check if the mainkey appears and get it if it does
    checkArgsHasMainKey(largs);
    mainval = getArgString(largs.removeFirst());

    if (map.containsKey(mainval)) {
      map.remove(mainval);
      ret = true;
    }

    return checkFound(ret);
  }

  private void checkArgsHasMainKey(List<Object> largs) {
    if (largs.size() < 1) {
      argErr(String.format("Missing %s", this.mainkey));
    }
  }

  private void checkInnerMap(MapObj map) {
    Iterator<Entry<String, Object>> it = map.entrySet().iterator();
    Entry<String, Object> kv;

    while (it.hasNext()) {
      kv = it.next();

      if (!checkParameter(kv.getKey(), kv.getValue())) {
        argErr("Invalid parameter");
      }
    }
  }

  private static void argErrCondition(Object condition, String msg) {
    if (!checkCondition(condition)) {
      argErr(msg);
    }
  }

  private static boolean checkCondition(Object condition) {
    return switch (condition) {
      case Boolean b -> b;
      case null -> false;
      default -> true;
    };
  }

  public abstract boolean checkParameter(String key, Object value);
}
