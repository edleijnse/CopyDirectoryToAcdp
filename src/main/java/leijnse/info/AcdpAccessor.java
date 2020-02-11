package leijnse.info;
import acdp.Database;
import acdp.Table;

import java.nio.file.Path;
import java.nio.file.Paths;


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

}
