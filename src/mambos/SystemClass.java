package mambos;

import Exceptions.*;
import java.util.*;

public class SystemClass implements SystemInterface{

    private Map<String, User> userList;
    private List<String> frases;



    public SystemClass(){
        userList = new HashMap<>();
        frases = new LinkedList<>();
        frases.add("vou te mutar");
        frases.add("im carring the game");
        frases.add("brand is trollong");
        frases.add("ashe is so feeded");
        frases.add("you mather fucketr stop feed");
        frases.add("skatter");
        frases.add("chug");
        frases.add("im cumming");
        frases.add("ta calado hugo");
        frases.add("rip mid");
        frases.add("dorime");
        frases.add("vou buscar as batatas");
        frases.add("preta vagabunda");
    }

    @Override
    public String getFrase() {
        int random = (int)(Math.random() * frases.size()-1);

        return frases.get(random);
    }

    @Override
    public LinkedList<Iterator<String>> getTeams(String[] names) throws InvalidNumberException {

        if((names.length > 20) || names.length < 2){
            throw new InvalidNumberException();
        }

        LinkedList<String> teamList, teamA, teamB;
        teamList = new LinkedList<>();

        for(int i = 1; i < names.length; i++){
            teamList.add(names[i]);
        }

        Collections.shuffle(teamList);

        teamA = new LinkedList<>();
        teamB = new LinkedList<>();

        for(int i = 0; i<(teamList.size()/2); i++){
            teamA.add(teamList.get(i));
        }

        for(int i = (teamList.size()/2); i<teamList.size(); i++){
            teamB.add(teamList.get(i));
        }

        LinkedList<Iterator<String>> iteratorList = new LinkedList<>();

        iteratorList.add(teamB.iterator());
        iteratorList.add(teamA.iterator());

        return iteratorList;


    }


}
