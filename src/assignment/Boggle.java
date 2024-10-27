// Source code is decompiled from a .class file using FernFlower decompiler.
package src.assignment;

import src.assignment.BoggleGame.SearchTactic;
import java.awt.Point;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Boggle {
   public Boggle() {
   }

   public static void main(String[] var0) {
      try {
         GameManager var13 = new GameManager();
         GameDictionary var1;
         (var1 = new GameDictionary()).loadDictionary("words.txt");
         Scanner var2 = new Scanner(System.in);
         String var3 = "cubes.txt";
         boolean var4 = true;

         while(var4) {
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
               System.out.print("Enter the number of players: ");
               String var7;
               if (a(var7 = var2.nextLine().trim())) {
                  var6 = Integer.parseInt(var7);
                  var5 = false;
               } else {
                  System.out.println("Number of players must be an integer greater than 0");
               }
            }

            var13.newGame(4, var6, var3, var1);
            Collection var16 = var13.getAllWords();
            System.out.println("\nNew Board:");
            displayBoard(var13.getBoard(), (List)null);
            boolean[] var14 = new boolean[var6];
            boolean var8 = false;

            while(true) {
               int var9;
               int var11;
               while(!var8) {
                  var8 = true;

                  for(var9 = 0; var9 < var6; ++var9) {
                     if (!var14[var9]) {
                        var8 = false;
                        System.out.println("\nPlayer " + (var9 + 1) + "'s turn:");
                        System.out.print("Enter a word or press the ENTER key to give up: ");
                        String var10;
                        if ((var10 = var2.nextLine().trim().toUpperCase()).equals("")) {
                           var14[var9] = true;
                        } else {
                           if ((var11 = var13.addWord(var10, var9)) == 0) {
                              System.out.println(var13.getErrorMessage());
                           } else {
                              System.out.println("Added '" + var10 + "' for " + var11 + " points.");
                           }

                           if (var13.getOrderedWordsList().size() == var16.size()) {
                              var8 = true;
                              break;
                           }

                           displayBoard(var13.getBoard(), var13.getLastAddedWord());
                        }
                     }
                  }
               }

               System.out.println("\nGame Complete.");
               System.out.println("\nScores of the players:");

               for(var9 = 0; var9 < var6; ++var9) {
                  System.out.println("\nPlayer " + (var9 + 1) + " score:");
                  System.out.print(var13.getScores()[var9]);
               }

               System.out.println("\nComputer's turn...");
               var13.setSearchTactic(SearchTactic.SEARCH_BOARD);
               HashSet var17;
               (var17 = new HashSet(var16)).removeAll(var13.getOrderedWordsList());
               int var18 = 0;

               String var15;
               Iterator var19;
               for(var19 = var17.iterator(); var19.hasNext(); var18 += var15.length() - 3) {
                  var15 = (String)var19.next();
               }

               System.out.println("Computer found " + var17.size() + " words you missed:");
               var19 = var17.iterator();

               while(var19.hasNext()) {
                  var15 = (String)var19.next();
                  System.out.println(var15);
               }

               System.out.println("\nFinal Scores:");

               for(var11 = 0; var11 < var6; ++var11) {
                  System.out.println("Player " + (var11 + 1) + ": " + var13.getScores()[var11]);
               }

               System.out.println("Computer: " + var18);
               System.out.print("\nDo you want to play again? (yes/no): ");
               String var20;
               if (!(var20 = var2.nextLine().trim().toLowerCase()).equals("yes") && !var20.equals("y")) {
                  var4 = false;
               }
               break;
            }
         }

         var2.close();
      } catch (IOException var12) {
         System.out.println("An error occurred: " + var12.getMessage());
      }
   }

   public static void displayBoard(char[][] var0, List var1) {
      for(int var2 = 0; var2 < var0.length; ++var2) {
         for(int var3 = 0; var3 < var0[var2].length; ++var3) {
            char var4 = var0[var2][var3];
            if (var1 != null && var1.contains(new Point(var2, var3))) {
               System.out.print(" " + Character.toLowerCase(var4) + "  ");
            } else {
               System.out.print(" " + Character.toUpperCase(var4) + "  ");
            }
         }

         System.out.println();
      }

   }

   private static boolean a(String var0) {
      try {
         return Integer.parseInt(var0) > 0;
      } catch (Exception var1) {
         return false;
      }
   }
}
