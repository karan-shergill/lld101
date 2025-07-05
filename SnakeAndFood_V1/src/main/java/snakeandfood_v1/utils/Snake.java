package snakeandfood_v1.utils;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {
    private Deque<Cell> body;
    private Map<Cell, Boolean> positionMap;


    public Snake() {
        this.body = new LinkedList<>();
        this.positionMap = new HashMap<>();

        // snake starts from 0,0 BUT this can be random as well
        Cell initialPos = new Cell(0, 0);
        this.body.add(initialPos);
        this.positionMap.put(initialPos, true);
    }

    public Deque<Cell> getBody() {
        return body;
    }

    public Map<Cell, Boolean> getPositionMap() {
        return positionMap;
    }
}
