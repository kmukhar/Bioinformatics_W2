import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class SpectrumGenerator {
	Hashtable<String, Integer> massMap = new Hashtable<>();

	public SpectrumGenerator() {
		readIntegerMasses();
	}

	public ArrayList<Integer> generateSpectrum(String input) {
		Hashtable<String, Integer> processed = new Hashtable<>();
		ArrayList<Integer> result = new ArrayList<>();
		result.add(0);
		int length = input.length();

		String text = input + input;
		for (int i = 1; i < length; i++) {
			int start = 0;
			while (start < length) {
				StringBuilder subText = new StringBuilder(text.substring(start,
						start + i));
//				if (processed.get(subText.toString()) == null
//						&& processed.get(subText.reverse().toString()) == null) {
					int mass = 0;
					char[] chars = new char[subText.length()];
					subText.getChars(0, subText.length(), chars, 0);
					for (char s : chars) {
						mass += massMap.get("" + s).intValue();
					}
					processed.put(subText.reverse().toString(), mass);
					result.add(mass);
//				}
				++start;
			}
		}

		int mass = 0;
		char[] chars = new char[input.length()];
		input.getChars(0, input.length(), chars, 0);
		for (char s : chars) {
			mass += massMap.get("" + s).intValue();
		}
		if (mass > 0)
			result.add(mass);

		Collections.sort(result);
		return result;
	}

	private void readIntegerMasses() {
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
