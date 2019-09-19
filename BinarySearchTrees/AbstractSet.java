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

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /* NO INSTANCE VARIABLES ALLOWED.
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * Also no direct references to ArrayList or other Java Collections.
     */
    	
	
	/**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     * O(N)
     */
    public void clear() {
    	Iterator<E> iterateSet = this.iterator();
    	
    	while(iterateSet.hasNext()) {
    		// iterate through and remove all elements
    		iterateSet.next();
    		iterateSet.remove();
    	}
    }
    
	/**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     * O(N)
     */
    public boolean contains(E item) {
    	
    	if(item == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	
    	// create iterator instance to go thru set
    	Iterator<E> iterateSet = this.iterator();    	
    	while(iterateSet.hasNext()) {
    		// searches for a target item
    		if( iterateSet.next().equals(item)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     * O(N^2)
     */
    public boolean containsAll(ISet<E> otherSet) {
    	if(otherSet == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	
    	Iterator<E> otherIterateSet = otherSet.iterator();
    	
    	while(otherIterateSet.hasNext()) {
    		E item1 = otherIterateSet.next();
    		boolean foundMatch = false;
    		
    		// searches this set for target element
    		if(this.contains(item1)) {
    			foundMatch = true;
    		}
    		
    		// if the current element was not found at all
    		if(foundMatch == false)
    			return false;
    	}
    	return true;
    }
    
    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     * O(N^2)
     */
    
    public boolean equals(Object other) {
		
		// checks if other object is a type of ISet
		if(other instanceof ISet<?>) {
			
			ISet<E> otherSet = ((ISet<E>)(other));
			
    		// lists aren't equal if sizes don't match
    		if(this.size() !=  otherSet.size()) {
    			return false;
    		}
    		// empty lists are equal
    		if(this.size() == 0 && otherSet.size() == 0) {
    			return true;
    		}
    		
    		// checks all the elements in both sets to ensure equality
    		if(this.containsAll(otherSet)) {
    			return true;
    		}
    		return false;
		}
		else {
			return false;
		}
	}
    
    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise
     * O(N)
     */
    public boolean remove(E item) {
    	if(item == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	
    	Iterator<E> iterateSet = this.iterator();
    	boolean foundMatch = false;
    	
    	while(iterateSet.hasNext()) {
    		E element = iterateSet.next();
    		// removes all occurrences of a target element
    		if(element.equals(item)) {
    			iterateSet.remove();
    			foundMatch = true;
    		}	
    	}
    	
    	return foundMatch;
    }
    
    
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     * O(N)
     */
    public int size() {
    	int currentSize = 0;
    	
    	Iterator<E> iterateSet = this.iterator();
    	
    	// calculates number of elements in this set
    	while(iterateSet.hasNext()) {
    		iterateSet.next();
    		currentSize++;		
    	}
    	
    	return currentSize;
    	
    }
   
    
    /**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     * O(N^2) for UnsortedSet, O(N) for SortedSet
     */
    public ISet<E> intersection(ISet<E> otherSet){
    	
    	if(otherSet == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	
    	// calculate the intersection by getting total union of sets
    	ISet<E>  totalSet = this.union(otherSet);
    	
    	// calculate total difference between sets
    	ISet<E> diff1 =  this.difference(otherSet);
    	ISet<E> diff2 = otherSet.difference(this);
    	ISet<E> allDiff = diff1.union(diff2);
    
    	// returns all the elements that are common between the sets
    	return totalSet.difference(allDiff);
    }
      
    // O(N)
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0)
            result.setLength(result.length() - seperator.length());

        result.append(")");
        return result.toString();
    }
}