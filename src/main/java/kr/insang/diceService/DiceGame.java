package kr.insang.diceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import kr.insang.model.Score;
import kr.insang.repo.ScoreRepository;


@Service
public class DiceGame {

	private int faceValue1;
	private int faceValue2;
	private int[] cell;
	
	private int curCell1;
	private int curCell2;
	
	private WinningStatus ws;
	
	@Autowired 
	private ScoreRepository scoreRepository;
	
	final private int Goal=29;
	
	public DiceGame() {
	
		faceValue1=faceValue2=curCell1=curCell2=0;
		cell = new int[30];
		
		for (int i=0; i<30; i++) cell[i]=i;
		
		make_trap_cells();
		make_bonus_cells();
		this.ws = WinningStatus.NotYet;
	}
	
	public WinningStatus roll(int faceValue1, int faceValue2) {
		
		
		curCell1 += faceValue1;
		curCell2 += faceValue2;
	
		
		if (curCell1 >=Goal && curCell2>=Goal) return this.ws=WinningStatus.Draw;
		else if (curCell1 >=Goal && curCell2<Goal) return this.ws=WinningStatus.Player;
		else if (curCell1 <Goal && curCell2>=Goal) return this.ws = WinningStatus.AlphaDice;
		else {
			if (curCell1 != cell[curCell1]) curCell1 = cell[curCell1];
			if (curCell2 != cell[curCell2]) curCell2 = cell[curCell2];
			return this.ws = WinningStatus.NotYet;
		}


	}
	
	public Score findScoreByName(String name) {
		Score score = scoreRepository.findByName(name).orElse(new Score());
		return score;	
	}
	
	public Score getScoreByName(String name) {
		return scoreRepository.findByName(name).orElseThrow(()->new Not_Exists_Exception());	
	}
	
	public void save(Score score) {
		scoreRepository.save(score);
	}
	
	public WinningStatus getWinningStatus() {
		return this.ws;
	}
	
	public int getCurCellPos1() {
		return curCell1;
	}
	
	
	public int getCurCellPos2() {
		return curCell2;
	}


	
	private void make_trap_cells() {
		cell[10]=0;
		cell[28]=0;
		cell[8]=3;
		cell[15]=5;
		cell[21]=12;
		cell[25]=17;
	}
	
	private void make_bonus_cells() {
		cell[11]=20;
		cell[26]=27;
		cell[9]=14;
		cell[16]=22;
	}

	public void scoreUpdate(Score score, WinningStatus ws) {
		
		if (ws == WinningStatus.Player) score.setWin(score.getWin()+1);
		else if (ws == WinningStatus.AlphaDice) score.setLose(score.getLose()+1);
		else if (ws == WinningStatus.Draw) score.setDraw(score.getDraw()+1);
		else throw new Not_Yet_Exception();
		
		this.curCell1 = 0;
		this.curCell2 = 0;
		this.ws = WinningStatus.NotYet;
		
		score.setTotScore((2*score.getWin()+score.getDraw()-score.getLose())*10);

	}
	


}
