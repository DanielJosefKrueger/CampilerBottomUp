package de.darktech;

import java.util.*;

public class Huelle {

    private final List<LR0_Item> I;
    private final List<LR0_Item> items;
    private final Grammatik grammatik;


    public Huelle(List<LR0_Item> i, Grammatik grammatik) {
        I = i;
        this.grammatik = grammatik;
        this.items = new ArrayList<>();
        generate();
    }

    private void generate() {
        //alle I in Hülle einfügen
        //because of recursion it must be checked for dpulication
        // a bit complicated because it is a list
        for(LR0_Item toAdd: I){
            if(!items.contains(toAdd)) {
                this.items.add(toAdd);
            }
        }

       // for(LR0_Item lr0_item: items){
        for(int i =0; i < items.size(); i++){
            LR0_Item lr0_item=   items.get(i);

            List<Production> productions = grammatik.getProductionsForLeftSide("" + lr0_item.afterPosition());

            int sizeBefore = items.size();
            for(Production production: productions){
                LR0_Item news = new LR0_Item(production, 0);
                if(!items.contains(news)) {
                    this.items.add(news);
                }

            }

            //recursive
            if(sizeBefore != items.size()){
                //something new has appeared. reGenerate
                generate();
            }

        }
    }


    public List<LR0_Item> getI() {
        return I;
    }

    public List<LR0_Item> getItems() {
        return items;
    }

    public Grammatik getGrammatik() {
        return grammatik;
    }

    public String toString(){
        String ret =  "Hülle (";

        boolean first = true;

        for(LR0_Item item:I){
            if(!first){
                ret+=" und ";
            }
            first = false;
            ret+= item;
        }



        ret += ") = " + "{\n";
        for(LR0_Item lr0_item: items){
            ret += "\t" + lr0_item + "\n";
        }
        return ret +"}";
    }


    Map<Character, List<LR0_Item>> groupBySymbolAfterPosition() {
        Map<Character, List<LR0_Item>>  mapping = new HashMap<>();
        for(LR0_Item item: items){
            Character afterPosition = item.afterPosition();
            if(afterPosition==null){
                continue; // the position is at the very end
            }

            if(mapping.containsKey(afterPosition)){
                mapping.get(afterPosition).add(item);
            }else{
                ArrayList<LR0_Item> listing = new ArrayList<>();
                listing.add(item);
                mapping.put(afterPosition, listing);
            }
        }
        return mapping;
    }


    public Huelle merge (Huelle other){
        this.I.addAll(other.I);
        this.items.addAll(other.items);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Huelle huelle = (Huelle) o;

        if (I != null ? !I.equals(huelle.I) : huelle.I != null) return false;
        if (items != null ? !items.equals(huelle.items) : huelle.items != null) return false;
        return grammatik != null ? grammatik.equals(huelle.grammatik) : huelle.grammatik == null;
    }

    @Override
    public int hashCode() {
        int result = I != null ? I.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (grammatik != null ? grammatik.hashCode() : 0);
        return result;
    }
}
