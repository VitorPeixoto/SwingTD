package swingtd.maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import swingtd.Map;

/**
 *
 * @author peixoto
 */
public class MapLoader {
    private static BufferedReader reader;
    
    public static Map load(String fileName) throws FileNotFoundException, IOException {
        reader = new BufferedReader(new FileReader(MapLoader.class.getResource(fileName).getPath()));
        
        Pair<Integer, Integer> size = readPair();
        Pair<Integer, Integer> start = readPair();
        Pair<Integer, Integer> end = readPair();
        
        char[][] tiles = new char[size.getKey()][size.getValue()];
        for(int mapLine = 0; mapLine < size.getValue(); mapLine++) {
            tiles[mapLine] = readMapLine();
        }
        List<Character> movements = new ArrayList<>();

        for(char movement : reader.readLine().toCharArray()) {
            movements.add(movement);
        }
        
        return new Map(size.getKey(), size.getValue(), tiles, movements, start, end);
    }
    
    private static Pair<Integer,Integer> readPair() throws IOException {
        String line = reader.readLine();
        
        return new Pair<Integer, Integer>(Integer.parseInt(line.split("/")[0]), Integer.parseInt(line.split("/")[1]));
    }
    
    private static char[] readMapLine() throws IOException {
        String line = reader.readLine();
        
        return line.toCharArray();
    }
}
