package model;

import java.util.Arrays;

/**
 * Created by iocz on 18/11/15.
 */
public class Field {
    private static final byte FIELD_SIZE = 4;
    private byte[][] elementsMass;

    public Field(){
        elementsMass = new byte[FIELD_SIZE][FIELD_SIZE];
    }

    public Field(byte fieldSize){
        elementsMass = new byte[fieldSize][fieldSize];
    }

    public Field(byte[][] elementsMass){
        this.elementsMass = elementsMass;
    }

    public static byte getFIELD_SIZE() {
        return FIELD_SIZE;
    }

    public byte[][] getElementsMass() {
        return elementsMass;
    }

    public void setElementsMass(byte[][] elementsMass) {
        this.elementsMass = elementsMass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        return Arrays.deepEquals(elementsMass, field.elementsMass);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(elementsMass);
    }

    private String getStringArray(byte[][] array){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                 result.append("array[").append(i).append("][").
                         append(j).append("]= ").append(array[i][j]).append("  ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "Field{" +
                "elementsMass=\n" + getStringArray(elementsMass) +
                '}';
    }
}
