import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {


    public static String getLine(int lineNumber){
        String filePath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "arquivo.txt";
        return FileReader.readLine(filePath, lineNumber);
    }

    public static String readLine(String filePath, int numberLine) {

        String textLine = "";

        try {
            Stream<String> lines = Files.lines(Paths.get(filePath));
            textLine = lines.skip(numberLine-1).findFirst().orElse("NOT_FOUND");
        } catch (IOException e) {
            e.getStackTrace();
            System.out.println(e.getLocalizedMessage());

        }

        return textLine;
    }
}
