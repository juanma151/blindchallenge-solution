package dsl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MapObj es una estructura auxiliar de tipo DSL para construir mapas (clave String -> valor Object)
 * mediante encadenamiento de métodos cortos.
 *
 * <p>Está inspirada en un Builder fluido, pero orientada a un estilo declarativo: - crear y
 * rellenar mapas rápido - escribir código expresivo (especialmente en tests o DSLs internos) -
 * componer operaciones comunes (agregar, borrar, filtrar subconjuntos)
 *
 * <p>Reglas principales de la DSL: - Las claves siempre deben ser String. - Los métodos que reciben
 * una secuencia de elementos interpretan la entrada como pares [clave, valor, clave, valor, ...]. -
 * Si hay claves repetidas, la última aparición gana (sobrescribe). - Si el número de elementos es
 * impar, se descarta el último elemento (una "clave" sin valor).
 *
 * <p>Ejemplo de uso:
 *
 * <p>MapObj m = MapObj.map("id", 10, "name", "Ana") .kv("active", true) .del("name") .sub("id",
 * "active");
 *
 * <p>Todos los métodos modificadores devuelven this para permitir encadenamiento fluido.
 */
public class MapObj extends HashMap<String, Object> {

  private static final long serialVersionUID = 557891133969456067L;

  /**
   * Constructor privado. Se fuerza el uso de los métodos de factoría map() para reforzar el estilo
   * DSL.
   */
  private MapObj() {}

  /**
   * Crea un mapa vacío.
   *
   * @return MapObj vacío
   */
  public static MapObj map() {
    return new MapObj();
  }

  /**
   * Crea un mapa a partir de una secuencia de pares clave-valor.
   *
   * <p>Reglas: - La entrada se interpreta como [String, Object, String, Object, ...]. - Si se
   * repite una clave, la última aparición sobrescribe el valor anterior. - Si el número de
   * argumentos es impar, se descarta el último elemento. - Si alguna "clave" no es String, se lanza
   * IllegalArgumentException.
   *
   * @param args secuencia de pares clave-valor (clave siempre String)
   * @return MapObj inicializado con los pares indicados
   * @throws IllegalArgumentException si alguna clave no es String
   */
  public static MapObj map(Object... args) {
    return map(List.of(args));
  }

  /**
   * Crea un mapa a partir de una lista que contiene pares clave-valor.
   *
   * <p>Reglas: - La lista se interpreta como [String, Object, String, Object, ...]. - Si se repite
   * una clave, la última aparición sobrescribe el valor anterior. - Si el número de elementos es
   * impar, se descarta el último elemento. - Si alguna "clave" no es String, se lanza
   * IllegalArgumentException.
   *
   * @param args lista con elementos en formato clave-valor
   * @return MapObj inicializado con los pares indicados
   * @throws IllegalArgumentException si alguna clave no es String
   */
  public static MapObj map(List<?> args) {
    return (new MapObj()).kv(args);
  }

  /**
   * Añade pares clave-valor al mapa.
   *
   * <p>Reglas: - La entrada se interpreta como [String, Object, String, Object, ...]. - Si el
   * número de argumentos es impar, se descarta el último elemento. - Si una clave ya existía o
   * aparece repetida, se sobrescribe con la última aparición. - Si alguna "clave" no es String, se
   * lanza IllegalArgumentException.
   *
   * @param args secuencia de pares clave-valor
   * @return this
   * @throws IllegalArgumentException si alguna clave no es String
   */
  public MapObj kv(Object... args) {
    return kv(Arrays.asList(args));
  }

