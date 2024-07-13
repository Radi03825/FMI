package com.geometry.utils;

import com.geometry.types.Circle;
import com.geometry.types.Cylinder;
import com.geometry.types.Point;

import java.util.Random;

public class SelectionSortTest {
    private static Comparable[] arrComparable = new Comparable[9];

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(10, 51);
            int y = random.nextInt(10, 51);
            Point point = new Point(x, y);

            arrComparable[i] = point;
        }

        for (int i = 3; i < 6; i++) {
            Point point = (Point) arrComparable[i - 3];
            int radius = random.nextInt(10,31);
            Circle circle = new Circle(point.getX(), point.getY(), radius);

            arrComparable[i] = circle;
        }

        for (int i = 6; i < 9; i++) {
            Circle circle = (Circle) arrComparable[i - 3];
            int height = random.nextInt(10, 61);
            Cylinder cylinder = new Cylinder(circle.getX(), circle.getY(), circle.getRadius(), height);

            arrComparable[i] = cylinder;
        }

        System.out.println("Unsorted array:");
        printArray();

        System.out.println("\nSorted array:");
        SelectionSort.sortArray(arrComparable);
        printArray();
    }

    private static void printArray() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrComparable.length; i++) {
            sb.append(arrComparable[i]);

            if (i != arrComparable.length - 1) {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
