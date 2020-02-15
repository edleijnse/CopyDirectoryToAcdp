package leijnse.info;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyDirectory {
    public void copyFilesToDirectory(String startsWithDirectory, String copyDirectory) {
        try {
            // https://www.codejava.net/java-core/concurrency/java-concurrency-understanding-thread-pool-and-executors
            // Better: completable Future https://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/


            System.out.println("handleDirectoryCopyFileToDatabase start: " + startsWithDirectory) ;
            Files.walk(Paths.get(startsWithDirectory))
                    .filter(p -> {
                        return ((p.toString().toLowerCase().endsWith(".cr2")) || (p.toString().toLowerCase().endsWith(".cr3")) || (p.toString().toLowerCase().endsWith(".jpg")));
                    })
                    .forEach(item -> {
                        File file = item.toFile();
                        if (file.isFile()) {
                            String sourceFile = "";
                            sourceFile = file.getAbsolutePath();
                            String destFile = copyDirectory + "/" + file.getName();
                            try {
                                copyFile(sourceFile,destFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            System.out.println("handleDirectoryCopyFile end");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("handleDirectoryCopyFile completed");
    }
    public void copyFilesToACDP(String startsWithDirectory, String copyDirectory) {
        try {
            // https://www.codejava.net/java-core/concurrency/java-concurrency-understanding-thread-pool-and-executors
            // Better: completable Future https://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/


            System.out.println("handleDirectoryCopyFileToDatabase start: " + startsWithDirectory) ;
            Files.walk(Paths.get(startsWithDirectory))
                    .filter(p -> {
                        return ((p.toString().toLowerCase().endsWith(".cr2")) ||
                                (p.toString().toLowerCase().endsWith(".cr3")) ||
                                (p.toString().toLowerCase().endsWith(".jpg_original")) ||
                                (p.toString().toLowerCase().endsWith(".jpg")));
                    })
                    .forEach(item -> {
                        File file = item.toFile();
                        if (file.isFile()) {
                            PictureMetaData pictureMetaData = new PictureMetaData();
                            ExtractPictureMetaData extractPictureMetaData = new ExtractPictureMetaData();
                            try {
                                pictureMetaData = extractPictureMetaData.getPictureMetaDataExif(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (pictureMetaData.getIPTC_KEYWORDS().isPresent()){
                                System.out.println("IPTC KEYWORDS: " + pictureMetaData.getIPTC_KEYWORDS().get());
                            }

                            String sourceFileAbsolutePath = "";
                            sourceFileAbsolutePath = file.getAbsolutePath();
                            String sourceFileName = file.getName();
                            String sourceParentName = file.getParent();
                            String destFile = copyDirectory + "/" + file.getName();
                            try {
                                System.out.println("absolute: " + sourceFileAbsolutePath + ", parent: " + sourceParentName  +", filename: " + sourceFileName);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            System.out.println("handleDirectoryCopyFile end");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("handleDirectoryCopyFile completed");
    }
    /**
     * Java 7 way to copy a file from one location to another
     * @param from
     * @param to
     * @throws IOException
     */
    public static void copyFile(String from, String to) throws IOException{
        Path src = Paths.get(from);
        Path dest = Paths.get(to);
        try {
            Files.copy(src, dest);
        } catch (FileAlreadyExistsException ex){
            // do nothing
            System.out.println("Datei bereits vorhanden: " +dest.toString());
        }
    }

}
