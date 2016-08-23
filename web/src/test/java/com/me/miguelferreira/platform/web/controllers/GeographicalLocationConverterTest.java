package com.me.miguelferreira.platform.web.controllers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(new GeographicalLocationConverter().convert("100.334,3000.9"), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.334)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.9))
        ));
        assertThat(new GeographicalLocationConverter().convert("100.334, 3000.9"), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.334)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.9))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100.334,3000.9"), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.334)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.9))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100.334,3000.9 "), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.334)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.9))
        ));
    }

    @Test
    public void test_convert_whenStringHasTwoIntegerParts() throws Exception {
        assertThat(new GeographicalLocationConverter().convert("100,3000"), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.0)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.0))
        ));
        assertThat(new GeographicalLocationConverter().convert("100, 3000"), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.0)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.0))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100,3000"), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.0)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.0))
        ));
        assertThat(new GeographicalLocationConverter().convert(" 100,3000 "), CoreMatchers.allOf(
                HasPropertyWithValue.hasProperty("latitude", is(100.0)),
                HasPropertyWithValue.hasProperty("longitude", is(3000.0))
        ));
    }


}
