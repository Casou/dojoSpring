package com.bparent.dojo.dojoSpring.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void countOccurence_shouldReturnTheNumberOfOccurrence() {
        assertEquals(2, StringUtils.countOccurence("Lorem ipsum", 'm'));
        assertEquals(1, StringUtils.countOccurence("Lorem ipsum", 'o'));
    }

    @Test
    public void countOccurence_shouldReturnTheNumberOfOccurrenceWihoutMatchCase() {
        assertEquals(1, StringUtils.countOccurence("Lorem ipsum", 'l'));
    }

}