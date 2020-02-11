package leijnse.info;

import org.junit.Before;
import org.junit.Test;

public class AcdpAccessorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void readImageTableColums() {
        AcdpAccessor testee = new AcdpAccessor();
        // testee.readTableColums("/home/parallels/IdeaProjects/acdpTest/layout", "");
        testee.readImageTableColums("src/data/acdpImage/layout", "");
    }
}