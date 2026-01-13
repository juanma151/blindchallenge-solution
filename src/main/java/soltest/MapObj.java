package soltest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapObj extends HashMap<String, Object> {
    private static final long serialVersionUID = 557891133969456067L;

    private MapObj() {
    }

    /**
     * Crea un mapa vacío.
     * 
     * @return MapObj vacío
     */
    public static MapObj map() {
	return new MapObj();
    }

    /**
     * Crea un mapa con tantos pares clave-valor como argumentos se utilicen.
     * 
     * Los argumentos siempre tienen que ser [String, Object] (las claves siempre
     * son String).
     * 
     * En caso de que se repita alguna clave, la última sobreescribirá a la primera.
     * 
     * Si el número de argumentos es impar, se elimina la últim clave,
     * 
     * @param args Parejas de Clave-Valor (con la clave como String).
     * @return Un MapObj con todos los pares.
     * @throws IllegalArgumentException Si alguna clave no es String
     */
    public static MapObj map(Object... args) {
	return map(List.of(args));
    }

    /**
     * Crea un mapa con tantos pares Clave-Valor como elementos aparezcan en la
     * lista pasada como atrgumento.
     * 
     * De los pares Clave-Valor, la clave siempre tiene que ser String.
     * 
     * Si alguna clave se repite, la última sobreescribirá a la primera.
     * 
     * Si el número de elementos es impar, se elimina la últimq clave.
     * 
     * @param args Lista de pares Clave-Valor
     * @return un MapObj con todos los pares Clave-Valor agregados.
     * @throws IllegalArgumentException Si alguna clave no es String
     */
    public static MapObj map(List<?> args) {
	return (new MapObj()).kv(args);
    }

    /**
     * Añade pares Clave-Valor al mapa.
     * 
     * Si el número de argumentos es impar, se elimina la última clave.
     * 
     * Si alguna clave se repite, o ya existía en el mapa, se sobreescribira con el
     * valor de la última aparición de la misma.
     * 
     * Las claves tienen que ser String.
     * 
     * @param args Pares Clave-Valor a agregar
     * @return Devuelve el mapa MapObj actualizado.
     * @throws IllegalArgumentException Si alguna clave no es String
     */
    public MapObj kv(Object... args) {
	return kv(Arrays.asList(args));
    }

    /**
     * Añade pares Clave-Valor al mapa.
     * 
     * Si el número de elementos es impar, se eleimina la última clave.
     * 
     * Si alguna clave se repite, o ya existía en el mapa, se sobreescribira con el
     * valor de la última aparición de la misma.
     * 
     * Las claves tienen que ser String.
     * 
     * @param args Lista con elementos Clave-Valor a agregar.
     * @return Devuelve el mapa MapObj actualizado.
     * @throws IllegalArgumentException Si alguna clave no es String
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
     * Añade los pares Clave-Valor de otro mapa.
     * 
     * Si alguna clave ya existía en el mapa actual, se sobreescribirá.
     * 
     * @param other El otro mapa MapObj del que copiar los pares Clave-Valor
     * @return el mapa MapObj actualizado
     */
    public MapObj kv(MapObj other) {
	if (other != null) {
	    this.putAll(other);
	}
	return this;
    }

    /**
     * Elimina los pares Clave-Valor de las claves que se pasan como argumentos.
     * 
     * @param keys Claves a eliminar
     * @return el mapa MapObj actualizado
     */
    public MapObj del(String... keys) {
	return del(Set.of(keys));
    }

    /**
     * Elimina los pares Clave-Valor con claves igual a elementos del conjunto
     * pasado como argumento.
     * 
     * @param keys Conjunto con las claves a eliminar
     * @return el mapa MapObj actualizado
     */
    public MapObj del(Set<String> keys) {
	keys.stream().forEach(this::remove);

	return this;
    }

    /**
     * Elimina los pares Clave-Valor cuyas claves son iguales a las claves de otro
     * mapa MapObj.
     * 
     * @param other El otro mapa MapObj.
     * @return El mapa MapObj actualizado.
     */
    public MapObj del(MapObj other) {
	return this.del(other.keySet());
    }

    /**
     * Elimina los pares Clave-Valor cuyas claves no aparezcan en los argumentos.
     * 
     * Se puede considerar como reducir el mapa a un subconjunto del mismo.
     * 
     * @param keys las claves que se van a conservar
     * @return el mapa MapObj actualizado
     */
    public MapObj sub(String... keys) {
	return sub(Set.of(keys));
    }

    /**
     * Elimina los pares Clave-Valor cuyas claves no aparezcan en los elementos del
     * conjunton pasado como argumento,
     * 
     * Se puede considerar como reducir el mapa a un subconjunto del mismo.
     * 
     * @param keys Conjunto con las claves que se van a mantener
     * @return el mapa MapObj actualizado
     */
    public MapObj sub(Set<String> keys) {
	this.del(this.keySet().stream().filter(k -> !(keys.contains(k))).collect(Collectors.toSet()));

	return this;
    }

    /**
     * Elimina los pares Clave-Valor cuyas claves no aparecen en el mapa MapObj
     * pasado como argumento.
     * 
     * Se puede considerar como la intersección de los dos mapas MapObj.
     * 
     * @param other el otro mapa MapObj
     * @return el mapa MapObj actualizado
     */
    public MapObj sub(MapObj other) {
	Set<String> keys = other.keySet();
	return this.sub(keys);
    }

    /**
     * Elimina todos los pares Clave-Valor del mapa
     * 
     * @return el mapa MapObj actualizado (vacío)
     */
    public MapObj clr() {
	this.clear();
	return this;
    }

    /**
     * Indica si existe una clave en el mapa.
     * 
     * @param k la clave por la que se pregunta
     * @return true si la clave existe en el mapa
     */
    public boolean has(String k) {
	return this.containsKey(k);
    }

    /**
     * Obtiene el valor para la clave indicada, o null si no existe la clave en el
     * mapa.
     * 
     * @param k clave por la que se pregunta
     * @return valor asociado a la clave, o null si la clave no se encuentra en el
     *         mapa
     */
    public Object get(String k) {
	return this.get(k);
    }

    /**
     * Obtiene el número de pares Clave-Valor del mapa
     * 
     * @return número de pares Clave-Valor del mapa
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
