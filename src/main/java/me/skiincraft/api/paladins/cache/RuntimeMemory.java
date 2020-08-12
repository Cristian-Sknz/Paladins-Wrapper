package me.skiincraft.api.paladins.cache;

import java.util.List;
import java.util.Set;

public interface RuntimeMemory<T> extends Iterable<T> {

	List<T> getAsList();
	Set<T> getAsSet();
	
	default int size() {
		int i = 0;
		while(iterator().hasNext()) {
		    i++;
		    iterator().next();
		}
		return i;
	}
	
	
	long lastUpdate();
	
	T getById(long id);
	
}
