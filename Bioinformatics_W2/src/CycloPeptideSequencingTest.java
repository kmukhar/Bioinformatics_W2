import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

public class CycloPeptideSequencingTest {
	CycloPeptideSequencing c = new CycloPeptideSequencing();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSequence01() {
		In in = new In("cycloseq_sample.txt");
		in.readLine();
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));

		in.readLine();
		ArrayList<String> expected = new ArrayList<>();
		for (String s : in.readLine().split(" "))
			expected.add(s);
		in.close();
		Collections.sort(expected);

		ArrayList<String> actual = c.sequence(input);
		Collections.sort(actual);
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testSequence02() {
		In in = new In("cycloseq_data.txt");
		in.readLine();
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));

		in.readLine();
		ArrayList<String> expected = new ArrayList<>();
		for (String s : in.readLine().split(" "))
			expected.add(s);
		in.close();
		Collections.sort(expected);

		ArrayList<String> actual = c.sequence(input);
		Collections.sort(actual);
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testSequence03() {
		In in = new In("dataset_22_4.txt");// leaderboard_spectrum_1.txt
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));
		in.close();

		ArrayList<String> actual = c.sequence(input);
		System.out.println(actual.toString().replace(",", ""));
	}

	@Test
	public void testSequence04() {
		In in = new In("leaderboard_spectrum_1.txt");
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));
		in.close();

		ArrayList<String> actual = c.sequence(input);
		System.out.println(actual.toString().replace(",", ""));
	}
	@Test
	public void testSequence05() {
		In in = new In("Tyrocidine_B1_theoretical_spectrum_1.txt");
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));
		in.close();

		ArrayList<String> actual = c.sequence(input);
		System.out.println(actual.toString().replace(",", ""));
	}
}
