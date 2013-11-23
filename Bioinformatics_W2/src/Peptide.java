import java.util.Hashtable;

public class Peptide {

	private Peptide() {
		mass = 0;
		readIntegerMasses();
	}

	private Peptide(String s) {
		mass = 0;
		value = s;
		for (int i = 0; i < s.length(); i++) {
			String sub = s.substring(i, i + 1);
			mass += ((Integer) massMap.get(sub)).intValue();
		}

		StringBuilder sb = new StringBuilder(s);
		reverseVal = sb.reverse().toString();
	}

	public static Peptide getPeptide(String s) {
		Peptide p = (Peptide) peptideMap.get(s);
		if (p == null) {
			p = new Peptide(s);
			peptideMap.put(s, p);
		}
		return p;
	}

	public Peptide createNewPeptide(String s) {
		String s2 = (new StringBuilder(String.valueOf(value))).append(s)
				.toString();
		Peptide p = (Peptide) peptideMap.get(s2);
		if (p == null) {
			p = new Peptide(s2);
			peptideMap.put(s2, p);
		}
		return p;
	}

	public int getWeight() {
		return mass;
	}

	public boolean equals(Object obj) {
		Peptide p = (Peptide) obj;
		return value.equals(p.value);
	}

	public int hashCode() {
		return value.hashCode();
	}

	public String toString() {
		return value;
	}

	private static void readIntegerMasses() {
		In in = new In("integer_mass_table.txt");
		String line = "";
		String key;
		Integer val;
		for (; !in.isEmpty(); massMap.put(key, val)) {
			line = in.readLine();
			String fields[] = line.split(" ");
			key = fields[0];
			val = Integer.valueOf(Integer.parseInt(fields[1]));
		}

		in.close();
	}

	String value;
	String reverseVal;
	int mass;
	static Hashtable<String, Integer> massMap = new Hashtable<>();
	static Hashtable<String, Peptide> peptideMap = new Hashtable();
	static Peptide _ref = new Peptide();
}
