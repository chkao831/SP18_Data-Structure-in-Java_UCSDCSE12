.SUFFIXES: .java .class

.java.class:
	javac $<

CLASSES: Deque12.class  BoundedDequeTester.class

DequeTester:Deque12.java BoundedDequeTester.java
	javac Deque12.java
	javac -cp '.':'*':'/usr/share/java/*' BoundedDequeTester.java
	java -cp '.':'*':'/usr/share/java/*' org.junit.runner.JUnitCore BoundedDequeTester

StackTester:Stack12.java Stack12Test.java Deque12.java
	javac Deque12.java
	javac Stack12.java
	javac -cp '.':'*':'/usr/share/java/*' Stack12Test.java
	java -cp '.':'*':'/usr/share/java/*' org.junit.runner.JUnitCore Stack12Test

QueueTester:Queue12.java Queue12Test.java Deque12.java
	javac Deque12.java
	javac Queue12.java
	javac -cp '.':'*':'/usr/share/java/*' Queue12Test.java
	java -cp '.':'*':'/usr/share/java/*' org.junit.runner.JUnitCore Queue12Test
	
