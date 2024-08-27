package com.golf.app.service;

import com.golf.app.model.Player;
import java.util.Optional;

public interface PlayerService {

    Iterable<Player> getAllPlayers();
    Optional<Player> getPlayerById(Long id);
    Player savePlayer(Player player);
    void deletePlayer(Long id);
    Optional<Player> getPlayerByName(String name);
    Optional<Player> getPlayerBySurnameAndName(String surname, String name);
    Iterable<Player> getAllPlayersByRound(String roundName);

}
