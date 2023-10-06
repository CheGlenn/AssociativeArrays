import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

// import structures.AssociativeArray;
// import structures.KeyNotFoundException;

/**
 * Tests of the AssociativeArray class.
 *
 * @author CSC-207 2023Fa
 */
public class AssociativeArrayTests {

  // +---------------------+-----------------------------------------
  // | Tests by Sam Bigham |
  // +---------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Micah Cantor |
  // +-----------------------+
  
  /**
   * Micah says Let's test out Hoare's billion dollar mistake. Sam, I hope these tests are right.
   * 
   * Sam says null shouldn't be a value.
   */
  public void micahCantorTest1() {
    // values may be null
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set("NULL", null);
    assertEquals("{ NULL: null }", arr.toString());
    try {
      assertEquals(null, arr.get("NULL"));
    } catch (Exception e) {
      fail("Should not throw an exception when accessing null value");
    }
    arr.set("NULL", "null"); // Don't crash here!
  }

  /**
   * SamR removed this test because null need not be a valid key.
   * (It wasn't specified.)
   */
  public void micahCantorTest2() {
    // keys and values may be null
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set(null, null);
    assertEquals("{ null: null }", arr.toString());
    try {
      assertEquals(null, arr.get(null)); // Why not?
      arr.set(null, "not null"); // don't crash here either
    } catch (Exception e) {
      fail("Should not crash when getting/setting null key.");
    }
  }

  /**
   * Micah remains evil.
   *
   * SamR removed this test because the behavior on null inputs
   * is unspecified.
   */
  public void micahCantorEdge1() {
    // Uh oh.
    AssociativeArray<Object, Object> arr = new AssociativeArray<Object, Object>();
    Object value = new Object();
    arr.set(null, value);
    assertEquals("{ null: " + value.toString() + " }", arr.toString());
    arr.set(value, null);
    try {
      assertEquals(null, arr.get(value));
    } catch (Exception e) {
      fail("Should not crash when accessing Object key with null value.");
    }

    // Make sure removal doesn't break with null values
    Object value2 = new Object();
    arr.set(value2, null);
    arr.remove(value);
    arr.remove(null); // Should remove the pair (null, value)
    arr.remove(null); // Should do nothing
    try {
      assertEquals(null, arr.get(value2));
    } catch (Exception e) {
      fail("Should not crash when accessing Object key with null value after removing other keys.");
    }
  }


  // +------------------------+--------------------------------------
  // | Tests by Reed Colloton |
  // +------------------------+

  /**
   * A test for removing a pair when it's the last element.
   */
  @Test
  public void reedCollotonEdge01() throws Exception {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<>();
    arr.set(100, 4);
    assertEquals(4, arr.get(100));
    arr.remove(100);
    assertFalse(arr.hasKey(100));
    assertEquals(arr.size(), 0);
  } // reedCollotonEdge01()
  
  // +------------------+--------------------------------------------
  // | Tests by Pom Dao |
  // +------------------+

  // +--------------------------+------------------------------------
  // | Tests by Joshua Delarosa |
  // +--------------------------+

  // +-------------------+-------------------------------------------
  // | Tests by Jinny Eo |
  // +-------------------+

  // +---------------------------+-----------------------------------
  // | Tests by Kevin Fitzgerald |
  // +---------------------------+

  // +---------------------+-----------------------------------------
  // | Tests by Joyce Gill |
  // +---------------------+

  // +--------------------+------------------------------------------
  // | Tests by Che Glenn |
  // +--------------------+

  @Test
    public void cheGlennTest01(){ //test if array is cloned properly
      
      AssociativeArray<String, String> arr = new AssociativeArray<String, String>(); //create assoc array

      //set some KV pairs
      arr.set("a", "apple");
      arr.set("b", "banana");
      arr.set("c", "cucumber");

      //clone arr
      AssociativeArray <String, String> arr2 = arr.clone();

      try{
        assertEquals("apple", arr2.get("a")); //arr2 should have same val at key a

      } catch (Exception e) {
        fail ("Array was not properly cloned");
      }
    }

