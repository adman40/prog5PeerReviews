// Source code is decompiled from a .class file using FernFlower decompiler.
package src.assignment;

import java.io.IOException;

public interface BoggleDictionary extends Iterable {
   void loadDictionary(String var1) throws IOException;

   boolean isPrefix(String var1);

   boolean contains(String var1);
}
