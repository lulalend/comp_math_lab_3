package ru.itmo.math.handlers;

import ij.IJ;
import ij.ImagePlus;

public class Painter {
    private final ImagePlus imagePlus;

    public Painter(int number) {
        String path = "src/main/resources/graphs/equation" + number + ".jpg";
        this.imagePlus = IJ.openImage(path);
    }

    public void show() {
        imagePlus.show();
    }

    public void close() {
        imagePlus.close();
    }
}