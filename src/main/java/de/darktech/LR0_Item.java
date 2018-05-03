package de.darktech;

public class LR0_Item {
    private final Production production;
    private final int position;


    public LR0_Item(Production production, int position) {
        this.production = production;
        this.position = position;
    }


    public Production getProduction() {
        return production;
    }

    public int getPosition() {
        return position;
    }


    public String toString(){
        return production.getLeft() + " ::= " +production.getRight().substring(0, position) + "." + production.getRight().substring(position,production.getRight().length());
    }


    public char afterPosition(){
        return production.getRight().charAt(position);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LR0_Item lr0_item = (LR0_Item) o;

        if (position != lr0_item.position) return false;
        return production != null ? production.equals(lr0_item.production) : lr0_item.production == null;
    }

    @Override
    public int hashCode() {
        int result = production != null ? production.hashCode() : 0;
        result = 31 * result + position;
        return result;
    }
}
