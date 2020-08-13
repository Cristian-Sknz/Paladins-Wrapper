package me.skiincraft.api.paladins.cache;

import java.util.List;
import java.util.Set;

public interface RuntimeMemory<T> extends Iterable<T> {

	List<T> getAsList();
	Set<T> getAsSet();
	
	int size();
	
	
	long lastUpdate();
	
	T getById(long id);
	
}
