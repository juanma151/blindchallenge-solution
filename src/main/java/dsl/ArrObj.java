package dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrObj es una estructura auxiliar de tipo DSL para la construcción fluida de arrays heterogéneos
 * (Object) mediante encadenamiento de métodos cortos.
 *
 * <p>Está inspirada en el patrón Builder, pero adaptada a un estilo declarativo y conciso, pensado
 * para: - Construcción rápida de estructuras de datos - Lectura expresiva del código - Uso en DSLs
 * internos, tests o generación de datos
 *
 * <p>Ejemplo de uso:
 *
 * <p>ArrObj arr = ArrObj.arr(1, 2, 3) .addl(4, 5) .addf(0) .rf() .addi(1, "x");
 *
 * <p>Todos los métodos modificadores devuelven this para permitir encadenamiento fluido.
 */
public class ArrObj extends ArrayList<Object> {

  private static final long serialVersionUID = -4740730975324856629L;

  /**
   * Constructor privado. Se fuerza el uso de los métodos de factoría arr() para reforzar el estilo
   * DSL.
   */
  private ArrObj() {}

  /**
   * Crea un ArrObj vacío.
   *
   * @return nuevo ArrObj
   */
  public static ArrObj arr() {
    return new ArrObj();
  }

  /**
   * Crea un ArrObj inicializado con los elementos indicados.
   *
   * @param args elementos iniciales
   * @return ArrObj inicializado
   */
  public static ArrObj arr(Object... args) {
    return arr(List.of(args));
  }

  /**
   * Crea un ArrObj inicializado con una lista.
   *
   * @param args lista de elementos iniciales
   * @return ArrObj inicializado
   */
  public static ArrObj arr(List<?> args) {
    ArrObj arr = new ArrObj();
    return arr.addl(args);
  }

  /**
   * Añade elementos al final del array.
   *
   * @param args elementos a añadir
   * @return this
   */
  public ArrObj addl(Object... args) {
    return addl(List.of(args));
  }

  /**
   * Añade una lista de elementos al final del array.
   *
   * @param args elementos a añadir
   * @return this
   */
  public ArrObj addl(List<?> args) {
    this.addAll(args);
    return this;
  }

  /**
   * Añade elementos al principio del array.
   *
   * @param args elementos a añadir
   * @return this
   */
  public ArrObj addf(Object... args) {
    return addf(List.of(args));
  }

  /**
   * Añade una lista de elementos al principio del array. Si el array está vacío, se comporta como
   * addl.
   *
   * @param args elementos a añadir
   * @return this
   */
  public ArrObj addf(List<?> args) {
    if (this.size() == 0) {
      this.addAll(args);
    } else {
      this.addAll(0, args);
    }
    return this;
  }

  /**
   * Inserta elementos a partir de un índice.
   *
   * @param index posición de inserción
   * @param args elementos a insertar
   * @return this
   */
  public ArrObj addi(int index, Object... args) {
    return addi(index, List.of(args));
  }

  /**
   * Inserta una lista de elementos a partir de un índice. Reglas: - índice &lt; 0: inserción al
   * inicio - índice >= tamaño: inserción al final
   *
   * @param index posición de inserción
   * @param args elementos a insertar
   * @return this
   */
  public ArrObj addi(int index, List<?> args) {
    if (index < 0) {
      this.addAll(0, args);
    } else if (index >= this.size()) {
      this.addAll(args);
    } else {
      this.addAll(index, args);
    }
    return this;
  }

  /**
   * Inserta un elemento en una posición determinada. A diferencia de ArrayList.set, este método no
   * reemplaza, sino que inserta desplazando elementos.
   *
   * @param index posición
   * @param obj elemento a insertar
   * @return this
   */
  public ArrObj set(int index, Object obj) {
    if (index < 0) {
      this.addFirst(obj);
    } else if (index >= this.size()) {
      this.add(obj);
    } else {
      this.add(index, obj);
    }
    return this;
  }

  /**
   * Elimina el primer elemento del array.
   *
   * @return this
   */
  public ArrObj rf() {
    return rf(1);
  }

  /**
   * Elimina los primeros len elementos del array.
   *
   * @param len número de elementos a eliminar
   * @return this
   */
  public ArrObj rf(int len) {
    len = Math.min(len, this.size());
    this.removeRange(0, len);
    return this;
  }

  /**
   * Elimina el último elemento del array.
   *
   * @return this
   */
  public ArrObj rl() {
    return rl(1);
  }

  /**
   * Elimina los últimos len elementos del array.
   *
   * @param len número de elementos a eliminar
   * @return this
   */
  public ArrObj rl(int len) {
    len = this.size() - Math.min(len, this.size());
    this.removeRange(len, this.size());
    return this;
  }

  /**
   * Elimina todos los elementos del array.
   *
   * @return this
   */
  public ArrObj clr() {
    this.clear();
    return this;
  }

  /**
   * Devuelve el número de elementos del array.
   *
   * @return tamaño
   */
  public int len() {
    return this.size();
  }

  /**
   * Devuelve el primer elemento del array.
   *
   * @return primer elemento
   */
  public Object f() {
    return this.getFirst();
  }

  /**
   * Devuelve el último elemento del array.
   *
   * @return último elemento
   */
  public Object l() {
    return this.getLast();
  }

  /**
   * Devuelve el elemento en una posición concreta. Si el índice está fuera de rango, devuelve null
   * en lugar de lanzar una excepción.
   *
   * @param index posición
   * @return elemento o null
   */
  public Object i(int index) {
    if (index < 0 || index >= this.size()) {
      return null;
    } else {
      return this.get(index);
    }
  }
}
