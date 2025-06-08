package ar.edu.utn.frc.tup.piii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;

    private User user;

    private MatchDifficulty difficulty;

    private Integer numberToGuess;

    private Integer remainingTries;

    private MatchStatus status;
}
