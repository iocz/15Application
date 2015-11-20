package controller;

import model.Field;

/**
 * Created by iocz on 18/11/15.
 */
public class GameLogic {
    //TODO rename.
    private static final byte NEIGHBORS_ARRAY_LENGTH = 4;
    private static final byte XY_COORDINATES_ARRAY_LENGTH = 2;
    private byte maxFieldSizeValue;
    private byte[] neighborElementsMatrix;

    public GameLogic(byte maxFieldSizeValue){
        this.maxFieldSizeValue = maxFieldSizeValue;
        neighborElementsMatrix = new byte[NEIGHBORS_ARRAY_LENGTH];
    }

    private void searchNeighborElements(byte[][] elementMatrix, byte coordinatesX,
                                        byte coordinatesY){
        searchNeighbor(elementMatrix, coordinatesX, coordinatesY);
    }

    private void searchNeighbor(byte[][] elementMatrix, byte coordinatesX,
                                byte coordinatesY){
        for (int i = 0; i < NEIGHBORS_ARRAY_LENGTH; i++) {
            neighborElementsMatrix[i] = fillNeighborElements(i, elementMatrix, coordinatesX, coordinatesY);
        }
    }

    private byte fillNeighborElements(int elementId, byte[][] elementMatrix,
                                      byte coordinatesX, byte coordinatesY){
        byte result = -1;
        switch(elementId){
            case 0:
                if (coordinatesY != 0)
                    result = elementMatrix[coordinatesY - 1][coordinatesX];
                break;
            case 1:
                if(coordinatesX != 0)
                    result = elementMatrix[coordinatesY][coordinatesX - 1];
                break;
            case 2:
                if(coordinatesX != NEIGHBORS_ARRAY_LENGTH - 1)
                    result = elementMatrix[coordinatesY][coordinatesX + 1];
                break;
            case 3:
                if(coordinatesY != NEIGHBORS_ARRAY_LENGTH - 1)
                    result = elementMatrix[coordinatesY + 1][coordinatesX];
                break;
            default:
                break;
        }
        return result;
    }

    private boolean checkOutputBorder(byte coordinates){
        if (coordinates == 0 || coordinates == maxFieldSizeValue) return true;
        return false;
    }

    private byte checkNeighborZero(){
        byte result = -1;
        for (byte i = 0; i < neighborElementsMatrix.length; i++)
            if (neighborElementsMatrix[i] == 0) result = i;
        return result;
    }

    private byte[] getZeroCoordinatesXY(byte[][] elementsMatrix){
        byte[] result = new byte[XY_COORDINATES_ARRAY_LENGTH];
        boolean findFlag = false;
        for (byte i = 0; i < maxFieldSizeValue; i++) {
            for (byte j = 0; j < maxFieldSizeValue; j++) {
                if (elementsMatrix[i][j] == 0) {
                    result[0] = i;
                    result[1] = j;
                    findFlag = true;
                    break;
                }
            }
            if(findFlag) break;
        }
        return result;
    }

    private void moveElement(byte[][] elementsMatrix, byte coordinateX, byte coordinateY){
        searchNeighborElements(elementsMatrix, coordinateX, coordinateY);
        byte zeroIndex = checkNeighborZero();
        byte[] zeroCoordinatesXY = getZeroCoordinatesXY(elementsMatrix);
        if (zeroIndex != -1){
            byte zero = elementsMatrix[zeroCoordinatesXY[0]][zeroCoordinatesXY[1]];
            elementsMatrix[zeroCoordinatesXY[0]][zeroCoordinatesXY[1]] = elementsMatrix[coordinateX][coordinateY];
            elementsMatrix[coordinateX][coordinateY] = zero;
        }
    }

    public byte[] getNeighborElementsMatrix() {
        return neighborElementsMatrix;
    }
}
