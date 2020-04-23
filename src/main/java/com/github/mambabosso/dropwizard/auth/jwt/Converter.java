package com.github.mambabosso.dropwizard.auth.jwt;

/**
 * Convert value 1 to value 2 and vice versa
 * @param <T1> Type of value 1
 * @param <T2> Type of value 2
 */
public interface Converter<T1, T2> {

    /**
     * Convert value 1 to value 2
     * @param value Value 1
     * @return Converted value 2
     */
    public T2 to(T1 value);

    /**
     * Convert value 2 to value 1
     * @param value Value 2
     * @return Converted value 1
     */
    public T1 from(T2 value);

}
