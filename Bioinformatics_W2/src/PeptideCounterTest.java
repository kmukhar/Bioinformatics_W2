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
	public void testCount() {
		pc.init(1024);
		Assert.assertEquals(0x36cf214a3L, pc.count(1024));
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
		Assert.assertEquals(2L, pc.count(113));
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
}
