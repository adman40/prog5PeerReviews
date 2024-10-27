import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.util.*;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import src.assignment.*;
import src.assignment.BoggleGame.SearchTactic;

public class BlackBoxTests{

    static BoggleDictionary testDictionary;
    static BoggleGame testGame;

    // This will run ONCE before all other tests. It can be useful to setup up
    // global variables and anything needed for all of the tests.
    @BeforeAll
    static void setupAll() throws IOException {
        testDictionary = new GameDictionary();
        testDictionary.loadDictionary("words.txt");
        testGame = new GameManager();
    }// You can test GameManager here. You will want to make additional tests as well
    @Test
    void testGameManager() throws IOException {
        testGame.newGame(4, 2, "cubes.txt", testDictionary);
        char[][] testGameBoard = new char [3][3];
        testGameBoard[0] = new char[]{'a','b','a'};
        testGameBoard[1] = new char[]{'z','c','u'};
        testGameBoard[2] = new char[]{'z','s','s'};
        testGame.setGame(testGameBoard);
        testGame.setSearchTactic(SearchTactic.SEARCH_DICT);
        Set<String> testSet = new HashSet<String>();
        testSet.add("scab");
        testSet.add("scuba");
        testSet.add("suba");
        testSet.add("abacus");
        testSet.add("cuss");
        testSet.add("buss");
        assertEquals(testSet, testGame.getAllWords());
        testGame.setSearchTactic(SearchTactic.SEARCH_BOARD);
        assertEquals(testSet, testGame.getAllWords());
        List<Point> testList = new ArrayList<Point>();
        testGame.addWord("buss", 0);
        testList.add(new Point(0,1));
        testList.add(new Point(1,2));
        testList.add(new Point(2,1));
        testList.add(new Point(2,2));
        assertEquals(testList, testGame.getLastAddedWord());
    }

    // Tests invalid GameDictionary
    @Test
    void testBadGameDictionary() throws IOException {

        //test empty loadDictionary
        BoggleDictionary badDictionary = new GameDictionary();
        Iterator<String> badIterator;
        
        badDictionary.loadDictionary("spaghetti");
        badIterator = badDictionary.iterator();
        assertEquals(false, badIterator.hasNext());

        //test bad loadDictionary
        File emptyFile = new File("emptyWords.txt");
        File twoWordFile = new File("twoWords.txt");
        File notLexographicFile = new File("notLexo.txt");

        //empty
        FileWriter writer = new FileWriter(new File("emptyWords.txt"));
        writer.close();
        badDictionary.loadDictionary("emptyWords.txt");
        badIterator = badDictionary.iterator();
        assertEquals(false, badIterator.hasNext());

        //two words
        writer = new FileWriter(new File("twoWords.txt"));
        writer.write("hello there");
        writer.close();
        badDictionary.loadDictionary("twoWords.txt");
        badIterator = badDictionary.iterator();
        assertEquals(false, badIterator.hasNext());
        
        //not lexographic
        writer = new FileWriter(new File("notLexo.txt"));
        writer.write("z\na");
        writer.close();
        badDictionary.loadDictionary("notLexo.txt");
        badIterator = badDictionary.iterator();
        assertEquals(false, badIterator.hasNext());

        //delete temp files
        emptyFile.delete();
        twoWordFile.delete();
        notLexographicFile.delete();
    }


    // Tests GameDictionary
    @Test
    void testGameDictionary() throws IOException {


        //create scanner and iterator objects
        Scanner scan = new Scanner(new File("words.txt"));
        Iterator<String> testIterator = testDictionary.iterator();

        //confirm that the iterator follows the file
        while(testIterator.hasNext()){

            //check iterator contents
            String hold = scan.nextLine();
            assertEquals(hold, testIterator.next());

            //check prefix/contains methods
            for(int i=0; i<=hold.length(); i++){
                assertEquals(true, testDictionary.isPrefix(hold.substring(0, i)));
            }
            assertEquals(true, testDictionary.contains(hold));
        }

        //assert that the iterator did not stop early
        assertEquals(false, scan.hasNextLine());

        //assert that remove is not allowed for the iterator
        assertThrows(UnsupportedOperationException.class, () -> {
            testIterator.remove();
        });

        //close scanner
        scan.close();
    }


}