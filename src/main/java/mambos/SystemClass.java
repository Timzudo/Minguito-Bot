package mambos;

import Exceptions.InvalidNumberException;
import net.dv8tion.jda.api.entities.Message;

import java.util.*;

public class SystemClass implements SystemInterface{

    private Message.Attachment attachment;

    private List<String> vibeList, piaoList, mcList, feedList, kiriList;
    private Map<String, String> pollList;

    public SystemClass() {
        attachment = null;

        pollList = new HashMap<>();
        vibeList = new ArrayList<>();
        piaoList = new ArrayList<>();
        mcList = new ArrayList<>();
        kiriList = new ArrayList<>();
        feedList = new ArrayList<>();
    }

    @Override
    public boolean isPoll(String id) {
        return pollList.containsKey(id);
    }

    @Override
    public void addPoll(String id, String name) {
        pollList.put(id, name);
    }

    @Override
    public void addVibe(String vibe) {
        vibeList.add(vibe);
    }

    @Override
    public void addPiao(String piao) {
        piaoList.add(piao);
    }

    @Override
    public void addMc(String mc) {
        mcList.add(mc);
    }

    @Override
    public void addFeed(String feed) {
        feedList.add(feed);
    }

    @Override
    public void addKiri(String kiri) {
        kiriList.add(kiri);
    }

    @Override
    public void setAttachment(Message.Attachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public Message.Attachment getAttachment() {
        return attachment;
    }

    @Override
    public String getPoll(String id) {
        return pollList.get(id);
    }

    @Override
    public String getVibe() {
        int random = new Random().nextInt(vibeList.size());
        return vibeList.get(random);
    }

    @Override
    public String getPiao() {
        int random = new Random().nextInt(piaoList.size());
        return piaoList.get(random);
    }

    @Override
    public String getMc() {
        int random = new Random().nextInt(mcList.size());
        return mcList.get(random);
    }

    @Override
    public String getFeed() {
        int random = new Random().nextInt(feedList.size());
        return feedList.get(random);
    }

    @Override
    public String getFeliz() {
        int random = new Random().nextInt(101);
        String feliz = null;

        if(random<=20){
            feliz = random + "% - J ta bue triste";
        }
        if(random>20 && random<=40){
            feliz = random + "% - J ta triste";
        }
        if(random>40 && random<=60){
            feliz = random + "% - J ta com mood normal";
        }
        if(random>60 && random<=80){
            feliz = random + "% - J ta com vibe feliz";
        }
        if(random>80){
            feliz = random + "% - J ta super contente";
        }
        return feliz;
    }

    @Override
    public String getKiri() {
        int random = new Random().nextInt(kiriList.size());
        return kiriList.get(random);
    }

    @Override
    public Pair<Iterator<String>, Iterator<String>> getTeams(List<String> teamList) throws InvalidNumberException {

        if((teamList.size() > 21) || teamList.size() < 2){
            throw new InvalidNumberException();
        }

        LinkedList<String> teamA, teamB;

        Collections.shuffle(teamList);

        teamA = new LinkedList<>();
        teamB = new LinkedList<>();

        for(int i = 0; i<(teamList.size()/2); i++){
            teamA.add(teamList.get(i));
        }

        for(int i = (teamList.size()/2); i<teamList.size(); i++){
            teamB.add(teamList.get(i));
        }

        Pair pair = new PairClass(teamB.iterator(), teamA.iterator());

        return pair;
    }

}
