package question1;

public class EnsembleTest extends junit.framework.TestCase {
    
    public void testAdd() {
        question1.Ensemble<Integer> ens;
        ens = new question1.Ensemble<Integer>();
        assertEquals(true, ens.add(2));
        assertEquals(true, ens.add(3));
        assertEquals(false, ens.add(2));
        assertEquals(false, ens.add(null));
    }

    public void testUnion() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> union = e1.union(e2);
        assertEquals(3, union.size());
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }
    
    public void testInter() {
        question1.Ensemble<Integer> e1, e2, resEns;
        e1 = new question1.Ensemble<Integer>();
        e1.add(1);
        e1.add(2);
        e1.add(3);
        e1.add(4);
        
        e2 = new question1.Ensemble<Integer>();
        e2.add(2);
        e2.add(4);
        
        resEns = new question1.Ensemble<Integer>(); 
        resEns.add(2);
        resEns.add(4);
        
        assertEquals(" intersection ? e1.inter(e2) ", resEns, e1.inter(e2));
        assertEquals(" intersection ? e2.inter(e1) ", resEns, e2.inter(e1));
        try {
            e1.inter(null);
            e2.inter(null);
            fail("Missing exception");
        } catch(IllegalArgumentException e) {
            assertEquals("ensemble null", e.getMessage()); 
        }    
    }
    
    public void testDiff() {
        question1.Ensemble<Integer> e1, e2, resEns, emptyEns;
        e1 = new question1.Ensemble<Integer>();
        e1.add(1);
        e1.add(2);
        e1.add(3);
        e1.add(4);
        
        e2 = new question1.Ensemble<Integer>();
        e2.add(2);
        e2.add(4);
        
        resEns = new question1.Ensemble<Integer>(); 
        resEns.add(1);
        resEns.add(3);
        
        emptyEns = new question1.Ensemble<Integer>(); 
        
        assertEquals(" difference ? e1.diff(e2) ", resEns, e1.diff(e2));
        assertEquals(" difference ? e2.diff(e1) ", emptyEns, e2.diff(e1));
        try {
            e1.diff(null);
            e2.diff(null);
            fail("Missing exception");
        } catch(IllegalArgumentException e) {
            assertEquals("ensemble null", e.getMessage()); 
        }
    }
    
    public void testDiffSym() {
        question1.Ensemble<Integer> e1, e2, resEns1, resEns2;
        e1 = new question1.Ensemble<Integer>();
        e1.add(1);
        e1.add(2);
        e1.add(3);
        e1.add(4);
        
        e2 = new question1.Ensemble<Integer>();
        e2.add(2);
        e2.add(4);
        e2.add(5);
        
        resEns1 = new question1.Ensemble<Integer>(); 
        resEns1.add(1);
        resEns1.add(3);
        resEns1.add(5);
        
        resEns2 = new question1.Ensemble<Integer>(); 
        resEns2.add(5);
        resEns2.add(1);
        resEns2.add(3);

        assertEquals(" difference symetrique ? e1.diffSym(e2) ", resEns1, e1.diffSym(e2));
        assertEquals(" difference symetrique ? e2.diffSym(e1) ", resEns2, e2.diffSym(e1));
        try {
            e1.diffSym(null);
            e2.diffSym(null);
            fail("Missing exception");
        } catch(IllegalArgumentException e) {
            assertEquals("ensemble null", e.getMessage()); 
        }
    }
    
}
