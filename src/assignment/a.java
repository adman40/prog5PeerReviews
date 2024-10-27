// Source code is decompiled from a .class file using FernFlower decompiler.
package src.assignment;

import java.util.HashMap;

final class a {
   private HashMap a = new HashMap();
   private boolean b = false;

   public a() {
   }

   public final a a(char var1) {
      return (a)this.a.get(var1);
   }

   public final void b(char var1) {
      if (this.a.get(var1) == null) {
         this.a.put(var1, new a());
      }

   }

   public final void a(boolean var1) {
      this.b = true;
   }

   public final boolean a() {
      return this.b;
   }
}