    @Test
    public void cheGlennTest02(){ //test if original array is affected when setting values of cloned array
      
      AssociativeArray<String, String> arr = new AssociativeArray<String, String>(); //create arr

      //set some vals
      arr.set("a", "apple");
      arr.set("b", "banana");
      arr.set("c", "cucumber");

      AssociativeArray <String, String> arr2 = arr.clone(); //clone
      
      //set a new KV Pair in arr2
      arr2.set("d", "diamond");

      try{
        assertEquals(false, arr.hasKey("d")); //og arr should not have new key
        assertEquals(true, arr2.hasKey("d")); //copy should
      } catch (Exception e) {
        fail ("Original array was changed when cloned array should have");
      }
    }

    @Test
    public void cheGlennEdge01(){ //check if the size of the array goes back to 0 after adding values and then removing them all

    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>(); //create new array
      for (int i = 0; i < 10; i++){
        arr.set(i, i+1); //set some values
        arr.remove(i); //delete them all
      }
      assertEquals(0, arr.size()); //check if size change is accurate
    }

  // +-------------------------+-------------------------------------
  // | Tests by Kevin Johanson |
  // +-------------------------+

  // +----------------------+----------------------------------------
  // | Tests by Chloe Kelly |
  // +----------------------+

  /**
   * Does toString() skip all empty values?
   *
   * SamR removed this test because it makes assumptions about the
   * order of values in the array.
   */
  public void chloeKellyTest1() {
    // Build Array
    AssociativeArray<String, String> testarr = new AssociativeArray<String, String>();
    // Set some values
    testarr.set("A", "Red");
    testarr.set("B", "Blue");
    testarr.set("C", "Green");
    try {
      assertEquals("Red", testarr.get("A"));
    } catch (Exception e) {
      fail("Array value could not be set to Red");
    } // try/catch Red
    try {
      assertEquals("Blue", testarr.get("B"));
    } catch (Exception e) {
      fail("Array value could not be set to Blue");
    } // try/catch Blue
    try {
      assertEquals("Green", testarr.get("C"));
    } catch (Exception e) {
      fail("Array value could not be set to Green");
    } // try/catch green

    assertEquals("{ A: Red, B: Blue, C: Green }", testarr.toString());
    // Remove the middle value
    testarr.remove("B");
    assertEquals("{ A: Red, C: Green }", testarr.toString());
  }// chloeKellyTest1()

  /**
   * Replace values at a given key
   */
  @Test
  public void chloeKellyTest2() {
    AssociativeArray<String, String> testarr = new AssociativeArray<String, String>();
    // Set the value
    testarr.set("A", "Red");
    try {
      assertEquals("Red", testarr.get("A"));
    } catch (Exception e) {
      fail("Array value could not be set to Red");
    } // try/catch Red
    // Replace the value
    testarr.set("A", "Yellow");
    // Check if it worked
    try {
      assertEquals("Yellow", testarr.get("A"));
    } catch (Exception e) {
      fail("Array value could not be set to Yellow");
    } // try/catch Red
  }// chloeKellyTest2()

  /**
   * Printing an empty array
   */
  @Test
  public void chloeKellyEdge1() {
    AssociativeArray<String, String> testarr = new AssociativeArray<String, String>();
    // Make sure it is empty
    assertEquals(0, testarr.size());
    assertEquals("{}", testarr.toString());
  }// chloeKellyEdge1()

  // +--------------------+------------------------------------------
  // | Tests by Hyeon Kim |
  // +--------------------+

  // +---------------------+-----------------------------------------
  // | Tests by Julian Kim |
  // +---------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Jayson Kunkel |
  // +-----------------------+

  /**
   * A series of tests of the size() method
   */
  @Test
  public void jaysonKunkelTest1(){
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();

    // empty array should have size 0
    assertEquals(0, arr.size());

    // array with one element should have size 1
    arr.set("0", "value");
    assertEquals(1, arr.size());

    // the default size is 16, so a full array should have size 16
    for(int i = 0; i < 15; i++){
      arr.set("" + i, "value");
    } // for
    assertEquals(16, arr.size());

    // we should now have 14 KVpairs
    arr.remove("2");
    arr.remove("4");
    assertEquals(14, arr.size());
    
  } // jaysonKunkelTest1


  /**
   * Testing that find works as intended
   */
  @Test
  public void jaysonKunkelTest2(){
    AssociativeArray<Integer, String> arr = new AssociativeArray<Integer, String>();

    // set some KVpairs
    for(int i = 0; i < 10; i++){
      arr.set(i, i + "mississippi");
    }

    // check that find works correctly for each index
    try {
      for(int i = 0; i < 10; i++){
        assertEquals(i, arr.find(i));
      }
    } catch (KeyNotFoundException e) {
      fail("Could not find key in array");
    }

    // should throw a KeyNotFoundException
    try{
      assertEquals(11, arr.find(11));
      fail("key 11 does not exist in the array");
    } catch (KeyNotFoundException e){
      // do nothing
    }

  } // jaysonKunkelTest2


