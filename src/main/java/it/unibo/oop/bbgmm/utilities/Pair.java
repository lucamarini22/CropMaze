package it.unibo.oop.bbgmm.utilities;

/**
 * Utility class for Pair.
 * @param <X>
 *          First Number
 * @param <Y>
 *          Second Number
 */
public final class Pair<X, Y> {

    private final X fst;
    private final Y snd;

    /**
     * Constructor for Pair.
     *
     * @param fst
     *          First Number
     * @param snd
     *          Second Number
     */
    public Pair(final X fst, final Y snd) {
        super();
        this.fst = fst;
        this.snd = snd;
    }

    /**
     * Getter for First Number.
     *
     * @return X
     *          First Number
     */
    public X getFst() {
        return fst;
    }

    /**
     * Getter for Second Number.
     *
     * @return Y
     *          Second Number
     */
    public Y getSnd() {
        return snd;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.fst == null) ? 0 : this.fst.hashCode());
        result = prime * result + ((this.snd == null) ? 0 : this.snd.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.fst == null) {
            if (other.fst != null) {
                return false;
            }
        } else if (!this.fst.equals(other.fst)) {
            return false;
        }
        if (this.snd == null) {
            if (other.snd != null) {
                return false;
            }
        } else if (!snd.equals(other.snd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.fst + ";" + this.snd + "]";
    }
}

