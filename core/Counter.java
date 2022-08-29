package core;

// Ofir Sasoni
// 325690386

/**
 * The class "Counter" represents an object which can count the amount of things (for example, the amount of
 * remaining blocks/balls.
 */
public class Counter {
    private int value;

    /**
     * Constructor.
     * @param value the current value of the counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Get method.
     * @return the value.
     */
    public int getValue() {
        return value;
    }

    /**
     * The function increases the value by the given number.
     * @param number the number we want to increase our value by.
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * The function decrease the value by the given number.
     * @param number the number we want to decrease our value by.
     */
    public void decrease(int number) {
        value -= number;
    }
}
