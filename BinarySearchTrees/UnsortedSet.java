/*  Student information for assignment:
 *
 *  On <MY> honor, <Pranav Teja Varanasi>, this programming assignment is <MY> own work
 *  and <I> have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 Pranav Teja Varanasi
 *  UTEID: ptv247
 *  email address: varanasipranav@gmail.com
 *  Grader name: Jacob Szwejbka
 *  Section number: 50750
 *    
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	private ArrayList<E> myCon;
	
	// initializes the arrayList
	public UnsortedSet(){
		myCon = new ArrayList<E>();
	}
	
	/**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     * O(N)
     */
    public boolean add(E item) {
    	if(item == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	
    	int prevSize = myCon.size();
    	
    	// only adds unique elements to this set
    	if(!(this.contains(item))) {
    		myCon.add(item);
    	}
    	
    	return (prevSize == myCon.size());
    }
    
    /**
     * A union operation. Add all items of otherSet that are not already present in this set
     * to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     * O(N^2)
     */
   public boolean addAll(ISet<E> otherSet) {
	   if(otherSet == null) {
   			throw new IllegalArgumentException("Invalid parameter!");
   		}
	   
	   Iterator<E> otherIterateSet = otherSet.iterator();
	   boolean hasChanged = false;
	   
	   // loops through other set checking for unique values to add
	   while(otherIterateSet.hasNext()) {
		   E element = otherIterateSet.next();
		   if(!(this.contains(element))) {
			   // ArrayList add is O(1) since adding at end
			   this.add(element);
			   hasChanged = true;
		   }
	   }
	   
	   return hasChanged;
   }
   
   /**
    * Create a new set that is the difference of this set and otherSet. Return an ISet of 
    * elements that are in this Set but not in otherSet. Also called
    * the relative complement. 
    * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
    * A.difference(B) would return an ISet with elements [X, Y] while
    * B.difference(A) would return an ISet with elements [W]. 
    * <br>pre: otherSet != null
    * <br>post: returns a set that is the difference of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return a set that is the difference of this set and otherSet
    * O(N^2)
    */
   public ISet<E> difference(ISet<E> otherSet){
	    if(otherSet == null) {
   			throw new IllegalArgumentException("Invalid parameter!");
    	}
	   
	   UnsortedSet<E> diff = new UnsortedSet<E>();
	   Iterator<E> iteratorSet = this.iterator();
	   
	   // adds all the elements that are only unique to this set
	   while(iteratorSet.hasNext()) {
		   E element = iteratorSet.next();
		   if(!(otherSet.contains(element))) {
			   diff.add(element);   
		   }
	   }
	   
	   return diff; 
   }
    
   /**
    * Return an Iterator object for the elements of this set.
    * pre: none
    * @return an Iterator object for the elements of this set
    * O(1)
    */
   public Iterator<E> iterator(){
	   return myCon.iterator();
   }
   
   /**
    * Return the number of elements of this set.
    * pre: none
    * @return the number of items in this set
    * O(1)
    */
   public int size() {
	   return myCon.size();
   }
    
   
   /**
    * Create a new set that is the union of this set and otherSet.
    * <br>pre: otherSet != null
    * <br>post: returns a set that is the union of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return a set that is the union of this set and otherSet
    * O(N^2)
    */
   public ISet<E> union(ISet<E> otherSet){
	   if(otherSet == null) {
  			throw new IllegalArgumentException("Invalid parameter!");
   	   }
	   
	   UnsortedSet<E> totalUnion = new UnsortedSet<E>();
	   // uses addAll union operation to combine the sets
	   totalUnion.addAll(this);
	   totalUnion.addAll(otherSet);
	   
	   return totalUnion;
   }
    
}