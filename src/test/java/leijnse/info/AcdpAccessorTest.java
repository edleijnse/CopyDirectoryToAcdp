package leijnse.info;

import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.math.BigInteger;
import static junit.framework.TestCase.assertEquals;

public class AcdpAccessorTest {

    @Before
    public void setUp() throws Exception {
        AcdpAccessor testee = new AcdpAccessor();
        testee.copyLayout("src/data/acdpImage", "src/data/acdpRun");
    }

    @Test
    public void readImageTableColums() {
        AcdpAccessor testee = new AcdpAccessor();
        int anzColumns = testee.readImageTableColums("src/data/acdpRun/layout", "");
        assertEquals(3, anzColumns);
    }

    @Test
    public void writeRowsToImageTableAndreadAllRowsFromImageTable() {
        AcdpAccessor testee = new AcdpAccessor();
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory1","file1", BigInteger.valueOf(1));
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory2","file2", BigInteger.valueOf(2));
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory3","file3", BigInteger.valueOf(3));
        int anzRows = testee.readAllRowsFromImageTable("src/data/acdpRun/layout");
        assertEquals(3,anzRows);
    }
    @Test
    public void writeRowsToImageTableAndreadSomeRowsFromImageTable() {
        AcdpAccessor testee = new AcdpAccessor();
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory1","file1", BigInteger.valueOf(1));
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory2","file2", BigInteger.valueOf(2));
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory3","file1", BigInteger.valueOf(3));
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory3","file2", BigInteger.valueOf(4));
        testee.writeRowToImageTable("src/data/acdpRun/layout", "directory4","file4", BigInteger.valueOf(5));


        int anzRows = testee.readSomeRowsFromImageTable("src/data/acdpRun/layout", "directory2","file1", BigInteger.valueOf(4));
        assertEquals(4,anzRows);
    }
}