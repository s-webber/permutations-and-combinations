package com.example;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class TestUtils {
   static void assertNumberOfIterations(Iterator<?> i, int expectedNumberOfIterations) {
      int actualNumberOfIterations = 0;
      while (i.hasNext()) {
         i.next();
         actualNumberOfIterations++;
      }

      assertEquals(actualNumberOfIterations, expectedNumberOfIterations);
   }

   static void assertResults(Iterator<int[]> i, int[]... expectations) {
      for (int[] expected : expectations) {
         assertNext(i, expected);
      }
      assertFinished(i);
   }

   static void assertNext(Iterator<int[]> i, int... expected) {
      assertTrue(i.hasNext());
      int[] next = i.next();
      assertEquals(next, expected, Arrays.toString(next) + " not equal to " + Arrays.toString(expected));
   }

   static void assertFinished(Iterator<int[]> i) {
      assertFalse(i.hasNext());
      assertException(NoSuchElementException.class, null, () -> i.next());
   }

   static void assertIllegalArgumentException(String expectedMessage, Runnable runnable) {
      assertException(IllegalArgumentException.class, expectedMessage, runnable);
   }

   static void assertException(Class<? extends Throwable> expectedClass, String expectedMessage, Runnable runnable) {
      try {
         runnable.run();
         fail("Expected " + expectedClass.getName() + " with message: " + expectedMessage);
      } catch (Throwable actual) {
         assertSame(actual.getClass(), expectedClass);
         assertEquals(actual.getMessage(), expectedMessage);
      }
   }

   static int[] array(int... values) {
      return values;
   }
}
