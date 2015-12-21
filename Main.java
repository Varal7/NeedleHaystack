import java.io.File;
import java.nio.ByteBuffer;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;

public class Main {

    public static void main (String[] args) {
        String hFileName = "source.txt"; //Fichier texte à parcourir
        String nFileName = "query.txt"; //Chaîne à rechercher
        if (args.length >=1) {
            hFileName = args[0];
        }
        if (args.length >=2) {
            hFileName = args[1];
        }

        char[] haystack  = readFile(hFileName);
    }

    static char[] readFile(String fileName) {
           /**
            * Reads file and returns array of chars
            **/
        File file = new File(fileName);
        long size = file.length();
        ByteBuffer buffer;
        try {
        buffer = new FileInputStream(file).getChannel().map(MapMode.READ_ONLY, 0, size);
        } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
        }
        CharBuffer charBuffer = Charset.defaultCharset().decode(buffer);
        char[] charArray = new char[charBuffer.limit()];
        charBuffer.get(charArray);
        return charArray;
        }
}

