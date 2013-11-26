import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

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

	SpectrumGenerator sg = new SpectrumGenerator();

	public ArrayList<String> sequence(ArrayList<Integer> input) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> queue = new ArrayList<>();
		queue.add("");

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
		CopyOnWriteArrayList<String> leaders = new CopyOnWriteArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		String leader = "";
		leaders.add(leader);
		int parentMass = spectrum.get(spectrum.size() - 1);
		// while Leaderboard is non-empty
		while (!leaders.isEmpty()) {
			leaders = expandQueue(leaders);
			// for each Peptide in Leaderboard
			for (String s : leaders) {
				int mass = getMass(s);
				if (mass == parentMass) {
					int sScore = Score(s, spectrum);
					int lScore = Score(leader, spectrum);
					if (sScore > lScore) {
						leader = s;
						result.clear();
						result.add(s);
					} else if (sScore == lScore) {
						result.add(s);
					}
				} else if (mass > parentMass) {
					// remove Peptide from Leaderboard
					leaders.remove(s);
				}
			}
			leaders = Cut(leaders, spectrum, maxCandidates);
		}

		System.out.println("leader: " + leader);
//		leaders = CutToScore(leaders, spectrum, Score(leader, spectrum));
//		result.addAll(leaders);
		result = convertToWeights(result);

		return result;
	}

	private CopyOnWriteArrayList<String> CutToScore(
			CopyOnWriteArrayList<String> leaders, ArrayList<Integer> spectrum,
			int i) {

		TreeMap<Integer, ArrayList<String>> ordered = new TreeMap<>();
		for (String s : leaders) {
			int score = Score(s, spectrum);
			ArrayList<String> candidates = ordered.get(score);
			if (candidates == null)
				candidates = new ArrayList<>();
			candidates.add(s);
			ordered.put(score, candidates);
		}

		leaders.clear();

		Integer key = ordered.lastKey();
		ArrayList<String> candidates = ordered.get(key);
		leaders.addAll(candidates);
		return leaders;
	}

	private int Score(String s, ArrayList<Integer> spectrum) {
		ArrayList<Integer> candidate = sg.generateSpectrum(s, true);
		ArrayList<Integer> target = new ArrayList<>(spectrum.size());
		target.addAll(spectrum);
		int result = 0;

		for (Integer i : candidate)
			if (target.remove(i))
				++result;
		return result;
	}

	private CopyOnWriteArrayList<String> Cut(
			CopyOnWriteArrayList<String> leaders, ArrayList<Integer> spectrum,
			int maxCandidates) {

		if (leaders.size() <= maxCandidates)
			return leaders;

		TreeMap<Integer, ArrayList<String>> ordered = new TreeMap<>();
		for (String s : leaders) {
			int score = Score(s, spectrum);
			ArrayList<String> candidates = ordered.get(score);
			if (candidates == null)
				candidates = new ArrayList<>();
			candidates.add(s);
			ordered.put(score, candidates);
		}

		leaders.clear();

		for (Integer key : ordered.descendingKeySet()) {
			ArrayList<String> candidates = ordered.get(key);
			leaders.addAll(candidates);
			if (leaders.size() >= maxCandidates)
				break;
		}
		return leaders;
	}

	private int getMass(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			Peptide p = Peptide.getPeptide(s.substring(i, i + 1));
			sum += p.getWeight();
		}
		return sum;
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

	private CopyOnWriteArrayList<String> expandQueue(
			CopyOnWriteArrayList<String> leaders) {
		CopyOnWriteArrayList<String> result = new CopyOnWriteArrayList<>();
		for (String s : leaders)
			for (Peptide p : peptides) {
				String es = s + p.value;
				result.add(es);
			}
		return result;
	}
}
