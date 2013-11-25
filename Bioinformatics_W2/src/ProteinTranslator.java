import java.util.Hashtable;

public class ProteinTranslator {
	public String translate(String string) {
		int start = 0;
		StringBuilder sb = new StringBuilder();
		while (start + 3 <= string.length()) {
			String key = string.substring(start, start + 3);
			String val = codonMap.get(key);
			if (val != null)
				sb.append(val);
			start += 3;
		}
		return sb.toString();
	}

	public ProteinTranslator() {
		codonMap = new Hashtable<>();
		readCodons();
	}

	private Hashtable<String, String> codonMap;

	public void readCodons() {
		In in = new In("RNA_codon_table_1.txt");
		String line = "";
		while (!in.isEmpty()) {
			line = in.readLine();
			String[] fields = line.split(" ");
			String key = fields[0];
			String val = "";
			if (fields.length == 2)
				val = fields[1];
			codonMap.put(key, val);
		}
		in.close();
	}
}
