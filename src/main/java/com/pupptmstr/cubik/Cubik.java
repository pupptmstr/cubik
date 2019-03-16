package com.pupptmstr.cubik;

import java.util.ArrayList;
import java.util.Random;

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
     *   e   000 <-top
     *   A   000
     *   |
     *  111  222  333
     *  111  222  333 <- sideC
     *  111  222  333
     *       |
     *       sideB
     *
     *       444
     *       444 <- bottom
     *       444
     *
     *       555
     *       555 <- sideD
     *       555
     *
     * Every number(from 0 to 5) means Color
     * faceSide - side that look at player now
     *
     * */
    private int size;
    private Side top;
    private Side sideA;
    private Side sideB;
    private Side sideC;
    private Side bottom;
    private Side sideD;
    private ArrayList<Side> body= new ArrayList<>();

    //Создается кубик, заполняющийся интами 0..5
    //Лицевой(повернутой к нам стороной) по дефолту считается bottomSize
    Cubik(int size) {
        this.size = size;
        top = new Side(size, 0);
        sideA = new Side(size, 1);
        sideB = new Side(size, 2);
        sideC = new Side(size, 3);
        bottom = new Side(size, 4);
        sideD = new Side(size, 5);
        body.add(top);
        body.add(sideA);
        body.add(sideB);
        body.add(sideC);
        body.add(bottom);
        body.add(sideD);

    }

    Cubik(int size, boolean random) {
        this.size = size;
        top = new Side(size, 0);
        sideA = new Side(size, 1);
        sideB = new Side(size, 2);
        sideC = new Side(size, 3);
        bottom = new Side(size, 4);
        sideD = new Side(size, 5);
        body.add(top);
        body.add(sideA);
        body.add(sideB);
        body.add(sideC);
        body.add(bottom);
        body.add(sideD);
        if (random) {
            shufle();
        }
    }

    public void moveLine(int row) {
        ArrayList<Integer> buffer = new ArrayList<>(sideB.getLine(false, row));
        sideB.setLine(false, row, sideA.getLine(false, row));
        sideA.setLine(false, row, sideD.getLine(false, size - row + 1));
        sideD.setLine(false, size - row + 1, sideC.getLine(false, row));
        sideC.setLine(false, row, buffer);
        if (row == (size - 1)) {
            bottom.moveSideByCircle();
        }
        if (row - 1 == 0) {
            top.moveSideByCircle(true);
        }
    }

    public void moveLine(int row, boolean isVertical) {
        if (isVertical) {
            ArrayList<Integer> buffer = new ArrayList<>(sideB.getLine(true, row));
            sideB.setLine(true, row, bottom.getLine(true, row));
            bottom.setLine(true, row, sideD.getLine(true, row));
            sideD.setLine(true, row, top.getLine(true, row));
            top.setLine(true, row, buffer);

            if (row == (size - 1)) {
                sideC.moveSideByCircle();
            }
            if (row - 1 == 0) {
                sideA.moveSideByCircle(true);
            }
        }
    }

    public void moveLine(int row, boolean isVertical, boolean isUntoYourself) {
        if (isUntoYourself) {
            moveLine(row, isVertical);
            moveLine(row, isVertical);
            moveLine(row, isVertical);
        }
    }

    //по умолчанию - поворот кубика осуществляется горизонтально движением руки от себя(вправо)
    //(против часовой стрелки)
    public void turnCubik() {
        ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(sideB.getSide());

        sideB.setSide(sideA.getSide());

        for (int i = 0; i < size; i++) {
            sideA.setLine(false, i + 1, new ArrayList<>(sideD.getLine(false, size - i)));
        }

        for (int i = 0; i < size; i++) {
            sideD.setLine(false, i + 1, sideC.getLine(false, size - i));
        }

        sideC.setSide(buffer);

        top.moveSideByCircle(true);
        bottom.moveSideByCircle();
    }

    //параметр isVertical меняет ось вращения на вертикальную, но поворот все равно движением руки от себя
    //(лицевая сторона оказывается сверху)
    public void turnCubik(boolean isVertical) {
        if (isVertical) {
            ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(sideB.getSide());
            sideB.setSide(bottom.getSide());
            bottom.setSide(sideD.getSide());
            sideD.setSide(top.getSide());
            top.setSide(buffer);
            sideC.moveSideByCircle();
            sideA.moveSideByCircle(true);

        } else turnCubik();
    }

    //параметр isUntoYourself меняет направление движения руки -> если движение горизонтально, то
    //лицевая сторона оказывается слева, а если вертикально, то снизу
    public void turnCubik(boolean isVertical, boolean isUntoYourself) {
        if (isUntoYourself) {
            if (isVertical){
                ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(sideB.getSide());
                sideB.setSide(top.getSide());
                top.setSide(sideD.getSide());
                sideD.setSide(bottom.getSide());
                bottom.setSide(buffer);
                sideA.moveSideByCircle();
                sideC.moveSideByCircle(true);
            } else {
                ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(sideB.getSide());
                sideB.setSide(sideC.getSide());

                for (int i = 0; i < size; i++) {
                    sideC.setLine(false, i, sideD.getLine(false, size - i));
                }

                for (int i = 0; i < size; i++) {
                    sideD.setLine(false, i, sideD.getLine(false, size - i));
                }

                sideA.setSide(buffer);
            }
        } else turnCubik(isVertical);
    }

    private void shufle() {
        Random randomRow = new Random();
        Random randomVertical = new Random();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0)
                moveLine(randomRow.nextInt(size) + 1);
            else
                moveLine(randomRow.nextInt(size) + 1, true);
        }
    }

    public String lookAtSide() {
        return toString();
    }


    public String toString() {
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
                    builder.append("           ")
                            .append(body.get(i).getLine(false, j + 1)).append("\n");
                }
                builder.append("\n");
                i++;
            }
        }
        return builder.toString();
    }

}
