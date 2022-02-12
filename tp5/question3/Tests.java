package question3;

import java.util.Set;

public class Tests extends junit.framework.TestCase {

    public void test1(question3.Factory<Set<Integer>> f) throws Exception {
        Set<Integer> set = f.create();
        for (int i = 20; i > 0; i--)
            set.add(i);
    }

    public void testCreation() {
        try {
            test1(new TreeSetFactory<Integer>());
            test1(new HashSetFactory<Integer>());
        } catch (NoSuchMethodError e) {
            fail("NoSuchMethodError : " + e.getMessage());
        } catch (Exception e) {
            fail(" exception inattendue : " + e.getMessage());
        }
    }
    
    public void testAdd() {
        Factory<Set<Integer>> setFactory;
        Set<Integer> set;
        
        setFactory = new TreeSetFactory<Integer>();
        set = setFactory.create();
        
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(1));
        assertFalse(set.add(1));
        assertFalse(set.add(2));
        assertEquals("[1, 2, 3]", set.toString());
        
        setFactory = new HashSetFactory<Integer>();
        set = setFactory.create();
        
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(1));
        assertFalse(set.add(1));
        assertFalse(set.add(2));
    }

}
