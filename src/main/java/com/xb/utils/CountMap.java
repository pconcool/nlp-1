package com.xb.utils;

import java.io.Serializable;
import java.util.HashMap;

public class CountMap<T> extends HashMap<T, Integer> implements Serializable {

	private static final long serialVersionUID = 6097963798841161750L;

	public void increase(T t) {
		Integer count = get(t);
		if (count == null) {
			put(t, 1);
		} else {
			put(t, ++count);
		}
	}

	public int count() {
		int count = 0;
		for (T t : keySet()) {
			count += get(t);
		}
		return count;
	}

	public int get(char c) {
		Integer count = super.get(c);
		return null == count ? 0 : count;
	}
}