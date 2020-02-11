package leijnse.info;
import acdp.Database;
import acdp.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;



public class AcdpAccessor {
    public void readImageTableColums(String myLayout, String copyDirectory) {
        Path myPath = Paths.get(myLayout);

        try (Database db = Database.open(myPath, 0,false)) {
            Table myTable = db.getTable("Image");
            System.out.println("Number of columns: " + myTable.getColumns().length);
            System.out.println("Number of rows: " + myTable.numberOfRows());
            //do something with myTable

        }
    }
    public void copyLayout(String fromLayout, String toLayout) throws IOException {
        Path sourcePath = Paths.get(fromLayout);
        Path destinationPath = Paths.get(toLayout);
        File directoryToPurge = new File(toLayout);
        purgeDirectory(directoryToPurge);
        copyFolder(sourcePath, destinationPath);

    }

    public  void copyFolder(Path src, Path dest) throws IOException {
        Files.walk(src)
                .forEach(source -> copy(source, dest.resolve(src.relativize(source))));
    }

    private void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void purgeDirectory(File dir) {
        for (File file: dir.listFiles()) {
            if (file.isDirectory())
                purgeDirectory(file);
            file.delete();
        }
    }

}
