package com.golf.app.service;

import com.golf.app.model.Player;
import com.golf.app.repo.PlayerRepository;
import com.golf.app.utils.PlayerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Iterable<Player> getAllPlayers() {
        Sort sort = Sort.by(Sort.Direction.ASC, "surname");
        return playerRepository.findAll(sort);
    }

    @Override
    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player savePlayer(Player player) {
        player.setSurname(player.getSurname().toUpperCase());
        player.setName(PlayerUtils.capitalizeName(player.getName()));
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> getPlayerByName(String name) {
        return playerRepository.getByName(name);
    }

    @Override
    public Optional<Player> getPlayerBySurnameAndName(String surname, String name) {
        return playerRepository.getBySurnameAndName(surname, name);
    }

    @Override
    public Iterable<Player> getAllPlayersByRound(String name) {
        Sort sort = Sort.by(Sort.Direction.ASC, "surname");
        return playerRepository.findAllByRoundName(name, sort);
    }

}
