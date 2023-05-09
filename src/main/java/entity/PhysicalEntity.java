package main.java.entity;

import javafx.scene.image.Image;
import main.java.Input;
import main.java.Map;

import java.util.Arrays;

public abstract class PhysicalEntity extends Entity{
    protected final double w;
    protected final double h;
    protected boolean on_ground;
    protected short[] touched;
    public PhysicalEntity(double x, double y, double vx, double vy, double w, double h) {
        super(x, y, vx, vy);
        this.w = w;
        this.h = h;
        on_ground=false;
        touched = new short[(int)(Math.ceil(w+1)*Math.ceil(h+10))];
    }

    @Override
    public void tick(Map map) {
        vy-=0.02;
        on_ground=false;
        if(vx<0){
            int check_x = (int)Math.floor(x);
            int end_x = (int)Math.floor(x+vx);
            int start_y = (int)Math.floor(y);
            int end_y = (int)Math.ceil(y+h)-1;
            boolean hit = false;
            while (check_x>end_x && !hit){
                check_x-=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(check_x,i)).solid();
                }
            }
            if(hit){
                vx=0;
                x=check_x+1;
            }
            else{
                x+=vx;
            }
        }
        else {
            int scan_x = (int)Math.ceil(x+w)-1;
            int end_x = (int)Math.floor(x+w+vx);
            int start_y = (int)Math.floor(y);
            int end_y = (int)Math.ceil(y+h)-1;
            boolean hit = false;
            while (scan_x<end_x && !hit){
                scan_x+=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(scan_x,i)).solid();
                }
            }
            if(hit){
                vx=0;
                x=scan_x-w;
            }
            else{
                x+=vx;
            }
        }
        if(vy<0){
            int scan_y = (int)Math.floor(y);
            int end_y = (int)Math.floor(y+vy);
            int start_x = (int)Math.floor(x);
            int end_x = (int)Math.ceil(x+w)-1;
            boolean hit = false;
            while (scan_y>end_y && !hit){
                scan_y-=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(i,scan_y)).solid();
                }
            }
            if(hit){
                on_ground=true;
                vy=0;
                y=scan_y+1;
            }
            else{
                y+=vy;
            }
        }
        else {
            int check_y = (int)Math.ceil(y+h)-1;
            int end_y = (int)Math.floor(y+h+vy);
            int start_x = (int)Math.floor(x);
            int end_x = (int)Math.ceil(x+w)-1;
            boolean hit = false;
            while (check_y<end_y && !hit){
                check_y+=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(i,check_y)).solid();
                }
            }
            if(hit){
                vy=0;
                y=check_y-h;
            }
            else{
                y+=vy;
            }
        }
        vx*=0.7;
        int top_x = (int)Math.floor(x);
        int bottom_x = (int)Math.ceil(x+w)-1;
        int top_y = (int)Math.ceil(y+h)-1;
        int bottom_y = (int)Math.floor(y);
        Arrays.fill(touched, (short) 0);
        int i=0;
        for(int j=bottom_x;j<=top_x;j++) {
            for (int k = bottom_y; k <= top_y; k++) {
                touched[i]=map.getBlock(j,k);
                i++;
            }
        }
    }

    public boolean isOnGround() {
        return on_ground;
    }

    public double getWidth() {
        return w;
    }
    public double getHeight() {
        return h;
    }
    @Override
    public double getImageSizeX(){
        return w;
    }
    @Override
    public double getImageSizeY(){
        return h;
    }
}
