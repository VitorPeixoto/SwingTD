package swingtd;

import java.io.IOException;

/**
 *
 * @author peixoto
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = Game.instance();
        game.setup("Map0");
        game.start();
    }
    
}
