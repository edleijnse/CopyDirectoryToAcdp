package leijnse.info;

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
        copyDirectory.copyFilesToACDP("/media/psf/MyDrive01/Annalis Bilder/BilderImport/AnnalisImport", "/media/psf/MyDrive01/BilderImport/Annalis/BilderExportBearbeitet3");

    }

}

