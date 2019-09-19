/*  Student information for assignment:
 *
 *  Number of slip days used: 2
 *
 *  Student 1 Pranav Teja Varanasi
 *  UTEID: ptv247
 *  email address: varanasipranav@gmail.com
 *  Grader name: Jacob Szwejbka
 *  Section number: 50750
 *  
 *  
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


/*
CS 314 Students, put your results to the experiments and answers to
questions here:


File Title                      Size     TotalWords    DistinctWords   Time (Sorted, Unsorted, HashSet, TreeSet)
America Munitions(smallText)    3 KB     473            255            0.009206739, 0.006099652, 0.001647629, 0.001858305

In The Days                   179 KB     31900          7628           0.061965092, 0.318935855, 0.030082962, 0.023939851

On The Track                  244 KB     43752          9513           0.083942874, 0.483705545, 0.023192340, 0.053029963

Folk Rhymes                   302 KB     55170          12375          0.090212130, 1.013290044, 0.033593399, 0.049979031  

Heroic                        571 KB     104352         17114          0.139574884, 1.701836360, 0.087684453, 0.105026601 


CHANGES:

File Title                      Size     TotalWords    DistinctWords   Time(seconds) (Sorted, Unsorted, HashSet, TreeSet)
America Munitions(smallText)    3 KB     473            255            0.009206739, 0.006099652, 0.001647629, 0.001858305

In The Days                    59.7X     67.4X         29.9X           6.7304060645X, 52.287549355X, 18.258334856X, 12.882627448X

On The Track                   1.36X     1.37X         1.25X           1.3546800511X, 1.5166232878X, 0.77094602586X, 2.2151333774X

Folk Rhymes                    1.23X     1.26X         1.30X           1.074684791X, 2.0948489313X, 1.4484695809X, 0.94246777053X

Heroic                         1.89x     1.89X         1.38X           1.5471853286X, 1.6795155248X, 2.6101691288X, 2.1014133107X  



Questions:

1) The processText order for UnsortedSet is O(N^2).
2) The processText order for SortedSet is O(N^2).
3) The processText order for HashSet is O(N).
4) The processText order for TreeSet is O(NlogN).

1) My UnsortedSet add method is O(N).
2) My SortedSet add method is O(N).
3) I think HashSet add method is O(1).
4) I think TreeSet add method is O(log N).

The differences between HashSet and TreeSet when printing out a set are that
TreeSet will print out the set in alphabetical order while HashSet will print
out the set according to hash codes which will be in a varying and unrecognizable order.

CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:

It is unwise to implement all three methods in the AbstractSet class as the AbstractSet class cannot
be instantiated and have private instance variables. Therefore, the AbstractSet class will not have an 
internal ArrayList storage container to represent the sets. Since intersection, difference and union are all set
operations that depend on processing the data in the myCon storage container, at least one of them must be overridden
in order to actually process the data in myCon and calculate the set operations.


*/


public class SetTester {

