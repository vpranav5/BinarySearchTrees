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
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;
 
    /**
     * create an empty SortedSet
     * O(1)
     */
    public SortedSet() {
    	// initializes ArrayList
    	myCon = new ArrayList<E>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
    	// initialize list using default constructor
    	this();
    	
    	if(other == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}

    	// make a deep copy of the other set and store it as this sorted set
    	for(E val : other) {
    		myCon.add(val);
    	}
    	// sort list using mergeSort
    	mergeSort(myCon);
    }
    
    // code for mergeSort from class slides
    private void mergeSort(ArrayList<E> list) {
    	
    	ArrayList<E> temp = new ArrayList<E>(list.size());
    	
    	// make a deep  copy of list in temp 
    	for(E elem : list) {
    		temp.add(elem);
    	}
    	
    	sort(list, temp, 0, list.size() - 1);
    
    }
    // code for mergeSort from class slides
    private void sort(ArrayList<E> list, ArrayList<E> temp, int low, int high) {  	
    	
    	// condition to check there is still data to be processed
    	if(low < high) {
    		int center = (low + high) / 2;
    		sort(list, temp, low, center);
    		sort(list, temp, center + 1, high);
    		merge(list, temp, low, center + 1, high);
    	}	
    }
    
    // code for mergeSort from class slides
    private void merge(ArrayList<E> list, ArrayList<E> temp, int leftPos, int rightPos, int rightEnd) {
    	int leftEnd = rightPos - 1;
    	int tempPos = leftPos;
    	int numElements = rightEnd  - leftPos + 1;
    	
    	// main loop
    	while(leftPos <= leftEnd && rightPos <= rightEnd) {
    		if(list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
    			
    			temp.set(tempPos, list.get(leftPos));
    			leftPos++;	
    		}
    		else {
    			temp.set(tempPos, list.get(rightPos));
    			rightPos++;
    		}
    		tempPos++;
    	}
    	
    	// copy rest of left half
    	while(leftPos <= leftEnd) {
    		temp.set(tempPos, list.get(leftPos));
    		tempPos++;
    		leftPos++;
    	}
    	
    	// copy rest of right half
    	while(rightPos <= rightEnd) {
    		temp.set(tempPos, list.get(rightPos));
    		tempPos++;
    		rightPos++;
    	}
    	
    	// copy temp back into list
    	for(int i = 0; i < numElements; i++, rightEnd--) {
    		list.set(rightEnd, temp.get(rightEnd));
    	}
    	
    }
    
    // code for binarySearch from class slides
    private int bsearch(ArrayList<E> data, E tgt) {
    	return bsearch(data, tgt, 0, data.size()-1);
    }
    
    // code for binarySearch from class slides
    private int bsearch(ArrayList<E> data, E tgt, int low, int high) {
    	if(low <= high) {
    		int mid = low + ((high - low) / 2);
    		if(data.get(mid).equals(tgt)) {
    			return mid;
    		}
    		else if(data.get(mid).compareTo(tgt) > 0) {
    			return bsearch(data, tgt, low, mid - 1);
    		}
    		else {
    			return bsearch(data, tgt, mid + 1, high);
    		}
    	}
    	return -1;
    }
    

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     * O(1)
     */
    public E min() {
    	if(size() == 0) {
    		throw new IllegalArgumentException("Size is 0!");
    	}
    	
    	return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     * O(1)
     */
    public E max() {
    	if(size() == 0) {
    		throw new IllegalArgumentException("Size is 0!");
    	}
    	
    	return myCon.get(myCon.size()-1);

    }
    
    /**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     * O(logN)
     */
    public boolean contains(E item) {
    	if(item == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	
    	// runs a binary search to try and find item
    	if(bsearch(myCon, item) == -1) {
    		return false;
    	}
    	
    	return true;
    }
    
    
    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     * O(N)
     */
    public boolean containsAll(ISet<E> otherSet) {
    	if(otherSet == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	 SortedSet<E> otherSortedSet;
    	 
    	 // if other is a UnsortedSet, sort it
    	 if (!(otherSet instanceof SortedSet<?>))
             otherSortedSet = new SortedSet<E>(otherSet); // should be O(NlogN)
         else
             otherSortedSet = (SortedSet<E>) otherSet;
    	 
    	 ArrayList<Boolean> matches = new ArrayList<Boolean>();
    	 int position = 0;
    	 int otherPosition = 0;
    	 
    	 // loop through both lists checking if this set contains all of otherSet
    	 while((position < myCon.size()) && (otherPosition < otherSortedSet.myCon.size())) {
    		 E elem1 = myCon.get(position);
    		 E elem2 = otherSortedSet.myCon.get(otherPosition);
    		 
    		 if((elem1).equals(elem2)) {
    			 // arraylist add as O(1) as values are added at end
    			 matches.add(true);
    			 otherPosition++;
    		 }
    		 position++; 
    	 }

    	 return (matches.size() == otherSortedSet.myCon.size());
    }
    
    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     * O(N)
     */
    public void clear() {
    	myCon.clear();
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
    	
    	int pos = getAddPos(item);
    	int prevSize = this.size();
    	
    	// if element doesn't already exist, add it
    	if(pos != -1) {
    		myCon.add(pos, item);
    	}
    	
    	return(prevSize == this.size());
    }
    
    // binary search code modified from class slides
    private int getAddPos(E tgt){
        int low = 0;
        int high = myCon.size()-1;
        
        // implements a binary search to find the index to insert
        while(low <= high){
            int mid = low + ((high - low) / 2);
            int compareResult = tgt.compareTo(myCon.get(mid));

            if(compareResult == 0){
                // element exists, don't add
                return -1;
            } 

            else if(compareResult > 0){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return low;
    }
    
    
 	/**
      * A union operation. Add all items of otherSet that are not already present in this set
      * to this set.
      * @param otherSet != null
      * @return true if this set changed as a result of this operation, false otherwise.
      * O(N)
      */
    public boolean addAll(ISet<E> otherSet) {
    	if(otherSet == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	// local var of correct type so we only have to cast once
        SortedSet<E> otherSortedSet;

        // do we need to create a new SortedSet based on otherSet or is otherSet really a SortedSet?
        if (!(otherSet instanceof SortedSet<?>))
            otherSortedSet = new SortedSet<E>(otherSet); // should be O(NlogN)
        else
            otherSortedSet = (SortedSet<E>) otherSet;
          	
    	int previousSize = this.size();
    	
    	// rest reference of this storage container to new combined container
    	myCon = addMerge(this.myCon, otherSortedSet.myCon);
    	
    	return this.size() == previousSize;
    }
    
    
    
    // helper method to merge elements maintaining unique data
    // O(N)
    private ArrayList<E> addMerge(ArrayList<E> list1, ArrayList<E> list2){
        ArrayList<E> combined = new ArrayList<E>();

        int pos1 = 0;
        int pos2 = 0; 
         
        while (pos1 < list1.size() && pos2 < list2.size()) {
            // if list1's element is smaller than list2, add it first
            if((list1.get(pos1)).compareTo(list2.get(pos2)) < 0){
                combined.add(list1.get(pos1));
                pos1++;
            } else if((list1.get(pos1)).compareTo(list2.get(pos2)) > 0) {
                combined.add(list2.get(pos2)); 
                pos2++;
            }
            else {
            	// if elements are equal, add it only once to the set
            	combined.add(list1.get(pos1));
            	pos1++;
            	pos2++;
            }
        }
        
        // add leftover elements
        if(pos1 == list1.size() && pos2!=list2.size()){
             // add remaining items in list2
            for(int i = pos2; i < list2.size(); i++){
                combined.add(list2.get(i));
            }
        } else if(pos1 != list1.size() && pos2 == list2.size()) {
            // add remaining items in list1
            for(int j = pos1; j < list1.size(); j++){
                combined.add(list1.get(j));
            }
        }
        
        return combined;

    }
    
    // helper method to merge sets and store the elements unique to this set
    // O(N)
    private ArrayList<E> diffMerge(ArrayList<E> list1, ArrayList<E> list2){
        
    	ArrayList<E> diff = new ArrayList<E>();

        int pos1 = 0;
        int pos2 = 0; 
         
        while (pos1 < list1.size() && pos2 < list2.size()) {
            // if list1's element smaller, add it first
            if((list1.get(pos1)).compareTo(list2.get(pos2)) < 0){
                diff.add(list1.get(pos1));
                pos1++;
            } else if((list1.get(pos1)).compareTo(list2.get(pos2)) > 0) {
            	// don't add elements that are only in list2
                pos2++;
            }
            else {
            	// skip equal elements
            	pos1++;
            	pos2++;
            }
        }

        if(pos1 != list1.size() && pos2 == list2.size()) {
            // add remaining items in list1
            for(int j = pos1; j < list1.size(); j++){
                diff.add(list1.get(j));
            }
        }
        
        return diff;

    }
    
    
    
    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     * O(N)
     */
    
    public boolean equals(Object other) {
		
		// checks if other object is a type of Set
		if(other instanceof ISet<?>) {
			
			SortedSet<E> otherSortedSet;
			  // do we need to create a new SortedSet based on otherSet or is otherSet really a SortedSet?
	        if (!(other instanceof SortedSet<?>))
	            otherSortedSet = new SortedSet<E>(((ISet<E>)(other))); // should be O(NlogN)
	        else
	            otherSortedSet = (SortedSet<E>) other;
			
    		// lists aren't equal if sizes don't match
    		if(this.size() !=  otherSortedSet.size()) {
    			return false;
    		}
    		// empty lists are equal
    		if(this.size() == 0 && otherSortedSet.size() == 0) {
    			return true;
    		}
    		
    		// checks all the elements in both inventories to ensure equality
    		if(this.containsAll(otherSortedSet)) {
    			return true;
    		}
    		return false;
		}
		else {
			return false;
		}
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
     *  O(N)
     */
    
    public ISet<E> difference(ISet<E> otherSet){
 	    if(otherSet == null) {
    			throw new IllegalArgumentException("Invalid parameter!");
     	}
 	    
 	   SortedSet<E> otherSortedSet;
 	   
 	   // if a set is unsorted, sort it
 	   if (!(otherSet instanceof SortedSet<?>))
           otherSortedSet = new SortedSet<E>(otherSet); // should be O(NlogN)
       else
           otherSortedSet = (SortedSet<E>) otherSet;
 	  
 	   SortedSet<E> diff = new SortedSet<E>();
 	   
 	   // set new set's internal container to the new merged difference
 	   diff.myCon = diffMerge(this.myCon, otherSortedSet.myCon);
 	   
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
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     * O(N)
     */
    public ISet<E> union(ISet<E> otherSet){
    	if(otherSet == null) {
    		throw new IllegalArgumentException("Invalid parameter!");
    	}
    	
    	 SortedSet<E> otherSortedSet;
      	 
      	 if (!(otherSet instanceof SortedSet<?>))
               otherSortedSet = new SortedSet<E>(otherSet); // should be O(NlogN)
           else
               otherSortedSet = (SortedSet<E>) otherSet;
      	 
       SortedSet<E> totalUnion = new SortedSet<E>();
       
       // add all the elements in this set, and otherSortedSet as per union operation
       totalUnion.addAll(this);
 	   totalUnion.addAll(otherSortedSet);
 	   
 	   return totalUnion;
    }
    
}