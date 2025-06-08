package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.*;
import ar.edu.utn.frc.tup.piii.models.Match;
import ar.edu.utn.frc.tup.piii.models.RoundMatch;
import ar.edu.utn.frc.tup.piii.models.User;
import ar.edu.utn.frc.tup.piii.services.MatchService;
import ar.edu.utn.frc.tup.piii.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guess-number/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MatchService matchService;

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto.getUserName(), userDto.getEmail());
        UserDto userDtoCreated = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDtoCreated);
    }

    @PostMapping("/{userId}/matches")
    public ResponseEntity<MatchDto> createUserMatch(@PathVariable Long userId,
                                                    @RequestBody CreateUserMatchDto createUserMatchDto) {

        Match userMatch = userService.createUserMatch(userId, createUserMatchDto.getMatchDifficulty());
        MatchDto userMatchDtoCreated = modelMapper.map(userMatch, MatchDto.class);
        return ResponseEntity.ok(userMatchDtoCreated);
    }

    @PostMapping("/{userId}/matches/{matchId}")
    public ResponseEntity<RoundMatchDto> playUserMatch(@PathVariable Long userId,
                                                       @PathVariable Long matchId,
                                                       @RequestBody PlayUserMatchDto playUserMatchDto) {
        RoundMatch roundMatch = userService.playUserMatch(userId, matchId, playUserMatchDto.getNumber());

        MatchDto matchDto = modelMapper.map(roundMatch.getMatch(), MatchDto.class);

        RoundMatchDto roundMatchDtoCreated = modelMapper.map(roundMatch, RoundMatchDto.class);

        roundMatchDtoCreated.setMatchDto(matchDto);

        return ResponseEntity.ok(roundMatchDtoCreated);
    }
}
