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

            Character afterPosition = lr0_item.afterPosition();
            if(afterPosition==null){
                continue; // we position is at the very end of the right side of the production
            }

            List<Production> productions = grammatik.getProductionsForLeftSide("" + afterPosition);

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
        String ret =  "Hülle (" + I + ") = " + "{";
        for(LR0_Item lr0_item: items){
            ret += lr0_item + "\n";
        }
        return ret +"}";
    }


    Map<Character, List<LR0_Item>> groupBySymbolAfterPosition() {
        Map<Character, List<LR0_Item>>  mapping = new HashMap<>();
        for(LR0_Item item: items){
            Character afterPosition = item.afterPosition();
            if(mapping.containsKey(afterPosition)){
                mapping.get(afterPosition).add(item.getDuplikateWithIncreasedPosition());
            }else{
                ArrayList<LR0_Item> listing = new ArrayList<>();
                listing.add(item.getDuplikateWithIncreasedPosition());
                mapping.put(afterPosition, listing);
            }
        }
        return mapping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Huelle huelle = (Huelle) o;


        if(this.items.size()!=huelle.items.size()){
            return false;
        }

        if(this.I.size()!=huelle.I.size()){
            return false;
        }


        if (I != null ? !I.containsAll(huelle.I) : huelle.I != null) return false;
        return items != null ? items.containsAll(huelle.items) : huelle.items == null;
    }

    @Override
    public int hashCode() {
        int result = I != null ? I.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    public Huelle merge (Huelle other){
        this.I.addAll(other.I);
        this.items.addAll(other.items);
        return this;
    }



}
