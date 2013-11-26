import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class ConvolutionerTest {
	Convolutioner c;

	@Before
	public void setUp() throws Exception {
		c = new Convolutioner();
	}

	@Test
	public void testConvolute01() {
		In in = new In("convolution_sample.txt");// spectral_convolution_data.txt
		in.readLine();
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));

		in.readLine();
		ArrayList<Integer> exp = new ArrayList<>();
		for (String s : in.readLine().split(" "))
			exp.add(Integer.parseInt(s));
		in.close();
		Collections.sort(exp);
		ArrayList<Integer> actual = c.convolute(input);
		Collections.sort(actual);
		System.out.println(exp.toString());
		System.out.println(actual);
		assertEquals(exp, actual);
	}

	@Test
	public void testConvolute02() {
		In in = new In("spectral_convolution_data.txt");
		in.readLine();
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));

		in.readLine();
		ArrayList<Integer> exp = new ArrayList<>();
		for (String s : in.readLine().split(" "))
			exp.add(Integer.parseInt(s));
		in.close();
		Collections.sort(exp);
		ArrayList<Integer> actual = c.convolute(input);
		Collections.sort(actual);
		printLongString(exp);
		printLongString(actual);
		assertEquals(exp, actual);
	}

	@Test
	public void testConvolute03() {
		In in = new In("dataset_26_4.txt");
		String text = in.readLine();
		ArrayList<Integer> input = new ArrayList<>();
		for (String s : text.split(" "))
			input.add(Integer.parseInt(s));

		in.close();
		ArrayList<Integer> actual = c.convolute(input);
		Collections.sort(actual);
		printLongString(actual);
	}

	private void printLongString(ArrayList<Integer> actual) {
		StringBuilder sb = new StringBuilder(actual.toString().replace(",", ""));
		int start = 0;
		while (start < sb.length()) {
			int end = start + 80;
			end = end <= sb.length() ? end : sb.length();
			System.out.println(sb.substring(start, end));
			start += 80;
		}
	}
}
