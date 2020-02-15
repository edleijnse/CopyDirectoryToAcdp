package leijnse.info;

import java.math.BigInteger;

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

        AcdpAccessor acdpAccessor =new AcdpAccessor();
       // acdpAccessor.readAllRowsFromImageTable("/media/psf/MyDrive01/acdp/acdpImage/layout");
       acdpAccessor.readSomeRowsFromImageTableSomeKeywords("/media/psf/MyDrive01/acdp/acdpImage/layout", "-","-", BigInteger.valueOf(0),"stuffed, small, brown");
       System.out.println("-----------------------------------------------------------------------------------------------------");
       acdpAccessor.readSomeRowsFromImageTableAllKeywords("/media/psf/MyDrive01/acdp/acdpImage/layout", "-","-", BigInteger.valueOf(0),"stuffed, small, brown");
    }

}

