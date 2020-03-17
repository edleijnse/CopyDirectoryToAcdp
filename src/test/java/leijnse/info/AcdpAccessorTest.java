package leijnse.info;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AcdpAccessorTest {
    String DIR_EMPTEE = "src/data/ImageDB";
    String DIR_RUN = "src/data/ImageDBRun";
    String LAYOUT_EMPTEE = "src/data/ImageDB/layout";
    String LAYOUT_RUN = "src/data/ImageDBRun/layout";
    String MSACCESS_DB_EMPTEE = "src/data/MSAccess/AccessImageDatabaseEmpty.accdb";
    String MSACCESS_DB_RUN = "src/data/MSAccess/AccessImageDatabase.accdb";

    @Before
    public void setUp() throws Exception {
        AcdpAccessor testee = new AcdpAccessor();
        testee.replaceFolder(DIR_EMPTEE, DIR_RUN);
        testee.replaceFolder(MSACCESS_DB_EMPTEE,MSACCESS_DB_RUN);
    }

    @Test
    public void readImageTableColums() throws IOException {
        AcdpAccessor testee = new AcdpAccessor();
        String[] myKeywords =  new String[]{"building", "red", "sitting",
                "man", "yellow", "side", "street", "old",
                "door", "black", "fire"};
        byte[] myFile = Files.readAllBytes(Paths.get("src/data/copyDirectoryTest/acdpTest/AesthetikDesZerfalls/AesthetikDesZerfalls-100.jpg"));

        testee.writeRowToImageTable(LAYOUT_RUN, "directory1","file1", myKeywords,myFile);
        int anzColumns = testee.readImageTableColums(LAYOUT_RUN, "");
        assertEquals(4, anzColumns);
    }
    @Test
    public void copyAllRowsFromImageTableToAccessTest() throws IOException {
        AcdpAccessor testee = new AcdpAccessor();
        String[] myKeywords =  new String[]{"building", "red", "sitting",
                "man", "yellow", "side", "street", "old",
                "door", "black", "fire"};
        byte[] myFile = Files.readAllBytes(Paths.get("src/data/copyDirectoryTest/acdpTest/AesthetikDesZerfalls/AesthetikDesZerfalls-100.jpg"));

        testee.writeRowToImageTable(LAYOUT_RUN, "directory1","file1", myKeywords,myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory2","file2", myKeywords,myFile);

        String databaseURL = "jdbc:ucanaccess://src//data//MsAccess//AccessImageDatabase.accdb";

        int anzColumns = testee.copyAllRowsFromImageTableToAccess(LAYOUT_RUN, databaseURL);
        assertEquals(2, anzColumns);
    }

    @Test
    public void selectAllKeywordsFromImageTableTest() throws IOException {
        AcdpAccessor testee = new AcdpAccessor();

        String databaseURL = "jdbc:ucanaccess://src//data//MsAccess//AccessImageDatabase.accdb";

        int anzColumns = testee.selectAllKeywordsFromImageTable(databaseURL);
        // assertEquals(2, anzColumns);
    }


    @Test
    public void writeRowsToImageTableAndreadAllRowsFromImageTable() throws IOException {
        AcdpAccessor testee = new AcdpAccessor();
        String[] myKeywords =  new String[]{"building", "red", "sitting",
                "man", "yellow", "side", "street", "old",
                "door", "black", "fire"};
        byte[] myFile = Files.readAllBytes(Paths.get("src/data/copyDirectoryTest/acdpTest/AesthetikDesZerfalls/AesthetikDesZerfalls-100.jpg"));

        testee.writeRowToImageTable(LAYOUT_RUN, "directory1","file1", myKeywords,myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory2","file2", myKeywords,myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory3","file3", myKeywords,myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory1","file1", myKeywords,myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory2","file2", myKeywords,myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory3","file3", myKeywords,myFile);

        int anzRows = testee.readAllRowsFromImageTable(LAYOUT_RUN);
        assertEquals(6,anzRows);
    }
    @Test
    public void writeRowsToImageTableAndreadSomeRowsFromImageTable() throws IOException {
        AcdpAccessor testee = new AcdpAccessor();
        String[] myKeywords =  new String[]{"building", "red", "sitting",
                "man", "yellow", "side", "street", "old",
                "door", "black", "fire"};
        byte[] myFile = Files.readAllBytes(Paths.get("src/data/copyDirectoryTest/acdpTest/AesthetikDesZerfalls/AesthetikDesZerfalls-100.jpg"));

        testee.writeRowToImageTable(LAYOUT_RUN, "directory1","file1", myKeywords,myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory2","file2", myKeywords, myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory3","file1", myKeywords, myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory3","file2", myKeywords, myFile);
        testee.writeRowToImageTable(LAYOUT_RUN, "directory4","file4", myKeywords, myFile);

        List<ImageRow>  rowsWithAllKeywords = testee.selectFromImageTable(true,LAYOUT_RUN, "directory2","file1","building");
        assertEquals(5,rowsWithAllKeywords.size());
    }
}