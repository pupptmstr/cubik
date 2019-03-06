package com.pupptmstr.cubik;

import java.util.ArrayList;

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
        ArrayList<ArrayList<Integer>> res = (ArrayList<ArrayList<Integer>>) side.clone();
        return res;
    }

    private void init(int num) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                side.get(i).set(j, num);
            }
        }
    }

    public void setSide(ArrayList<ArrayList<Integer>> newSide) {
        side =(ArrayList<ArrayList<Integer>>) newSide.clone();
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
        ArrayList<ArrayList<Integer>> bufferr = (ArrayList<ArrayList<Integer>>) side.clone();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                side.get(j).set((size - (i+1)), bufferr.get(i).get(j));
            }
        }
    }

    //против
    public void moveSideByCircle(boolean isCounterclockwise) {
        if (isCounterclockwise) {
            ArrayList<ArrayList<Integer>> bufferr = (ArrayList<ArrayList<Integer>>) side.clone();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    side.get(size - (j+1)).set(i, bufferr.get(i).get(j));
                }
            }
        } else moveSideByCircle();

    }


}
