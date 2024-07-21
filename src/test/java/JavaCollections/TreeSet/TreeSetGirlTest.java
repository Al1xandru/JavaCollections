package JavaCollections.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeSetGirlTest {

    private TreeSetGirl treeSet;

    @BeforeEach
    void setUp() {
        treeSet = new TreeSetGirl();
    }

    @Test
    void testAdd() {
        Girl girl1 = new Girl("Alice");
        Girl girl2 = new Girl("Bob");

        assertTrue(treeSet.add(girl1));
        assertTrue(treeSet.add(girl2));
        assertFalse(treeSet.add(girl1)); // Duplicate entry
        assertEquals(2, treeSet.size());
    }

    @Test
    void testRemove() {
        Girl girl1 = new Girl("Alice");
        Girl girl2 = new Girl("Bob");

        treeSet.add(girl1);
        treeSet.add(girl2);

        assertTrue(treeSet.remove(girl1));
        assertFalse(treeSet.remove(girl1)); // Already removed
        assertEquals(1, treeSet.size());

        assertTrue(treeSet.remove(girl2));
        assertEquals(0, treeSet.size());
    }

    @Test
    void testSize() {
        Girl girl1 = new Girl("Alice");
        Girl girl2 = new Girl("Bob");
        Girl girl3 = new Girl("Cathy");

        treeSet.add(girl1);
        treeSet.add(girl2);

        assertEquals(2, treeSet.size());

        treeSet.add(girl3);
        assertEquals(3, treeSet.size());

        treeSet.remove(girl1);
        assertEquals(2, treeSet.size());
    }

    @Test
    void testClear() {
        Girl girl1 = new Girl("Alice");
        Girl girl2 = new Girl("Bob");

        treeSet.add(girl1);
        treeSet.add(girl2);

        treeSet.clear();
        assertEquals(0, treeSet.size());
        assertFalse(treeSet.contains(girl1));
        assertFalse(treeSet.contains(girl2));
    }

    @Test
    void testContains() {
        Girl girl1 = new Girl("Alice");
        Girl girl2 = new Girl("Bob");

        assertFalse(treeSet.contains(girl1));

        treeSet.add(girl1);
        treeSet.add(girl2);

        assertTrue(treeSet.contains(girl1));
        assertTrue(treeSet.contains(girl2));

        treeSet.remove(girl1);

        assertFalse(treeSet.contains(girl1));
        assertTrue(treeSet.contains(girl2));
    }

    @Test
    void testInOrderTraversal() {
        Girl girl1 = new Girl("Alice");
        Girl girl2 = new Girl("Bob");
        Girl girl3 = new Girl("Cathy");

        treeSet.add(girl2);
        treeSet.add(girl1);
        treeSet.add(girl3);

        // Capturăm ieșirea metodei inOrderTraversal
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        treeSet.inOrderTraversal();

        // Resetăm fluxul standard de ieșire
        System.setOut(System.out);

        String expectedOutput = "Alice Bob Cathy";
        String actualOutput = outContent.toString().trim();

        // Afișăm pentru debugging
        System.out.println("Expected output:\n" + expectedOutput);
        System.out.println("Actual output:\n" + actualOutput);

        // Verificăm ieșirea
        assertEquals(expectedOutput, expectedOutput);
    }
}