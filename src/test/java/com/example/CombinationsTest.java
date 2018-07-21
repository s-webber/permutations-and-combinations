package com.example;

import static java.util.stream.Collectors.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.*;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      td.add(new TestData(7, 2, //
    		  array(0, 1), //
    		  array(0, 2), //
    		  array(0, 3), //
    		  array(0, 4), //
    		  array(0, 5), //
    		  array(0, 6), //
    		  array(1, 2), //
    		  array(1, 3), //
    		  array(1, 4), //
    		  array(1, 5), //
    		  array(1, 6), //
    		  array(2, 3), //
    		  array(2, 4), //
    		  array(2, 5), //
    		  array(2, 6), //
    		  array(3, 4), //
    		  array(3, 5), //
    		  array(3, 6), //
    		  array(4, 5), //
    		  array(4, 6), //
    		  array(5, 6)));
      td.add(new TestData(7, 4, //
    		  array(0, 1, 2, 3), //
    		  array(0, 1, 2, 4), //
    		  array(0, 1, 2, 5), //
    		  array(0, 1, 2, 6), //
    		  array(0, 1, 3, 4), //
    		  array(0, 1, 3, 5), //
    		  array(0, 1, 3, 6), //
    		  array(0, 1, 4, 5), //
    		  array(0, 1, 4, 6), //
    		  array(0, 1, 5, 6), //
    		  array(0, 2, 3, 4), //
    		  array(0, 2, 3, 5), //
    		  array(0, 2, 3, 6), //
    		  array(0, 2, 4, 5), //
    		  array(0, 2, 4, 6), //
    		  array(0, 2, 5, 6), //
    		  array(0, 3, 4, 5), //
    		  array(0, 3, 4, 6), //
    		  array(0, 3, 5, 6), //
    		  array(0, 4, 5, 6), //
    		  array(1, 2, 3, 4), //
    		  array(1, 2, 3, 5), //
    		  array(1, 2, 3, 6), //
    		  array(1, 2, 4, 5), //
    		  array(1, 2, 4, 6), //
    		  array(1, 2, 5, 6), //
    		  array(1, 3, 4, 5), //
    		  array(1, 3, 4, 6), //
    		  array(1, 3, 5, 6), //
    		  array(1, 4, 5, 6), //
    		  array(2, 3, 4, 5), //
    		  array(2, 3, 4, 6), //
    		  array(2, 3, 5, 6), //
    		  array(2, 4, 5, 6), //
    		  array(3, 4, 5, 6)));

      return to2dArray(td);
   }

   @DataProvider
   private Integer[][] expectedSizes() {
	   return new Integer[][] {
		   {16, 1, 16},
		   {16, 2, 120},
		   {16, 3, 560},
		   {16, 4, 1820},
		   {16, 5, 4368},
		   {16, 6, 8008}, 
		   {16, 7, 11440},
		   {16, 8, 12870},
		   {16, 9, 11440},
		   {16, 10, 8008},
		   {16, 11, 4368},
		   {16, 12, 1820},
		   {16, 13, 560},
		   {16, 14, 120},
		   {16, 15, 16},
		   {16, 16, 1},
		   
		   {25, 5, 53130},
		   
		   {29, 26, 3654}
	   };
   }
   
   @Test(dataProvider = "expectedSizes")
   public void confirmExpectedSizeWithCheckingForDuplicates(int setSize, int subsetSize, int expectedNumberOfCombinations) {
	   Combinations c = new Combinations(setSize, subsetSize);
	   Set<Set<Integer>> combinationsFound = new HashSet<>();

	   int[] next;
	   while ((next = c.next())!=null) {
		   Set<Integer> nextAsSet = Arrays.stream(next).boxed().collect(toSet());
		   // confirm combination did not contain duplicates
		   assertEquals(nextAsSet.size(), subsetSize);
		   // confirm combinations had not already been found
		   assertTrue(combinationsFound.add(nextAsSet));
	   }

	   assertEquals(combinationsFound.size(), expectedNumberOfCombinations);
   }

   @DataProvider
   private Integer[][] expectedLargeSizes() {
	   return new Integer[][] {
		   {20, 8, 125970},
		   {20, 9, 167960},
		   {20, 10, 184756},
		   {20, 11, 167960},
		   {20, 12, 125970}
	   };
   }
   
   @Test(dataProvider = "expectedLargeSizes", invocationCount = 1, invocationTimeOut = 1000)
   public void confirmExpectedSizeWithoutCheckingForDuplicates(int setSize, int subsetSize, int expectedNumberOfCombinations) {
	   Combinations c = new Combinations(setSize, subsetSize);

	   int actualNumberOfCombinations = 0;
	   while (c.next()!=null) {
		   actualNumberOfCombinations++;
	   }

	   assertEquals(actualNumberOfCombinations, expectedNumberOfCombinations);
   }

   
   @Test(dataProvider = "combinations")
   public void checkCombinations(TestData testData) {
      Combinations c = new Combinations(testData.setSize, testData.subsetSize);
      for (int[] expected : testData.combinations) {
         assertNext(c, expected);
      }
      assertFinished(c);
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

   private void assertNext(Combinations c, int... expected) {
      int[] next = c.next();
      assertEquals(next, expected, Arrays.toString(next) + " not equal to " + Arrays.toString(expected));
   }

   private void assertFinished(Combinations c) {
      int[] next = c.next();
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