  /**
   *  What happens if we try to expand the empty array?
   */
  @Test
  public void jaysonKunkelEdge1(){
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();

    // confirm the size of the empty array is 0
    assertEquals(0, arr.size());

    // since there are no pairs in the empty array, expand() should have no effect
    arr.expand();
    assertEquals(0, arr.size());

    // now lets confirm it works correctly
    arr.set("A", "Apple");
    // assertEquals(1, arr.length);
    // arr.expand();
    // assertEquals(2, arr.length);

  } // jaysonKunkelEdge1



  // +---------------------+-----------------------------------------
  // | Tests by Wenfei Lin |
  // +---------------------+

  /**
    * Checking cloned array expands properly
   */
  @Test
  public void wenfeiLinTest01() {
    // Create an array
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    // Set 16 KVPairs
    for (int i = 0; i <= 15; i++) {
      arr.set(i, i + 5);
    }

    // Clone original array
    AssociativeArray<Integer, Integer> clonedArr = arr.clone();
    // Set an additional KVPair to make cloned array expand
    clonedArr.set(20, 100);

    // Attempt to retrieve the 17 values from the 17 keys
    try {
      for (int i = 0; i <= 15; i++) {
        assertEquals(i + 5, clonedArr.get(i));
      }
      assertEquals(100, clonedArr.get(20));
    } catch (Exception e) {
    }
    assertEquals(17, clonedArr.size());
  } // wenfeiLinTest01()

  /**
   * Checking that the size doesn't change when setting a new value for an existing key
   */
  @Test
  public void wenfeiLinTest02() {
    // Create an array
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    // Create a KVPair with "popcorn" as the key
    arr.set("popcorn", "salted butter");
    // Check initial size after adding a KVPair
    assertEquals(1, arr.size());

    // Change the value associated with the key "popcorn" from "salted butter" to "caramel"
    arr.set("popcorn", "caramel");
    // Test new value was set
    try {
      assertEquals("caramel", arr.get("popcorn"));
    } catch (Exception e) {
      fail("Exception in call to get");
    }
    // Check that the size did not increment
    assertEquals(1, arr.size());
  } // wenfeiLinTest02()

  /**
   * Testing that no key exists in an empty array with hasKey
   */
  @Test
  public void wenfeiLinEdge01() {
    // Create an array
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    assertEquals(false, arr.hasKey("anything"));
  } // wenfeiLinEdge01()

  // +-----------------------+---------------------------------------
  // | Tests by Noah Mendola |
  // +-----------------------+

  // +----------------------+----------------------------------------
  // | Tests by John Miller |
  // +----------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Albert Okine |
  // +-----------------------+

  // +---------------------+-----------------------------------------
  // | Tests by Alma Ordaz |
  // +---------------------+

  // +-----------------------------+---------------------------------
  // | Tests by Samuel A. Rebelsky |
  // +-----------------------------+

  /**
   * A test of cloning.
   */
  @Test
  public void samuelRebelskyTest01() {
    // Build an array
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set("A", "Apple");
    try {
      assertEquals("Apple", arr.get("A"));
    } catch (Exception e) {
      fail("Original array does not contain expected value");
    }
    // Make a copy
    AssociativeArray<String, String> arr2 = arr.clone();
    // Make sure it contains the appropriate value
    try {
      assertEquals("Apple", arr2.get("A"));
    } catch (Exception e) {
      fail("Clone does not contain original value");
    } // try/catch
    // Change the original array
    arr.set("A", "aardvark");
    // Make sure we haven't changed the clone.
    try {
      assertEquals("Apple", arr2.get("A"));
    } catch (Exception e) {
      fail("Change to original changes clone.");
    }
    // Change the clone
    arr2.set("A", "Ant");
    // And look for values
    try {
      assertEquals("Ant", arr2.get("A"));
    } catch (Exception e) {
      fail("Cannot change clone");
    }
    try {
      assertEquals("aardvark", arr.get("A"));
    } catch (Exception e) {
      fail("Change to clone changes original");
    }
  } // samuelRebelskyTest01()

