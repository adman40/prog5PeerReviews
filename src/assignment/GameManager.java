// Source code is decompiled from a .class file using FernFlower decompiler.
package src.assignment;

import src.assignment.BoggleGame.SearchTactic;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameManager implements BoggleGame {
   private BoggleDictionary a;
   private BoggleGame.SearchTactic b;
   private List c;
   private char[][] d;
   private int[] e;
   private int f;
   private int g;
   private ArrayList h;
   private HashMap i;
   private String j;

   public GameManager() {
      this.b = SearchTactic.SEARCH_BOARD;
      this.h = new ArrayList();
      this.i = new HashMap();
      this.j = new String();
   }

   public int addWord(String var1, int var2) {
      int var3 = 0;
      ArrayList var10002 = this.h;
      Collection var7 = this.getAllWords();
      ArrayList var6 = var10002;
      boolean var10000;
      if (var1 == null) {
         this.j = "Invalid word: Word is null.";
         var10000 = false;
      } else if (var1.length() < 4) {
         this.j = "Invalid word: Word must be at least 4 words long.";
         var10000 = false;
      } else if (var1.length() > this.f * this.f) {
         this.j = "Invalid word: The length of word cannot exceed " + this.f * this.f + ".";
         var10000 = false;
      } else if (!var7.contains(var1)) {
         this.j = "Invalid word: Word does not exist on the board.";
         var10000 = false;
      } else if (var6.contains(var1)) {
         this.j = "Invalid word: Word has already been guessed.";
         var10000 = false;
      } else {
         var10000 = true;
      }

      if (var10000 && this.i.containsKey(var1)) {
         this.h.add(var1);
         var3 = 0 + var1.length() - 3;
      }

      int[] var8 = this.e;
      var8[var2] += var3;
      return var3;
   }

   public Collection getAllWords() {
      if (this.b == SearchTactic.SEARCH_BOARD) {
         for(int var1 = 0; var1 < this.f; ++var1) {
            for(int var2 = 0; var2 < this.f; ++var2) {
               this.a("", var1, var2, new ArrayList());
            }
         }
      } else if (this.b == SearchTactic.SEARCH_DICT) {
         Iterator var5 = this.a.iterator();

         while(var5.hasNext()) {
            String var6 = ((String)var5.next()).toUpperCase();

            for(int var3 = 0; var3 < this.f; ++var3) {
               for(int var4 = 0; var4 < this.f; ++var4) {
                  this.a(var6, var3, var4, new ArrayList(), 0);
               }
            }
         }
      }

      return this.i.keySet();
   }

   public char[][] getBoard() {
      return this.d;
   }

   public List getLastAddedWord() {
      return this.h.size() != 0 ? (List)this.i.get(this.h.get(this.h.size() - 1)) : null;
   }

   public int[] getScores() {
      return this.e;
   }

   public void newGame(int var1, int var2, String var3, BoggleDictionary var4) throws IOException {
      this.f = var1;
      this.g = var2;
      this.a = var4;
      this.c = new ArrayList();
      this.e = new int[var2];
      this.d = new char[var1][var1];
      BufferedReader var7 = new BufferedReader(new FileReader(var3));

      while((var3 = var7.readLine()) != null && !var3.equals("")) {
         this.c.add(var3);
      }

      for(int var8 = 0; this.c.size() < var1 * var1; ++var8) {
         this.c.add((String)this.c.get(var8));
      }

      Collections.shuffle(this.c);
      Random var9 = new Random();

      for(int var10 = 0; var10 < var1; ++var10) {
         for(int var5 = 0; var5 < var1; ++var5) {
            String var6 = (String)this.c.get((var10 << 2) + var5);
            this.d[var10][var5] = var6.charAt(var9.nextInt(6));
         }
      }

      var7.close();
   }

   public void setGame(char[][] var1) {
      this.d = var1;
   }

   public void setSearchTactic(BoggleGame.SearchTactic var1) {
      try {
         this.b = var1;
      } catch (Exception var2) {
         this.b = SearchTactic.SEARCH_BOARD;
      }
   }

   private void a(String var1, int var2, int var3, ArrayList var4) {
      if (var2 <= this.d.length - 1 && var3 <= this.d.length - 1 && var2 >= 0 && var3 >= 0) {
         var1 = var1 + this.d[var2][var3];
         if (!var4.contains(new Point(var2, var3))) {
            var4.add(new Point(var2, var3));
            if (!this.a.isPrefix(var1)) {
               var4.remove(var4.size() - 1);
            } else {
               if (this.a.contains(var1) && var1.length() >= 4 && !this.i.containsKey(var1)) {
                  ArrayList var5 = new ArrayList(var4);
                  this.i.put(var1, var5);
               }

               this.a(var1, var2 + 1, var3, var4);
               this.a(var1, var2 + 1, var3 + 1, var4);
               this.a(var1, var2 + 1, var3 - 1, var4);
               this.a(var1, var2 - 1, var3, var4);
               this.a(var1, var2 - 1, var3 + 1, var4);
               this.a(var1, var2 - 1, var3 - 1, var4);
               this.a(var1, var2, var3 + 1, var4);
               this.a(var1, var2, var3 - 1, var4);
               var4.remove(var4.size() - 1);
            }
         }
      }
   }

   private void a(String var1, int var2, int var3, ArrayList var4, int var5) {
      if (var2 <= this.d.length - 1 && var3 <= this.d.length - 1 && var2 >= 0 && var3 >= 0) {
         if (var1.charAt(var5) == this.d[var2][var3]) {
            if (!var4.contains(new Point(var2, var3))) {
               var4.add(new Point(var2, var3));
               if (var5 + 1 == var1.length()) {
                  if (!this.i.containsKey(var1)) {
                     ArrayList var6 = new ArrayList(var4);
                     this.i.put(var1, var6);
                  }

                  var4.remove(var4.size() - 1);
               } else {
                  this.a(var1, var2 + 1, var3, var4, var5 + 1);
                  this.a(var1, var2 + 1, var3 + 1, var4, var5 + 1);
                  this.a(var1, var2 + 1, var3 - 1, var4, var5 + 1);
                  this.a(var1, var2 - 1, var3, var4, var5 + 1);
                  this.a(var1, var2 - 1, var3 + 1, var4, var5 + 1);
                  this.a(var1, var2 - 1, var3 - 1, var4, var5 + 1);
                  this.a(var1, var2, var3 + 1, var4, var5 + 1);
                  this.a(var1, var2, var3 - 1, var4, var5 + 1);
                  var4.remove(var4.size() - 1);
               }
            }
         }
      }
   }

   public ArrayList getOrderedWordsList() {
      return this.h;
   }

   public String getErrorMessage() {
      return this.j;
   }
}
