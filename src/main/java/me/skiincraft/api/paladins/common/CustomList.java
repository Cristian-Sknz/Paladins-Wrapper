package me.skiincraft.api.paladins.common;

import java.util.List;
import java.util.stream.Stream;

public interface CustomList<T> extends Iterable<T> {

	/**<p>Obtain all Item contained in this instance through a List</p>
	 */
	List<T> getAsList();
	
	/**<p>Obtain all Item contained in this instance through a Stream</p>
	 */
	Stream<T> getAsStream();
	
	/**<p>Get a Item by id</p>	 */
	T getById(long itemId);
	default T get(int i) {
		return getAsList().get(i);
	}
	
	/**<p>Get a Item List size()</p>
	 */
	default int size() {
		return getAsList().size();
	};
	
	
}
