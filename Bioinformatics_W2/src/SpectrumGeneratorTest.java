import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

public class SpectrumGeneratorTest {

	static SpectrumGenerator sg;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sg = new SpectrumGenerator();
	}

	@Test
	public void testSpectrumGenerator() {
		assertEquals(20, sg.massMap.size());
		assertEquals(sg.massMap.get("G").intValue(), 57);
		assertEquals(sg.massMap.get("A").intValue(), 71);
		assertEquals(sg.massMap.get("S").intValue(), 87);
		assertEquals(sg.massMap.get("P").intValue(), 97);
		assertEquals(sg.massMap.get("V").intValue(), 99);
		assertEquals(sg.massMap.get("T").intValue(), 101);
		assertEquals(sg.massMap.get("C").intValue(), 103);
		assertEquals(sg.massMap.get("I").intValue(), 113);
		assertEquals(sg.massMap.get("L").intValue(), 113);
		assertEquals(sg.massMap.get("N").intValue(), 114);
		assertEquals(sg.massMap.get("D").intValue(), 115);
		assertEquals(sg.massMap.get("K").intValue(), 128);
		assertEquals(sg.massMap.get("Q").intValue(), 128);
		assertEquals(sg.massMap.get("E").intValue(), 129);
		assertEquals(sg.massMap.get("M").intValue(), 131);
		assertEquals(sg.massMap.get("H").intValue(), 137);
		assertEquals(sg.massMap.get("F").intValue(), 147);
		assertEquals(sg.massMap.get("R").intValue(), 156);
		assertEquals(sg.massMap.get("Y").intValue(), 163);
		assertEquals(sg.massMap.get("W").intValue(), 186);
	}

	@Test
	public void testGenerateSpectrum01() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(0);
		assertEquals(expected, sg.generateSpectrum(""));
	}

	@Test
	public void testGenerateSpectrum02() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(0);
		expected.add(99);
		assertEquals(expected, sg.generateSpectrum("V"));
	}

	@Test
	public void testGenerateSpectrum03() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(0);
		expected.add(71);
		expected.add(87);
		expected.add(158);
		assertEquals(expected, sg.generateSpectrum("SA"));
	}

	@Test
	public void testGenerateSpectrum04() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(0);
		expected.add(71);
		expected.add(87);
		expected.add(137);
		expected.add(158);
		expected.add(208);
		expected.add(224);
		expected.add(295);
		assertEquals(expected, sg.generateSpectrum("ASH"));
	}

	@Test
	public void testGenerateSpectrum05() {
		In in = new In("sample_spectrum_data.txt");
		in.readLine();
		String text = in.readLine();
		in.readLine();
		ArrayList<Integer> expected = new ArrayList<>();
		String[] vals = in.readLine().split(" ");
		in.close();
		for (String s : vals) 
			expected.add(Integer.parseInt(s));
		Collections.sort(expected);

		ArrayList<Integer> actual = sg.generateSpectrum(text);		
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testGenerateSpectrum06() {
		In in = new In("theoretical_spectrum_data.txt");
		in.readLine();
		String text = in.readLine();
		in.readLine();
		ArrayList<Integer> expected = new ArrayList<>();
		String[] vals = in.readLine().split(" ");
		in.close();
		for (String s : vals) 
			expected.add(Integer.parseInt(s));
		Collections.sort(expected);

		ArrayList<Integer> actual = sg.generateSpectrum(text);		
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testGenerateSpectrum07() {
		In in = new In("dataset_20_3.txt");
		String text = in.readLine();
		ArrayList<Integer> actual = sg.generateSpectrum(text);		
		System.out.println(actual.toString().replace(",",""));
	}
}
