package com.example;

import static com.example.Utils.initArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Determine all subsets of a given size for a set of a given size. */
public final class Combinations implements Iterator<int[]> {
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

   @Override
   public int[] next() {
      if (!hasNext()) {
         throw new NoSuchElementException();
      }

      int[] copy = Arrays.copyOf(current, current.length);

      if (current[subsetSize - 1] != setSize - 1) {
         // increment last element of array if not already set to maximum value
         current[subsetSize - 1]++;
      } else if (current[0] == setSize - subsetSize) {
         // if the first element of the array is already at its maximum value then there are no more combinations
         current = null;
      } else {
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

   @Override
   public boolean hasNext() {
      return current != null;
   }
}
