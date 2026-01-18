package solution.maps;

public class PersonSMap extends SMap {

  private static PersonSMap instance;

  private PersonSMap() {
    super("name");
  }

  public static PersonSMap getInstance() {
    if (instance == null) {
      instance = new PersonSMap();
    }

    return instance;
  }

  @Override
  public boolean checkParameter(String key, Object val) {
    return switch (key) {
      case "name" -> checkName(val);
      case "age" -> checkAge(val);
      default -> true;
    };
  }

  private boolean checkName(Object obj) {
    boolean ret = false;

    if (obj instanceof String name) {
      if (!name.isBlank()) {
        ret = true;
      }
    }

    return argErr(ret, "Invalid name", ret);
  }

  private boolean checkAge(Object obj) {
    boolean ret = false;
    if (obj instanceof Integer age) {
      if (age >= 0) {
        ret = true;
      }
    }

    return argErr(ret, "Invalid age", ret);
  }
}
