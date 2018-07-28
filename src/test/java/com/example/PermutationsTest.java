package com.example;

import static com.example.TestUtils.array;
import static com.example.TestUtils.assertIllegalArgumentException;
import static com.example.TestUtils.assertNumberOfIterations;
import static com.example.TestUtils.assertResults;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PermutationsTest {
   @DataProvider
   private Integer[][] lessThanOne() {
      return new Integer[][] {{0}, {-1}, {Integer.MIN_VALUE}};

   }

   @Test(dataProvider = "lessThanOne")
   public void setSizeLessThanOne(int size) {
      assertIllegalArgumentException("The number of elements must be greater than 0 but was: " + size, () -> new Permutations(size));
   }

   @DataProvider
   private TestData[] permutations() {
      // @formatter:off
      return new TestData[] {
         new TestData(array(0)),
         new TestData(array(0, 1), array(1, 0)),
         new TestData(
            array(0, 1, 2),
            array(0, 2, 1),
            array(1, 0, 2),
            array(1, 2, 0),
            array(2, 0, 1),
            array(2, 1, 0)
         ), new TestData(
            array(0, 1, 2, 3),
            array(0, 1, 3, 2),
            array(0, 2, 1, 3),
            array(0, 2, 3, 1),
            array(0, 3, 1, 2),
            array(0, 3, 2, 1),
            array(1, 0, 2, 3),
            array(1, 0, 3, 2),
            array(1, 2, 0, 3),
            array(1, 2, 3, 0),
            array(1, 3, 0, 2),
            array(1, 3, 2, 0),
            array(2, 0, 1, 3),
            array(2, 0, 3, 1),
            array(2, 1, 0, 3),
            array(2, 1, 3, 0),
            array(2, 3, 0, 1),
            array(2, 3, 1, 0),
            array(3, 0, 1, 2),
            array(3, 0, 2, 1),
            array(3, 1, 0, 2),
            array(3, 1, 2, 0),
            array(3, 2, 0, 1),
            array(3, 2, 1, 0)
         ), new TestData(
            array(0, 1, 2, 3, 4),
            array(0, 1, 2, 4, 3),
            array(0, 1, 3, 2, 4),
            array(0, 1, 3, 4, 2),
            array(0, 1, 4, 2, 3),
            array(0, 1, 4, 3, 2),
            array(0, 2, 1, 3, 4),
            array(0, 2, 1, 4, 3),
            array(0, 2, 3, 1, 4),
            array(0, 2, 3, 4, 1),
            array(0, 2, 4, 1, 3),
            array(0, 2, 4, 3, 1),
            array(0, 3, 1, 2, 4),
            array(0, 3, 1, 4, 2),
            array(0, 3, 2, 1, 4),
            array(0, 3, 2, 4, 1),
            array(0, 3, 4, 1, 2),
            array(0, 3, 4, 2, 1),
            array(0, 4, 1, 2, 3),
            array(0, 4, 1, 3, 2),
            array(0, 4, 2, 1, 3),
            array(0, 4, 2, 3, 1),
            array(0, 4, 3, 1, 2),
            array(0, 4, 3, 2, 1),
            array(1, 0, 2, 3, 4),
            array(1, 0, 2, 4, 3),
            array(1, 0, 3, 2, 4),
            array(1, 0, 3, 4, 2),
            array(1, 0, 4, 2, 3),
            array(1, 0, 4, 3, 2),
            array(1, 2, 0, 3, 4),
            array(1, 2, 0, 4, 3),
            array(1, 2, 3, 0, 4),
            array(1, 2, 3, 4, 0),
            array(1, 2, 4, 0, 3),
            array(1, 2, 4, 3, 0),
            array(1, 3, 0, 2, 4),
            array(1, 3, 0, 4, 2),
            array(1, 3, 2, 0, 4),
            array(1, 3, 2, 4, 0),
            array(1, 3, 4, 0, 2),
            array(1, 3, 4, 2, 0),
            array(1, 4, 0, 2, 3),
            array(1, 4, 0, 3, 2),
            array(1, 4, 2, 0, 3),
            array(1, 4, 2, 3, 0),
            array(1, 4, 3, 0, 2),
            array(1, 4, 3, 2, 0),
            array(2, 0, 1, 3, 4),
            array(2, 0, 1, 4, 3),
            array(2, 0, 3, 1, 4),
            array(2, 0, 3, 4, 1),
            array(2, 0, 4, 1, 3),
            array(2, 0, 4, 3, 1),
            array(2, 1, 0, 3, 4),
            array(2, 1, 0, 4, 3),
            array(2, 1, 3, 0, 4),
            array(2, 1, 3, 4, 0),
            array(2, 1, 4, 0, 3),
            array(2, 1, 4, 3, 0),
            array(2, 3, 0, 1, 4),
            array(2, 3, 0, 4, 1),
            array(2, 3, 1, 0, 4),
            array(2, 3, 1, 4, 0),
            array(2, 3, 4, 0, 1),
            array(2, 3, 4, 1, 0),
            array(2, 4, 0, 1, 3),
            array(2, 4, 0, 3, 1),
            array(2, 4, 1, 0, 3),
            array(2, 4, 1, 3, 0),
            array(2, 4, 3, 0, 1),
            array(2, 4, 3, 1, 0),
            array(3, 0, 1, 2, 4),
            array(3, 0, 1, 4, 2),
            array(3, 0, 2, 1, 4),
            array(3, 0, 2, 4, 1),
            array(3, 0, 4, 1, 2),
            array(3, 0, 4, 2, 1),
            array(3, 1, 0, 2, 4),
            array(3, 1, 0, 4, 2),
            array(3, 1, 2, 0, 4),
            array(3, 1, 2, 4, 0),
            array(3, 1, 4, 0, 2),
            array(3, 1, 4, 2, 0),
            array(3, 2, 0, 1, 4),
            array(3, 2, 0, 4, 1),
            array(3, 2, 1, 0, 4),
            array(3, 2, 1, 4, 0),
            array(3, 2, 4, 0, 1),
            array(3, 2, 4, 1, 0),
            array(3, 4, 0, 1, 2),
            array(3, 4, 0, 2, 1),
            array(3, 4, 1, 0, 2),
            array(3, 4, 1, 2, 0),
            array(3, 4, 2, 0, 1),
            array(3, 4, 2, 1, 0),
            array(4, 0, 1, 2, 3),
            array(4, 0, 1, 3, 2),
            array(4, 0, 2, 1, 3),
            array(4, 0, 2, 3, 1),
            array(4, 0, 3, 1, 2),
            array(4, 0, 3, 2, 1),
            array(4, 1, 0, 2, 3),
            array(4, 1, 0, 3, 2),
            array(4, 1, 2, 0, 3),
            array(4, 1, 2, 3, 0),
            array(4, 1, 3, 0, 2),
            array(4, 1, 3, 2, 0),
            array(4, 2, 0, 1, 3),
            array(4, 2, 0, 3, 1),
            array(4, 2, 1, 0, 3),
            array(4, 2, 1, 3, 0),
            array(4, 2, 3, 0, 1),
            array(4, 2, 3, 1, 0),
            array(4, 3, 0, 1, 2),
            array(4, 3, 0, 2, 1),
            array(4, 3, 1, 0, 2),
            array(4, 3, 1, 2, 0),
            array(4, 3, 2, 0, 1),
            array(4, 3, 2, 1, 0)
         )
      };
      // @formatter:on
   }

   @Test(dataProvider = "permutations")
   public void checkPermutations(TestData testData) {
      Permutations p = new Permutations(testData.setSize);
      assertResults(p, testData.expectations);
   }

   @DataProvider
   private Integer[][] expectedSizes() {
      // @formatter:off
      return new Integer[][] {
         {6, 720},
         {7, 5040},
         {8, 40_320},
         {9, 362_880},
         {10, 3_6288_00},
         {11, 39_916_800}
      };
      // @formatter:on
   }

   @Test(dataProvider = "expectedSizes", invocationCount = 1, invocationTimeOut = 3000)
   public void confirmExpectedSizeWithoutCheckingForDuplicates(int setSize, int expectedNumberOfCombinations) {
      Permutations p = new Permutations(setSize);
      assertNumberOfIterations(p, expectedNumberOfCombinations);
   }

   @Test(invocationCount = 1, invocationTimeOut = 30000)
   public void confirmTwelveElements() {
      Permutations p = new Permutations(12);
      assertNumberOfIterations(p, 479_001_600);
   }

   private static class TestData {
      final int setSize;
      final int[][] expectations;

      public TestData(int[]... expectations) {
         this.setSize = expectations[0].length;
         this.expectations = expectations;
      }

      @Override
      public String toString() {
         return "setSize = " + setSize;
      }
   }
}
