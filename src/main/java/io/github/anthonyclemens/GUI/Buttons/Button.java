package io.github.anthonyclemens.GUI.Buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import io.github.anthonyclemens.GUI.GUIElement;
import io.github.anthonyclemens.GUI.OnClickListener;

public class Button extends GUIElement {
    protected Color c;
    protected boolean clicked;
    protected OnClickListener onClickListener;

    public Button(float x, float y, float w, float h){
        super(x,y,w,h);
        this.c = null;
        this.onClickListener = null;
    }

    @Override
    public void render(Graphics g){
        if(this.c!=null){
            g.setColor(this.c);
            g.fill(this.getRect());
            g.setColor(Color.black);
            g.draw(this.r);
        }else{
            g.draw(this.getRect());
        }
    }

    @Override
    public void update(Input input){
        if(this.clicked){
            this.clicked=!this.clicked;
        }
        this.clicked = this.r.contains(input.getMouseX(), input.getMouseY()) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
    }

    //Getters
    public boolean isClicked(){
        return this.clicked;
    }

    public Color getColor(){
        return this.c;
    }

    //Setters
    public void setColor(Color c){
        this.c=c;
    }

    public void onClick(OnClickListener listener) {
        this.onClickListener = listener;
    }

    public OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

}
