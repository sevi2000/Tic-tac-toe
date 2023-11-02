package com.mygdx.game;

public enum Type {
    X,O,BLANK;

    public Type not() {
        return switch (this.ordinal()){
            case 0 -> Type.O;
            case 1 -> Type.X;
            case 2 -> Type.X;
            default -> throw new IllegalStateException("Unexpected value: " + this.ordinal());
        };
    }
}
