package com.pupptmstr.cubik;

import java.util.ArrayList;

/**
 * Вариант 15 -- кубик Рубика (модель)
 *
 * Хранит состояние кубика Рубика.
 *
 * Операции:
 *  - поворот указанного числа слоев
 *    (необходимо продумать, как пользователи класса будут специфицировать грань и направление поворота),
 *  - поворот всего кубика (т.е. переименование граней),
 *  - запрос состояния грани.
 *
 * Бонус: произвольный размер кубика; реализация алгоритма “решения” кубика одного размера (хотя бы 2x2x2).
 *
 * Утилиты: случайная установка состояния кубика
 * */

public class Cubik {

    /** Хранение состояния кубика.
     *
     *   s
     *   i
     *   d   000
     *   e   000 <-sideC
     *   D   000
     *   |
     *  111  222  333
     *  111  222  333 <- sideB
     *  111  222  333
     *       |
     * bottomSide
     *
     *       444
     *       444 <- sideA
     *       444
     *
     *       555
     *       555 <- topSide
     *       555
     *
     * Every number(from 0 to 5) means Color
     * faceSide - side that look at player now
     *
     * */
    private int size;
    private Side sideC;
    private Side sideD;
    private Side bottomSide;
    private Side sideB;
    private Side sideA;
    private Side topSide;
    private ArrayList<Side> body= new ArrayList<>();

    //Создается кубик, заполняющийся интами 0..5
    //Лицевой(повернутой к нам стороной) по дефолту считается bottomSize
    Cubik(int size) {
        this.size = size;
        sideC = new Side(size, 0);
        sideD = new Side(size, 1);
        bottomSide = new Side(size, 2);
        sideB = new Side(size, 3);
        sideA = new Side(size, 4);
        topSide = new Side(size, 5);
        body.add(sideC);
        body.add(sideD);
        body.add(bottomSide);
        body.add(sideB);
        body.add(sideA);
        body.add(topSide);

    }

    Cubik(int size, boolean random) {
        this.size = size;
        sideC = new Side(size, 0);
        sideD = new Side(size, 1);
        bottomSide = new Side(size, 2);
        sideB = new Side(size, 3);
        sideA = new Side(size, 4);
        topSide = new Side(size, 5);
        body.add(sideC);
        body.add(sideD);
        body.add(bottomSide);
        body.add(sideB);
        body.add(sideA);
        body.add(topSide);
        if (random) {
            shufle();
        }
    }

    public void movePart(int row) {
        ArrayList<Integer> buffer = new ArrayList<>(bottomSide.getLine(false, row));
        bottomSide.setLine(false, row, sideD.getLine(false, row));
        sideD.setLine(false, row, topSide.getLine(false, size - row));
        topSide.setLine(false, size - row, sideB.getLine(false, row));
        sideB.setLine(false, row, buffer);
        if (row == (size - 1)) {
            sideA.moveSideByCircle();
        }
        if (row - 1 == 0) {
            sideC.moveSideByCircle(true);
        }
    }

    public void movePart(int row, boolean isVertical) {
        if (isVertical) {
            ArrayList<Integer> buffer = new ArrayList<>(bottomSide.getLine(true, row));
            bottomSide.setLine(true, row, sideA.getLine(true, row));
            sideA.setLine(true, row, topSide.getLine(true, row));
            topSide.setLine(true, row, sideC.getLine(true, row));
            sideC.setLine(true, row, buffer);

            if (row == (size - 1)) {
                sideB.moveSideByCircle();
            }
            if (row - 1 == 0) {
                sideD.moveSideByCircle(true);
            }
        }

    }

    //по умолчанию - поворот кубика осуществляется горизонтально движением руки от себя(вправо)
    //(против часовой стрелки)
    public void turnCubik() {
        ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(bottomSide.getSide());
        bottomSide.setSide(sideD.getSide());

        for (int i = 0; i < size; i++) {
            sideD.setLine(false, i + 1, topSide.getLine(false, size - i));
        }

        for (int i = 0; i < size; i++) {
            topSide.setLine(false, i + 1, sideB.getLine(false, size - i));
        }

        sideB.setSide(buffer);

        sideC.moveSideByCircle(true);
        sideA.moveSideByCircle();
    }

    /*//параметр isVertical меняет ось вращения на вертикальную, но поворот все равно движением руки от себя
    //(лицевая сторона оказывается сверху)
    public void turnCubik(boolean isVertical) {
        if (isVertical) {
            ArrayList<ArrayList<Integer>> buffer = (ArrayList<ArrayList<Integer>>) bottomSide.clone();
            bottomSide = (ArrayList<ArrayList<Integer>>) sideA.clone();
            sideA = (ArrayList<ArrayList<Integer>>) topSide.clone();
            topSide = (ArrayList<ArrayList<Integer>>) sideC.clone();
            sideC = (ArrayList<ArrayList<Integer>>) buffer.clone();
            moveSideByCircle(sideB);
            moveSideByCircle(true, sideD);

        } else turnCubik();
    }

    //параметр isUntoYourself меняет направление движения руки -> если движение горизонтально, то
    //лицевая сторона оказывается слева, а если вертикально, то снизу
    public void turnCubik(boolean isVertical, boolean isUntoYourself) {
        if (isUntoYourself) {
            if (isVertical){
               ArrayList<ArrayList<Integer>> buffer = (ArrayList<ArrayList<Integer>>) bottomSide.clone();
               bottomSide =(ArrayList<ArrayList<Integer>>) sideC.clone();
               sideC = (ArrayList<ArrayList<Integer>>) topSide.clone();
               topSide = (ArrayList<ArrayList<Integer>>) sideA.clone();
               sideA = (ArrayList<ArrayList<Integer>>) buffer.clone();
            } else {
                ArrayList<ArrayList<Integer>> buffer = (ArrayList<ArrayList<Integer>>) bottomSide.clone();
                bottomSide = (ArrayList<ArrayList<Integer>>) sideB.clone();

                for (int i = 0; i < size; i++) {
                    sideB.set(i, topSide.get(topSide.size()-(i+1)));
                }

                for (int i = 0; i < size; i++) {
                    topSide.set(i, topSide.get(sideD.size()-(i+1)));

                }

                sideD = (ArrayList<ArrayList<Integer>>) buffer.clone();
            }
        } else turnCubik(isVertical);
    }

*/
    public void shufle() {

    }

    public String lookAtSide() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i  < 6) {
            if (i == 1) {
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < 3; k++) {
                        builder.append(" ").append(body.get(i + k).getLine(false, j + 1));
                    }
                    builder.append("\n");
                }
                i+=3;
                builder.append("\n");
            } else {
                for (int j = 0; j < size; j++) {
                    builder.append("           ").append(body.get(i).getLine(false, j + 1)).append("\n");
                }
                builder.append("\n");
                i++;
            }
        }

        return builder.toString();
    }


    public String toString() {
        return lookAtSide();
    }

}
