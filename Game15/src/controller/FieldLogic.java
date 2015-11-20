package controller;

import model.Field;

import java.util.Random;

/**
 * Created by iocz on 18/11/15.
 */
public class FieldLogic {
    private static final byte FIELD_SIZE = 4;
    private byte[] gameNumbersMass;
    private byte[][] elementsMass;

    public FieldLogic(){
        gameNumbersMass = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        elementsMass = new byte[FIELD_SIZE][FIELD_SIZE];
    }
    // Implementing Fisher–Yates shuffle
    private static void shuffleArray(byte[] mass) {
        Random random = new Random();
        for (int i = mass.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Simple swap
            byte a = mass[index];
            mass[index] = mass[i];
            mass[i] = a;
        }
    }

    private void fillField(){
        shuffleArray(gameNumbersMass);
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                elementsMass[i][j] = gameNumbersMass[j + FIELD_SIZE * i];
    }

    public Field getField(){
        fillField();
        Field resultField = new Field(FIELD_SIZE);
        resultField.setElementsMass(elementsMass);
        return resultField;
    }

    public static byte getFieldSize() {
        return FIELD_SIZE;
    }
}
