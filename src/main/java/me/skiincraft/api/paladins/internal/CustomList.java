package me.skiincraft.api.paladins.internal;

import java.util.List;
import java.util.stream.Stream;
/**
 * <h1>CustomList</h1>
 * <p>It is an immutable "list", containing generic items that can be saved.</p>
 */
public interface CustomList<T> extends Iterable<T> {

	/**
	 * Returns a copy of the list of T items saved in that class.
	 */
	List<T> getAsList();

	/**
	 * Returns a copy of the stream of T items saved in that class.
	 */
	Stream<T> getAsStream();

	/**
	 * Returns an item by Id, if there is no item with the Id, null will be returned
	 */
	T getById(long itemId);

	/**
	 * Get the item
	 */
	default T get(int i) {
		return getAsList().get(i);
	}

	/**
	 * Quantity of items in the class
	 */
	default int size() {
		return getAsList().size();
	}


}
