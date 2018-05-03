package de.darktech;

import jdk.nashorn.internal.ir.Terminal;

import java.util.ArrayList;
import java.util.List;

public class ErweiterteGrammatik {

    private final Grammatik grammatik;
    private final Production firstProduction;

    public ErweiterteGrammatik(Grammatik grammatik){

        List<Production> productionList = new ArrayList<>();
        char startSymbol = 'ß';
        productionList.addAll(grammatik.getProductions());
        firstProduction = new Production(startSymbol + "", grammatik.getStartSymbol() + "");
        productionList.add(firstProduction);
        List<Character> nonterminals = new ArrayList<>();
        nonterminals.addAll(grammatik.getNonterminals());
        nonterminals.add(startSymbol);
        this.grammatik= new Grammatik(grammatik.getTerminals(), nonterminals, startSymbol, productionList);
    }

    public Grammatik getGrammatik() {
        return grammatik;
    }


    public Production getFirstProduction() {
        return firstProduction;
    }
}
