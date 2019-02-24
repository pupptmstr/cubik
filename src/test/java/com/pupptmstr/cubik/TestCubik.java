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

    //TODO() написать на все таски тесты

}
