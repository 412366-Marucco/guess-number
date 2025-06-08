package ar.edu.utn.frc.tup.piii.services.impl;

import ar.edu.utn.frc.tup.piii.entities.MatchEntity;
import ar.edu.utn.frc.tup.piii.entities.UserEntity;
import ar.edu.utn.frc.tup.piii.models.*;
import ar.edu.utn.frc.tup.piii.repositories.MatchRepository;
import ar.edu.utn.frc.tup.piii.services.MatchService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Random random = new Random();

    @Override
    public Match createMatch(User user, MatchDifficulty matchDifficulty) {
        MatchEntity matchEntity = new MatchEntity();

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        matchEntity.setUserEntity(userEntity);

        matchEntity.setDifficulty(matchDifficulty);
        switch (matchDifficulty) {
            case EASY -> matchEntity.setRemainingTries(10);
            case MEDIUM -> matchEntity.setRemainingTries(8);
            case HARD -> matchEntity.setRemainingTries(5);
        }

        matchEntity.setNumberToGuess(random.nextInt(100));

        matchEntity.setStatus(MatchStatus.IN_PROGRESS);

        matchEntity.setCreatedAt(LocalDateTime.now());
        matchEntity.setUpdatedAt(LocalDateTime.now());

        MatchEntity matchEntitySaved = matchRepository.save(matchEntity);
        return modelMapper.map(matchEntitySaved, Match.class);
    }

    @Override
    public Match getMatchById(Long matchId) {
        Optional<MatchEntity> matchEntityOptional = matchRepository.findById(matchId);

        if (matchEntityOptional.isEmpty()) throw new EntityNotFoundException();

        return modelMapper.map(matchEntityOptional.get(), Match.class);
    }

    @Override
    public RoundMatch playMatch(Match match, Integer number) {
        RoundMatch roundMatch = new RoundMatch();
        roundMatch.setMatch(match);
        if (match.getStatus().equals(MatchStatus.FINISHED)) {
            //TODO: error
            return null;
        }

        Integer numberToGuess = match.getNumberToGuess();

        if (numberToGuess.equals(number)) {
            //TODO: calcular score
            //TODO: dar respuesta
            match.setStatus(MatchStatus.FINISHED);
            roundMatch.setRespond("GANO");
        } else {
            match.setRemainingTries(match.getRemainingTries() - 1);

            if (match.getRemainingTries().equals(0)) {
                match.setStatus(MatchStatus.FINISHED);
                roundMatch.setRespond("PERDIO");
            } else {
                if (number > match.getNumberToGuess()) {
                    roundMatch.setRespond("MENOR");
                } else {
                    roundMatch.setRespond("MAYOR");
                }
            }
        }
        UserEntity userEntity = modelMapper.map(match.getUser(), UserEntity.class);

        MatchEntity matchEntity = modelMapper.map(match, MatchEntity.class);

        matchEntity.setUserEntity(userEntity);

        matchEntity.setUpdatedAt(LocalDateTime.now());

        matchRepository.save(matchEntity);
        return roundMatch;
    }

}
