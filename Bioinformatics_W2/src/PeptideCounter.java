import java.util.ArrayList;
import java.util.Hashtable;

public class PeptideCounter {
	static String[] aminos = new String[] { "G", "A", "S", "P", "V", "T", "C",
			"I", "N", "D", "K", "E", "M", "H", "F", "R", "Y", "W" };

	// treat I/L and K/Q as the same, so remove L, remove Q
	// Peptide.getPeptide("L"),
	// Peptide.getPeptide("Q"),
	static Peptide[] peptides = new Peptide[] { Peptide.getPeptide("G"),
			Peptide.getPeptide("A"), Peptide.getPeptide("S"),
			Peptide.getPeptide("P"), Peptide.getPeptide("V"),
			Peptide.getPeptide("T"), Peptide.getPeptide("C"),
			Peptide.getPeptide("I"), Peptide.getPeptide("N"),
			Peptide.getPeptide("D"), Peptide.getPeptide("K"),
			Peptide.getPeptide("E"), Peptide.getPeptide("M"),
			Peptide.getPeptide("H"), Peptide.getPeptide("F"),
			Peptide.getPeptide("R"), Peptide.getPeptide("Y"),
			Peptide.getPeptide("W") };

	private long[] counts;

	public void init(int total) {
		counts = new long[total + 1];
	}

	public long count(int total) {
		if (total < 0)
			return 0;
		if (total == 0)
			return 1;
		if (counts[total] > 0)
			return counts[total];
		for (Peptide p : peptides) {
			int newTotal = total - p.getWeight();
			if (newTotal < 0)
				break;
			counts[total] += count(newTotal);
		}
		return counts[total];
	}

	public static long count2(int total) {
		int[] counts = new int[total + 1];
		for (Peptide p : peptides) {
			if (p.getWeight() <= total)
				++counts[p.getWeight()];
		}
		for (int i = 57; i <= total; i++) {
			Peptide apeptide1[];
			int j1 = (apeptide1 = peptides).length;
			for (int i1 = 0; i1 < j1; i1++) {
				Peptide p = apeptide1[i1];
				if (p.getWeight() > i)
					break;
				for (int j = i; j <= total; j++) {
					if (j + p.getWeight() > total)
						break;
					if (counts[j] != 0)
						counts[j + p.getWeight()]++;
				}

			}

		}

		return (long) counts[total];
	}

	public static long count3(int total) {
		int counts[] = new int[total + 1];
		Peptide apeptide[];
		int l = (apeptide = peptides).length;
		for (int k = 0; k < l; k++) {
			Peptide p = apeptide[k];
			if (p.getWeight() <= total)
				counts[p.getWeight()]++;
		}

		for (int i = 57; i <= total; i++) {
			Peptide apeptide1[];
			int j1 = (apeptide1 = peptides).length;
			for (int i1 = 0; i1 < j1; i1++) {
				Peptide p = apeptide1[i1];
				if (p.getWeight() > i)
					break;
				for (int j = i; j <= total; j++) {
					if (j + p.getWeight() > total)
						break;
					if (counts[j] != 0)
						counts[j + p.getWeight()]++;
				}
			}
		}
		return (long) counts[total];
	}
}
