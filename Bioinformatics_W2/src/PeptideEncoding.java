import java.util.ArrayList;

import com.mukhar.PatternReverser;

public class PeptideEncoding {
	public ArrayList<String> findEncoding(String text, String peptide) {
		ArrayList<String> result = new ArrayList<>();

		int pLength = peptide.length() * 3;
		int start = 0;

		ProteinTranslator pt = new ProteinTranslator();
		PatternReverser reverser = new PatternReverser(false);

		while (start + pLength <= text.length()) {
			String subText = text.substring(start, start + pLength);
			String subText1 = subText.replace('T', 'U');
			String candidate1 = pt.translate(subText1);

			String subText2 = reverser.reverseAndComplement(subText).replace('T', 'U');
			String candidate2 = pt.translate(subText2);

			if (peptide.equalsIgnoreCase(candidate1)||peptide.equalsIgnoreCase(candidate2))
				result.add(subText);
			start += 1;
		}

		return result;
	}
}
