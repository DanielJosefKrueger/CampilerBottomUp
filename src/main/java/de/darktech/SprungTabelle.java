package de.darktech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SprungTabelle {



    private final ArrayList<SprungTabelleEintrag> eintrage = new ArrayList<>();
    private int counter =0;

    public SprungTabelle(ErweiterteGrammatik erweiterteGrammatik){
        Grammatik grammatik = erweiterteGrammatik.getGrammatik();
        Character startSymbol = grammatik.getStartSymbol();
        LR0_Item startItem = new LR0_Item(erweiterteGrammatik.getFirstProduction(), 0);
        List<LR0_Item> arg = new ArrayList<>();
        arg.add(startItem);

        Huelle c0 = new Huelle(arg, grammatik);
        SprungTabelleEintrag eintrag = new SprungTabelleEintrag("C" + counter, c0);
        counter++;
        System.out.println(eintrag);
        eintrage.add(eintrag);

        Map<Character, List<LR0_Item>> symbolToLR0Ttems = eintrag.getHuelle().groupBySymbolAfterPosition();



    }










    class SprungTabelleEintrag{


        private final String name;
        private final Huelle huelle;


        SprungTabelleEintrag(String name, Huelle huelle){

            this.huelle = huelle;
            this.name = name;

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



    }






}
