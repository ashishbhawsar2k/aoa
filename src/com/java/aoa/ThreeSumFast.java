/******************************************************************************
 *  Compilation:  javac ThreeSumFast.java
 *  Execution:    java ThreeSumFast input.txt
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program with N^2 log N running time. Read in N integers
 *  and counts the number of triples that sum to exactly 0.
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *     - doesn't handle case when input has duplicates
 *
 *java DoublingRatio
   250     0.0   2.5
   500     0.0   1.8
  1000     0.0   3.2
  2000     0.1   4.2
 ******************************************************************************/

package com.java.aoa;

import java.util.Arrays;

import com.java.aoa.Stopwatch;
import edu.princeton.cs.algs4.*;

/**
 *  The <tt>ThreeSumFast</tt> class provides static methods for counting
 *  and printing the number of triples in an array of distinct integers that
 *  sum to 0 (ignoring integer overflow).
 *  <p>
 *  This implementation uses sorting and binary search and takes time 
 *  proportional to N^2 log N, where N is the number of integers.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/14analysis">Section 1.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class ThreeSumFast {
	private static final int MAXIMUM_INTEGER = 1000000;
    // Do not instantiate.
    private ThreeSumFast() { }

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

    /**
     * Prints to standard output the (i, j, k) with i < j < k such that a[i] + a[j] + a[k] == 0.
     * @param a the array of integers
     * @throws IllegalArgumentException if the array contains duplicate integers
     */
    public static void printAll(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) StdOut.println(a[i] + " " + a[j] + " " + a[k]);
            }
        }
    } 

    /**
     * Returns the number of triples (i, j, k) with i < j < k such that a[i] + a[j] + a[k] == 0.
     * @param a the array of integers
     * @return the number of triples (i, j, k) with i < j < k such that a[i] + a[j] + a[k] == 0
     */
    public static int count(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) cnt++;
            }
        }
        return cnt;
    } 

    /**
     * Returns the amount of time to call <tt>ThreeSum.count()</tt> with <em>N</em>
     * random 6-digit integers.
     * @param N the number of integers
     * @return amount of time (in seconds) to call <tt>ThreeSum.count()</tt>
     *   with <em>N</em> random 6-digit integers
     */
    public static double timeTrial(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        count(a);
        return timer.elapsedTime();
    }
    
    /**
     * Reads in a sequence of distinct integers from a file, specified as a command-line argument;
     * counts the number of triples sum to exactly zero; prints out the time to perform
     * the computation.
     */
    public static void main(String[] args)  { 
//        In in = new In(args[0]);
//        int[] a = in.readAllInts();
//        int cnt = count(a);
//        StdOut.println(cnt);
    	double prev = timeTrial(125);
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%6d %7.1f %5.1f\n", N, time, time/prev);
            prev = time;
        } 
    } 
} 