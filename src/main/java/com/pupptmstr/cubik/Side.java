package com.pupptmstr.cubik;

import java.util.ArrayList;
import java.util.List;

public class Side {

    private ArrayList<ArrayList<Integer>> side = new ArrayList<>();
    private int size;

    Side(int size, int startingNum) {
        this.size = size;
        for (int j = 0; j < size; j++) {
            ArrayList<Integer> line = new ArrayList<>();
            for (int k = 0; k < size; k++) {
                line.add(0);
            }
            side.add(line);
        }
        init(startingNum);
    }

    public ArrayList<ArrayList<Integer>> getSide() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (ArrayList<Integer> row: side) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (Integer el: row) {
                tmp.add(el);
            }
            list.add(tmp);
        }
        return list;
    }

    private void init(int num) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                side.get(i).set(j, num);
            }
        }
    }

    public void testInit() {
        int num = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                side.get(i).set(j, num);
                num++;
            }
        }
    }

    public void setSide(ArrayList<ArrayList<Integer>> newSide) {
        side = new ArrayList<>(newSide);
    }

    //отсчет сверху вниз с еденицы
    public ArrayList<Integer> getLine(boolean isVertical, int rowNum) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (isVertical) {
                res.add(side.get(i).get(rowNum - 1));
            } else {
                res.add(side.get(rowNum - 1).get(i));
            }
        }

        return res;
    }



    public void setLine(boolean isVertical, int rowNum, ArrayList<Integer> newPart) {
        for (int i = 0; i < size; i++) {
            if (isVertical) {
                side.get(i).set(rowNum - 1, newPart.get(i));
            } else {
                side.get(rowNum - 1).set(i, newPart.get(i));
            }
        }
    }

    //по часовой стрелке
    public void moveSideByCircle() {
        Side buffer = new Side(3, 0);
        buffer.setSide(getSide());
        setLine(true, 3, buffer.getLine(false, 1));
        setLine(true, 2, buffer.getLine(false, 2));
        setLine(true, 1, buffer.getLine(false, 3));
    }

    //против
    public void moveSideByCircle(boolean isCounterclockwise) {
        Side buffer = new Side(3, 0);
        buffer.setSide(getSide());
        if (isCounterclockwise) {
            moveSideByCircle();
            moveSideByCircle();
            moveSideByCircle();
        } else moveSideByCircle();

    }

    public String toString() {
        StringBuilder bldr = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bldr.append(side.get(i).get(j)).append(" ");
            }
            bldr.append("\n");
        }
        return bldr.toString();
    }


}
