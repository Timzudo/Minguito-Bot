package listeners;

import mambos.SystemInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class MerendasListener extends MinguitoListener {

    private static final String MERENDAS = "687683461523439644";

    public MerendasListener(SystemInterface s){
        super(s);
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().isFromGuild() && event.getGuild().getId().equals(MERENDAS)){
            int random = new Random().nextInt(400);
            if (random == 100) {
                event.getChannel().sendMessage("cala so a boca fds").queue();
            }
            String comando = processGetCommand(event);
            String prefixo = comando.substring(0, 1);
            if(prefixo.equals("$")){
                boolean action = true;
                switch (comando) {
                    case HELP:
                        processHelp(event);
                        break;
                    default:
                        action = false;
                        break;
                }
                if(action){
                    event.getMessage().addReaction(event.getGuild().getEmoteById("457683584610729990")).queue();
                }
            }
        }
    }

    private static void processHelp(MessageReceivedEvent event){
        EmbedBuilder eb = new EmbedBuilder();
        Color green = new Color(17, 128, 106, 255);
        eb.setColor(green);

        eb.setTitle(VERSIONMERENDAS);
        eb.addField("Mambos:"   , MAMBOSC, false);
        eb.addField("Equipas:"  , EQUIPASC, false);
        eb.addField("Som:"      , SOMC, false);
        eb.addField("Outros:"   , OUTROSC, false);
        eb.addField("Novidades:", NOVIDADES, false);
        event.getTextChannel().sendMessage(eb.build()).queue();
    }

}
