import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.*;
import mambos.*;
import Exceptions.*;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter
{

    private static final String PING = "_ping";
    private static final String MINI = "_mini";
    private static final String CLASSICO = "_classico";
    private static final String MEDA = "_meda";
    private static final String FRASE = "_frase";
    private static final String EQUIPAS = "_equipas";
    private static final String SIMAO = "!play";
    private static final String PRETO = "_preto";
    private SystemInterface s = new SystemClass();

    public static void main(String[] args) throws LoginException
    {
        new JDABuilder(AccountType.BOT)
                .setToken("NzIzNjExNjEyNTU2MTY1MTYx.Xu0S0g.PKWHdWKRthVP5-0XBEQfsyyGThw")
                .addEventListeners(new Main())
                .setActivity(Activity.listening("Mc Timzudo"))
                .build();
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {

        Message msg = event.getMessage();
        String msgContent = msg.getContentRaw();
        String[] id = msgContent.split(" ", 2);


        switch (id[0]){
            case PING:
                processPing(event);
                break;

            case MINI:
                processMini(event);
                break;

            case CLASSICO:
                processClassico(event);
                break;

            case MEDA:
                processMeda(event);
                break;

            case FRASE:
                processFrase(event, s);
                break;

            case EQUIPAS:
                processEquipas(event, s);
                break;

            case SIMAO:
                processSimao(event);
                break;

            case PRETO:
                processPreto(event);
                break;

            default:
                break;
        }
    }

    private static void processPing(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();
        channel.sendMessage("Pong!") /* => RestAction<Message> */.queue(response /* => Message */ -> { response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue(); });
    }

    private static void processMini(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        channel.sendMessage("Juro para de feedar mini! omg").queue();
    }

    private static void processClassico(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        channel.sendMessage("Classico mini, CLASSICO").queue();
    }

    private static void processMeda(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        channel.sendMessage("vai la pro caralho").queue();
    }

    private static void processFrase(MessageReceivedEvent event, SystemInterface s){
        MessageChannel channel = event.getChannel();
        String frase = s.getFrase();

        channel.sendMessage(frase).queue();
    }

    private static void processEquipas(MessageReceivedEvent event, SystemInterface s){

        MessageChannel channel = event.getChannel();
        String msgContent = event.getMessage().getContentRaw();
        String[] userList = msgContent.trim().split(" ");

        try {
            List<String> teamList = new LinkedList<>();

            List<Iterator<String>> iteratorList = s.getTeams(userList);

            Iterator<String> ItA = iteratorList.get(0);
            Iterator<String> ItB = iteratorList.get(1);

            channel.sendMessage("Team A:").queue();

            while (ItA.hasNext()) {
                channel.sendMessage(ItA.next()).queue();
            }

            channel.sendMessage("Team B:").queue();

            while (ItB.hasNext()) {
                channel.sendMessage(ItB.next()).queue();
            }
        }

        catch(InvalidNumberException e){
            channel.sendMessage("fds mete um numero entre 1 e 20, tao ai " + (userList.length - 1)).queue();
        }
    }



    private static void processSimao(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();

        String msgContent = event.getMessage().getContentRaw();

        String[] simao = msgContent.trim().split(" ");
        if(simao[1].equals("eu") && simao[2].equals("sou") && simao[3].equals("peixeiro")){
            channel.sendMessage("cala a boca simao").queue();
        }
    }

    private static void processPreto(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();

        channel.sendMessage("otario di merda").queue();
    }

}
