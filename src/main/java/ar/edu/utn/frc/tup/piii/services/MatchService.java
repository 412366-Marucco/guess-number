package ar.edu.utn.frc.tup.piii.services;

import ar.edu.utn.frc.tup.piii.entities.MatchEntity;
import ar.edu.utn.frc.tup.piii.entities.UserEntity;
import ar.edu.utn.frc.tup.piii.models.Match;
import ar.edu.utn.frc.tup.piii.models.MatchDifficulty;
import ar.edu.utn.frc.tup.piii.models.RoundMatch;
import ar.edu.utn.frc.tup.piii.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    Match createMatch(User user, MatchDifficulty matchDifficulty);

    Match getMatchById(Long matchId);

    RoundMatch playMatch(Match match, Integer number);
}
