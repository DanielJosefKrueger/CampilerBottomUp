package de.darktech;

public class Production {

    private final String left;
    private final String right;


    public Production(String left, String right) {
        this.left = left;
        this.right = right;
    }


    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }


    public String toString(){
        return left+ " ::= " +right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        return right != null ? right.equals(that.right) : that.right == null;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
