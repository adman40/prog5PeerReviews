// Source code is decompiled from a .class file using FernFlower decompiler.
package src.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameDictionary implements BoggleDictionary {
   private a a = new a();
   private List b;

   public GameDictionary() {
   }

   public boolean contains(String var1) {
      var1 = var1.toLowerCase();
      a var2 = this.a;

      for(int var3 = 0; var3 < var1.length(); ++var3) {
         char var4 = var1.charAt(var3);
         if ((var2 = var2.a(var4)) == null) {
            return false;
         }
      }

      return var2.a();
   }

   public boolean isPrefix(String var1) {
      var1 = var1.toLowerCase();
      a var2 = this.a;

      for(int var3 = 0; var3 < var1.length(); ++var3) {
         char var4 = var1.charAt(var3);
         if ((var2 = var2.a(var4)) == null) {
            return false;
         }
      }

      return true;
   }

   public void loadDictionary(String var1) throws IOException {
      this.b = new ArrayList();
      BufferedReader var7 = new BufferedReader(new FileReader(var1));

      String var2;
      while((var2 = var7.readLine()) != null && !var2.equals("")) {
         String var4 = var2;
         a var3 = this.a;

         for(int var5 = 0; var5 < var4.length(); ++var5) {
            char var6 = var4.charAt(var5);
            var3.b(var6);
            var3 = var3.a(var6);
         }

         var3.a(true);
         if (var2.length() >= 4) {
            this.b.add(var2);
         }
      }

      var7.close();
   }

   public Iterator iterator() {
      return this.b.iterator();
   }
}
