/******************************************************************************
 *  Compilation:  javac SelectionBars.java
 *  Execution:    java SelectionBars N
 *  Dependencies: StdDraw.java
 *  
 *  Selection sort N random real numbers between 0 and 1, visualizing   
 *  the results by ploting bars with heights proportional to the values.
 *  
 *  % java SelectionBars 20
 *
 ******************************************************************************/

package com.java.aoa;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class SelectionBars {

    public static void sort(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) { 
            int min = i;
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            show(a, i, min);
            exch(a, i, min);
        }
    }

    private static void show(double[] a, int i, int min) {
        StdDraw.setYscale(-a.length + i + 1, i);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k++)
            StdDraw.line(k, 0, k, a[k]*.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = i; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]*.6);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(min, 0, min, a[min]*.6);
    }

    private static boolean less(double v, double w) {
        return v < w;
    }

    private static void exch(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(640, 640);
        StdDraw.setXscale(-1, N+1);
        StdDraw.setPenRadius(.006);
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();
        sort(a);
    }

}