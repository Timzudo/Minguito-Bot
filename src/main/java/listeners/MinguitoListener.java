package listeners;

import Exceptions.InvalidNumberException;
import audio.PlayerManager;
import mambos.Game;
import mambos.Pair;
import mambos.PairClass;
import mambos.SystemInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MinguitoListener extends ListenerAdapter {

    //USERS
    protected static final String IDTIMZUDO = "222675252516225024";

    //SERVERS
    protected static final String TRUTAS = "457643754744971266";
    protected static final String MERENDAS = "687683461523439644";
    protected static final String BOAVIBE = "761008190091034635";
    protected static final String FORAZ = "723609492096549006";

    //CHANNELS
    protected static final String FEEDSGTCHANNEL = "818881850931019777";
    protected static final String MAMBOCHANNEL = "818881987489824768";
    protected static final String SUGGESTCHANNEL = "818882523941175377";
    protected static final String VIBECHANNEL = "822903476971503706";
    protected static final String PIAOCHANNEL = "822903514757464094";
    protected static final String MCCHANNEL = "822903539860111431";
    protected static final String KIRICHANNEL = "822903555794534433";
    protected static final String FEEDCHANNEL = "822903589282119720";

    //MESSAGES
    protected static final String TOPMESSAGE = "819041461072429118";
    protected static final String LEFTMESSAGE = "818905651861258320";

    //VERSIONS
    protected static final String VERSION = "MinguitoBot v4.3 | Comandos:";
    protected static final String VERSIONMERENDAS = "MinguitoBot v4.3 (Merendas) | Comandos:";
    protected static final String VERSIONBOAVIBE = "MinguitoBot v4.3 (Boa Vibe) | Comandos:";

    protected static final String NOVIDADES = "Online 24/7";

    //HELP
    protected static final String MAMBOSC =         "$simp: diz o nome de um simp.\n" +
                                                    "$feed: mostra a cara do maior feeder da tuga.\n" +
                                                    "$top: top 3 users por XP.\n" +
                                                    "$poll: fazer polls \n";

    protected static final String EQUIPASC =        "$equipas: faz 2 equipas a toa.\n" +
                                                    "$1v1: faz 1v1 entre dois users.\n" +
                                                    "$5v5: faz equipas para 5v5.\n" +
                                                    "$reroll: da reroll nas equipas.\n";

    protected static final String SOMC =            "$say: mete o bot a falar.\n" +
                                                    "$vibe: mete a vibe da sala no ponto.\n" +
                                                    "$piao: xue hua piao piao.\n" +
                                                    "$earrape: manda earrape num video.\n" +
                                                    "$skip: da skip na musica.\n" +
                                                    "$stop: estraga a vibe.\n" +
                                                    "$sound: toggle do som do bot.(Admin)\n";

    protected static final String OUTROSC =         "$mambo: faz uns mambos duvidosos com uma imagem \uD83D\uDC40.\n" +
                                                    "$wide: torna uma imagem thicc \uD83D\uDE29.\n" +
                                                    "$sugerir: sugere novos comandos para o bot.\n";

    protected static final String MAMBOSCTRUTAS =   "$ping: ping.\n" +
                                                    "$mini: para de feedar.\n" +
                                                    "$classico: classico mini...\n" +
                                                    "$meda: comando especifico para mandar o meda para o caralho.\n" +
                                                    "$hugana: classico hugana.\n" +
                                                    "$simp: diz o nome de um simp.\n" +
                                                    "$feed: mostra a cara do maior feeder da tuga.\n" +
                                                    "$top: top 3 users por XP.\n" +
                                                    "$poll: fazer polls \n";

    protected static final String SOMCTRUTAS =      "$say: mete o bot a falar.\n" +
                                                    "$vibe: mete a vibe da sala no ponto.\n" +
                                                    "$mc: mete som bem rebentado na sala.\n" +
                                                    "$piao: xue hua piao piao.\n" +
                                                    "$earrape: manda earrape num video.\n" +
                                                    "$skip: da skip na musica.\n" +
                                                    "$stop: estraga a vibe.\n" +
                                                    "$sound: toggle do som do bot.(Admin)\n";

    protected static final String OUTROSCTRUTAS =   "$mambo: faz uns mambos duvidosos com uma imagem \uD83D\uDC40.\n" +
                                                    "$wide: torna uma imagem thicc \uD83D\uDE29.\n" +
                                                    "$sfeed: sugerir um feed.\n" +
                                                    "$sugerir: sugere novos comandos para o bot.\n" +
                                                    "$water liga a proteção de water™.\n" +
                                                    "$helplil: mostra comandos para lil.";

    protected static final String OUTROSCBOAVIBE =  "$mambo: faz uns mambos duvidosos com uma imagem \uD83D\uDC40.\n" +
                                                    "$wide: torna uma imagem thicc \uD83D\uDE29.\n" +
                                                    "$sugerir: sugere novos comandos para o bot.\n" +
                                                    "$kiri: comando para simpar :eyes:.";

    protected static final String EQUIPAS =       "$equipas";
    protected static final String V =             "$5v5";
    protected static final String REROLL =        "$reroll";
    protected static final String FEED =          "$feed";
    protected static final String TOP =           "$top";
    protected static final String INFO =          "$info";
    protected static final String HELP =          "$help";
    protected static final String VIBE =          "$vibe";
    protected static final String SKIP =          "$skip";
    protected static final String STOP =          "$stop";
    protected static final String SAY =           "$say";
    protected static final String SOUND =         "$sound";
    protected static final String SUGGEST =       "$sugerir";
    protected static final String V1 =            "$1v1";
    protected static final String POLL =          "$poll";
    protected static final String EARRAPE =       "$earrape";
    protected static final String SIMP =          "$simp";
    protected static final String PIAO =          "$piao";
    protected static final String TESTE =         "$teste";
    protected static final String SIMAS =         "$simao";
    protected static final String FUND =          "$fund";
    protected static final String MAMBO =         "$mambo";
    protected static final String WIDE =          "$wide";
    protected static final String GAME =          "$game";

    protected static final String ERRO =      "Erro mamboso";

    //EMOJIS
    protected static final String YESEMOJI = "\uD83C\uDDF8";
    protected static final String NOEMOJI = "\uD83C\uDDF3";
    protected static final String LEFTEMOJI = "RE:U+25c0U+fe0f";
    protected static final String RIGHTEMOJI = "RE:U+25b6U+fe0f";

    protected static final String ZEEMOTE = "457683584610729990";
    protected static final String SHREKEMOTE = "457683584610729990";
    protected static final String SMARTEMOTE = "806964637123477515";


    protected SystemInterface s;

    protected static boolean sound;
    protected static Pair<List<String>, Boolean> reroll;
    protected static String v1;

    public MinguitoListener(SystemInterface s){
        sound = true;
        reroll = null;
        v1 = null;

        this.s = s;
    }


    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Objects.requireNonNull(Objects.requireNonNull(event.getJDA().getGuildById(FORAZ)).getTextChannelById(MAMBOCHANNEL)).retrieveMessageById(LEFTMESSAGE).queue(message -> message.getAttachments().get(0).downloadToFile("Left.png"));
        Objects.requireNonNull(Objects.requireNonNull(event.getJDA().getGuildById(FORAZ)).getTextChannelById(MAMBOCHANNEL)).retrieveMessageById(TOPMESSAGE).queue(message -> message.getAttachments().get(0).downloadToFile("Top.png"));

        load(event, s);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        event.getJDA().getUserById("222675252516225024").openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage("Chesh: " + dtf.format(now)).queue();
        });


    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
    }

    public void onGenericMessageReaction(@Nonnull GenericMessageReactionEvent event) {
        if(s.isPoll(event.getMessageId())){

            String name = s.getPoll(event.getMessageId());

            event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> {
                int pos = -1;
                for(MessageReaction emote: message.getReactions()){
                    String s = emote.getReactionEmote().getName();
                    if(s.equals(YESEMOJI)){
                        pos += emote.getCount();
                    }
                }
                int neg = -1;
                for(MessageReaction emote: message.getReactions()){
                    String n = emote.getReactionEmote().getName();
                    if(n.equals(NOEMOJI)){
                        neg += emote.getCount();
                    }
                }


                int posS;
                int negS;

                if((pos+neg) == 0){
                    posS = 0;
                    negS = 0;
                }
                else{
                    posS = (pos*100)/(pos+neg);
                    negS = (neg*100)/(pos+neg);
                }

                StringBuilder msgS = new StringBuilder();

                msgS.append("Sim:  ");
                for(int i = 0; i < (posS/8); i++){
                    msgS.append(":white_large_square:");
                }
                msgS.append(" ").append(posS).append("%");

                StringBuilder msgN = new StringBuilder();

                msgN.append("Nao: ");
                for(int i = 0; i < (negS/8); i++){
                    msgN.append(":white_large_square:");
                }
                msgN.append(" ").append(negS).append("%");

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(name);
                Color green = new Color(17, 128, 106, 255);
                eb.setColor(green);
                eb.addField(msgS.toString(), "", false);
                eb.addField(msgN.toString(), "", false);

                message.editMessage(eb.build()).queue();
            });
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try{
            if(!event.getAuthor().isBot() && (event.getMessage().getContentRaw().length() != 0)){
                String comando = processGetCommand(event);
                String prefixo = comando.substring(0, 1);
                if(prefixo.equals("$")){
                    switch (comando) {
                        case EQUIPAS:
                            processEquipas(event, s);
                            break;

                        case V:
                            process5v5(event, s);
                            break;

                        case REROLL:
                            processReroll(event, s);
                            break;

                        case FEED:
                            processFeed(event, s);
                            break;

                        case TOP:
                            processTop(event, s);
                            break;

                        case INFO:

                        case HELP:
                            processInfo(event);
                            break;

                        case VIBE:
                            processVibe(event, s);
                            break;

                        case SKIP:
                            processSkip(event);
                            break;

                        case STOP:
                            processStop(event);
                            break;

                        case SAY:
                            processSay(event);
                            break;

                        case SOUND:
                            processSound(event);
                            break;

                        case SUGGEST:
                            processSuggest(event, s);
                            break;

                        case V1:
                            processV1(event);
                            break;

                        case POLL:
                            processPoll(event, s);
                            break;

                        case EARRAPE:
                            processEarrape(event);
                            break;

                        case SIMP:
                            processSimp(event, s);
                            break;

                        case PIAO:
                            processPiao(event, s);
                            break;

                        case TESTE:
                            processTeste(event);
                            break;

                        case SIMAS:
                            processSimas(event);
                            break;

                        case FUND:
                            processFund(event);
                            break;

                        case MAMBO:
                            processMambo(event);
                            break;

                        case WIDE:
                            processWide(event);
                            break;

                        case GAME:
                            processGame(event);
                            break;
                    }
                    emote(event);
                }
                for(User u : event.getMessage().getMentionedUsers()){
                    System.out.println(u.getId());
                }
                User user = event.getJDA().getUserById("723611612556165161");
                if(event.getMessage().getMentionedUsers().contains(user)){
                    event.getTextChannel().sendMessage("https://cdn.discordapp.com/attachments/825942456708497449/826170693681938472/shrek.png").queue();
                }
            }
            System.out.println("mensagem");
        }
        catch (IOException | InterruptedException | ExecutionException e){
            MessageChannel channel = event.getChannel();
            channel.sendMessage(ERRO).queue();
        }
    }

    protected static void processEquipas(MessageReceivedEvent event, SystemInterface s){

        MessageChannel channel = event.getChannel();
        String msgContent = event.getMessage().getContentRaw();
        String[] userList = msgContent.trim().split(" ");
        List<String> nameList = new LinkedList<>();

        nameList.addAll(Arrays.asList(userList).subList(1, userList.length));

        try {

            Pair<EmbedBuilder, EmbedBuilder> pair = teamEmbedBuilder(s.getTeams(nameList));

            EmbedBuilder eb1 = pair.getFirst();
            EmbedBuilder eb2 = pair.getSecond();

            channel.sendMessage(eb1.build()).queue();
            channel.sendMessage(eb2.build()).queue();

            reroll = new PairClass<>(nameList, true);
        }

        catch(InvalidNumberException e){
            channel.sendMessage("fds mete um numero entre 1 e 20, tao ai " + (userList.length - 1)).queue();
        }
    }

    protected static Pair<EmbedBuilder, EmbedBuilder> teamEmbedBuilder(Pair<Iterator<String>, Iterator<String>> pair){


        Iterator<String> ItA = pair.getFirst();
        Iterator<String> ItB = pair.getSecond();

        EmbedBuilder eb1 = new EmbedBuilder();
        EmbedBuilder eb2 = new EmbedBuilder();

        eb1.setColor(Color.blue);
        eb1.setTitle("Team A:");

        while(ItA.hasNext()){
            eb1.appendDescription(ItA.next() + "\n");
        }


        eb2.setColor(Color.red);
        eb2.setTitle("Team B:");

        while(ItB.hasNext()){
            eb2.appendDescription(ItB.next() + "\n");
        }
        return new PairClass<>(eb1, eb2);

    }

    protected static void processReroll(MessageReceivedEvent event, SystemInterface s){
        if(reroll.getSecond() != null){

            Pair<EmbedBuilder, EmbedBuilder> pair;

            if(reroll.getSecond()){
                pair = teamEmbedBuilder(s.getTeams(reroll.getFirst()));
            }
            else {
                pair = vEmbedBuilder(s.getTeams(reroll.getFirst()));
            }

            EmbedBuilder eb1 = pair.getFirst();
            EmbedBuilder eb2 = pair.getSecond();

            event.getChannel().sendMessage(eb1.build()).queue();
            event.getChannel().sendMessage(eb2.build()).queue();

        }
        else{
            event.getChannel().sendMessage("Faz equipas crl").queue();
        }
    }

    protected static Pair<EmbedBuilder, EmbedBuilder> vEmbedBuilder(Pair<Iterator<String>, Iterator<String>> pair){


        Iterator<String> ItA = pair.getFirst();
        Iterator<String> ItB = pair.getSecond();

        EmbedBuilder eb1 = new EmbedBuilder();
        EmbedBuilder eb2 = new EmbedBuilder();

        eb1.setColor(Color.blue);
        eb1.setTitle("Team A:");


        eb1.appendDescription("TOP - " + ItA.next() + "\n");
        eb1.appendDescription("JNG - " + ItA.next() + "\n");
        eb1.appendDescription("MID - " + ItA.next() + "\n");
        eb1.appendDescription("BOT - " + ItA.next() + "\n");
        eb1.appendDescription("SUP - " + ItA.next() + "\n");


        eb2.setColor(Color.red);
        eb2.setTitle("Team B:");

        eb2.appendDescription("TOP - " + ItB.next() + "\n");
        eb2.appendDescription("JNG - " + ItB.next() + "\n");
        eb2.appendDescription("MID - " + ItB.next() + "\n");
        eb2.appendDescription("BOT - " + ItB.next() + "\n");
        eb2.appendDescription("SUP - " + ItB.next() + "\n");

        return new PairClass<>(eb1, eb2);

    }

    protected static void process5v5(MessageReceivedEvent event, SystemInterface s){


        String[] msg = event.getMessage().getContentRaw().split(" ");

        List<Member> memberList = event.getGuild().getVoiceChannelById("731256824786845803").getMembers();
        List<String> nameList = new LinkedList<>();

        boolean deuMerda = false;

        if(memberList.size() == 10){
            for (Member member: memberList){
                nameList.add(member.getEffectiveName());
            }
        }
        else{
            if(msg.length == 11){
                Collections.addAll(nameList, msg);
                nameList.remove(0);
            }
            else{
                deuMerda = true;
                event.getChannel().sendMessage("Mete ai 10 nomes crl").queue();
            }
        }


        if(!deuMerda){
            Pair<EmbedBuilder, EmbedBuilder> pair = vEmbedBuilder(s.getTeams(nameList));

            EmbedBuilder eb1 = pair.getFirst();
            EmbedBuilder eb2 = pair.getSecond();

            event.getChannel().sendMessage(eb1.build()).queue();
            event.getChannel().sendMessage(eb2.build()).queue();

            reroll = new PairClass<>(nameList, false);
        }

    }

    protected static void processFeed(MessageReceivedEvent event, SystemInterface s){
        String feed = s.getFeed();

        event.getTextChannel().sendMessage(feed).queue();
    }


    protected static void processTop(MessageReceivedEvent event, SystemInterface s){
        MessageChannel channel = event.getTextChannel();
        try{

            String text = "Lil Sugas feat MC Pirilampo - 4457 xp" + "\n" + "Hugana - 1344 xp" + "\n" + "Vasco - 587 xp";

            EmbedBuilder eb = new EmbedBuilder();
            Color green = new Color(17, 128, 106, 255);
            eb.setColor(green);
            eb.setTitle("Top 3 XP:");
            eb.setDescription(text);

            channel.sendMessage(eb.build()).queue();
        }
        catch (InvalidNumberException e){
            channel.sendMessage("Erro mamboso").queue();
        }

    }

    protected static void processSuggest(MessageReceivedEvent event, SystemInterface s) throws IOException {

        String[] message = event.getMessage().getContentRaw().split(" ", 2);
        String nickName = event.getAuthor().getName();

        event.getChannel().sendMessage("bacano").queue();

        event.getJDA().getGuildById(FORAZ).getTextChannelById(SUGGESTCHANNEL).sendMessage(nickName + ": \n" + message[1]).queue();
    }


    protected static void processVibe(MessageReceivedEvent event, SystemInterface s){

        if(sound){
            if(Objects.requireNonNull(event.getMember()).getVoiceState().inVoiceChannel()){

                String url = s.getVibe();

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

    protected static void processPiao(MessageReceivedEvent event, SystemInterface s){

        if(sound){
            if(Objects.requireNonNull(event.getMember()).getVoiceState().inVoiceChannel()){

                String url = s.getPiao();

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

    protected static void processStop(MessageReceivedEvent event){

        Guild guild = event.getGuild();

        AudioManager audioManager = guild.getAudioManager();
        audioManager.closeAudioConnection();

        PlayerManager manager = PlayerManager.getInstance();
        manager.stop(event.getTextChannel());
    }

    protected static void processSkip(MessageReceivedEvent event){

        PlayerManager manager = PlayerManager.getInstance();
        manager.next(event.getTextChannel());
    }

    protected void processSound(MessageReceivedEvent event){
        JDA jda = event.getJDA();
        if(event.getAuthor().getId().equals(IDTIMZUDO)){
            sound = !sound;
            if(sound){
                event.getChannel().sendMessage("Som ativo").queue();
                jda.getPresence().setStatus(OnlineStatus.ONLINE);
            }
            else{
                event.getChannel().sendMessage("Som inativo").queue();
                jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
            }
        }
        else {
            if(sound){
                event.getChannel().sendMessage("Estado: Som ativo").queue();
            }
            else{
                event.getChannel().sendMessage("Estado: Som inativo").queue();
            }
        }
    }

    protected static void processV1(MessageReceivedEvent event){
        if(v1 == null){
            v1 = event.getMember().getId();
            event.getMessage().reply(":eyes:").queue();
        }
        else if(!v1.equals(event.getMember().getId())){
            int random = new Random().nextInt(2);
            if(random == 0){
                event.getChannel().sendMessage(event.getGuild().getMemberById(v1).getUser().getName() + " tem mambo superior").queue();
            }
            else{
                event.getChannel().sendMessage( event.getGuild().getMemberById(event.getMember().getId()).getUser().getName() + " tem mambo superior").queue();
            }
            v1 = null;
        }
    }

    protected static void processPoll(MessageReceivedEvent event, SystemInterface s) {
        String[] message = event.getMessage().getContentRaw().split(" ", 2);

        if (message.length == 1) {
            event.getTextChannel().sendMessage("escreve ai alguma coisa wi").queue();
        }
        else{
            String name = message[1];
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(name);
            Color green = new Color(17, 128, 106, 255);
            eb.setColor(green);
            eb.addField("Sim:   0% \n" + "Nao:  0%", "", false);

            event.getChannel().sendMessage(eb.build()).queue(message1 -> {

                s.addPoll(message1.getId(), name);

                message1.addReaction(YESEMOJI).queue();
                message1.addReaction(NOEMOJI).queue();
            });
        }
    }

    protected static void processEarrape(MessageReceivedEvent event){
        if(sound){
            String[] message = event.getMessage().getContentRaw().split(" ");
            if(message.length == 2){
                if(Objects.requireNonNull(event.getMember()).getVoiceState().inVoiceChannel()){

                    String url = message[1];

                    playEarrape(event, url);
                }
                else{
                    event.getTextChannel().sendMessage("Entra numa sala wi").queue();
                }
            }
            else{
                event.getChannel().sendMessage("Mete ai um link fds").queue();
            }
        }
        else{
            event.getChannel().sendMessage("Som inativo, contacte o Timzudo").queue();
        }
    }

    protected static void processSimp(MessageReceivedEvent event, SystemInterface s){
        List<Member> userList = event.getGuild().getMembers();

        int random = new Random().nextInt(userList.size());

        event.getChannel().sendMessage(userList.get(random).getEffectiveName() + " e simp.").queue();
    }


    protected static void processTeste(MessageReceivedEvent event) throws InterruptedException {
        Game game  = new Game(event);
        game.start();
    }


    protected static void processSimas(MessageReceivedEvent event){
        event.getChannel().sendMessage("Olá , \n" +
                "preciso da tua ajuda! Podes meter like nessa foto e partilhar? Não custa nada e é uma grande ajuda para mim !!! :slight_smile::slight_smile:\n" + "Obrigado!\n" + "https://m.facebook.com/story.php?story_fbid=3145342268907755&id=100002960384957&sfnsn=mo").queue();
    }


    protected static void playEarrape(MessageReceivedEvent event, String url){

        String channelId = Objects.requireNonNull(event.getMember()).getVoiceState().getChannel().getId();
        Guild guild = event.getGuild();
        VoiceChannel channel = event.getGuild().getVoiceChannelById(channelId);

        AudioManager audioManager = guild.getAudioManager();
        audioManager.openAudioConnection(channel);
        audioManager.setSelfDeafened(true);

        PlayerManager manager = PlayerManager.getInstance();
        manager.loadAndPlay(event.getTextChannel(), url);
        manager.getGuildMusicManager(event.getGuild()).player.setVolume(10000);
    }


    protected static void play(MessageReceivedEvent event, String url){

        String channelId = Objects.requireNonNull(event.getMember()).getVoiceState().getChannel().getId();
        Guild guild = event.getGuild();
        VoiceChannel channel = guild.getVoiceChannelById(channelId);

        AudioManager audioManager = guild.getAudioManager();
        audioManager.openAudioConnection(channel);
        audioManager.setSelfDeafened(true);

        PlayerManager manager = PlayerManager.getInstance();
        manager.loadAndPlay(event.getTextChannel(), url);
        manager.getGuildMusicManager(event.getGuild()).player.setVolume(20);



    }

    protected static void processFund(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();

        channel.sendMessage("https://www.gofundme.com/f/nitro-para-os-trutas?utm_medium=copy_link&utm_source=customer&utm_campaign=p_lico+share-sheet").queue();
    }

    protected static void processSay(MessageReceivedEvent event){

        String[] message = event.getMessage().getContentRaw().split(" ", 2);
        if(message.length != 1 && message[1].length() <= 50){
            MessageBuilder msg = new MessageBuilder();
            msg.append(message[1]);
            if(sound){
                msg.setTTS(true);
            }
            event.getTextChannel().sendMessage(msg.build()).queue();
            event.getMessage().delete().complete();
        }
        else{
            event.getChannel().sendMessage("Tamanho de mensagem bem cansado.").queue();
        }
    }

    private static void processGame(MessageReceivedEvent event) throws InterruptedException {
        EmbedBuilder eb = new EmbedBuilder();
        Color green = new Color(17, 128, 106, 255);
        eb.setColor(green);
        eb.setTitle("Fds");
        eb.setImage("https://cdn.discordapp.com/attachments/825942456708497449/825945108599537724/start.png");
        event.getTextChannel().sendMessage(eb.build()).queue(message ->{
            try {
                String messageId = message.getId();
                TimeUnit.SECONDS.sleep(10);
                event.getTextChannel().retrieveMessageById(messageId).queue(message1 -> {
                    Random rand = new Random();
                    int randInt = rand.nextInt(2);
                    String emoji;
                    EmbedBuilder eb1 = new EmbedBuilder();
                    eb1.setColor(green);
                    eb1.setTitle("Yau");
                    if(randInt == 0){
                        emoji = LEFTEMOJI;
                        eb1.setImage("https://cdn.discordapp.com/attachments/825942456708497449/825950053382357022/left.png");
                    }
                    else{
                        emoji = RIGHTEMOJI;
                        eb1.setImage("https://cdn.discordapp.com/attachments/825942456708497449/825950049140867092/right.png");
                    }
                    StringBuilder builder = new StringBuilder();
                    for(MessageReaction r : message1.getReactions()){
                        if(r.getReactionEmote().toString().equals(emoji)){
                            r.retrieveUsers().queue(users -> {
                                for(User u : users){
                                    builder.append(u.getName() + "\n");
                                }
                            });
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    EmbedBuilder eb2 = new EmbedBuilder();
                    eb2.setColor(green);
                    eb2.addField("Wis que ganharam:", builder.toString(), true);
                    message1.editMessage(eb1.build()).queue();
                    event.getTextChannel().sendMessage(eb2.build()).queue();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    protected static String processGetCommand(MessageReceivedEvent event){
        Message msg = event.getMessage();
        String msgContent = msg.getContentRaw();
        String[] id = msgContent.split(" ", 2);

        return id[0];
    }

    protected void processMambo(MessageReceivedEvent event) throws InterruptedException, IOException {
        Message.Attachment a = getImage(event, s);

        if(a != null){
            a.downloadToFile("In.png");

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            File discFile = new File("In.png");
                            try{
                                BufferedImage discImage = ImageIO.read(discFile);

                                BufferedImage newImage = resize(discImage,657, 379);
                                newImage = bottomImage(newImage);
                                newImage = topImage(newImage);

                                ImageIO.write(newImage, "png", new File("column.png"));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            File Out = new File("column.png");

                            event.getTextChannel().sendFile(Out).queue();

                            discFile.delete();
                        }
                    },
                    800
            );

        }
        else{
            event.getTextChannel().sendMessage("Mete ai uma imagem fds").queue();
        }
    }

    protected static BufferedImage bottomImage(BufferedImage img) throws IOException, InterruptedException {
        File bottomFile = new File("Left.png");
        BufferedImage defaultImage = ImageIO.read(bottomFile);

        final BufferedImage rowImage = new BufferedImage(1332,378,BufferedImage.TYPE_INT_RGB);

        Graphics2D g2dColumn = rowImage.createGraphics();
        g2dColumn.drawImage(defaultImage,0,0, null);
        g2dColumn.drawImage(img,671,4, null);

        return rowImage;
    }

    protected static BufferedImage topImage(BufferedImage img) throws IOException {
        File topFile = new File("Top.png");
        BufferedImage defaultImage = ImageIO.read(topFile);

        final BufferedImage columnImage = new BufferedImage(1332,757,BufferedImage.TYPE_INT_RGB);

        Graphics2D g2dColumn = columnImage.createGraphics();
        g2dColumn.drawImage(defaultImage,0,0, null);
        g2dColumn.drawImage(img,0,379, null);

        return columnImage;
    }

    protected static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    protected void processWide(MessageReceivedEvent event) throws InterruptedException, IOException, ExecutionException {
        Message.Attachment a = getImage(event, s);

        if(a != null){
            File discFile = a.downloadToFile("Wide.png").get();

            BufferedImage discImage = null;
            try {
                discImage = ImageIO.read(discFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedImage newImage = resize(discImage,discImage.getWidth(), discImage.getHeight()/2);

            try {
                ImageIO.write(newImage, "png", new File("WideOut.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            File Out = new File("WideOut.png");

            event.getTextChannel().sendFile(Out).queue();

            discFile.delete();

        }
        else{
            event.getTextChannel().sendMessage("Mete ai uma imagem fds").queue();
        }
    }

    protected void processInfo(MessageReceivedEvent event){
        String guildId = event.getGuild().getId();

        if(!guildId.equals(TRUTAS) && !guildId.equals(MERENDAS) && !guildId.equals(BOAVIBE)){
            EmbedBuilder eb = new EmbedBuilder();
            Color green = new Color(17, 128, 106, 255);
            eb.setColor(green);


            eb.setTitle(VERSION);
            eb.addField("Mambos:"   , MAMBOSC, false);
            eb.addField("Equipas:"  , EQUIPASC, false);
            eb.addField("Som:"      , SOMC, false);
            eb.addField("Outros:"   , OUTROSC, false);
            eb.addField("Novidades:", NOVIDADES, false);

            event.getTextChannel().sendMessage(eb.build()).queue();
        }
    }

    private void load(@NotNull ReadyEvent event, SystemInterface system){
        event.getJDA().getGuildById(FORAZ).getTextChannelById(VIBECHANNEL).getHistoryFromBeginning(100).queue(messageHistory -> {

            Iterator<Message> it = messageHistory.getRetrievedHistory().iterator();

            while (it.hasNext()){
                s.addVibe(it.next().getContentRaw());
            }
        });

        event.getJDA().getGuildById(FORAZ).getTextChannelById(PIAOCHANNEL).getHistoryFromBeginning(100).queue(messageHistory -> {

            Iterator<Message> it = messageHistory.getRetrievedHistory().iterator();

            while (it.hasNext()){
                s.addPiao(it.next().getContentRaw());
            }
        });

        event.getJDA().getGuildById(FORAZ).getTextChannelById(MCCHANNEL).getHistoryFromBeginning(100).queue(messageHistory -> {

            Iterator<Message> it = messageHistory.getRetrievedHistory().iterator();

            while (it.hasNext()){
                s.addMc(it.next().getContentRaw());
            }
        });

        event.getJDA().getGuildById(FORAZ).getTextChannelById(KIRICHANNEL).getHistoryFromBeginning(100).queue(messageHistory -> {

            Iterator<Message> it = messageHistory.getRetrievedHistory().iterator();

            while (it.hasNext()){
                s.addKiri(it.next().getContentRaw());
            }
        });

        event.getJDA().getGuildById(FORAZ).getTextChannelById(FEEDCHANNEL).getHistoryFromBeginning(100).queue(messageHistory -> {

            Iterator<Message> it = messageHistory.getRetrievedHistory().iterator();

            while (it.hasNext()){
                s.addFeed(it.next().getContentRaw());
            }
        });
    }

    private Message.Attachment getAttachment(MessageReceivedEvent event){

       MessageHistory history = event.getTextChannel().getHistoryBefore(event.getMessage(), 100).complete();

        Iterator<Message> it = history.getRetrievedHistory().iterator();

        while (it.hasNext()){
            Message m = it.next();
            if(!m.getAttachments().isEmpty() && m.getAttachments().get(0).isImage()){
                return m.getAttachments().get(0);
            }
        }

        return null;
    }

    private Message.Attachment getImage(MessageReceivedEvent event, SystemInterface s) throws InterruptedException {
        if(!event.getMessage().getAttachments().isEmpty() && event.getMessage().getAttachments().get(0).isImage()){
            return event.getMessage().getAttachments().get(0);
        }
        else {
            Message.Attachment attachment = getAttachment(event);

            if(attachment != null){
                return attachment;
            }
        }
        return null;
    }

    private static void emote(MessageReceivedEvent event){
        String guildId = event.getGuild().getId();

        switch (guildId){
            case TRUTAS:
                event.getMessage().addReaction(event.getGuild().getEmoteById(ZEEMOTE)).queue();
                break;
            case MERENDAS:
                event.getMessage().addReaction(event.getGuild().getEmoteById(SHREKEMOTE)).queue();
            case BOAVIBE:
                event.getMessage().addReaction(event.getGuild().getEmoteById(SMARTEMOTE)).queue();
                break;
        }

    }

}