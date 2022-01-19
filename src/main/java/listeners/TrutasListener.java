package listeners;

import audio.PlayerManager;
import mambos.SystemInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class TrutasListener extends MinguitoListener {


    private static final String HELPLILTEXT =   "$kick: kicka todos os membros com a role 'kick'.\n" +
                                                "kicklist: mostra os membros que podem levar kick com o comando $kick.\n" +
                                                "$helplil: mostra os camandos para lil.\n";


    private static final String PING =          "$ping";
    private static final String MINI =          "$mini";
    private static final String CLASSICO =      "$classico";
    private static final String MEDA =          "$meda";
    private static final String HUGANA =        "$hugana";
    private static final String FELIZ =         "$feliz";
    private static final String SUGGESTFEED =   "$sfeed";
    private static final String MC =            "$mc";
    private static final String WATER =         "$water";
    private static final String NIGGER =        "$nigger";

    private static final String KICK =          "$kick";
    private static final String KICKLIST =      "$kicklist";
    private static final String HELPLIL =       "$helplil";



    private static boolean water, moved;


    public TrutasListener(SystemInterface s){
        super(s);

        water = false;
        moved = false;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
    }

    @Override
    public void onGenericMessageReaction(@NotNull GenericMessageReactionEvent event) {
        if(event.getGuild().getId().equals(TRUTAS)){
            super.onGenericMessageReaction(event);
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().isFromGuild() && event.getGuild().getId().equals(TRUTAS)){
            if(!event.getAuthor().isBot() && (event.getMessage().getContentRaw().length() != 0)){

                int random = new Random().nextInt(200);
                if(random == 100){
                    event.getChannel().sendMessage("cala so a boca fds").queue();
                }

                String comando = processGetCommand(event);
                String prefixo = comando.substring(0, 1);
                if(prefixo.equals("$")){
                    boolean action = true;
                    switch (comando) {
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

                        case HUGANA:
                            processHugana(event);
                            break;

                        case FELIZ:
                            processFeliz(event, s);
                            break;

                        case SUGGESTFEED:
                            processSuggestFeed(event);
                            break;

                       case MC:
                            processMc(event, s);
                            break;

                        case WATER:
                            processWater(event);
                            break;

                        case NIGGER:
                            processNigger(event);
                            break;

                        case HELP:
                            processHelp(event);
                            break;

                        default:
                            action = lilCommands(event);
                            break;
                    }
                    if(action){
                        event.getMessage().addReaction(event.getGuild().getEmoteById(ZEEMOTE)).queue();
                    }
                }
            }
        }
    }

    //CHANGE CHANNEL
    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        if(event.getGuild().getId().equals(TRUTAS)){
            super.onGuildVoiceJoin(event);
            infantario(event);
            squirt(event);
        }
    }

    @Override
    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {
        if(event.getGuild().getId().equals(TRUTAS)){
            super.onGuildVoiceMove(event);
            infantario(event);
            squirtMove(event);
        }
    }

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        if(event.getGuild().getId().equals(TRUTAS)){
            List<AuditLogEntry> list  = null;
            try {
                list = event.getGuild().retrieveAuditLogs().complete(true);
            } catch (RateLimitedException e) {
                e.printStackTrace();
            }

            AuditLogEntry entry = list.get(0);
            net.dv8tion.jda.api.entities.User user = entry.getUser();

            if(!(user.getId().equals("723611612556165161") || user.isBot() || user.getId().equals(IDTIMZUDO)) && entry.getType().equals(ActionType.MEMBER_VOICE_KICK)){
                event.getGuild().kickVoiceMember(event.getGuild().getMember(list.get(0).getUser())).complete();
                event.getGuild().getDefaultChannel().sendMessage("Para de ser mongo " + user.getName()).queue();
            }
            removeSquirt(event);
        }
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        if(event.getGuild().getId().equals(TRUTAS)){
            super.onGuildMemberJoin(event);
            event.getUser().openPrivateChannel().queue((channel)->{
                if(event.getGuild().getId().equals(TRUTAS)){
                    channel.sendMessage("Dja bu sabi " + event.getMember().getEffectiveName() + ", espera que alguem te de roles para poderes falar.").queue();
                }
            });
        }
    }

    private static boolean lilCommands(MessageReceivedEvent event){
        Role lilRole = event.getGuild().getRoleById("457956034997387284");

        if(event.getMember().getRoles().contains(lilRole)){
            String[] comandoV = event.getMessage().getContentRaw().split(" ");
            String comando = comandoV[0];

            switch (comando){
                case HELPLIL:
                    processHelpLil(event);
                    break;
                case KICK:
                    processKick(event);
                    break;
                case KICKLIST:
                    processKickList(event);
                    break;
                default:
                    return false;
            }
        }
        else{
            return false;
        }
        return true;
    }

    private static void processPing(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();
        channel.sendMessage("Pong!").queue(response -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
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

    private static void processHugana(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        channel.sendMessage("o meu pc custa 300000 paus").queue();
    }

    private static void processFeliz(MessageReceivedEvent event, SystemInterface s){
        MessageChannel channel = event.getChannel();
        channel.sendMessage(s.getFeliz()).queue();
    }

    private static void processSuggestFeed(MessageReceivedEvent event){
        event.getTextChannel().sendMessage("bacano").queue();

        Message msg = event.getMessage();
        event.getJDA().getGuildById(FORAZ).getTextChannelById(SUGGEST).sendMessage(msg).queue();
    }

    private static void processMc(MessageReceivedEvent event, SystemInterface s){
        if(sound){
            if(Objects.requireNonNull(event.getMember()).getVoiceState().inVoiceChannel()){

                String url = s.getMc();

                play(event, url);
            }
            else{
                event.getTextChannel().sendMessage("Entra numa sala wi").queue();
            }
        }
        else{
            event.getChannel().sendMessage("Som inativo, contacte o Timzudo").queue();
        }
    }

    private static void processWater(MessageReceivedEvent event){
        Role waterRole = event.getGuild().getRoleById("738110296370970735");

        if(!event.getMember().getRoles().contains(waterRole)){
            water = !water;
            if(water){
                event.getTextChannel().sendMessage("protecao de water ligada.").queue();
            }
            else{
                event.getTextChannel().sendMessage("protecao de water desligada.").queue();
            }
        }else{
            event.getChannel().sendMessage("lida " + event.getMember().getEffectiveName()).queue();
        }

    }

    private static void infantario(GenericGuildVoiceEvent event){
        if(water){
            Member member = event.getMember();
            //criar metodo remove water
            Role water = event.getGuild().getRoleById("738110296370970735");

            if(event.getMember().getRoles().contains(water)){
                event.getGuild().moveVoiceMember(member, event.getGuild().getVoiceChannelById("727233940284506193")).complete();
                if(!moved){
                    event.getGuild().getTextChannelById("724758690636365834").sendMessage(event.getMember().getEffectiveName() + " removido com sucesso.").queue();
                }

                AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(event.getGuild().getVoiceChannelById("727233940284506193"));
                audioManager.setSelfDeafened(true);

                PlayerManager manager = PlayerManager.getInstance();
                manager.loadAndPlay(event.getGuild().getDefaultChannel(), "https://www.youtube.com/watch?v=nHvo-aoN-Zc");
                manager.getGuildMusicManager(event.getGuild()).player.setVolume(20);
            }
            moved = !moved;
        }
    }

    private static void squirt(GuildVoiceJoinEvent event){
        if(event.getChannelJoined().getId().equals("747157430508716042")){
            event.getMember().modifyNickname("\t" /*event.getMember().getEffectiveName() + " \uD83D\uDCA6"*/).complete();
        }
    }

    private static void squirtMove(GuildVoiceMoveEvent event){
        if(event.getChannelJoined().getId().equals("747157430508716042")){
            event.getMember().modifyNickname("\t" /*event.getMember().getEffectiveName() + " \uD83D\uDCA6"*/).complete();
        }
        else{
            event.getMember().modifyNickname("\t" /*event.getMember().getEffectiveName() + " \uD83D\uDCA6"*/).complete();
        }
    }

    private static void removeSquirt(GuildVoiceLeaveEvent event){
        if(event.getChannelLeft().getId().equals("747157430508716042")) {
            event.getMember().modifyNickname("\t" /*event.getMember().getEffectiveName() + " \uD83D\uDCA6"*/).complete();
        }
    }

    private static void processKick(MessageReceivedEvent event){
        Role kickRole = event.getGuild().getRoleById("818538802028085288");
        Role trutasRole = event.getGuild().getRoleById("457937025430192129");

        List<Member> memberList = event.getGuild().getMembers();

        for(Member m: memberList){
            if(m.getRoles().contains(kickRole)){
                if(!m.getRoles().contains(trutasRole)){
                    m.kick().reason("es cansado").queue();
                    event.getTextChannel().sendMessage("O membro " + m.getEffectiveName() + " foi kickado pelo lil: " + event.getAuthor().getName()).queue();
                }
                else{
                    event.getTextChannel().sendMessage("O membro " + m.getEffectiveName() + " nao pode ser kickado").queue();
                }
            }
        }
    }
    private static void processKickList(MessageReceivedEvent event){
        Role kickRole = event.getGuild().getRoleById("818538802028085288");
        Role trutasRole = event.getGuild().getRoleById("457937025430192129");

        List<Member> memberList = event.getGuild().getMembers();

        StringBuilder msg = new StringBuilder();

        for(Member m: memberList){
            if(m.getRoles().contains(kickRole)){
                if(!m.getRoles().contains(trutasRole)){
                    msg.append("\n" + m.getUser().getName());
                }
            }
        }

        if(msg.toString().equals("")){
            event.getTextChannel().sendMessage("Nao existem membros que podem levar kick.").queue();
        }
        else{
            EmbedBuilder eb = new EmbedBuilder();
            Color lil = new Color(173, 20, 87, 255);
            eb.setColor(lil);
            eb.addField("Membros que podem levar kick:", msg.toString(), false);
            event.getTextChannel().sendMessage(eb.build()).queue();
        }
    }

    private static void processHelpLil(MessageReceivedEvent event){
        EmbedBuilder eb = new EmbedBuilder();
        Color lil = new Color(173, 20, 87, 255);
        eb.setColor(lil);
        eb.setTitle(VERSION);
        eb.addField("Comandos exclusivos Lil", HELPLILTEXT, false);
        event.getTextChannel().sendMessage("Vai aos dms").queue();

        event.getMember().getUser().openPrivateChannel().queue((channel)->{
            channel.sendMessage(eb.build()).queue();
        });
    }

    private static void processNigger(MessageReceivedEvent event){
        play(event, "https://www.youtube.com/watch?v=-dGsaExqG5M");
    }

    private static void processHelp(MessageReceivedEvent event){
        EmbedBuilder eb = new EmbedBuilder();
        Color green = new Color(17, 128, 106, 255);
        eb.setColor(green);

        eb.setTitle(VERSION);
        eb.addField("Mambos:"   , MAMBOSCTRUTAS, false);
        eb.addField("Equipas:"  , EQUIPASC, false);
        eb.addField("Som:"      , SOMCTRUTAS, false);
        eb.addField("Outros:"   , OUTROSCTRUTAS, false);
        eb.addField("Novidades:", NOVIDADES, false);
        event.getTextChannel().sendMessage(eb.build()).queue();
    }


}
