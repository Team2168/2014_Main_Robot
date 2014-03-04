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
* A mutable <code>double</code> wrapper.
* 
* @see Double
* @since 2.1
* @version $Id: MutableDouble.java 618693 2008-02-05 16:33:29Z sebb $
*/
public class MutableDouble implements Mutable {



/** The mutable value. */
private double value;

/**
* Constructs a new MutableDouble with the default value of zero.
*/
public MutableDouble() {
 super();
}

/**
* Constructs a new MutableDouble with the specified value.
* 
* @param value
*          a value.
*/
public MutableDouble(double value) {
 super();
 this.value = value;
}



// -----------------------------------------------------------------------
/**
* Gets the value as a Double instance.
* 
* @return the value as a Double
*/
public Object getValue() {
 return new Double(this.value);
}

/**
* Sets the value.
* 
* @param value
*          the value to set
*/
public void setValue(double value) {
 this.value = value;
}

/**
* Sets the value from any Number instance.
* 
* @param value
*          the value to set
* @throws NullPointerException
*           if the object is null
* @throws ClassCastException
*           if the type is not a {@link Number}
*/
public void setValue(Object value) {
 setValue(((Double) value).doubleValue());
}

// -----------------------------------------------------------------------
// shortValue and bytValue rely on Number implementation
/**
* Returns the value of this MutableDouble as a int.
* 
* @return the numeric value represented by this object after conversion to
*         type int.
*/
public int intValue() {
 return (int) value;
}

/**
* Returns the value of this MutableDouble as a long.
* 
* @return the numeric value represented by this object after conversion to
*         type long.
*/
public long longValue() {
 return (long) value;
}

/**
* Returns the value of this MutableDouble as a float.
* 
* @return the numeric value represented by this object after conversion to
*         type float.
*/
public float floatValue() {
 return (float) value;
}

/**
* Returns the value of this MutableDouble as a double.
* 
* @return the numeric value represented by this object after conversion to
*         type double.
*/
public double doubleValue() {
 return value;
}

/**
* Checks whether the double value is the special NaN value.
* 
* @return true if NaN
*/
public boolean isNaN() {
 return Double.isNaN(value);
}

/**
* Checks whether the double value is infinite.
* 
* @return true if infinite
*/
public boolean isInfinite() {
 return Double.isInfinite(value);
}

// -----------------------------------------------------------------------
/**
* Gets this mutable as an instance of Double.
* 
* @return a Double instance containing the value from this mutable
*/
public Double toDouble() {
 return new Double(doubleValue());
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
public void add(double operand) {
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
public void subtract(double operand) {
 this.value -= operand;
}




/**
* Returns a suitable hashcode for this mutable.
* 
* @return a suitable hashcode
*/
public int hashCode() {
 long bits = Double.doubleToLongBits(value);
 return (int) (bits ^ (bits >>> 32));
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

/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements. See the NOTICE file distributed with this
* work for additional information regarding copyright ownership. The ASF
* licenses this file to You under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
* http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations under
* the License.
*/

/**
* Provides mutable access to a value.
* <p>
* <code>Mutable</code> is used as a generic interface to the implementations
* in this package.
* <p>
* A typical use case would be to enable a primitive or string to be passed to a
* method and allow that method to effectively change the value of the
* primitive/string. Another use case is to store a frequently changing
* primitive in a collection (for example a total in a map) without needing to
* create new Integer/Long wrapper objects.
* 
* @author Matthew Hawthorne
* @since 2.1
* @version $Id: Mutable.java 618693 2008-02-05 16:33:29Z sebb $
*/
interface Mutable {

/**
* Gets the value of this mutable.
* 
* @return the stored value
*/
Object getValue();

/**
* Sets the value of this mutable.
* 
* @param value
*          the value to store
* @throws NullPointerException
*           if the object is null and null is invalid
* @throws ClassCastException
*           if the type is invalid
*/
void setValue(Object value);

}