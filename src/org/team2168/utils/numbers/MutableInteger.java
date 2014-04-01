package org.team2168.utils.numbers;


/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
* 
*      http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/**
* A mutable <code>int</code> wrapper.
* 
* @see Integer
* @since 2.1
* @version $Id: MutableInteger.java 618693 2008-02-05 16:33:29Z sebb $
*/
public class MutableInteger{



/** The mutable value. */
private int value;

/**
* Constructs a new MutableInteger with the default value of zero.
*/
public MutableInteger() {
 super();
}

/**
* Constructs a new MutableInteger with the specified value.
* 
* @param value
*          a value.
*/
public MutableInteger(int value) {
 super();
 this.value = value;
}



// -----------------------------------------------------------------------
/**
* Gets the value as a Integer instance.
* 
* @return the value as a Integer
*/
public Object getValue() {
 return new Integer(this.value);
}

/**
* Sets the value.
* 
* @param value
*          the value to set
*/
public void setValue(int value) {
 this.value = value;
}



// -----------------------------------------------------------------------
// shortValue and bytValue rely on Number implementation
/**
* Returns the value of this MutableInteger as a int.
* 
* @return the numeric value represented by this object after conversion to
*         type int.
*/
public int intValue() {
 return (int) value;
}

/**
* Returns the value of this MutableInteger as a long.
* 
* @return the numeric value represented by this object after conversion to
*         type long.
*/
public long longValue() {
 return (long) value;
}

/**
* Returns the value of this MutableInteger as a float.
* 
* @return the numeric value represented by this object after conversion to
*         type float.
*/
public float floatValue() {
 return (float) value;
}


/**
* Checks whether the int value is the special NaN value.
* 
* @return true if NaN
*/
public boolean isNaN() {
 return Double.isNaN(value);
}

/**
* Checks whether the int value is infinite.
* 
* @return true if infinite
*/
public boolean isInfinite() {
 return Double.isInfinite(value);
}

// -----------------------------------------------------------------------
/**
* Gets this mutable as an instance of Integer.
* 
* @return a Integer instance containing the value from this mutable
*/
public Integer toInteger() {
 return new Integer(intValue());
}

// -----------------------------------------------------------------------
/**
* Increments the value.
* 
* @since Commons Lang 2.2
*/
public void increment() {
 value++;
}

/**
* Decrements the value.
* 
* @since Commons Lang 2.2
*/
public void decrement() {
 value--;
}

// -----------------------------------------------------------------------
/**
* Adds a value.
* 
* @param operand
*          the value to add
* 
* @since Commons Lang 2.2
*/
public void add(int operand) {
 this.value += operand;
}


/**
* Subtracts a value.
* 
* @param operand
*          the value to add
* 
* @since Commons Lang 2.2
*/
public void subtract(int operand) {
 this.value -= operand;
}


/**
* Returns the String value of this mutable.
* 
* @return the mutable value as a string
*/
public String toString() {
 return String.valueOf(value);
}

}