package leijnse.info;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AcdpAccessorTest {

    @Before
    public void setUp() throws Exception {
        AcdpAccessor testee = new AcdpAccessor();
        testee.copyLayout("src/data/acdpImage", "src/data/acdpRun");
    }

    @Test
    public void readImageTableColums() {
        AcdpAccessor testee = new AcdpAccessor();
        testee.readImageTableColums("src/data/acdpRun/layout", "");
    }
}