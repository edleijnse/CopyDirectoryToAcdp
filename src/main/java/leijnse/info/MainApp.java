package leijnse.info;

import acdp.Row;
import com.thoughtworks.xstream.XStream;

import java.math.BigInteger;
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
        // copyDirectory.copyFilesToACDP("/media/psf/MyDrive01/Annalis Bilder/BilderExportBearbeitet/Annalis", "/media/psf/MyDrive01/acdp/acdpImage/layout");

        AcdpAccessor acdpAccessor = new AcdpAccessor();
        // acdpAccessor.readAllRowsFromImageTable("/media/psf/MyDrive01/acdp/acdpImage/layout");
        List<ImageRow> imageWithSomeKeywords = acdpAccessor.selectFromImageTable(false,"/media/psf/MyDrive01/acdp/acdpImage/layout", "-","-", BigInteger.valueOf(0),"building, black, door, graffiti");
        XStream xStream = new XStream();
        System.out.println(xStream.toXML(imageWithSomeKeywords));
        System.out.println("-----------------------------------------------------------------------------------------------------");
        List<ImageRow> imageWithAllKeywords = acdpAccessor.selectFromImageTable(true, "/media/psf/MyDrive01/acdp/acdpImage/layout", "-","-", BigInteger.valueOf(0),"building, black, door, graffiti");
        System.out.println(xStream.toXML(imageWithAllKeywords));
        System.out.println("-----------------------------------------------------------------------------------------------------");

    }

}

