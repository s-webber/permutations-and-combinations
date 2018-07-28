package com.example;

class Utils {
   static int[] initArray(int size) {
      int[] result = new int[size];
      for (int i = 0; i < size; i++) {
         result[i] = i;
      }
      return result;
   }
}
