package bean;

public class Word {
	String value;
	

	public Word() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Word(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Word [value=" + value + "]";
	}
	

}
