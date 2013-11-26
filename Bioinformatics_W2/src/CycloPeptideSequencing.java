import java.util.ArrayList;

public class CycloPeptideSequencing {
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

	public ArrayList<String> sequence(ArrayList<Integer> input) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> queue = new ArrayList<>();
		queue.add("");
		SpectrumGenerator sg = new SpectrumGenerator();

		while (!queue.isEmpty()) {
			queue = expandQueue(queue);
			ArrayList<String> toRemove = new ArrayList<>();
			for (String s : queue) {
				ArrayList<Integer> Cyclospectrum = sg.generateSpectrum(s, true);
				ArrayList<Integer> spectrum = sg.generateSpectrum(s, false);

				if (input.equals(Cyclospectrum)) {
					result.add(s);
					toRemove.add(s);
				} else {
					for (Integer i : input)
						while (spectrum.size() > 0 && spectrum.remove(i))
							;

					if (spectrum.size() > 0)
						toRemove.add(s);
				}
			}
			for (String s : toRemove)
				queue.remove(s);
		}

		System.out.println(result);
		result = convertToWeights(result);
		return result;
	}

	public ArrayList<String> leaderboardSequence(ArrayList<Integer> spectrum,
			int maxCandidates) {
		ArrayList<String> leaders = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		String leader = "";
		leaders.add(leader);
		// while Leaderboard is non-empty
		while (!leaders.isEmpty()) {
//        Leaderboard <- Expand(Leaderboard)
//        for each Peptide in Leaderboard
//            if Mass(Peptide) = ParentMass(Spectrum)
//                if Score(Peptide, Spectrum) > Score(LeaderPeptide, Spectrum)
//                    LeaderPeptide <- Peptide
//            else if Mass(Peptide) > ParentMass(Spectrum)
//                remove Peptide from Leaderboard
//        Leaderboard <- Cut(Leaderboard, Spectrum, N)
//    output LeaderPeptide
		}
		return result;
	}
    
	private ArrayList<String> convertToWeights(ArrayList<String> result) {
		ArrayList<String> converted = new ArrayList<>();
		for (String s : result) {
			int start = 0;
			Peptide p = Peptide.getPeptide(s.substring(start, start + 1));
			StringBuilder sb = new StringBuilder();
			sb.append(p.getWeight());
			++start;
			while (start + 1 <= s.length()) {
				String sub = s.substring(start, start + 1);
				sb.append("-").append(Peptide.getPeptide(sub).getWeight());
				++start;
			}
			converted.add(sb.toString());
		}
		return converted;
	}

	private ArrayList<String> expandQueue(ArrayList<String> queue) {
		ArrayList<String> result = new ArrayList<>(queue.size() * 18);
		for (String s : queue)
			for (Peptide p : peptides) {
				String es = s + p.value;
				result.add(es);
			}
		return result;
	}
}