  /**
   * Can we successfully add a bunch of values? (Checks array expansion.)
   */
  @Test
  public void samuelRebelskyTest02() {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    // Add a bunch of values
    for (int i = 10; i < 50; i++) {
      arr.set(i, i * i);
    } // for
    try {
      for (int i = 49; i >= 10; i--) {
        assertEquals(i * i, arr.get(i));
      }
    } catch (Exception e) {
      fail("Exception in call to get");
    }
  } // samuelRebelskyTest02()

  /**
   * Do we get exceptions when grabbing a deleted value from the array?
   */
  @Test
  public void samuelRebelskyTest03() {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    // Add an element to the array
    arr.set("A", "Apple");
    // Make sure that it's there.
    try {
      assertEquals("Apple", arr.get("A"));
    } catch (KeyNotFoundException e) {
      fail("Could not set A to Apple");
    }
    // Remove it.
    arr.remove("A");
    // Make sure it's no longer there.
    try {
      // The following line should throw an exception
      arr.get("A");
      fail("Did not throw an exception");
    } catch (KeyNotFoundException e) {
      // Do nothing
    }
  } // samuelRebelskyTest03

  /**
   * Do we get exceptions when grabbing a value from the empty array.
   */
  @Test
  public void samuelRebelskyEdge01() {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    try {
      // The following line should throw an exception
      arr.get("A");
      fail("Did not throw an exception");
    } catch (KeyNotFoundException e) {
      // Do nothing
    }
  } // samuelRebelskyEdge01

  // +--------------------------+------------------------------------
  // | Tests by Maria Rodriguez |
  // +--------------------------+

 //Check to see if clone accurately sets a new value and has the same values as the previous array
 @Test
 public void mariaRodriguezTest01(){
   AssociativeArray<String, String> test1 = new AssociativeArray<String, String>();
   AssociativeArray<String,String> clone = new AssociativeArray<String,String>();
   test1.set("a", "apple");   
   test1.set("b", "balloon");
   clone = test1.clone();
   clone.set("c", "cartoon");

   try{
     assertEquals("cartoon", clone.get("c"));
     assertEquals("balloon", clone.get("b"));
     assertEquals("apple", clone.get("a"));

   } catch (KeyNotFoundException e) {}

   //the original array should not have a key that was added to the cloned array
   try {
     test1.get("c");
     fail("Did not throw an exception");
   } catch (Exception e) {
   }

 }//mariaRodriguezTest01

 //tests to see that associativeArray functions work as intended
 @Test
 public void mariaRodriguezTest02(){
   AssociativeArray<String, String> test2 = new AssociativeArray<String, String>();
   test2.set("a", "apple");   
   test2.set("b", "balloon");

   try{
     assertEquals("apple", test2.get("a"));
     assertEquals("balloon", test2.get("b"));
   } catch (KeyNotFoundException e) {}

   assertEquals(2, test2.size());
   assertEquals(true, test2.hasKey("b"));
   test2.remove("a");
   assertEquals(false, test2.hasKey("a"));
   assertEquals(1, test2.size());
 }//mariaRodriguezTest02

 //tests to make sure the code treats " " and "  " as two separate keys (tests empty space)
 @Test
 public void mariaRodriguezEdge01(){
   AssociativeArray<String, String> edge = new AssociativeArray<String, String>();
   edge.set(" ", "apple");   
   edge.set("  ", "balloon");

   try{
     assertEquals("apple", edge.get(" "));
     assertEquals("balloon", edge.get("  "));
   } catch (KeyNotFoundException e) {
  
   }
 }//mariaRodriguezEdge01

  // +-----------------------------+---------------------------------
  // | Tests by Gabriela Roznawska |
  // +-----------------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Shuta Shibue |
  // +-----------------------+
  @Test
  public void shutaShibueTest1() {
      // Build Array
      AssociativeArray<String, Integer> test1 = new AssociativeArray<String, Integer>();
      // Setting some values
      test1.set("CSC", 207);
      test1.set("BIO", 150);
      test1.set("STA", 209);
      try {
      assertEquals(150, test1.get("BIO"));
      } catch (Exception e) {
      fail("Value should be 150");
      } // try-catch
      assertEquals("{ CSC: 207, BIO: 150, STA: 209 }", test1.toString());
      // Remove the middle value
      test1.remove("CSC"); // should remove element
      assertEquals(2, test1.size);
      test1.remove("CSC"); // should do nothing
      assertEquals(2, test1.size);
  }// shutaShibueTest1()

