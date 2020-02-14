package leijnse.info;

import acdp.*;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.*;

import java.util.stream.*;
import java.util.*;


public class AcdpAccessor {
    public int readImageTableColums(String myLayout, String copyDirectory) {
        Path myPath = Paths.get(myLayout);
        int anzColumns = 0;

        try (Database db = Database.open(myPath, -1, false)) {
            Table myTable = db.getTable("Image");
            // System.out.println("Number of columns: " + myTable.getColumns().length);
            anzColumns = myTable.getColumns().length;
            // System.out.println("Number of rows: " + myTable.numberOfRows());
            //do something with myTable
        }
        return anzColumns;
    }

    public void writeRowToImageTable(String myLayout, String myDirectory, String myFile, BigInteger myId) {
        Path myPath = Paths.get(myLayout);

        try (Database db = Database.open(myPath, -1, false)) {
            Table myTable = db.getTable("Image");
            myTable.insert(myDirectory, myFile, myId);
            // System.out.println("Number of columns: " + myTable.getColumns().length);
            // System.out.println("Number of rows: " + myTable.numberOfRows());
            db.forceWrite();
            db.close();
            //do something with myTable
        }
    }

    public int readAllRowsFromImageTable(String myLayout) {
        Path myPath = Paths.get(myLayout);
        int anzahlRows = 0;

        try (Database db = Database.open(myPath, -1, false)) {
            Table myTable = db.getTable("Image");
            //  myTable.iterator().next();
            Column<?> myDirectoryColumn = myTable.getColumn("Directory");
            Column<?> myFileColumn = myTable.getColumn("File");
            Column<?> myID = myTable.getColumn("ID");


            Table.TableIterator myIterator = myTable.iterator(myDirectoryColumn, myFileColumn, myID);
            while (myIterator.hasNext()) {
                anzahlRows++;
                System.out.println("Row " + anzahlRows);
                Row myRow = myIterator.next();
                String fieldDirectory = (String) myRow.get(myDirectoryColumn);
                String fieldFile = (String) myRow.get(myFileColumn);
                BigInteger fieldID = (BigInteger) myRow.get(myID);
                System.out.println("field Directory: " + fieldDirectory);
                System.out.println("field File: " + fieldFile);
                System.out.println("field ID: " + fieldID);

            }

            //do something with myTable
        }
        return anzahlRows;
    }

    public int readSomeRowsFromImageTable(String myLayout, String myDirectory, String myFile, BigInteger myId) {
        Path myPath = Paths.get(myLayout);
        final int[] anzahlRows = {0};


        try (Database db = Database.open(myPath, -1, false)) {
            Table myTable = db.getTable("Image");
            //  myTable.iterator().next();
            Column<?> myDirectoryColumn = myTable.getColumn("Directory");
            Column<?> myFileColumn = myTable.getColumn("File");
            Column<?> myID = myTable.getColumn("ID");


            Table.TableIterator myIterator = myTable.iterator(myDirectoryColumn, myFileColumn, myID);

            // see sample https://www.tutorialspoint.com/convert-an-iterator-to-stream-in-java

            Stream<Row> myStream = convertIterator(myIterator);
            Stream<Row> rowStream = myStream.filter(myRow -> {
                        String fieldDirectory = (String) myRow.get(myDirectoryColumn);
                        String fieldFile = (String) myRow.get(myFileColumn);
                        BigInteger fieldID = (BigInteger) myRow.get(myID);
                        if (fieldDirectory.contentEquals(myDirectory)) {
                            return true;
                        }
                        if (fieldFile.contentEquals(myFile)) {
                            return true;
                        }
                        int comparevalue = fieldID.compareTo(myId);
                        if (comparevalue == 0) {
                            return true;
                        }

                        return false;
                    }
            );
            rowStream.forEach(myRow -> {
                anzahlRows[0]++;
                System.out.println("Row " + anzahlRows[0]);
                String fieldDirectory = (String) myRow.get(myDirectoryColumn);
                String fieldFile = (String) myRow.get(myFileColumn);
                BigInteger fieldID = (BigInteger) myRow.get(myID);

                System.out.println("field Directory: " + fieldDirectory);
                System.out.println("field File: " + fieldFile);
                System.out.println("field ID: " + fieldID);
            });
        }

        return anzahlRows[0];
    }

    public void copyLayout(String fromLayout, String toLayout) throws IOException {
        Path sourcePath = Paths.get(fromLayout);
        Path destinationPath = Paths.get(toLayout);
        File directoryToPurge = new File(toLayout);
        purgeDirectory(directoryToPurge);
        copyFolder(sourcePath, destinationPath);

    }

    public void copyFolder(Path src, Path dest) throws IOException {
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
        for (File file : dir.listFiles()) {
            if (file.isDirectory())
                purgeDirectory(file);
            file.delete();
        }
    }

    public static <T> Stream<T>
    convertIterator(Iterator<T> iterator) {
        return StreamSupport.stream(((Iterable) () -> iterator).spliterator(), false);
    }

}
