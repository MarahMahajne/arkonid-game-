/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 2
 * @since 2021-06-06
 */
public class Counter {
    private int counter;
    /**
     * This is an constructor for class "Counter".
     * <p>
     *  the constructor creates a new Counter object.
     * <p>
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * this function adds number to current count.
     * @param number - a number.
     */
    void increase(int number) {
        this.counter += number;
    }
    /**
     * this function subtracts number from current count.
     * @param number - a number.
     */
    void decrease(int number) {
        this.counter -= number;
    }
    /**
     * @return current count.
     */
    int getValue() {
        return (this.counter);
    }
}