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
        return new ArrayList<>(side);
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

    /**http://redgears.ru/%D0%BF%D0%BE%D0%B2%D0%BE%D1%80%D0%BE%D1%82-%D0%BC%D0%B0%D1%82%D1%80%D0%B8%D1%86%D1%8B-%D0%BD%D0%B0-90%C2%B0-%D0%B3%D1%80%D0%B0%D0%B4%D1%83%D1%81%D0%BE%D0%B2/
     * */
    //по часовой стрелке
    public void moveSideByCircle() {
        ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(side);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                side.get(j).set((size - i - 1), buffer.get(i).get(j));
            }
        }
    }

    //против
    public void moveSideByCircle(boolean isCounterclockwise) {
        if (isCounterclockwise) {
            ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(side);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    side.get(size - (j+1)).set(i, buffer.get(i).get(j));
                }
            }
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
