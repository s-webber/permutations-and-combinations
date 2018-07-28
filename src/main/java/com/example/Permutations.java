package com.example;

import static com.example.Utils.initArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Permutations implements Iterator<int[]> {
   private int[] current;
   private int[] indexes;

   public Permutations(int numberOfElements) {
      if (numberOfElements < 1) {
         throw new IllegalArgumentException("The number of elements must be greater than 0 but was: " + numberOfElements);
      }
      this.current = initArray(numberOfElements);
      this.indexes = Arrays.copyOf(current, current.length);
   }

   @Override
   public int[] next() {
      if (!hasNext()) {
         throw new NoSuchElementException();
      }

      int[] copy = Arrays.copyOf(current, current.length);

      current = calculateNext();

      return copy;
   }

   private int[] calculateNext() {
      for (int i = current.length - 2; i > -1; i--) {
         int h = higherValueOfSubsequentElements(i);
         if (h != -1) {
            for (int i2 = i; i2 < current.length; i2++) {
               indexes[current[i2]] = Integer.MAX_VALUE;
            }
            current[i] = h;
            indexes[h] = i;

            for (int i2 = i + 1; i2 < current.length; i2++) {
               current[i2] = lowestUnallocatedValue(i2);
               indexes[current[i2]] = i2;
            }

            return current;
         }
      }
      return null;
   }

   private int higherValueOfSubsequentElements(int idx) {
      for (int i = current[idx] + 1; i < current.length; i++) {
         if (indexes[i] > idx) {
            return i;
         }
      }
      return -1;
   }

   private int lowestUnallocatedValue(int idx) {
      for (int i = 0; i < current.length; i++) {
         if (indexes[i] > idx) {
            return i;
         }
      }
      throw new IllegalStateException();
   }

   @Override
   public boolean hasNext() {
      return current != null;
   }
}
