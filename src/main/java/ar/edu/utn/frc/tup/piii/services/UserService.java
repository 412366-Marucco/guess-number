package ar.edu.utn.frc.tup.piii.services;

import ar.edu.utn.frc.tup.piii.models.Match;
import ar.edu.utn.frc.tup.piii.models.MatchDifficulty;
import ar.edu.utn.frc.tup.piii.models.RoundMatch;
import ar.edu.utn.frc.tup.piii.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(String userName, String email);

    Match createUserMatch(Long userId, MatchDifficulty matchDifficulty);

    RoundMatch playUserMatch(Long userId, Long matchId, Integer number);
}
