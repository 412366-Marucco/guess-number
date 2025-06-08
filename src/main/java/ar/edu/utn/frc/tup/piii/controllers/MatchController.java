package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.MatchDto;
import ar.edu.utn.frc.tup.piii.models.Match;
import ar.edu.utn.frc.tup.piii.services.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guess-number/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping("")
//    public ResponseEntity<MatchDto> getMatchList() {
//        List<Match> matchList = matchService.getMatchList();
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MatchDto> getMatchById(@PathVariable Long id) {
//        Match match = matchService.getMatch(id);
//        return null;
//    }

//    @PostMapping("")
//    public ResponseEntity<MatchDto> createMatch(@RequestBody MatchDto matchDto) {
//        return null;
//    }

//    @PutMapping("")
//    public ResponseEntity<MatchDto> updateMatch(MatchDto matchDto) {
//        Match match = matchService.updateMatch(null);
//        return null;
//    }
//
//    @DeleteMapping("")
//    public ResponseEntity<Void> deleteMatch(MatchDto matchDto) {
//        matchService.deleteMatch(null);
//        return null;
//    }
}
