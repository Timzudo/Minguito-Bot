package listeners;

import mambos.SystemInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class BoaVibeListener extends MinguitoListener {

    private static final String BOAVIBE = "761008190091034635";

    private static final String ROCKYID = "283304461789822986";

    private static final String KIRI =  "$kiri";
    private static final String UWU =   "uwu";


    public BoaVibeListener(SystemInterface s){
        super(s);
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().isFromGuild() && event.getGuild().getId().equals(BOAVIBE)){
            if(!event.getAuthor().isBot() && (event.getMessage().getContentRaw().length() != 0)){
                Message msg = event.getMessage();
                String comando = processGetCommand(event);
                String prefixo = comando.substring(0, 1);
                if(prefixo.equals("$")){
                    boolean action = true;
                    switch (comando) {
                        case KIRI:
                            processKiri(event, s);
                            break;
                        case HELP:
                            processHelp(event);
                            break;
                        default:
                            action = false;
                            break;
                    }
                    if(action){
                        event.getMessage().addReaction(event.getGuild().getEmoteById("806964637123477515")).queue();
                    }
                }
                if(msg.getContentRaw().equalsIgnoreCase(UWU) && event.getMember().getId().equals(ROCKYID)){
                    event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/761008190091034638/784462592928120842/isnowillegal.gif").queue();
                }
            }

        }
    }

    private static void processKiri(MessageReceivedEvent event, SystemInterface s){
        event.getChannel().sendMessage(s.getKiri()).queue();
    }

    private static void processHelp(MessageReceivedEvent event){
        EmbedBuilder eb = new EmbedBuilder();
        Color green = new Color(17, 128, 106, 255);
        eb.setColor(green);

        eb.setTitle(VERSIONBOAVIBE);
        eb.addField("Mambos:"   , MAMBOSC, false);
        eb.addField("Equipas:"  , EQUIPASC, false);
        eb.addField("Som:"      , SOMC, false);
        eb.addField("Outros:"   , OUTROSCBOAVIBE, false);
        eb.addField("Novidades:", NOVIDADES, false);
        event.getTextChannel().sendMessage(eb.build()).queue();
    }

}