    public static void main(String[] args){
    	// Student Tests:
     
        ISet<String> s1 = new UnsortedSet<String>();
        s1.add("P");
        s1.add("R");
        s1.add("A");
        s1.add("N");

        //test 1
        if( s1.contains("A") )
            System.out.println("Passed test 1: add method UnsortedSet");
        else
            System.out.println("Failed test 1: add method UnsortedSet");


        ISet<String> s2 = new UnsortedSet<String>();
        s2.add("A");
        s2.add("V");
      

        //test 2
        s1.addAll(s2);
        if( s1.size() == 5)
            System.out.println("Passed test 2: addAll method UnsortedSet");
        else
            System.out.println("Failed test 2: addAll method UnsortedSet");

        //test 3
        s1.clear();
        if( s1.size() == 0 )
            System.out.println("Passed test 3: clear method UnsortedSet");
        else
            System.out.println("Failed test 3: clear method UnsortedSet");

        s1 = new UnsortedSet<String>();
        s2 = new UnsortedSet<String>();

        s2.add("C");
        s2.add("A");
        s2.add("B");

        //test 4
        if( s2.contains("C") )
            System.out.println("Passed test 4: contains method UnsortedSet");
        else
            System.out.println("Failed test 4: contains method UnsortedSet");

        //test 5
        s1.add("C");
        s1.add("A");
        if( !s1.containsAll(s2) )
            System.out.println("Passed test 5: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 5: containsAll method UnsortedSet");

        //test 6
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<String>();
        expected.add("B");
        if( s3.equals(expected))
            System.out.println("Passed test 6: difference method UnsortedSet");
        else
            System.out.println("Failed test 6: difference method UnsortedSet");

        //test 7
        s3 = s2.union(s1);
        expected = new UnsortedSet<String>();
        expected.add("C");
        expected.add("A");
        expected.add("B");

        if( s3.equals(expected))
            System.out.println("Passed test 7: union method UnsortedSet");
        else
            System.out.println("Failed test 7: union method UnsortedSet");

        //test 8
        s3 = s2.intersection(s1);
        expected.remove("B");
        if( s3.equals(expected))
            System.out.println("Passed test 8: intersection methods UnsortedSet");
        else
            System.out.println("Failed test 8: intersection methods UnsortedSet");

        s1 = new UnsortedSet<String>();
        s2 = new UnsortedSet<String>();

        s1.add("A");
        s1.add("B");
        s2.add("A");
        s2.add("B");

        // test 9
        if( s3.equals(expected))
            System.out.println("Passed test 9: equals method UnsortedSet");
        else
            System.out.println("Failed test 9: equals method UnsortedSet");

        // test 10
        Iterator iteratorVal = s1.iterator();
        if(iteratorVal.next().equals("A"))
             System.out.println("Passed test 10: iterator method UnsortedSet");
        else
            System.out.println("Failed test 10: iterator method UnsortedSet");

        // test 11
        if(s1.size() == 2)
            System.out.println("Passed test 11: size method UnsortedSet");
        else
            System.out.println("Failed test 11: size method UnsortedSet");

        // test 12
        s1.remove("A");
        if(s1.size() == 1)
             System.out.println("Passed test 12: remove method UnsortedSet");
        else
             System.out.println("Failed test 12: remove method UnsortedSet");

        // test 13
        
        ISet<String> s4 = new UnsortedSet<String>();
        if (s4.toString().equals("()")) 
             System.out.println("Passed test 13: UnsortedSet constructor");
        else
             System.out.println("Failed test 13: UnsortedSet constructor");


        // SortedSet Tests

        s1 = new SortedSet<String>();
        s2 = new SortedSet<String>();


        // test 14
        if (s1.toString().equals("()")) 
             System.out.println("Passed test 14: SortedSet no args constructor");
        else
             System.out.println("Failed test 14: SortedSet no args constructor");

        s1.add("V");
       

        // test 15
        if (s1.toString().equals("(V)")) 
             System.out.println("Passed test 15: SortedSet args constructor");
        else
             System.out.println("Failed test 15: SortedSet args constructor");

        s1.add("A");
        s1.add("N");
        s1.add("A");
        s1.add("R");
        s1.add("P");
        //test 16
        if( s1.contains("A") )
            System.out.println("Passed test 16: add method SortedSet");
        else
            System.out.println("Failed test 16: add method SortedSet");


        s2.add("V");
        s2.add("A");
        s2.add("N");
        s2.add("A");
        s2.add("R");
        s2.add("P");

        //test 17
        
        if(s1.containsAll(s2))
            System.out.println("Passed test 17: containsAll method SortedSet");
        else
            System.out.println("Failed test 17: containsAll method SortedSet");

        // test 18
        s1.clear();
        s2.clear();

        if( s1.size() == 0 )
            System.out.println("Passed test 18: clear method SortedSet");
        else
            System.out.println("Failed test 18: clear method SortedSet");

        s1 = new SortedSet<String>();
        s2 = new SortedSet<String>();

        s1.add("A");
        s1.add("B");

        s2.add("D");
        s2.add("A");
        s2.add("B");


        //test 19
        s3 = s2.difference(s1);
        expected = new SortedSet<String>();
        expected.add("D");
        if( s3.equals(expected))
            System.out.println("Passed test 19: difference method SortedSet");
        else
            System.out.println("Failed test 19: difference method SortedSet");

        //test 20
        s2.remove("D");

        if(s1.equals(s2))
            System.out.println("Passed test 20: equals methods SortedSet");
        else
            System.out.println("Failed test 20: equals methods SortedSet");


        //test 21
        s1 = new SortedSet<String>();
        s2 = new SortedSet<String>();

        s1.add("B");
        s1.add("A");
        s1.add("D");

        s2.add("C");
        s2.add("D");
        s2.add("E");

        
        s3 = s1.intersection(s2);
        if(s3.size() == 1)
            System.out.println("Passed test 21: intersection method SortedSet");
        else
            System.out.println("Failed test 21: intersection method SortedSet");


        // test 22
        Iterator iterateValues = s1.iterator();

        if(iterateValues.next().equals("A"))
            System.out.println("Passed test 22: iterator method SortedSet");
        else
            System.out.println("Failed test 22: iterator method SortedSet");

        // test 23

        if(s1.size() == 3)
            System.out.println("Passed test 23: size method SortedSet");
        else
            System.out.println("Failed test 23: size method SortedSet");

        // test 24
        expected = new SortedSet<String>();
        s1 = new SortedSet<String>();
        s2 = new SortedSet<String>();

        s1.add("P");
        s1.add("R");
        s1.add("A");

        s2.add("N");
        s2.add("V");

        s3 = s1.union(s2);
        expected.add("P");
        expected.add("R");
        expected.add("A");
        expected.add("N");
        expected.add("V");

        if(s3.equals(expected))
            System.out.println("Passed test 24: union method SortedSet");
        else
            System.out.println("Failed test 24: union method SortedSet");

        // CS314 Students. Uncomment this section when ready to 
        // run your experiments
        /**
              try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch(Exception e) {
                    System.out.println("Unable to change look and feel");
                }
       		Scanner sc = new Scanner(System.in);
        		String response = "";
        		do {
        			largeTest();
        			System.out.print("Another file? Enter y to do another file: ");
        			response = sc.next();
        		} while( response != null && response.length() > 0 
                      && response.substring(0,1).equalsIgnoreCase("y") );
         */
       
    }
    
    
    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest(){
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    
    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }

    
    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, 
            int totalWords, int setSize) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }	
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}