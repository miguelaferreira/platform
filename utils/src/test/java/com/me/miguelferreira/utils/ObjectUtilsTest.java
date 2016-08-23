package com.me.miguelferreira.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ObjectUtilsTest {

    private static final String SOME_TEXT = "someText";
    private static final String OTHER_TEXT = "otherText";
    private static final int SOME_INT = 1;
    private static final int OTHER_INT = 2;

    private final TestObject testObject1 = new TestObject(SOME_TEXT, SOME_INT);
    private final TestObject testObject2 = new TestObject(SOME_TEXT, SOME_INT);
    private final TestObject testObject3 = new TestObject(SOME_TEXT, SOME_INT);
    private final TestObject testObject4 = new TestObject(OTHER_TEXT, OTHER_INT);

    @Test
    public void toString_testObject() throws Exception {
        assertThat(testObject1.toString(), allOf(containsString(SOME_TEXT), containsString(Integer.toString(SOME_INT))));
    }

    @Test
    public void hashCode_onTheSameObjectProducesTheSameHash() throws Exception {
        assertThat(testObject1.hashCode(), is(testObject1.hashCode()));
    }

    @Test
    public void hashCode_twoEqualObjectsProduceTheSameHash() throws Exception {
        assertThat(testObject1, is(testObject2));
        assertThat(testObject1.hashCode(), is(testObject2.hashCode()));
    }

    @Test
    public void equals_isReflexive() throws Exception {
        assertThat(testObject1, is(testObject1));
    }

    @Test
    public void equals_isSymmetric() throws Exception {
        assertThat(testObject1, is(testObject2));
        assertThat(testObject2, is(testObject1));
    }

    @Test
    public void equals_isTransitive() throws Exception {
        assertThat(testObject1, is(testObject2));
        assertThat(testObject2, is(testObject3));
        assertThat(testObject1, is(testObject3));
    }

    @Test
    public void equals_isConsistent() throws Exception {
        assertThat(testObject1, is(testObject2));
        assertThat(testObject1, is(testObject2));
        assertThat(testObject1, is(testObject2));
    }

    @Test
    public void equals_isNeverTrueForNull() throws Exception {
        assertThat(testObject1.equals(null), is(false));
    }

    class TestObject {
        private final String textField;
        private final int integerNumber;

        public TestObject(final String textField, final int integerNumber) {
            this.textField = textField;
            this.integerNumber = integerNumber;
        }

        @Override
        public String toString() {
            return ObjectUtils.toString(this);
        }

        @Override
        public boolean equals(final Object o) {
            return ObjectUtils.equals(this, o);
        }

        @Override
        public int hashCode() {
            return ObjectUtils.hashCode(this);
        }
    }
}
