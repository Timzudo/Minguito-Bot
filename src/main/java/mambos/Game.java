package mambos;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Game {

    MessageReceivedEvent event;

    private static final String GUESS = "https://cdn.discordapp.com/attachments/825942456708497449/825945108599537724/start.png";
    private static final String LEFT = "https://cdn.discordapp.com/attachments/825942456708497449/825950053382357022/left.png";
    private static final String RIGHT = "https://cdn.discordapp.com/attachments/825942456708497449/825950049140867092/right.png";

    private static final Color GREEN = new Color(17, 128, 106, 255);

    private static final String LEFTEMOJI = "RE:U+25c0U+fe0f";
    private static final String RIGHTEMOJI = "RE:U+25b6U+fe0f";

    private Message m;


    public Game(MessageReceivedEvent event){
        this.event = event;
    }

    public void start() throws InterruptedException {

        Message m = event.getTextChannel().sendMessage(guessEmbed().build()).complete();
        this.m = m;
        Random rand = new Random();
        int randInt = rand.nextInt(2);
        String emoji;
        EmbedBuilder eb;
        if(randInt == 0){
            emoji = LEFTEMOJI;
            eb = leftEmbed();
        }
        else{
            emoji = RIGHTEMOJI;
            eb = rightEmbed();
        }
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        end(eb, emoji);
                    }
                },
                10000
        );
    }

    public void end(EmbedBuilder eb, String emoji){
        m = event.getTextChannel().retrieveMessageById(m.getId()).complete();

        m.editMessage(eb.build()).queue();
        StringBuilder builder = new StringBuilder();
        for(MessageReaction r : m.getReactions()){
            if(r.getReactionEmote().toString().equals(emoji)){
                List<User> users = r.retrieveUsers().complete();
                for(User u : users){
                    builder.append(u.getName() + "\n");
                }
            }
        }
        eb.setTitle("Wis que ganharam");
        eb.addField("", builder.toString(), false);


        m.editMessage(eb.build()).queue();
    }


    private EmbedBuilder gameEmbed(){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(GREEN);
        return eb;
    }

    private EmbedBuilder leftEmbed(){
        EmbedBuilder eb = gameEmbed();
        eb.setImage("https://cdn.discordapp.com/attachments/825942456708497449/825950053382357022/left.png");
        return eb;
    }

    private EmbedBuilder rightEmbed(){
        EmbedBuilder eb = gameEmbed();
        eb.setImage("https://cdn.discordapp.com/attachments/825942456708497449/825950049140867092/right.png");
        return eb;
    }

    private EmbedBuilder guessEmbed(){
        EmbedBuilder eb = gameEmbed();
        eb.setTitle("Fds");
        eb.setImage("https://cdn.discordapp.com/attachments/825942456708497449/825945108599537724/start.png");
        return eb;
    }




}
