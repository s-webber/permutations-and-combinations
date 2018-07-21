package com.example;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CombinationsTest {
   @DataProvider
   private Integer[][] lessThanOne() {
      return new Integer[][] {{0}, {-1}, {Integer.MIN_VALUE}};
   }

   @Test(dataProvider = "lessThanOne")
   public void setSizeLessThanOne(int setSize) {
      assertIllegalArgumentException("The size of the set must be greater than 0 but was: " + setSize, () -> new Combinations(setSize, 3));
   }

   @Test(dataProvider = "lessThanOne")
   public void subsetSizeLessThanOne(int subsetSize) {
      assertIllegalArgumentException("The size of the subset must be greater than 0 but was: " + subsetSize, () -> new Combinations(3, subsetSize));
   }

   @DataProvider
   private Integer[][] firstNumberLessThanSecond() {
      return new Integer[][] {{1, 2}, {3, 4}, {3, Integer.MAX_VALUE}};
   }

   @Test(dataProvider = "firstNumberLessThanSecond")
   public void setSizeLessThanSubsetSize(int setSize, int subsetSize) {
      assertIllegalArgumentException("The size of the subset must be less than or equal to the size of the set but: " + setSize + " < " + subsetSize,
                  () -> new Combinations(setSize, subsetSize));
   }

   @DataProvider
   private TestData[][] combinations() {
      List<TestData> td = new ArrayList<>();

      td.add(new TestData(1, 1, array(0)));
      td.add(new TestData(5, 5, array(0, 1, 2, 3, 4)));
      td.add(new TestData(2, 1, array(0), array(1)));
      td.add(new TestData(4, 1, array(0), array(1), array(2), array(3)));
      td.add(new TestData(3, 2, array(0, 1), array(0, 2), array(1, 2)));
      td.add(new TestData(5, 4, array(0, 1, 2, 3), array(0, 1, 2, 4), array(0, 1, 3, 4), array(0, 2, 3, 4), array(1, 2, 3, 4)));
      td.add(new TestData(5, 3, //
                  array(0, 1, 2), //
                  array(0, 1, 3), //
                  array(0, 1, 4), //
                  array(0, 2, 3), //
                  array(0, 2, 4), //
                  array(0, 3, 4), //
                  array(1, 2, 3), //
                  array(1, 2, 4), //
                  array(1, 3, 4), //
                  array(2, 3, 4)));

      return to2dArray(td);
   }

   @Test(dataProvider = "combinations")
   public void checkCombinations(TestData testData) {
      Combinations p = new Combinations(testData.setSize, testData.subsetSize);
      for (int[] expected : testData.combinations) {
         assertNext(p, expected);
      }
      assertFinished(p);
   }

   private void assertIllegalArgumentException(String expectedMessage, Runnable runnable) {
      assertException(IllegalArgumentException.class, expectedMessage, runnable);
   }

   private void assertException(Class<? extends Throwable> expectedClass, String expectedMessage, Runnable runnable) {
      try {
         runnable.run();
         fail("Expected " + expectedClass.getName() + " with message: " + expectedMessage);
      } catch (Throwable actual) {
         assertSame(actual.getClass(), expectedClass);
         assertEquals(actual.getMessage(), expectedMessage);
      }
   }

   private void assertNext(Combinations p, int... expected) {
      int[] next = p.next();
      assertEquals(next, expected, Arrays.toString(next) + " not equal to " + Arrays.toString(expected));
   }

   private void assertFinished(Combinations p) {
      int[] next = p.next();
      assertNull(next, "Not null: " + Arrays.toString(next));
   }

   private TestData[][] to2dArray(List<TestData> data) {
      TestData[][] result = new TestData[data.size()][1];
      for (int i = 0; i < data.size(); i++) {
         result[i] = new TestData[] {data.get(i)};
      }
      return result;
   }

   private int[] array(int... values) {
      return values;
   }

   private static class TestData {
      final int setSize;
      final int subsetSize;
      final int[][] combinations;

      public TestData(int setSize, int subsetSize, int[]... combinations) {
         this.setSize = setSize;
         this.subsetSize = subsetSize;
         this.combinations = combinations;
      }

      @Override
      public String toString() {
         return "setSize = " + setSize + " subsetSize = " + subsetSize;
      }
   }
}