  @Test
  public void shutaShibueTest2() {
      // Build Array
      AssociativeArray<String, String> test1 = new AssociativeArray<String, String>();
      // Setting some values
      test1.set("CSC", "207");
      test1.set("CSC", "161");
      test1.set("STA", "209");
      try {
      assertEquals("161", test1.get("CSC"));
      } catch (Exception e) {
      fail("Value should be 161");
      } // try-catch
      assertEquals("{ CSC: 161, STA: 209 }", test1.toString());
      // Remove the middle value
      test1.remove("CSC"); // should remove element
      assertEquals(1, test1.size);
      test1.remove("STA"); // should do nothing
      assertEquals(0, test1.size);
  }// shutaShibueTest2()

  @Test
  public void shutaShibueEdge() {
      // Build Array
      AssociativeArray<String, String> test1 = new AssociativeArray<String, String>();
      // Setting some values
      test1.set("", "207");
      try {
      assertEquals("207", test1.get(""));
      } catch (Exception e) {
      fail("Value should be 207");
      } // try-catch
      assertEquals("{ : 207 }", test1.toString());
      // Remove the middle value
  }// shutaShibueEdge()

  // +----------------------+----------------------------------------
  // | Tests by Madel Sibal |
  // +----------------------+

  // +------------------------------+--------------------------------
  // | Tests by Livia Stein Freitas |
  // +------------------------------+

  // +------------------------+--------------------------------------
  // | Tests by Tyrell Taylor |
  // +------------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Audrey Trinh |
  // +-----------------------+

  // +-------------------------+-----------------------------------------
  // | Tests by Rene Urias Jr. |
  // +-------------------------+

  /**
   * Test basic functionality of setting and getting values
   */
  @Test
  public void reneUriasTest01() {
    AssociativeArray<Integer, String> arr = new AssociativeArray<Integer, String>();
    arr.set(1, "One");
    arr.set(2, "Two");
    arr.set(3, "Three");

    try {
      assertEquals("One", arr.get(1));
      assertEquals("Two", arr.get(2));
      assertEquals("Three", arr.get(3));
    } catch (Exception e) {
      fail("Exception in call to get");
    }
  } // reneUriasTest01

  /**
   * Test array expansion and size calculation
   */
  @Test
  public void reneUriasTest02() {
    AssociativeArray<String, Integer> arr = new AssociativeArray<String, Integer>();

    // Add values to trigger expansion
    for (int i = 1; i <= 30; i++) {
      arr.set("Key" + i, i);
    }

    assertEquals(30, arr.size());

    try {
      for (int i = 1; i <= 30; i++) {
        assertEquals(i, (int) arr.get("Key" + i));
      }
    } catch (Exception e) {
      fail("Exception in call to get");
    }
  } // reneUriasTest02

  /**
   * Test removing a key and ensue it throws KeyNotFoundException on retrieval
   */
  @Test
  public void reneUriasTest03() {
    AssociativeArray<String, Double> arr = new AssociativeArray<String, Double>();
    arr.set("Pi", 3.14);

    try {
      assertEquals(3.14, arr.get("Pi"), 0.001);
    } catch (Exception e) {
      fail("Exception in call to get");
    }

    arr.remove("Pi");

    try {
      // The following line should throw KeyNotFoundException
      arr.get("Pi");
      fail("Did not throw KeyNotFoundException");
    } catch (KeyNotFoundException e) {
      // Do nothing
    }
  } // reneUriasTest03

  /**
   * Test setting and getting values in the associative array
   */
  @Test
  public void reneUriasTestSetAndGet() {
    AssociativeArray<String, Integer> array = new AssociativeArray<>();

    array.set("one", 1);
    array.set("two", 2);
    array.set("three", 3);

    try {
      assertEquals(1, array.get("one"));
      assertEquals(2, array.get("two"));
      assertEquals(3, array.get("three"));
    } catch (KeyNotFoundException e) {
      fail("Unexpected KeyNotFoundException: " + e.getMessage());
    }
  } // reneUriasTestSetAndGet

