// IMyAidlInterface.aidl
package com.faustusz.test;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(in int anInt, in long aLong, in boolean aBoolean, in float aFloat,
            in double aDouble, in String aString);
}
