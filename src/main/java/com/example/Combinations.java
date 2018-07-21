package com.example;

import java.util.Arrays;

/** Determine all subsets of a given size for a set of a given size. */
public class Combinations {
   private final int setSize;
   private final int subsetSize;
   private int[] current;

   /**
    * @param setSize the size of the set to find subsets of
    * @param subsetSize the size of the subsets to find
    */
   Combinations(int setSize, int subsetSize) {
      if (subsetSize < 1) {
         throw new IllegalArgumentException("The size of the subset must be greater than 0 but was: " + subsetSize);
      }
      if (setSize < 1) {
         throw new IllegalArgumentException("The size of the set must be greater than 0 but was: " + setSize);
      }
      if (setSize < subsetSize) {
         throw new IllegalArgumentException("The size of the subset must be less than or equal to the size of the set but: " + setSize + " < " + subsetSize);
      }
      this.setSize = setSize;
      this.subsetSize = subsetSize;
      this.current = initArray(subsetSize);
   }

   private static int[] initArray(int size) {
      int[] result = new int[size];
      for (int i = 0; i < size; i++) {
         result[i] = i;
      }
      return result;
   }

   /** @return the next combination, or {@code null} if all combinations have already been found */
   int[] next() {
      if (current == null) {
         // no more combinations
         return null;
      }

      int[] copy = Arrays.copyOf(current, current.length);

      if (current[subsetSize - 1] != setSize - 1) {
         // increment last element of array if not already set to maximum value
         current[subsetSize - 1]++;
      } else if (current[0] == setSize - subsetSize) {
         // if the first element of the array is already at its maximum value then there are no more combinations
         current = null;
      } else {
         // working back from the penultimate element in the array,
         // find an an element whose value is one less that the
         int i = subsetSize - 2;
         while (current[i] + 1 == current[i + 1]) {
            i--;
         }
         current[i]++;
         i++;
         while (i < subsetSize) {
            current[i] = current[i - 1] + 1;
            i++;
         }
      }

      return copy;
   }
}
