package main.java;

import javafx.scene.image.Image;

public class Map {
    private int[][] blocks;
    private final Image[] images;
    private final int w,h;
    public Map(){
        w=200;
        h=80;
        blocks=new int[h][w];
        images = new Image[]{new Image("file:/home/martin/Documents/JAVA/Escape/src/main/resources/blocks/crate.png")};
        for (int x=0;x<w;x++){
            for (int y=0;y<h;y++){
                double p = (double) (h - y) /h;
                if(Math.random()< p*p*p*p*p || y==0){
                    blocks[y][x]=1;
                }
            }
        }
    }

    public int getBlock(int x,int y) {
        if(x<0 || y<0 || x>=w || y>=h){
            return 0;
        }
        return blocks[y][x];
    }

    public void setBlock(int x,int y,int val) {
        this.blocks[y][x] = val;
    }

    public Image getImage(int i) {
        return images[i];
    }

    public void setImage(int i,Image image) {
        this.images[i] = image;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }
}
