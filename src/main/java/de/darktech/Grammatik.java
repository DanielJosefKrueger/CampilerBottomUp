package de.darktech;

import java.util.ArrayList;
import java.util.List;

public class Grammatik {

    private final List<Character> terminals;
    private final List<Character> nonterminals;
    private final Character startSymbol;
    private final List<Production> productions;


    public Grammatik(List<Character> terminals, List<Character> nonterminals, Character startSymbol, List<Production> productions) {
        this.terminals = terminals;
        this.nonterminals = nonterminals;
        this.startSymbol = startSymbol;
        this.productions = productions;
    }

    public List<Character> getTerminals() {
        return terminals;
    }

    public List<Character> getNonterminals() {
        return nonterminals;
    }

    public Character getStartSymbol() {
        return startSymbol;
    }

    public List<Production> getProductions() {
        return productions;
    }


    public boolean isTerminal(Character c){
        return terminals.contains(c);
    }

    public boolean isNonTerminal(Character c){
        return nonterminals.contains(c);
    }


    public List<Production> getProductionsForLeftSide(String c){
        List<Production> returns = new ArrayList<Production>();
        productions.stream().filter(e->e.getLeft().equals(c)).forEach(returns::add);
        return returns;
    }

}
