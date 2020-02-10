package leijnse.info;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AcdpAccessorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void readTableColums() {
        AcdpAccessor testee = new AcdpAccessor();
        // testee.readTableColums("/home/parallels/IdeaProjects/acdpTest/layout", "");
        testee.readTableColums("src/data/acdpTest/layout", "");
    }
}