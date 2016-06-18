package com.me.miguelferreira.platform.controllers;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import org.junit.Test;

public class GeographicalLocationConverterTest {

    @Test(expected = NullPointerException.class)
    public void test_convert_whenStringIsNull() throws Exception {
        new GeographicalLocationConverter().convert(null);
    }

    @Test(expected = NumberFormatException.class)
    public void test_convert_whenStringIsEmpty() throws Exception {
        new GeographicalLocationConverter().convert("");
    }

    @Test(expected = NumberFormatException.class)
    public void test_convert_whenStringContainsAlphas() throws Exception {
        new GeographicalLocationConverter().convert("10.34,a12.3");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_convert_whenStringOnlyHasOnePart() throws Exception {
        new GeographicalLocationConverter().convert("1000.334");
    }

    @Test
    public void test_convert_whenStringHasTwoDoubleParts() throws Exception {
        assertThat(new GeographicalLocationConverter().convert("100.334,3000.9"), allOf(
                hasProperty("latitude", is(100.334)),
                hasProperty("longitude", is(3000.9))
        ));
        assertThat(new GeographicalLocationConverter().convert("100.334, 3000.9"), allOf(
                hasProperty("latitude", is(100.334)),
                hasProperty("longitude", is(3000.9))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100.334,3000.9"), allOf(
                hasProperty("latitude", is(100.334)),
                hasProperty("longitude", is(3000.9))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100.334,3000.9 "), allOf(
                hasProperty("latitude", is(100.334)),
                hasProperty("longitude", is(3000.9))
        ));
    }

    @Test
    public void test_convert_whenStringHasTwoIntegerParts() throws Exception {
        assertThat(new GeographicalLocationConverter().convert("100,3000"), allOf(
                hasProperty("latitude", is(100.0)),
                hasProperty("longitude", is(3000.0))
        ));
        assertThat(new GeographicalLocationConverter().convert("100, 3000"), allOf(
                hasProperty("latitude", is(100.0)),
                hasProperty("longitude", is(3000.0))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100,3000"), allOf(
                hasProperty("latitude", is(100.0)),
                hasProperty("longitude", is(3000.0))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100,3000 "), allOf(
                hasProperty("latitude", is(100.0)),
                hasProperty("longitude", is(3000.0))
        ));
    }


}