package com.pupptmstr.cubik;

import org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCubik {

    @Test
    public void toStringTest() {
        Cubik cubik = new Cubik(3);
        assertEquals("           [0, 0, 0]\n" +
                "           [0, 0, 0]\n" +
                "           [0, 0, 0]\n\n" +
                " [1, 1, 1] [2, 2, 2] [3, 3, 3]\n" +
                " [1, 1, 1] [2, 2, 2] [3, 3, 3]\n" +
                " [1, 1, 1] [2, 2, 2] [3, 3, 3]\n\n" +
                "           [4, 4, 4]\n" +
                "           [4, 4, 4]\n" +
                "           [4, 4, 4]\n\n" +
                "           [5, 5, 5]\n" +
                "           [5, 5, 5]\n" +
                "           [5, 5, 5]\n\n",
                cubik.toString()
                );
        Cubik cubik1 = new Cubik(4);
        assertEquals("           [0, 0, 0, 0]\n" +
                        "           [0, 0, 0, 0]\n" +
                        "           [0, 0, 0, 0]\n" +
                        "           [0, 0, 0, 0]\n\n" +
                        " [1, 1, 1, 1] [2, 2, 2, 2] [3, 3, 3, 3]\n" +
                        " [1, 1, 1, 1] [2, 2, 2, 2] [3, 3, 3, 3]\n" +
                        " [1, 1, 1, 1] [2, 2, 2, 2] [3, 3, 3, 3]\n" +
                        " [1, 1, 1, 1] [2, 2, 2, 2] [3, 3, 3, 3]\n\n" +
                        "           [4, 4, 4, 4]\n" +
                        "           [4, 4, 4, 4]\n" +
                        "           [4, 4, 4, 4]\n" +
                        "           [4, 4, 4, 4]\n\n" +
                        "           [5, 5, 5, 5]\n" +
                        "           [5, 5, 5, 5]\n" +
                        "           [5, 5, 5, 5]\n" +
                        "           [5, 5, 5, 5]\n\n",
                cubik1.toString()
        );
    }

    @Test
    public void moveSideByCircle() {
        Side side = new Side(3, 0);
        side.testInit();
        side.moveSideByCircle();
        assertEquals("6 3 0 \n" +
                "7 4 1 \n" +
                "8 5 2 \n", side.toString());
    }

    @Test
    public void moveSideByCircleUnto() {
        Side side = new Side(3, 0);
        side.testInit();
        side.moveSideByCircle(true);
    }

    @Test
    public void turnLine() {
        Cubik cubik = new Cubik(3);
        cubik.moveLine(3);
        assertEquals("           [0, 0, 0]\n" +
                        "           [0, 0, 0]\n" +
                        "           [0, 0, 0]\n\n" +
                        " [1, 1, 1] [2, 2, 2] [3, 3, 3]\n" +
                        " [1, 1, 1] [2, 2, 2] [3, 3, 3]\n" +
                        " [5, 5, 5] [1, 1, 1] [2, 2, 2]\n\n" +
                        "           [4, 4, 4]\n" +
                        "           [4, 4, 4]\n" +
                        "           [4, 4, 4]\n\n" +
                        "           [3, 3, 3]\n" +
                        "           [5, 5, 5]\n" +
                        "           [5, 5, 5]\n\n",
                cubik.toString());
    }

    @Test
    public void turnLineVertical() {
        Cubik cubik = new Cubik(3);
        cubik.moveLine(3, true);
        assertEquals("           [0, 0, 2]\n" +
                        "           [0, 0, 2]\n" +
                        "           [0, 0, 2]\n\n" +
                        " [1, 1, 1] [2, 2, 4] [3, 3, 3]\n" +
                        " [1, 1, 1] [2, 2, 4] [3, 3, 3]\n" +
                        " [1, 1, 1] [2, 2, 4] [3, 3, 3]\n\n" +
                        "           [4, 4, 5]\n" +
                        "           [4, 4, 5]\n" +
                        "           [4, 4, 5]\n\n" +
                        "           [5, 5, 0]\n" +
                        "           [5, 5, 0]\n" +
                        "           [5, 5, 0]\n\n",
                cubik.toString());
    }

    @Test
    public void turnLineUnto() {
        Cubik cubik = new Cubik(3);
        Cubik cubik1 = new Cubik(3);
        cubik.moveLine(3, true);
        cubik.moveLine(3, true);
        cubik.moveLine(3, true);
        cubik1.moveLine(3, true, true);
        assertEquals(cubik.toString(), cubik1.toString());
    }

    @Test
    public  void  turnCubik() {
        Cubik cubik = new Cubik(3);
        cubik.turnCubik();
        assertEquals("           [0, 0, 0]\n" +
                        "           [0, 0, 0]\n" +
                        "           [0, 0, 0]\n\n" +
                        " [5, 5, 5] [1, 1, 1] [2, 2, 2]\n" +
                        " [5, 5, 5] [1, 1, 1] [2, 2, 2]\n" +
                        " [5, 5, 5] [1, 1, 1] [2, 2, 2]\n\n" +
                        "           [4, 4, 4]\n" +
                        "           [4, 4, 4]\n" +
                        "           [4, 4, 4]\n\n" +
                        "           [3, 3, 3]\n" +
                        "           [3, 3, 3]\n" +
                        "           [3, 3, 3]\n\n",
                cubik.toString()
        );
    }

    @Test
    public  void  turnCubikVertical() {
        Cubik cubik = new Cubik(3);
        cubik.turnCubik(true);
        assertEquals("           [2, 2, 2]\n" +
                "           [2, 2, 2]\n" +
                "           [2, 2, 2]\n\n" +
                " [1, 1, 1] [4, 4, 4] [3, 3, 3]\n" +
                " [1, 1, 1] [4, 4, 4] [3, 3, 3]\n" +
                " [1, 1, 1] [4, 4, 4] [3, 3, 3]\n\n" +
                "           [5, 5, 5]\n" +
                "           [5, 5, 5]\n" +
                "           [5, 5, 5]\n\n" +
                "           [0, 0, 0]\n" +
                "           [0, 0, 0]\n" +
                "           [0, 0, 0]\n\n",
                cubik.toString());
    }

    @Test
    public void turnCubikUnto() {
        Cubik cubik = new Cubik(3);
        cubik.turnCubik(true, true);
        cubik.turnCubik(true, true);
        cubik.turnCubik(true, true);
        Cubik cubik1 = new Cubik(3);
        cubik1.turnCubik(true);
        assertEquals(cubik1.toString(), cubik.toString());

    }

    @Test
    public void shufle() {
        Cubik cubik = new Cubik(3, true);
        System.out.println(cubik);
    }

}
