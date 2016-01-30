package com.algorithms.framework.code.examples;

public class EqualAndHashCodeOveride {
    /*a.quals(b) <=> a.hashCode()==b.hashCode()*/
	private int id;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EqualAndHashCodeOveride other = (EqualAndHashCodeOveride) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
