
import static java.lang.reflect.Array.newInstance;


/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> copy = new AssociativeArray<>(); //makes new assoc array
    
    while (copy.pairs.length < this.pairs.length){ //expands copy to size of original
      copy.expand();
    }

    for(int i = 0; i < pairs.length; i++){ //loops through og assoc array
      if(pairs[i] != null){ //checks if val in index is null
        KVPair<K,V> clonePair = new KVPair<>(this.pairs[i].key, this.pairs[i].value); //creates KV pair from og array KV pair
        copy.pairs[i] = clonePair; //sets value of copy at i to kv pair
        copy.size++; //incrament size of copy
      } else {
        copy.pairs[i] = null; //sets val to null
      } 
    }

    return copy;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    int temp = 0; //counter to track size of array

    String returnString = "{}"; //what we will return

    if(this.size > 0){ //checks if there are vals in array
      temp = this.size; //set temp val to value of array size
      returnString = "{ "; 
    }

    for(int i = 0; i < pairs.length; i++){ //loop through array
      if (pairs[i] != null){ //check if val at index is null
        if(temp != 1){ //check for more values in array
          returnString = returnString.concat(pairs[i].key.toString()+ ": " + pairs[i].value.toString() + ", "); //concats to string KV pair
          temp--; //decrease counter
        } else{
          returnString = returnString.concat(pairs[i].key.toString()+ ": " + pairs[i].value.toString() + " }"); //concats to string KV pair and is last val
        }
      } 
    }

    return returnString;
    } // toString()
   


  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value){
    if(size == 0){ //check if size is 0
      pairs[0] = new KVPair<>(key, value); //set index 0 to new kv pair
      this.size++; //incrament size
      return;
    }//if 
    else{
      for (int i = 0; i < pairs.length; i++){ //loop through array

        if (i == pairs.length - 1){ //check if we are at the end of array
          this.expand(); //expand array
        }

        if (pairs[i] == null){ //check if val is empty
          pairs[i] = new KVPair<>(key, value); //set index to KV pair
          this.size++; //incrament size
          return;
        }//if
        else if(pairs[i].key.equals(key)){ //check if key at index is equal to key
          pairs[i].value = value; //set value to new value
          return;
        }//else if
      }//for
    }//else
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key does not appear in the associative
   *                              array.
   */
  public V get(K key) throws KeyNotFoundException {

      return pairs[find(key)].value; //uses find to get index key is at
    
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    try{ //use find to see if key exists in array, if it does returns true
      find(key);
      return true;
    } catch (KeyNotFoundException e) {}
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key){
      try{
      pairs[find(key)] = null; //sets index with key to null
      this.size--; //decrease size
      }  catch
        (Exception e) {}
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < pairs.length; i++){ //loop through array
      if (pairs[i] != null){ //check if val at index is null
        if(pairs[i].key.equals(key)){ //check if key at index is equal to inputted key
          return i; //returns current index
        } 
      } 
    }
    throw new KeyNotFoundException();
  } // find(K)

} // class AssociativeArray
