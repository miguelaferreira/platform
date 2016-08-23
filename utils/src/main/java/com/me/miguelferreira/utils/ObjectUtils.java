package com.me.miguelferreira.utils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ObjectUtils {
    public static String toString(final Object object) {
        return ToStringBuilder.reflectionToString(object);
    }

    public static int hashCode(final Object object) {
        return HashCodeBuilder.reflectionHashCode(object);
    }

    public static boolean equals(final Object o1, final Object o2) {
        return EqualsBuilder.reflectionEquals(o1, o2);
    }
}
