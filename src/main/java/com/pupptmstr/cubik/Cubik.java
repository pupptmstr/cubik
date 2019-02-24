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
    private static int size;
    private static ArrayList<ArrayList<Integer>> sideC = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sideD = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> bottomSide = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sideB = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sideA = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> topSide = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<Integer>>> body= new ArrayList<>();

    //Создается кубик, заполняющийся интами 0..5
    //Лицевой(повернутой к нам стороной) по дефолту считается bottomSize
    Cubik(int size) {
        this.size = size;
        create();
        init();
    }

    Cubik(int size, boolean random) {
        this.size = size;
        create();
        init();

        if (random) {
            shufle();
        }
    }

    //Создание тела кубика и инициализация его цветов
    private static void create() {

        for (int j = 0; j < size; j++) {
            ArrayList<Integer> line = new ArrayList<>();
            for (int k = 0; k < size; k++) {
                line.add(0);
            }
            sideC.add(line);
            sideD.add(line);
            bottomSide.add(line);
            sideB.add(line);
            sideA.add(line);
            topSide.add(line);
        }
        body.add(sideC);
        body.add(sideD);
        body.add(bottomSide);
        body.add(sideB);
        body.add(sideA);
        body.add(topSide);
    }

    private static void init() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> line = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    line.add(i);
                }
                body.get(i).set(j, line);
            }
        }
        sideC = body.get(0);
        sideD = body.get(1);
        bottomSide = body.get(2);
        sideB = body.get(3);
        sideA = body.get(4);
        topSide = body.get(5);
    }

    public static void movePart(int row) {
        //TODO()
    }

    public static void movePart(int row, boolean isVertical) {
        //TODO()
    }

    public static void movePart() {
        //TODO()
    }

    private static void moveSideByCircle(ArrayList<ArrayList<Integer>> list) {
        ArrayList<ArrayList<Integer>> bufferr = (ArrayList<ArrayList<Integer>>) list.clone();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                list.get(j).set((size - (i+1)), bufferr.get(i).get(j));
            }
        }
    }

    private static void moveSideByCircle(boolean isCounterclockwise, ArrayList<ArrayList<Integer>> list) {
        if (isCounterclockwise) {
            ArrayList<ArrayList<Integer>> bufferr = (ArrayList<ArrayList<Integer>>) list.clone();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    list.get(size - (j+1)).set(i, bufferr.get(i).get(j));
                }
            }
        } else moveSideByCircle(list);

    }

    //по умолчанию - поворот кубика осуществляется горизонтально движением руки от себя(вправо)
    //(против часовой стрелки)
    public static void turnCubik() {
        ArrayList<ArrayList<Integer>> buffer = new ArrayList<>(bottomSide);
        bottomSide = (ArrayList<ArrayList<Integer>>) sideD.clone();

        for (int i = 0; i < size; i++) {
            sideD.set(i, topSide.get(topSide.size()-(i+1)));
        }

        for (int i = 0; i < size; i++) {
            topSide.set(i, sideB.get(sideB.size() - (i+1)));
        }

        sideB = (ArrayList<ArrayList<Integer>>) buffer.clone();

        moveSideByCircle(true, sideC);
        moveSideByCircle(sideA);
    }

    //параметр isVertical меняет ось вращения на вертикальную, но поворот все равно движением руки от себя
    //(лицевая сторона оказывается сверху)
    public static void turnCubik(boolean isVertical) {
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
    public static void turnCubik(boolean isVertical, boolean isUntoYourself) {
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

    public static void shufle() {

    }

    public static String lookAtSide() {
        body.set(0, sideC);
        body.set(1, sideD);
        body.set(2, bottomSide);
        body.set(3, sideB);
        body.set(4, sideA);
        body.set(5, topSide);
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i  < 6) {
            if (i == 1) {
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < 3; k++) {
                        builder.append(" ").append(body.get(i + k).get(j));
                    }
                    builder.append("\n");
                }
                i+=3;
                builder.append("\n");
            } else {
                for (int j = 0; j < size; j++) {
                    builder.append("           ").append(body.get(i).get(j)).append("\n");
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
