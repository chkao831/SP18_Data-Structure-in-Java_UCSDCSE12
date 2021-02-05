/**
 * File: BST12Tester.java
 * @author Chih-Hsuan Kao <c4kao@ucsd.edu>
 * Login cs12sgh
 * PID A92092372
 * Date: Jun 8, 2018
 */ 
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class is a JUnit tester for the BST12 and BST12Adapt classes. 
 * This class extends junit.framework.TestCase
 */
public class BST12Tester {

    /** instance variables */
    private BinSearchTree12<Integer> tree = new BST12Adapt<Integer>();
    private BinSearchTree12<Integer> mytree = new BST12<Integer>();
    private BinSearchTree12<Integer> seventeen = new BST12<Integer>();

    /** supplied tester */
    @Test
    public void testEmptyTree() {
        tree.clear();
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
        assertEquals("[]", tree.toString());
    }

    /** this tester tests clear method for BST12 */
    @Test
    public void testMyEmptyTree(){
        mytree.clear();

        assertEquals(0, mytree.size());
        assertEquals(0, mytree.height());
        assertEquals("", mytree.toString());
    }

    /**
     * Standard Test Fixture.
     * Use either BST12Adapt or BST12 as a test fixture, depending on the
     * need. 
     * @exception Exception
     */
    @Before
    public void setUp() throws Exception {
        tree.add(5);
        tree.add(4);
        tree.add(2);
        tree.add(3);
        tree.add(6);
        tree.add(1);

        mytree.add(5);
        mytree.add(4);
        mytree.add(2);
        mytree.add(3);
        mytree.add(6);
        mytree.add(1);

        seventeen.add(25);
        seventeen.add(36);
        seventeen.add(20);
        seventeen.add(10);
        seventeen.add(30);
        seventeen.add(40);
        seventeen.add(22);
        seventeen.add(28);
        seventeen.add(12);
        seventeen.add(38);
        seventeen.add(5);
        seventeen.add(48);
        seventeen.add(45);
        seventeen.add(50);
        seventeen.add(8);
        seventeen.add(1);
        seventeen.add(15);

    }

    /*
    /**
     * The tester test first() and last() on a BST12 object called seventeen
     @Test
     public void testFirstAndLastOnSeventeen(){
//should be 50 
//System.out.println("LAST : " +seventeen.last().toString());
//should be 1
//System.out.println("FIRST : " +seventeen.first().toString());
     }
     */

    /**
     * The tester tests if BST12Adapt throws proper exception while using
     * remove(), since it's undefined.  
     */
    @Test
    public void testAdaptIteRemove(){
        Iterator<Integer> it = tree.iterator();
        try{
            it.remove();
            fail();
        } catch (UnsupportedOperationException e){
        }
    }

    /**
     * The tester tests if BST12 throws proper exception while using remove()
     */
    @Test
    public void testMyIteRemove(){
        Iterator<Integer> it = mytree.iterator();
        try{
            it.remove();
            fail();
        } catch (UnsupportedOperationException e){

        }
    }

    /**
     * The tester tests if BST12Adapt iterator functions properly using
     * Adapter Design Pattern. 
     */
    @Test
    public void testAdaptIteHasNext(){
        Iterator<Integer> it = tree.iterator();
        int count = 0;
        while(it.hasNext()){
            it.next();
            count++;
        }
        assertEquals(6,count);
    }

    /**
     * Supplied Tester. 
     */
    @Test
    public void testAddUnique() {
        for (int n = 1; n <= 6; n++) {
            assertTrue(tree.contains(n));
        }
    }

    /**
     * This tester is modified by the supplied tester. Tests on BST12. 
     */ 
    @Test
    public void testMyAddUnique() {
        for (int n = 1; n <= 6; n++) {
            assertTrue(mytree.contains(n));
        }
    }

    /**
     * This tester tests if addAll by adding a collection works. The
     * collection contains same things as mytree (in the setup) does. 
     */
    @Test
    public void testAddAll(){
        Collection<Integer> collection= new ArrayList<Integer>();
        collection.add(5);
        collection.add(4);
        collection.add(2);
        collection.add(3);
        collection.add(6);
        collection.add(1);

        BinSearchTree12<Integer> addall = new BST12<Integer>(collection);
        assertEquals(6, addall.size());
        //System.out.println(addall.toString());
        assertEquals(4, addall.height());
        //System.out.println(addall.first().toString());
        //System.out.println(addall.last().toString());
    }

