package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class QuickSelectTest {
    @Test
    void quickSelectMinimumOfManyElements() {
        List<Integer> elements = generateRandomIntegers(NUM_RND_ELEMENTS);
        int actual = QuickSelect.select(elements, 0);
        int expected = elements.stream().min(Comparator.naturalOrder()).get();
        assertEquals(expected, actual);
    }


    @Test
    void quickSelectMaximumOfManyElements() {
        List<Integer> elements = generateRandomIntegers(NUM_RND_ELEMENTS);
        int actual = QuickSelect.select(elements, NUM_RND_ELEMENTS - 1);
        int expected = elements.stream().max(Comparator.naturalOrder()).get();
        assertEquals(expected, actual);
    }
}
