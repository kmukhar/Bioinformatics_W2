import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PeptideCounterTest {
	PeptideCounter pc;

	@Before
	public void setUp() throws Exception {
		pc = new PeptideCounter();
	}

	@Test
	public void testCountSample() {
		pc.init(1024);
		Assert.assertEquals(0x36cf214a3L, pc.count(1024));
	}

	@Test
	public void testCountDataset_21_2() {
		pc.init(1239);
		System.out.println("1239: " + pc.count(1239));
	}

	@Test
	public void testCountTyrocidineB1() {
		pc.init(1322);
		System.out.println("1322: " + pc.count(1322));
	}

	@Test
	public void testCountLinearRegression() {
		for (int i = 0; i <= 2000; i += 100) {
			pc.init(i);
			System.out.println(i+"\t" + pc.count(i));
		}
	}

	// @Test
	public void testCountBigN01() {
		pc.init(10000);
		System.out.println("10000 \t" + pc.count(10000));
	}

	@Test
	public void testCount01() {
		pc.init(50);
		Assert.assertEquals(0L, pc.count(50));
	}

	@Test
	public void testCount02() {
		pc.init(114);
		Assert.assertEquals(2L, pc.count(114));
	}

	@Test
	public void testCount03() {
		pc.init(113);
		Assert.assertEquals(1L, pc.count(113));
	}

	@Test
	public void testCount04() {
		pc.init(116);
		Assert.assertEquals(0L, pc.count(116));
	}

	@Test
	public void testCount05() {
		pc.init(128);
		Assert.assertEquals(3L, pc.count(128));
	}

	@Test
	public void testCount06() {
		pc.init(300);
		Assert.assertEquals(87L, pc.count(300));
	}

	@Test
	public void testCount07() {
		pc.init(250);
		Assert.assertEquals(6L, pc.count(250));
	}
}
