package kr.insang.diceService;

public class GameStatus {
	
	private WinningStatus ws;
	private int curCell1;
	private int curCell2;
	
	public void GameStatus() {
		this.ws = WinningStatus.NotYet;
		this.curCell1 = 0;
		this.curCell2  =0;
	}
	
	public void setWs(WinningStatus ws) {
		this.ws = ws;
	}
	
	public WinningStatus getWs() {
		return this.ws;
	}
	

	
	public void setCurCell1(int curCell1) {
		this.curCell1 = curCell1;	
	}
	
	public int getCurCell1() {
		return this.curCell1;	
	}
	
	public void setCurCell2(int curCell2) {
		this.curCell2 = curCell2;	
	}
	
	public int getCurCell2() {
		return this.curCell2;	
	}
}