  /**
   * Test setting values and overriding existing values in the associative array
   */
  @Test
  public void reneUriasTestSetOverride() {
    AssociativeArray<String, String> array = new AssociativeArray<>();

    array.set("color", "red");

    try {
      assertEquals("red", array.get("color"));

      array.set("color", "blue"); // Overriding value for key "color"

      assertEquals("blue", array.get("color"));
    } catch (KeyNotFoundException e) {
      fail("Unexpected KeyNotFoundException: " + e.getMessage());
    }
  } // reneUriasTestSetOverride

  /**
   * Test checking if a key exists in the associative array
   */
  @Test
  public void reneUriasTestHasKey() {
    AssociativeArray<Character, Double> array = new AssociativeArray<>();

    array.set('A', 4.0);
    array.set('B', 3.5);

    assertTrue(array.hasKey('A'));
    assertTrue(array.hasKey('B'));
    assertFalse(array.hasKey('C')); // Key 'C' does not exists
  } // reneUriasTestHasKey

  /**
   * Test removing entires from the associative array
   */
  @Test
  public void reneUriasTestRemove() {
    AssociativeArray<Integer, String> array = new AssociativeArray<>();
    array.set(1, "one");
    array.set(2, "two");
    array.set(3, "three");

    array.remove(2); // Removing entry with key 2

    assertFalse(array.hasKey(2)); // Key 2 should not exists after removal
    assertEquals(2, array.size()); // Size should be reduced to 2
  } // reneUriasTestRemove

  /**
   * Test checking the size of the associative array
   */
  @Test
  public void reneUriasTestSize() {
    AssociativeArray<String, Boolean> array = new AssociativeArray<>();

    assertEquals(0, array.size()); // Initial size should be 0

    array.set("true", true);
    array.set("false", false);

    assertEquals(2, array.size()); // After adding two entries

    array.remove("true");

    assertEquals(1, array.size()); // After removing one entry
  } // reneUriasTestSize

  /**
   * Test converting the associative array to a string representation
   *
   * SamR removed this test because it makes assumptions about the
   * ordering of values in the array.
   */
  public void reneUriasTestToString() {
    AssociativeArray<String, Integer> array = new AssociativeArray<>();
    array.set("apple", 5);
    array.set("banana", 3);
    array.set("cherry", 8);

    String expected = "{ apple: 5, banana: 3, cherry: 8 }";

    assertEquals(expected, array.toString());
  } // reneUriasTestToString

  /**
   * Test handling KeyNotFoundException when trying to get a non-existent key
   */
  @Test
  public void reneUriasTestKeyNotFoundException() {
    AssociativeArray<String, Double> array = new AssociativeArray<>();
    array.set("pi", 3.14);

    assertThrows(KeyNotFoundException.class, () -> array.get("e")); // Key 'e' does not exist
  } // reneUriasTestKeyNotFoundExceptions

  // +-----------------------+---------------------------------------
  // | Tests by Christina Vu |
  // +-----------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Luke Walters |
  // +-----------------------+
  /** 
   * Checks that the expand method and clone methods work properly and that they have the same pair size length at the end
  */
  @Test
  public void LukeWaltersTest1(){
    AssociativeArray<Integer, Integer> testArray = new AssociativeArray<>();
    for(int i = 0; i < 20; i++){
      testArray.set(i, i*2);
    }
    AssociativeArray <Integer, Integer> cloneArray = testArray.clone();
    assertEquals(cloneArray.pairs.length, testArray.pairs.length);
    }

  /**
  * Ensures that when an element is removed from the middle of the list the next added element takes its place
  */
  @Test
  public void LukeWaltersTest2(){
    AssociativeArray<Integer, Integer> testArray = new AssociativeArray<>();
    testArray.set(1, 11);
    testArray.set(2, 22);
    testArray.set(3, 33);
    testArray.remove(2);
    testArray.set(4, 44);
    String checker = "{ 1: 11, 4: 44, 3: 33 }";
    assertEquals(testArray.toString(), checker);
  }

  /**
   * Checks to ensure that an error is thrown when get is used on an empty array
   */
  @Test
  public void LukeWaltersEdge1(){
    AssociativeArray<Integer, Integer> testArray = new AssociativeArray<>();
    try{
    testArray.get(1);
    fail("Did not Throw KeyNotFoundException");
    }
    catch (KeyNotFoundException e){
    }
  }

  // +------------------------+--------------------------------------
  // | Tests by Jonathan Wang |
  // +------------------------+

  // +-------------------+-------------------------------------------
  // | Tests by Lydia Ye |
  // +-------------------+

} // class AssociativeArrayTests

