package kr.insang.gamecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import kr.insang.diceService.DiceGame;
import kr.insang.diceService.GameStatus;
import kr.insang.diceService.GameStatus;
import kr.insang.diceService.WinningStatus;

import kr.insang.model.Score;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController    
@RequestMapping(path="/dicegame") 
public class DicegameController {
	
	@Autowired
	private DiceGame diceGame;
	
//	@RequestMapping(value="/{name}/add")
//	public void newDiceGame(@PathVariable String name) {
//		
//	}
	
	@RequestMapping(path="/") 
	@ResponseStatus(HttpStatus.OK)
	public GameStatus  roll(@RequestParam String dice1, @RequestParam String dice2) {

		int player = Integer.parseInt(dice1);
		int alpha = Integer.parseInt(dice2);
		
		
		
		WinningStatus ws = diceGame.roll(player, alpha);
		GameStatus gs = new GameStatus();
		

		gs.setWs(ws);
		gs.setCurCell1(diceGame.getCurCellPos1());
		gs.setCurCell2(diceGame.getCurCellPos2());

		return gs;
	}

	
	@RequestMapping(path="/save") 
	@ResponseStatus(HttpStatus.OK)
	public Score  saveScore(@RequestParam String name) {

			WinningStatus ws = diceGame.getWinningStatus();
			Score score = diceGame.findScoreByName(name);
			score.setName(name);
			diceGame.scoreUpdate(score, ws);
			diceGame.save(score);
			return score;
	}
	
	@RequestMapping(path="/getScore") 
	@ResponseStatus(HttpStatus.OK)
	public Score  getScore(@RequestParam String name) {
	
			Score score = diceGame.getScoreByName(name);
	
			return score;
	}

}
