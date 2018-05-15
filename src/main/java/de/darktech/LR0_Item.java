package de.darktech;

public class LR0_Item {
    private final Production production;
    private final int position;


    public LR0_Item(Production production, int position) {
        if(position>production.getRight().length()){
            throw new IllegalArgumentException("Position may not be greather than the length of the production");
        }

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
        if(afterPosition()==null){
            return production.getLeft() + " ::= " +production.getRight().substring(0, position-1) + ".";
        }else{
            return production.getLeft() + " ::= " +production.getRight().substring(0, position) + "." + production.getRight().substring(position,production.getRight().length());
        }
    }


    public Character afterPosition(){
        if(position<production.getRight().length()){
            return production.getRight().charAt(position);
        }else{
            return null;
        }
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


    LR0_Item getItemWithIncreasedPosition(){
        return new LR0_Item(production, this.position+1);
    }
}
