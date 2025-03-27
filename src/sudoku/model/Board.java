package sudoku.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Board {
	
	// 1a lista = coluna
	// 2a lista = linha da coluna
	private final List<List<Space>> spaces;
	
	public Board(final List<List<Space>> spaces) {
		this.spaces = spaces;
	}

	public List<List<Space>> getSpaces() {
		return spaces;
	}
	
	public GameStatusEnum getStatus() {
		
		if(spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && s.getActual()!= null)) {
			return GameStatusEnum.NON_STARTED;
		}
		
		return spaces.stream().flatMap(Collection::stream).anyMatch(s -> Objects.isNull(s.getActual())) ? GameStatusEnum.INCOMPLETE : GameStatusEnum.COMPLETE;
	}
	
	public boolean hasError() {
		if(getStatus().equals(GameStatusEnum.NON_STARTED)) {
			return false;
		}
		
		return spaces.stream().flatMap(Collection::stream).anyMatch(s -> Objects.nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
	}
	
	public boolean changeValue(final int col, final int row, final int value) {
		var space = spaces.get(col).get(row);
		
		if(space.isFixed()) {
			return false;
		}
		
		space.setActual(value);
		return true;
	}
	
	public boolean clearValue(final int col, final int row) {
		var space = spaces.get(col).get(row);
		
		if(space.isFixed()) {
			return false;
		}
		
		space.clearSpace();
		return true;
	}
	
	public  void reset() {
		spaces.forEach(c -> c.forEach(Space::clearSpace));
	}
	
	public boolean gameIsFinished() {
		return !hasError() && getStatus().equals(GameStatusEnum.COMPLETE);
	}
}
