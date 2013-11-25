import java.util.Hashtable;

public class Peptide {
	String value;
	String reverseVal;
	int mass = 0;
	static Hashtable<String, Integer> massMap = new Hashtable<>();
	static Hashtable<String, Peptide> peptideMap = new Hashtable<>();
	static Peptide _ref = new Peptide();

	private Peptide() {
		readIntegerMasses();
	}

	private Peptide(String s) {
		value = s;
		for (int i = 0; i < s.length(); i++) {
			String sub = s.substring(i, i + 1);
			mass += massMap.get(sub).intValue();
		}

		StringBuilder sb = new StringBuilder(s);
		reverseVal = sb.reverse().toString();
	}

	public static Peptide getPeptide(String s) {
		Peptide p = peptideMap.get(s);
		if (p == null) {
			p = new Peptide(s);
			peptideMap.put(s, p);
		}
		return p;
	}

	public Peptide createNewPeptide(String s) {
		String s2 = value + s;
		Peptide p = peptideMap.get(s2);
		if (p == null) {
			p = new Peptide(s2);
			peptideMap.put(s2, p);
		}
		return p;
	}

	public int getWeight() {
		return mass;
	}

	@Override
	public boolean equals(Object obj) {
		Peptide p = (Peptide) obj;
		return value.equals(p.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value;
	}

	private static void readIntegerMasses() {
		In in = new In("integer_mass_table.txt");
		String line = "";
		while (!in.isEmpty()) {
			line = in.readLine();
			String[] fields = line.split(" ");
			String key = fields[0];
			Integer val = Integer.parseInt(fields[1]);
			massMap.put(key, val);
		}
		in.close();
	}

}
