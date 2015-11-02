# aoa
## resources
weblink: http://algs4.cs.princeton.edu/home/

Code: http://algs4.cs.princeton.edu/code/

## Memory usage
For a typical 64-bit machine,
    Primitive types
	Objects. To determine the memory usage of an object, we add the amount of memory used by each instance variable to the overhead associated with each object, typically 16 bytes. Moreover, the memory usage is typically padded to be a multiple of 8 bytes (on a 64-bit machine).
	References. A reference to an object typically is a memory address and thus uses 8 bytes of memory (on a 64-bit machine). 
	Linked lists. A nested non-static (inner) class such as our Node class requires an extra 8 bytes of overhead (for a reference to the enclosing instance). 
	Arrays. Arrays in Java are implemented as objects, typically with extra overhead for the length. An array of primitive-type values typically requires 24 bytes of header information (16 bytes of object overhead, 4 bytes for the length, and 4 bytes of padding) plus the memory needed to store the values. 
	Strings. A Java 7 string of length N typically uses 32 bytes (for the String object) plus 24 + 2N bytes (for the array that contains the characters) for a total of 56 + 2N bytes. 

## Misc
If you use reflection, you can access the private fields of any class and change them

Q. Why do I get a "can't create an array of generics" error when I try to create an array of generics?
    public class ResizingArrayStack<Item> {
       Item[] a = new Item[1];
A. Unfortunately, creating arrays of generics is not possible in Java 1.5. The underlying cause is that arrays in Java are covariant, but generics are not. In other words, String[] is a subtype of Object[], but Stack<String> is not a subtype of Stack<Object>. To get around this defect, you need to perform an unchecked cast as in ResizingArrayStack.java. ResizingArrayStackWithReflection.java(http://algs4.cs.princeton.edu/13stacks/ResizingArrayStackWithReflection.java.html) is an (unwieldy) alternative that avoids the unchecked cast by using reflection. 

Q. So, why are arrays covariant?
A. Many programmers (and programming language theorists) consider covariant arrays to be a serious defect in Java's type system: they incur unnecessary run-time performance overhead (for example, see ArrayStoreException) and can lead to subtle bugs. Covariant arrays were introduced in Java to circumvent the problem that Java didn't originally include generics in its design, e.g., to implement Arrays.sort(Comparable[]) and have it be callable with an input array of type String[]. 

Q. Can I create and return a new array of a parameterized type, e.g., to implement a toArray() method for a generic queue?
A. Not easily. You can do it using reflection provided that the client passes an object of the desired concrete type to toArray() This is the (awkward) approach taken by Java's Collection Framework. GenericArrayFactory.java(http://algs4.cs.princeton.edu/13stacks/GenericArrayFactory.java.html) provides an alternate solution in which the client passes a variable of type Class. See also Neal Gafter's blog for a solution that uses type tokens(http://gafter.blogspot.ru/2004/09/puzzling-through-erasure-answer.html). 

Q. How do I increase the amount of memory and stack space that Java allocates? 
A. You can increase the amount of memory allotted to Java by executing with java -Xmx200m Hello where 200m means 200 megabytes. The default setting is typically 64MB. You can increase the amount of stack space allotted to Java by executing with java -Xss200k Hello where 200k means 200 kilobytes. The default setting is typically 128KB	

Q. I get inconsistent timing information in my computational experiments. Any advice? 
A. Consider turning off the HotSpot compiler, using java -Xint, to ensure a more uniform testing environment


## Problem
Local minimum of an array. Write a program that, given an array a[] of N distinct integers, finds a local minimum: an index i such that botha[i] < a[i-1] and a[i] < a[i+1] (assuming the neighboring entry is in bounds). Your program should use ~2 lg N compares in the worst case.

Answer: Examine the middle value a[N/2] and its two neighbors a[N/2 - 1] and a[N/2 + 1]. If a[N/2] is a local minimum, stop; otherwise search in the half with the smaller neighbor. 
