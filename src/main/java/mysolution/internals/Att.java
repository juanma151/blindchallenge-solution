package mysolution.internals;

public class Att {
  private String name;
  private Class<?> type;
  private boolean key;

  public Att(String name) {
    this(name, String.class, false);
  }

  public Att(String name, Class<?> type) {
    this(name, type, false);
  }

  public Att(String name, Class<?> type, boolean isKey) {
    this.name = name;
    this.type = type;
    this.key = isKey;
  }

  public String getName() {
    return name;
  }

  public Class<?> getType() {
    return type;
  }

  public boolean isKey() {
    return key;
  }

  public boolean check(Object o) {
    return this.type.isInstance(o);
  }
}