    /**
     * This tester verifies the BST12 tree called seventeen has correct
     * numbers of children for each nodes as I speculated. 
     * and verifies proper exceptions are caught. 
     */
    @Test
    public void testSeventeenChildrenNumber(){
        assertEquals(2, seventeen.numChildren(25));
        assertEquals(2, seventeen.numChildren(20));
        assertEquals(2, seventeen.numChildren(36));
        assertEquals(2, seventeen.numChildren(10));
        assertEquals(0, seventeen.numChildren(22));
        assertEquals(1, seventeen.numChildren(30));
        assertEquals(2, seventeen.numChildren(40));
        assertEquals(2, seventeen.numChildren(5));
        assertEquals(1, seventeen.numChildren(12));
        assertEquals(0, seventeen.numChildren(28));
        assertEquals(0, seventeen.numChildren(38));
        assertEquals(2, seventeen.numChildren(48));
        assertEquals(0, seventeen.numChildren(1));
        assertEquals(0, seventeen.numChildren(8));
        assertEquals(0, seventeen.numChildren(15));
        assertEquals(0, seventeen.numChildren(45));
        assertEquals(0, seventeen.numChildren(50));
        try{
            assertEquals(0, seventeen.numChildren(100));
            fail();
        } catch (NoSuchElementException e){
        }
        try{
            seventeen.numChildren();
            fail();
        }catch(NullPointerException exc){
        }

    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testSize() {
        assertEquals(6, tree.size());
    }

    /**
     * Modified by supplied tester to test BST12 size. 
     */
    @Test
    public void testMySize() {
        assertEquals(6, mytree.size());
        //System.out.println(mytree.toString());
    }

    /**
     * Supplied tester. 
     * Modified to fit the requirement. 
     */
    @Test
    public void testDepth() {
        assertEquals(6, tree.height());
    }

    /**
     * Modified by supplied tester, test BST12 object height. 
     */ 
    @Test
    public void testMyDepth(){
        assertEquals(4, mytree.height());
    }

    /**
     * Test size on BST12 object called seventeen. 
     */
    @Test
    public void testSeventeenSize() {
        assertEquals(17, seventeen.size());
        //System.out.println(seventeen.toString());
    }

    /**
     * Test height on BST12 object called seventeen. 
     */
    @Test
    public void testSeventeenDepth() {
        assertEquals(5, seventeen.height());
    }

    /**
     * Test basic contains() method on seventeen. 
     */
    @Test
    public void testSeventeenContains(){
        assertTrue(seventeen.contains(38));
    }

    /**
     * This tester dynamically tests the height of a BST12 object called
     * mytree, after every add and remove, to see if the height meets my
     * speculation based on how the tree works. 
     */
    @Test
    public void testMyDynamicHeight() {
        assertEquals(4, mytree.height());
        mytree.add(7);
        mytree.add(8);
        assertEquals(4, mytree.height());
        assertEquals(8,mytree.size());
        mytree.remove(1);
        assertEquals(7,mytree.size());
        assertEquals(4,mytree.height());
        mytree.remove(2);
        assertEquals(6,mytree.size());
        assertEquals(4,mytree.height());
        assertEquals("3 4 5 6 7 8 ", mytree.toString());
        mytree.remove(7);
        assertEquals(5,mytree.size());
        assertEquals(3,mytree.height());
        assertEquals("3 4 5 6 8 ", mytree.toString());
        mytree.remove(5);
        //now root is 6
        assertEquals(4,mytree.size());
        assertEquals(3,mytree.height());
        assertEquals("3 4 6 8 ", mytree.toString());
        mytree.remove(3);
        assertEquals(3,mytree.size());
        assertEquals(2,mytree.height());
        mytree.remove(6);
        mytree.remove(8);
        assertEquals(1,mytree.size());
        assertEquals(1,mytree.height());
        assertEquals("4 ", mytree.toString());
        //System.out.println(mytree.first().toString());
        //System.out.println(mytree.last().toString());

        mytree.remove(4);
        assertEquals(0,mytree.size());
        assertEquals(0,mytree.height());
        assertTrue(mytree.isEmpty());
        try{
            mytree.first();
            fail();
        }catch(NoSuchElementException e){

        }
    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testToString() {
        assertEquals("[1, 2, 3, 4, 5, 6]", tree.toString());
    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testAddDuplicates() {
        for (int n = 1; n <= 6; n += 2)
            assertFalse(tree.add(n));
    }

    /**
     * Modified by supplied tester, to test duplicates on add() on BST12
     * object. 
     */
    @Test
    public void testMyAddDuplicates() {
        for (int n = 1; n <= 6; n += 2)
            assertFalse(mytree.add(n));
    }

    /**
     * Supplied tester. 
     */ 
    @Test
    public void testRemoveExistingLeaf() {
        assertTrue(tree.remove(1));
        assertEquals(5, tree.size());
        assertEquals("[2, 3, 4, 5, 6]", tree.toString());
    }

    /**
     * Modified by supplied tester, to test basic remove function on specific
     * element on BST12 object. 
     */ 
    @Test
    public void testMyRemoveExistingLeaf() {
        assertTrue(mytree.remove(1));
        //System.out.println(mytree.toString());
        assertEquals(5, mytree.size());
        assertEquals("2 3 4 5 6 ", mytree.toString());
    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testRemoveExistingMiddleItemWithEmptyRightChild() {
        assertTrue(tree.remove(4));
        assertEquals(5, tree.size());
        assertEquals("[1, 2, 3, 5, 6]", tree.toString());
    }

    /**
     * Modified by supplied tester, to test remove an internal node with empty
     * right child on BST12 object. 
     */
    @Test
    public void testMyRemoveExistingMiddleItemWithEmptyRightChild() {
        assertTrue(mytree.remove(4));
        assertEquals(5, mytree.size());
        // System.out.println(mytree.toString());
        assertEquals("1 2 3 5 6 ", mytree.toString());
    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testRemoveExistingMiddleItemWithEmptyLeftChild() {
        tree.add(7);
        assertTrue(tree.remove(6));
        assertEquals(6, tree.size());
        assertEquals("[1, 2, 3, 4, 5, 7]", tree.toString());
    }

    /**
     * Modified by supplied tester, to test remove method on an internal node
     * with empty left child on BST12 object. 
     */
    @Test
    public void testMyRemoveExistingMiddleItemWithEmptyLeftChild() {
        mytree.add(7);
        assertTrue(mytree.remove(6));
        assertEquals(6, mytree.size());
        assertEquals("1 2 3 4 5 7 ", mytree.toString());
    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testRemoveExistingMiddleItemWithTwoChildren() {
        assertTrue(tree.remove(2));
        assertEquals(5, tree.size());
        assertEquals("[1, 3, 4, 5, 6]", tree.toString());
    }

    /**
     * Modified by supplied tester, to test remove method on an internal node
     * with both children on BST12 object. 
     */
    @Test
    public void testMyRemoveExistingMiddleItemWithTwoChildren() {
        assertTrue(mytree.remove(2));
        assertEquals(5, mytree.size());
        assertEquals("1 3 4 5 6 ", mytree.toString());
    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testRemoveRoot() {
        assertTrue(tree.remove(5));
        assertEquals(5, tree.size());
        assertEquals("[1, 2, 3, 4, 6]", tree.toString());
    }

    /**
     * Modified by supplied tester, to test if removing roots of a BST12
     * object works properly. 
     */ 
    @Test
    public void testMyRemoveRoot() {
        assertTrue(mytree.remove(5));
        assertEquals(5, mytree.size());
        assertEquals("1 2 3 4 6 ", mytree.toString());
    }

    /**
     * Supplied tester. 
     */
    @Test
    public void testRandomAddAndRemove() {
        Random rnd = new Random();

        SortedSet<Integer> oracle = new TreeSet<Integer>();
        for (int n = 1; n <= 6; n++)
            oracle.add(n);

        for (int n = 0; n < 1000; n++) {
            int toAdd = rnd.nextInt(10);
            assertEquals(oracle.add(toAdd), tree.add(toAdd));
            int toRemove = rnd.nextInt(10);
            assertEquals(oracle.remove(toRemove), tree.remove(toRemove));
            int checkExists = rnd.nextInt(10);
            assertEquals(oracle.contains(checkExists), tree
                    .contains(checkExists));
            assertEquals(oracle.size(), tree.size());
            assertEquals(oracle.toString(), tree.toString());
        }
    }

    /**
     * Modified by supplied tester to test on BST12 object. 
     */
    @Test
    public void testMyRandomAddAndRemove() {
        Random rnd = new Random();

        SortedSet<Integer> oracle = new TreeSet<Integer>();
        for (int n = 1; n <= 6; n++)
            oracle.add(n);

        for (int n = 0; n < 1000; n++) {
            int toAdd = rnd.nextInt(10);
            assertEquals(oracle.add(toAdd), mytree.add(toAdd));
            int toRemove = rnd.nextInt(10);
            assertEquals(oracle.remove(toRemove), mytree.remove(toRemove));
            int checkExists = rnd.nextInt(10);
            assertEquals(oracle.contains(checkExists), mytree
                    .contains(checkExists));
            assertEquals(oracle.size(), mytree.size());
            //System.out.println(oracle.toString());
            //System.out.println(mytree.toString());
        }
    }

    /**
     * Supplied tester. See if adding other types work. 
     */ 
    @Test
    public void testOtherType(){
        BST12Adapt<String> stringTree = new BST12Adapt<String>();
        stringTree.add("D");
        stringTree.add("A");
        stringTree.add("C");
        stringTree.add("A");
        stringTree.add("B");
        assertEquals(4, stringTree.size());
        assertTrue(stringTree.contains("C"));
        stringTree.remove("C");
        assertFalse(stringTree.contains("C"));
    }

    /**
     * Modified by supplied tester. Adding string on BST12 object and see if
     * it works. 
     */ 
    @Test
    public void testMyOtherType(){
        BST12<String> st = new BST12<String>();
        st.add("D");
        st.add("A");
        st.add("C");
        st.add("A");
        st.add("B");
        //System.out.println(st.toString());
        assertEquals(4, st.size());
        assertTrue(st.contains("C"));
        st.remove("C");
        assertFalse(st.contains("C"));
    }
}
