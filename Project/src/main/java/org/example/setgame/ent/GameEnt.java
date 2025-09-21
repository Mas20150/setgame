package org.example.setgame.ent;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HelloApplication;
import org.example.setgame.ent.enums.GameModus;
import org.example.setgame.rep.GameRepository;
import org.example.setgame.rep.PlayerRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "game")
public class GameEnt{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_gen")
    @SequenceGenerator(name = "game_id_gen", sequenceName = "game_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    //@NotNull

    @Column(name = "countofplayers", nullable = false)
    private Integer countOfPlayers;

    @Column(name = "lastsaved")
    private Date lastSaved;

    //
    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "finished")
    private Boolean finished = false;

    //@JoinColumn(name = "gameid", table = "set")
    @OneToMany(mappedBy = "game",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<SetEnt> sets;

    //@JoinColumn(name = "gameid", table = "gameboardcard")

    @OneToMany(mappedBy = "game",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<GameBoardCardEnt> gameboardCardSet;

    @Column(name = "modus")
    private GameModus modus;

    //@JoinColumn(table = "player", name = "gameid")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "game")
    private Set<PlayerEnt> players;

    @Column(name = "round",table = "game")
    private int round;

    @Column
    private String winner;

    @Transient
    private PlayerRepository playerRepository = new PlayerRepository();



    public GameEnt(){

    }

    public void selectSet(int pos0, int pos1, int pos2, int playerId){
        ArrayList<CardEnt> cards = new ArrayList<>();

        for (GameBoardCardEnt gameboardcardEnt : gameboardCardSet) {
            int pos = gameboardcardEnt.getPosition();
            if (pos == pos0 || pos == pos1 || pos == pos2) {
                cards.add(gameboardcardEnt.getCard());
                if (cards.size() == 3)
                    break;
            }
        }

        PlayerEnt player = new PlayerEnt();

        for (PlayerEnt playerEnt : players) {
            if (playerEnt.getId() == playerId){
                player = playerEnt;
            }
        }

        SetEnt set = new SetEnt(cards.get(0),cards.get(1),cards.get(2), player, this, round);
        if (set.getIsvalid()){
            sets.add(set);

            for (PlayerEnt playerEnt : players) {
                if (playerEnt.getId() == playerId){
                    player.getSets().add(set);
                }
            }

            for (GameBoardCardEnt gameboardcardEnt : gameboardCardSet) {
                int pos = gameboardcardEnt.getPosition();
                if (pos == pos0 || pos == pos1 || pos == pos2) {
                    gameboardcardEnt.setCard(null);
                }
            }
            refillBoard();
        }
        else {
            HelloApplication.getWindowSpiel().refreshShapes();
        }

    }

    public void refillBoard(){

       while (gameboardCardSet.size() < 12) {
           gameboardCardSet.add(new GameBoardCardEnt(this,gameboardCardSet.size()));
       }

       if (gameboardCardSet.size() > 12) {
           for (GameBoardCardEnt gameboardcardEnt : gameboardCardSet) {
               if (gameboardcardEnt.getCard() == null) {
                   if (gameboardcardEnt.getPosition()<12){
                       gameboardcardEnt.setToRemove(true);
                       gameboardCardSet.stream().filter(
                               gameBoardCard -> gameBoardCard.getPosition() > 11 && gameBoardCard.getCard() != null)
                               .toList().get(0)
                               .setPosition(gameboardcardEnt.getPosition());
                   }
                   else {
                       gameboardcardEnt.setToRemove(true);
                   }
               }
           }



           HashSet<GameBoardCardEnt> toRemove = new HashSet<>(
                   gameboardCardSet.stream().filter(GameBoardCardEnt::isToRemove).toList());

           //toRemove.addAll(gameboardCardSet.stream().filter(gameBoardCardEnt-> gameBoardCardEnt.getPosition() >= 12).toList());

           for (GameBoardCardEnt gameboardcardEnt : toRemove) {
               gameboardCardSet.remove(gameboardcardEnt);
           }
       }


        for (GameBoardCardEnt gameboardcardEnt : gameboardCardSet) {
            if (gameboardcardEnt.getCard() == null) {
                gameboardcardEnt.setCard(getNextCard());
                if (gameboardcardEnt.getCard() == null) {
                    if (!checkForSets())
                        finishRound();
                }
            }
        }
        HelloApplication.getWindowSpiel().refreshImage();
    }

    private boolean checkForSets(){
        for (GameBoardCardEnt gameboardcardEnt : gameboardCardSet.stream().filter(gameBoardCardEnt -> gameBoardCardEnt.getCard() != null).toList()) {
            for (GameBoardCardEnt gameboardcardEnt1 : gameboardCardSet) {
                for (GameBoardCardEnt gameboardcardEnt2 : gameboardCardSet) {
                    if (gameboardcardEnt != gameboardcardEnt1 && gameboardcardEnt != gameboardcardEnt2) {
                        SetEnt set = new SetEnt();
                        set.setCard0(gameboardcardEnt.getCard());
                        set.setCard1(gameboardcardEnt1.getCard());
                        set.setCard2(gameboardcardEnt2.getCard());
                        if (set.isValid()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public CardEnt getNextCard(){

        if (sets == null){
            sets = new HashSet<>();
        }


        ArrayList<CardEnt> playedCards = new ArrayList<>();
        for (SetEnt set: sets){
            if (set.getRound() == round) {
                playedCards.add(set.getCard0());
                playedCards.add(set.getCard1());
                playedCards.add(set.getCard2());
            }
        }
        for (GameBoardCardEnt gameboardcardEnt : gameboardCardSet) {
            playedCards.add(gameboardcardEnt.getCard());
        }

        List<CardEnt> remainCards = new ArrayList<>(HelloApplication.getAllCards().stream().toList());
        for (CardEnt card : playedCards) {
            remainCards.remove(card);
        }

        if (remainCards.isEmpty()) {
            return null;
        }

        if (remainCards.size() -1 == 0) {
            return remainCards.get(0);
        }

        return remainCards.get(new Random().nextInt(0,remainCards.size()-1));

    }

    public void finishRound(){
        for (SetEnt set : sets){
            if (set.getIsvalid() && set.getRound() == round){
                for (PlayerEnt player : players){
                    if (Objects.equals(player.getId(), set.getPlayer().getId())){
                        player.setScoresfromformerround(player.getScoresfromformerround()+1);
                    }
                }
            }
        }
        if (modus == GameModus.SHORT || round == countOfPlayers -1){
            finishGame();
        }
        else{
            nextRound();
        }
    }

    public void nextRound(){
        round ++;
        for (GameBoardCardEnt gameboardcardEnt : gameboardCardSet){
            gameboardcardEnt.setCard(getNextCard());
        }
    }

    public void finishGame(){

        HashMap<Integer, Integer> scores = new HashMap<>();

        for (PlayerEnt player : players){
            scores.put(player.getId(), player.getScoresfromformerround());
        }
        int winnerId = -1;
        int winnerScore = 0;

        for (int playerId : scores.keySet()){
            if (scores.get(playerId) > winnerScore){
                winnerId = playerId;
                winnerScore = scores.get(playerId);
            }
        }
        System.out.println(winnerId);

        for (PlayerEnt player : players){
            if (player.getId() == winnerId){
                winner = player.getName();
                break;
            }
        }

        HelloApplication.setGameRuns(false);
        saveGame();
    }


    public void noSetFound(){
        for (int i = 0; i<3; i++){
            gameboardCardSet.add(new GameBoardCardEnt(this,gameboardCardSet.size(),getNextCard()));
        }
    }

    public void saveGame(){
        lastSaved = new Date();
        HelloApplication.getGameRepository().update(this);
    }

    public void loadGame(){

        HelloApplication.setGameRuns(true);
        refillBoard();

    }


    public void startNewGame(int countOfPlayers, ArrayList<String> playerNames, boolean longGame) {

        setPlayers(new HashSet<>());

        setCountOfPlayers(countOfPlayers);

        HelloApplication.getGameRepository().insert(HelloApplication.getGameEnt());

        for (String playerName : playerNames){
            PlayerEnt player = new PlayerEnt();
            player.setName(playerName);
            player.setGame(HelloApplication.getGameEnt());

            HelloApplication.getPlayerRepository().insert(player);



            players.add(player);

            if (longGame)
                modus = GameModus.LONG;
            else
                modus = GameModus.SHORT;

            if (gameboardCardSet == null){
                gameboardCardSet = new HashSet<>();
            }

            for (int i = 0; i<12;i++){
                gameboardCardSet.add(new GameBoardCardEnt(this,i));
            }
        }

        HelloApplication.setGameRuns(true);



        refillBoard();

        HelloApplication.getGameRepository().update(this);

    }
}