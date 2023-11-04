import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {
    // Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Shell Sort
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Radix Sort
    public static void radixSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }

        int max = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int value : arr) {
            count[(value / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] sizes = {10000, 100000, 1000000};

        for (int size : sizes) {
            int[] arr = new int[size];
            Random random = new Random();

            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt();
            }

            int[] copyArr;

            // Execute each sorting algorithm 15 times
            for (int i = 0; i < 15; i++) {
                copyArr = Arrays.copyOf(arr, arr.length);
                long startTime, endTime;

                System.out.println("Array size: " + size + " - Iteration: " + (i + 1));

                // Selection Sort
                startTime = System.nanoTime();
                selectionSort(copyArr);
                endTime = System.nanoTime();
                System.out.println("Selection Sort Time: " + (endTime - startTime) + " nanoseconds");

                copyArr = Arrays.copyOf(arr, arr.length);

                // Bubble Sort
                startTime = System.nanoTime();
                bubbleSort(copyArr);
                endTime = System.nanoTime();
                System.out.println("Bubble Sort Time: " + (endTime - startTime) + " nanoseconds");

                copyArr = Arrays.copyOf(arr, arr.length);

                // Insertion Sort
                startTime = System.nanoTime();
                insertionSort(copyArr);
                endTime = System.nanoTime();
                System.out.println("Insertion Sort Time: " + (endTime - startTime) + " nanoseconds");

                copyArr = Arrays.copyOf(arr, arr.length);

                // Shell Sort
                startTime = System.nanoTime();
                shellSort(copyArr);
                endTime = System.nanoTime();
                System.out.println("Shell Sort Time: " + (endTime - startTime) + " nanoseconds");

                copyArr = Arrays.copyOf(arr, arr.length);

                // Merge Sort
                startTime = System.nanoTime();
                mergeSort(copyArr);
                endTime = System.nanoTime();
                System.out.println("Merge Sort Time: " + (endTime - startTime) + " nanoseconds");

                copyArr = Arrays.copyOf(arr, arr.length);

                // Quick Sort
                startTime = System.nanoTime();
                quickSort(copyArr, 0, copyArr.length - 1);
                endTime = System.nanoTime();
                System.out.println("Quick Sort Time: " + (endTime - startTime) + " nanoseconds");

                copyArr = Arrays.copyOf(arr, arr.length);

                // Radix Sort
                startTime = System.nanoTime();
                radixSort(copyArr);
                endTime = System.nanoTime();
                System.out.println("Radix Sort Time: " + (endTime - startTime) + " nanoseconds");

                System.out.println();
            }
        }
    }
}
