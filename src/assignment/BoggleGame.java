// Source code is decompiled from a .class file using FernFlower decompiler.
package src.assignment;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface BoggleGame {
    public enum SearchTactic {
    SEARCH_BOARD,
    SEARCH_DICT;
    }
   SearchTactic SEARCH_DEFAULT = SearchTactic.SEARCH_BOARD;

   void newGame(int var1, int var2, String var3, BoggleDictionary var4) throws IOException;

   char[][] getBoard();

   int addWord(String var1, int var2);

   List getLastAddedWord();

   void setGame(char[][] var1);

   Collection getAllWords();

   void setSearchTactic(SearchTactic var1);

   int[] getScores();
}