  /**
   * Añade pares clave-valor al mapa a partir de una lista.
   *
   * <p>Reglas: - La lista se interpreta como [String, Object, String, Object, ...]. - Si el número
   * de elementos es impar, se descarta el último elemento. - Si una clave ya existía o aparece
   * repetida, se sobrescribe con la última aparición. - Si alguna "clave" no es String, se lanza
   * IllegalArgumentException.
   *
   * <p>Nota: este método modifica la lista recibida si su tamaño es impar (descarta el último
   * elemento).
   *
   * @param args lista con elementos en formato clave-valor
   * @return this
   * @throws IllegalArgumentException si alguna clave no es String
   */
  public MapObj kv(List<?> args) {
    String k;
    Object v;

    if (args.size() % 2 != 0) {
      args.removeLast();
    }

    for (int i = 0; i < args.size(); i += 2) {
      k = getKeyFromList(args, i);
      v = getValFromList(args, i + 1);
      this.put(k, v);
    }

    return this;
  }

  /**
   * Añade todos los pares clave-valor de otro MapObj.
   *
   * <p>Si una clave ya existía en el mapa actual, se sobrescribe. Si other es null, no hace nada.
   *
   * @param other mapa del que copiar pares
   * @return this
   */
  public MapObj kv(MapObj other) {
    if (other != null) {
      this.putAll(other);
    }
    return this;
  }

  /**
   * Elimina del mapa las claves indicadas.
   *
   * @param keys claves a eliminar
   * @return this
   */
  public MapObj del(String... keys) {
    return del(Set.of(keys));
  }

  /**
   * Elimina del mapa las claves que aparezcan en el conjunto indicado.
   *
   * @param keys conjunto de claves a eliminar
   * @return this
   */
  public MapObj del(Set<String> keys) {
    keys.stream().forEach(this::remove);
    return this;
  }

  /**
   * Elimina del mapa las claves que existan en otro MapObj.
   *
   * @param other mapa cuyas claves se eliminarán del actual
   * @return this
   */
  public MapObj del(MapObj other) {
    return this.del(other.keySet());
  }

  /**
   * Reduce el mapa para conservar únicamente las claves indicadas.
   *
   * <p>Equivale a eliminar todo lo que NO esté en keys.
   *
   * @param keys claves a conservar
   * @return this
   */
  public MapObj sub(String... keys) {
    return sub(Set.of(keys));
  }

  /**
   * Reduce el mapa para conservar únicamente las claves del conjunto indicado.
   *
   * <p>Equivale a eliminar todo lo que NO esté en keys.
   *
   * @param keys conjunto de claves a conservar
   * @return this
   */
  public MapObj sub(Set<String> keys) {
    this.del(this.keySet().stream().filter(k -> !(keys.contains(k))).collect(Collectors.toSet()));
    return this;
  }

  /**
   * Reduce el mapa para conservar únicamente las claves que también existan en other.
   *
   * <p>Se puede entender como una intersección por claves.
   *
   * @param other mapa cuyas claves se usarán como filtro
   * @return this
   */
  public MapObj sub(MapObj other) {
    Set<String> keys = other.keySet();
    return this.sub(keys);
  }

  /**
   * Elimina todos los pares clave-valor del mapa.
   *
   * @return this
   */
  public MapObj clr() {
    this.clear();
    return this;
  }

  /**
   * Indica si existe una clave en el mapa.
   *
   * @param k clave por la que se pregunta
   * @return true si la clave existe en el mapa
   */
  public boolean has(String k) {
    return this.containsKey(k);
  }

  /**
   * Obtiene el valor asociado a la clave indicada, o null si no existe.
   *
   * <p>Nota: este método delega en HashMap.get(Object).
   *
   * @param k clave por la que se pregunta
   * @return valor asociado o null
   */
  public Object get(String k) {
    return super.get(k);
  }

  /**
   * Obtiene el número de pares clave-valor del mapa.
   *
   * @return tamaño del mapa
   */
  public int len() {
    return this.size();
  }

  private static Object getValFromList(List<?> args, int index) {
    if (index < args.size()) {
      return args.get(index);
    } else {
      throw new IndexOutOfBoundsException("Key Values of MapObj missed a value");
    }
  }

  private static String getKeyFromList(List<?> args, int index) {
    Object obj = getValFromList(args, index);

    if (obj instanceof String key) {
      return key;
    } else {
      throw new IllegalArgumentException("Key of MapObj is not a String");
    }
  }
}
