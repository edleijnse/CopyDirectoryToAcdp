package leijnse.info;

import acdp.Row;
// import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
      /*  Main main = new Main();
        main.addRouteBuilder(new MyRouteBuilder());
        main.run(args);*/
        CopyDirectory copyDirectory = new CopyDirectory();
        // copyDirectory.copyFilesToACDP("/media/psf/MyDrive01/BilderImport/Annalis/Bilder nachbearbeitet", "/media/psf/MyDrive01/BilderImport/Annalis/BilderExportBearbeitet3");
        // copyDirectory.copyFilesToACDP("E:\\acdp\\Annalis", "E:\\acdp\\acdpImageAlbums\\layout");

        // copyDirectory.copyFilesDirectoryNameToACDP("/media/psf/MyDrive01/MyDrive01Original/bilderexport", "/media/psf/MyDrive01/acdp/acdpImageDirectories/layout");
        // copyDirectory.copyFilesDirectoryNameToACDP("E:\\acdp\\allAlbums", "E:\\acdp\\acdpImageAlbums\\layout");
        String mySubscriptionKey = new String(Files.readAllBytes(Paths.get("C:\\Users\\edlei\\OneDrive\\Finanzen\\Lizensen\\Microsoft\\keys\\subscriptionKey1")));

        copyDirectory.setSubscriptionKey(mySubscriptionKey);
        // copyDirectory.addVisionTagsToFiles("E:\\acdp\\vogels", "E:\\temp");


        AcdpAccessor acdpAccessor = new AcdpAccessor();
        // "jdbc:ucanaccess://src//data//MsAccess//AccessImageDatabase.accdb";
        // "jdbc:ucanaccess://E://acdp//AccessImageDatabase.accdb"

        acdpAccessor.copyAllRowsFromImageTableToAccess("E:\\acdp\\ImageDatabase\\run\\ImageDBRun\\layout","jdbc:ucanaccess://E://acdp//AccessImageDatabase.accdb");
        // acdpAccessor.readAllRowsFromImageTable("/media/psf/MyDrive01/acdp/acdpImageDirectories/layout");
        // List<ImageRow> imageWithSomeKeywords = acdpAccessor.selectFromImageTable(false,"/media/psf/MyDrive01/acdp/acdpImageDirectories/layout", "-","-", BigInteger.valueOf(0),"Hochzeit");
        // XStream xStream = new XStream();
        // System.out.println(xStream.toXML(imageWithSomeKeywords));
        System.out.println("-----------------------------------------------------------------------------------------------------");
        // List<ImageRow> imageWithAllKeywords = acdpAccessor.selectFromImageTable(false, "/media/psf/MyDrive01/acdp/acdpImageDirectories/layout", "-","-", BigInteger.valueOf(0),"Locarno,Valentina");
        // System.out.println(xStream.toXML(imageWithAllKeywords));
        System.out.println("-----------------------------------------------------------------------------------------------------");
        // ImageKeywordsList imageKeywordsList = acdpAccessor.selectAllKeywordsFromImageTable("jdbc:ucanaccess://E://acdp//AccessImageDatabase.accdb");
        // imageKeywordsList.getImageKeywordList().forEach(imageKeyword -> {
        //            System.out.println(imageKeyword.KEYWORD +", " +  imageKeyword.total);
        //        }
        // );
        // acdpAccessor.renameFilesInDirectory(new File("E:\\acdp\\vogels"));
    }

}

