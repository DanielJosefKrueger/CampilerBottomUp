package de.darktech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SprungTabelle {



    private final ArrayList<SprungTabelleEintrag> eintrage = new ArrayList<>();
    private final ArrayList<SprungTabelleEintrag> alreadyDone = new ArrayList<>();
    Map<String,String> mapping = new HashMap<String, String>();
    private int counter =0;

    public SprungTabelle(ErweiterteGrammatik erweiterteGrammatik){
        Grammatik grammatik = erweiterteGrammatik.getGrammatik();
        Character startSymbol = grammatik.getStartSymbol();
        LR0_Item startItem = new LR0_Item(erweiterteGrammatik.getFirstProduction(), 0);
        List<LR0_Item> arg = new ArrayList<>();
        arg.add(startItem);

        Huelle c0 = new Huelle(arg, grammatik);
        SprungTabelleEintrag eintrag = new SprungTabelleEintrag("C" + counter, c0, null, null);
        counter++;
        eintrage.add(eintrag);
        boolean changed = true;


        while(changed){
            int lengthOld = eintrage.size();
            for(int i=0; i < eintrage.size(); i++){
                expand(grammatik, eintrage.get(i));
            }
            changed = lengthOld!=(eintrage.size());
        }


    }





    private void expand(Grammatik grammatik, SprungTabelleEintrag eintrag) {
        if(alreadyDone.contains(eintrag)){
            System.out.println("Already done");
            return;
        }
        Map<Character, List<LR0_Item>> symbolToLR0Ttems = eintrag.getHuelle().groupBySymbolAfterPosition();
        for(Map.Entry<Character, List<LR0_Item>> entry: symbolToLR0Ttems.entrySet()){
            List<LR0_Item> items = entry.getValue();
            if(items.isEmpty()){
                throw new IllegalArgumentException("List may not be empty");
            }



            Huelle huelle = new Huelle(items, grammatik);


            SprungTabelleEintrag sprungTabelleEintrag = new SprungTabelleEintrag("C" + counter, huelle, eintrag, entry.getKey());
            mapping.put(eintrag.name+sprungTabelleEintrag.symbol, sprungTabelleEintrag.name);
            if(!eintrage.contains(sprungTabelleEintrag)){
                counter++;
                eintrage.add(sprungTabelleEintrag);

            }
        }
        alreadyDone.add(eintrag);
    }





    class SprungTabelleEintrag{


        private final String name;
        private final Huelle huelle;
        private final SprungTabelleEintrag from;
        private final Character symbol;


        SprungTabelleEintrag(String name, Huelle huelle, SprungTabelleEintrag from, Character symbol){

            this.huelle = huelle;
            this.name = name;

            this.from = from;
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public Huelle getHuelle() {
            return huelle;
        }

        public String toString(){
            return "Name: " + name + "\n Inhalt: " + huelle;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SprungTabelleEintrag that = (SprungTabelleEintrag) o;

            return huelle != null ? huelle.equals(that.huelle) : that.huelle == null;
        }

        @Override
        public int hashCode() {
            return huelle != null ? huelle.hashCode() : 0;
        }
    }


    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("SprungTabellenInhtalt");
        for(SprungTabelleEintrag  eintrag :eintrage){
            builder.append("\nEintrag:").append(eintrag.name).append(" \nSprung(").append(eintrag.from!=null?eintrag.from.name:"START")
                    .append(",").append(eintrag.symbol!=null?eintrag.symbol:"NONE").append(")");


            builder.append("\n").append(eintrag.huelle).append("\n\n");

        }
        return builder.toString();
    }





}
