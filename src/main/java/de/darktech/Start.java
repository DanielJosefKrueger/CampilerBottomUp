package de.darktech;

import java.util.ArrayList;
import java.util.List;

public class Start {


    public static void main(String[] args) {


        List<Character> terminals = new ArrayList<>();
        terminals.add('(');
        terminals.add('*');
        terminals.add('+');
        terminals.add(')');
        terminals.add('i');
        List<Character> nonterminals = new ArrayList<>();
        nonterminals.add('E');
        nonterminals.add('T');
        nonterminals.add('F');
        List<Production> productions = new ArrayList<>();
        productions.add(new Production("E", "E+T"));
        productions.add(new Production("E", "T"));
        productions.add(new Production("T", "T*F"));
        productions.add(new Production("T", "F"));
        productions.add(new Production("F", "i"));
        productions.add(new Production("F", "(E)"));
        Character startSymbol = 'E';
        Grammatik grammatik = new Grammatik(terminals, nonterminals, startSymbol, productions);
     /*   LR0_Item test = new LR0_Item(new Production("E", "E+T") , 2);

        ArrayList<LR0_Item> I = new ArrayList<>();
        I.add(test);
        Huelle test1 = new Huelle(I,grammatik);
        System.out.println(test1);*/


        ErweiterteGrammatik erweiterteGrammatik = new ErweiterteGrammatik(grammatik);
        SprungTabelle sprungTabelle = new SprungTabelle(erweiterteGrammatik);
        System.out.println(sprungTabelle);

    }


}
