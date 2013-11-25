import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class PeptideEncodingTest {
	PeptideEncoding pe;

	@Before
	public void setUp() throws Exception {
		pe = new PeptideEncoding();
	}

	@Test
	public void testFindEncoding() {
		ArrayList<String> result = pe.findEncoding("", "");
		assertEquals(1, result.size());
	}

	@Test
	public void testFindEncoding01() {
		In in = new In("p2_sample.txt");
		String text = in.readLine();
		String peptide = in.readLine();
		ArrayList<String> expected = new ArrayList<>();
		while (in.hasNextLine())
			expected.add(in.readLine());
		in.close();
		Collections.sort(expected);
		ArrayList<String> actual = pe.findEncoding(text, peptide);
		Collections.sort(actual);
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindEncoding02() {
		In in = new In("peptide_encoding_data.txt");
		in.readLine();
		String text = in.readLine();
		String peptide = in.readLine();
		in.readLine();
		ArrayList<String> expected = new ArrayList<>();
		while (in.hasNextLine())
			expected.add(in.readLine());
		in.close();
		Collections.sort(expected);
		ArrayList<String> actual = pe.findEncoding(text, peptide);
		Collections.sort(actual);
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindEncoding03() {
		In in = new In("dataset_18_6.txt");
		String text = in.readLine();
		String peptide = in.readLine();
		in.close();
		ArrayList<String> actual = pe.findEncoding(text, peptide);
		Collections.sort(actual);
		System.out.println(actual.toString().replace(",", ""));
	}

	@Test
	public void testFindEncoding04() {
		In in = new In("B_brevis.txt");
		StringBuilder sb = new StringBuilder(6296436);
		while (in.hasNextLine())
			sb.append(in.readLine());
		String text = sb.toString();
		String peptide = "VKLFPWFNQY";
		in.close();
		ArrayList<String> actual = pe.findEncoding(text, peptide);
		Collections.sort(actual);
		System.out.println(actual.toString().replace(",", ""));
		System.out.println(actual.size());
	}
}
