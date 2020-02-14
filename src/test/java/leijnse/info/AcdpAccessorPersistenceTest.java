package leijnse.info;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;

public class AcdpAccessorPersistenceTest {

    @Before
    public void setUp() throws Exception {
        AcdpAccessor testee = new AcdpAccessor();
        testee.copyLayout("src/data/acdpRunPersistence", "src/data/acdpRun");
    }

    @Test
    public void readImageTableColums() {
        AcdpAccessor testee = new AcdpAccessor();
        int anzColumns = testee.readImageTableColums("src/data/acdpRun/layout", "");
        assertEquals(3, anzColumns);
    }

    @Test
    public void readAllRowsFromImageTable() {
        AcdpAccessor testee = new AcdpAccessor();
        int anzRows = testee.readAllRowsFromImageTable("src/data/acdpRun/layout");
        assertEquals(6,anzRows);
    }
    @Test
    public void readSomeRowsFromImageTable() {
        AcdpAccessor testee = new AcdpAccessor();
        int anzRows = testee.readSomeRowsFromImageTable("src/data/acdpRun/layout", "directory2","file1", BigInteger.valueOf(4));
        assertEquals(4,anzRows);
    }
}