package icbat.games.tradesong.engine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/***/
public class RandomGeneratorTest {

    protected RandomGenerator<String> generator;
    protected String first;
    protected String second;
    private List<String> possibleResults;
    private Random random;

    @Before
    public void setUp() throws Exception {
        random = mock(Random.class);
        first = "first";
        second = "second";
        possibleResults = new ArrayList<String>();
        possibleResults.add(first);
        possibleResults.add(second);

        generator = new RandomGenerator<String>(possibleResults, random);
    }

    @Test
    public void getRandom_returnsAThing() throws Exception {
        final String firstResult = generator.getNext();

        assertTrue("", possibleResults.contains(firstResult));
    }

    @Test
    public void getRandom_canReturnAllTheThings() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(0).thenReturn(1);

        assertEquals(first, generator.getNext());
        assertEquals(second, generator.getNext());
    }

    @Test
    public void getRandom_withEmptyPossibilities() throws Exception {
        generator = new RandomGenerator<String>(new ArrayList<String>(), random);

        try {
            generator.getNext();
            fail("No exception for an unhandled case");
        } catch (IndexOutOfBoundsException ioobe) {
            fail("Too generic of an error!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void getNewRandom() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(0).thenReturn(0).thenReturn(1);

        final String different = generator.getNextDifferent(first);

        assertNotEquals(first, different);
        assertEquals(second, different);
    }

    @Test
    public void getNewRandom_cantBeInfinite() throws Exception {
        while (possibleResults.size() > 1) {
            possibleResults.remove(0);
        }

        try {
            generator.getNextDifferent(first);
            fail("no error thrown for the infinite loop case");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }

    }
}