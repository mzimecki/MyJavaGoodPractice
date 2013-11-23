package com.zimek.equalshashcode;

import java.awt.Color;

/**
 * Example of properly implemented equals and hashCode method.
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
	}
}
