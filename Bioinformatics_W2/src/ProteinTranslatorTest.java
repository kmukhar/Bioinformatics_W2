import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProteinTranslatorTest {

	private ProteinTranslator pt;

	@Before
	public void setUp() throws Exception {
		pt = new ProteinTranslator();
	}

	@Test
	public void testtranslate01() {
		assertEquals(
				"MAMAPRTEINSTRING",
				pt.translate("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA"));
	}

	@Test
	public void testtranslate02() {
		In in = new In("protein_translate_data.txt");
		String line2 = "";
		String line4 = "";
		in.readLine();
		line2 = in.readLine();
		in.readLine();
		line4 = in.readLine();
		in.close();

		assertEquals(line4, pt.translate(line2));
	}

	@Test
	public void testtranslate03() {
		In in = new In("dataset_18_3.txt");
		String line2 = "";
		line2 = in.readLine();
		in.close();
		System.out.println(pt.translate(line2));
	}
}
