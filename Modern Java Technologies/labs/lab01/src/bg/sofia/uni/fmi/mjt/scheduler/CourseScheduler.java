package bg.sofia.uni.fmi.mjt.scheduler;

public class CourseScheduler {
    public static int maxNonOverlappingCourses(int[][] courses) {
        if (courses.length < 2) {
            return courses.length;
        }

        customInsertionSort(courses);

        int minRemovedCourses = Integer.MAX_VALUE;

        for (int i = 0; i < courses.length; i++) {
            int removedCourses = getRemovedCourses(courses, i) + i;

            if (removedCourses < minRemovedCourses) {
                minRemovedCourses = removedCourses;
            }
        }

        return (courses.length - minRemovedCourses);
    }

    private static int getRemovedCourses(int[][] courses, int index) {
        int[] currentCourse = courses[index];
        int removedCourses = 0;

        for (int i = index + 1; i < courses.length; i++) {
            if (currentCourse[1] > courses[i][0]) {
                removedCourses++;
                continue;
            }

            currentCourse = courses[i];
        }

        return removedCourses;
    }

    public static void customInsertionSort(int[][] courses) {
        int length = courses.length;

        for (int i = 1; i < length; i++) {
            int[] currentCourse = courses[i];
            int j = i - 1;

            while (j >= 0 && (courses[j][0] > currentCourse[0] || (courses[j][0] == currentCourse[0] && courses[j][1] > currentCourse[1]))) {
                courses[j + 1] = courses[j];
                j = j - 1;
            }

            courses[j + 1] = currentCourse;
        }
    }

    public static void main(String[] args) {
        System.out.println(maxNonOverlappingCourses(new int[][]{}));
        System.out.println(maxNonOverlappingCourses(new int[][]{{9, 15}, {10, 12}, {12, 15}}));
        System.out.println(maxNonOverlappingCourses(new int[][]{{9, 11}, {10, 12}, {11, 13}, {15, 16}}));
        System.out.println(maxNonOverlappingCourses(new int[][]{{19, 22}, {17, 19}, {9, 12}, {9, 11}, {15, 17}, {15, 17}}));
        System.out.println(maxNonOverlappingCourses(new int[][]{{19, 22}}));
        System.out.println(maxNonOverlappingCourses(new int[][]{{13, 15}, {13, 17}, {11, 17}}));
    }
    
}
