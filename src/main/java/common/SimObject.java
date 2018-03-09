package common;

import com.weidu.common.string.ComString;

public class SimObject {
	
	public Object obj;
	
	private float minScore;

	public SimObject(Object obj, float minScore) {
		this.obj = obj;
		this.minScore = minScore;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimObject other = (SimObject) obj;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (ComString.cosine(this.obj.toString(), other.obj.toString()) < minScore)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return obj.hashCode();
	}

	@Override
	public String toString() {
		return obj.toString();
	}
	
}
