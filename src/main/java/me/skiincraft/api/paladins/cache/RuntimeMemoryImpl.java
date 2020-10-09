package me.skiincraft.api.paladins.cache;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;

public abstract class RuntimeMemoryImpl<T> implements RuntimeMemory<T>{
	
	public T[] item;
	protected long lastupdate;
	
	public RuntimeMemoryImpl(T[] item){
		this.item = item;
		
	}

	@Nonnull
	public Iterator<T> iterator() {
		return Arrays.stream(item).iterator();
	}

	public List<T> getAsList() {
		return Arrays.stream(item).collect(Collectors.toList());
	}

	public Set<T> getAsSet() {
		return Arrays.stream(item).collect(Collectors.toSet());
	}
	
	public int size() {
		return item.length;
	}

	public long lastUpdate() {
		return lastupdate;
	}

	public abstract T getById(long id);

}
