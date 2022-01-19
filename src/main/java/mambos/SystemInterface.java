package mambos;

import net.dv8tion.jda.api.entities.Message;

import java.util.Iterator;
import java.util.List;

public interface SystemInterface {

    boolean isPoll(String id);

    void addPoll(String id, String name);

    void addVibe(String vibe);

    void addPiao(String piao);

    void addMc(String mc);

    void addFeed(String feed);

    void addKiri(String kiri);

    void setAttachment(Message.Attachment attachment);

    Message.Attachment getAttachment();

    public String getPoll(String id);

    String getVibe();

    String getPiao();

    String getMc();

    String getFeed();

    String getFeliz();

    String getKiri();

    Pair<Iterator<String>, Iterator<String>> getTeams(List<String> teamList);

}
