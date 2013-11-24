package com.zimek.equalshashcode;

import java.awt.Color;

/**
 * Example of properly implemented equals and hashCode method.
 * 
 * The equals method implements an equivalence relation:
 * It is reflexive: for any reference value x, x.equals(x) should return true.
 * It is symmetric: for any reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
 * It is transitive: for any reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, 
 * 					 then x.equals(z) should return true.
 * It is consistent: for any reference values x and y, multiple invocations of x.equals(y) consistently return true or 
 * 					 consistently return false, provided no information used in equals comparisons on the object is modified.
 * For any non-null reference value x, x.equals(null) should return false.
 * 
 * 
 * Condition				Required				Not Required (but allowed)
 * x.equals(y) == true		x.hashCode() ==			
 * 							y.hahsCode()
 * x.hashCode() ==									x.equals(y) == true
 * y.hashCode()
 * 
 * x.equals(y) == false								No hashCode() requirements
 * 
 * x.hahsCode() !=			x.equals(y) == false	
 * y.hashCode()
 * 
 */
class Point {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other == this) {
			return true;
		}
		if (other instanceof Point) {
			Point that = (Point) other;
			result = (that.canEqual(this) && this.getX() == that.getX() && this.getY() == that.getY());
		}
		return result;
	}

	@Override
	public int hashCode() {
		return (41 * (41 + getX()) + getY());
	}

	public boolean canEqual(Object other) {
		return (other instanceof Point);
	}
}

/**
 * Example of properly implemented equals and hashCode method in 
 * a subclass which adds a property to base class.
 */
class ColoredPoint extends Point {

	private final Color color;

	public ColoredPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other == this) {
			return true;
		}
		if (other instanceof ColoredPoint) {
			ColoredPoint that = (ColoredPoint) other;
			result = (that.canEqual(this) && this.color.equals(that.color) && super.equals(that));
		}
		return result;
	}

	@Override
	public int hashCode() {
		return (41 * super.hashCode() + color.hashCode());
	}

	@Override
	public boolean canEqual(Object other) {
		return (other instanceof ColoredPoint);
	}
}

public class EqualsHashCodeExample {
	
	public static void main(String [] args) {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 3);
		Point p3 = new Point(2, 3);
		Point p4 = new Point(2, 3);
		ColoredPoint p5 = new ColoredPoint(2, 3, Color.RED);
		ColoredPoint p6 = new ColoredPoint(2, 3, Color.RED);
		System.out.println(p2.equals(p3));
		System.out.println(p3.equals(p4));
		System.out.println(p2.equals(p4));
		System.out.println(p2.equals(p5));
		System.out.println(p5.equals(p6));
		System.out.println(p5.equals(p1));
		System.out.println(p5.equals(null));
		System.out.println(p5.equals(p5));
	}
}
