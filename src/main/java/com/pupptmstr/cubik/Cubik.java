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

    private static int size;


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
    private static ArrayList<ArrayList<Integer>> topSide = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> bottomSide = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sideA = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sideB = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sideC = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sideD = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> faceSide;
    private static ArrayList<ArrayList<ArrayList<Integer>>> body= new ArrayList<>();

    //Создается кубик, заполняющийся нулями, потом значения меняются на остальные цвета
    Cubik(int size) {
        this.size = size;
        create();
        init();
        faceSide = sideC;
    }

    Cubik(int size, boolean random) {
        this.size = size;
        create();
        init();
        faceSide = sideC;

        if (random) {
            shufle();
        }
    }

    //Создание тела кубика
    private static void create() {
        for (int i = 0; i < size; i++){
            ArrayList<Integer> line = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                line.add(0);
            }
            topSide.add(line);
            bottomSide.add(line);
            sideA.add(line);
            sideB.add(line);
            sideC.add(line);
            sideD.add(line);
        }
    }

    //Расстановка(иницализация) цветов кубика
    private static void init() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sideD.get(i).set(j, 1);
                bottomSide.get(i).set(j, 2);
                sideB.get(i).set(j, 3);
                sideA.get(i).set(j, 4);
                topSide.get(i).set(j, 5);
            }
        }
    }

    public static void movePart() {}

    public static void turn(boolean isRight) {}

    public static void shufle() {

    }

    public static String lookAtSide() {return "";}


    public String toString() {
        StringBuilder builder = new StringBuilder();
        body.add(sideC);
        body.add(sideD);
        body.add(bottomSide);
        body.add(sideB);
        body.add(sideA);
        body.add(topSide);

        for (int i = 0; i < 6; i++) {
            if (i < 1 || i > 3 ) {
                for (int j = 0; j < size; j++) {
                    builder.append("      ");
                    for (int k = 0; k < size; k ++) {
                        builder.append(body.get(i).get(j).get(k));
                    }
                    builder.append("\n");
                }
                builder.append("\n");
            } else {
                for (int j = 0; j < size; j++) {
                    builder.append(" ");
                    for (int k = 0; k < size; k++) {
                        builder.append(body.get(i).get(j).get(k));
                    }
                    builder.append(" ");

                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
