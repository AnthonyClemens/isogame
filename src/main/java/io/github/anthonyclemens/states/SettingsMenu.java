package io.github.anthonyclemens.states;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import io.github.anthonyclemens.GUI.Banner;
import io.github.anthonyclemens.GUI.Buttons.ImageTextButton;
import io.github.anthonyclemens.Math.TwoDimensionMath;
import io.github.anthonyclemens.Settings;
import io.github.anthonyclemens.Utils;

public class SettingsMenu extends BasicGameState{
    //Variables
    private Settings settings;
    private Input input;
    private Image backgroundImage;
    private Banner titleBanner;
    private final List<ImageTextButton> menuButtons = new ArrayList<>();

    //Constants
    private static final String TITLE_STRING = "Options";
    private static final String MAIN_FONT = "fonts/MedievalTimes.ttf";

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        // Set background image
        backgroundImage = new Image("textures/Background.png");
        // Create title banner
        Image bannerImage = new Image("textures/GUI/TextField/UI_Paper_Banner_01_Downward.png");
        bannerImage.setFilter(Image.FILTER_NEAREST);
        titleBanner = new Banner(bannerImage, TITLE_STRING, Utils.getFont(MAIN_FONT, 48f), TwoDimensionMath.getMiddleX(640, container.getWidth()), 10, 640, 251);
        titleBanner.changeYOffset(20f);
        // Load button images
        Image buttonImage = new Image("textures/GUI/TextField/UI_Paper_Textfield_01.png");
        buttonImage.setFilter(Image.FILTER_NEAREST);
        // Create menu buttons
        ImageTextButton videoSettings = new ImageTextButton(buttonImage, "Video Settings", Utils.getFont(MAIN_FONT, 32f), TwoDimensionMath.getMiddleX(342, container.getWidth()), 300, 342, 114);
        ImageTextButton soundSettings = new ImageTextButton(buttonImage, "Sound Settings", Utils.getFont(MAIN_FONT, 32f), TwoDimensionMath.getMiddleX(342, container.getWidth()), 450, 342, 114);
        ImageTextButton controlSettings = new ImageTextButton(buttonImage, "Control Settings", Utils.getFont(MAIN_FONT, 32f), TwoDimensionMath.getMiddleX(342, container.getWidth()), 600, 342, 114);
        ImageTextButton backButton = new ImageTextButton(buttonImage, "Back", Utils.getFont(MAIN_FONT, 40f), 10, 10, 240, 80);
        menuButtons.clear();
        menuButtons.addAll(List.of(videoSettings,soundSettings,controlSettings,backButton));
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        input = container.getInput();
        settings = Settings.getInstance();

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        drawBackground(container);
        titleBanner.render(g);
        for(ImageTextButton itb : menuButtons){
            itb.render(g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        for(ImageTextButton itb : menuButtons){
            itb.update(input); // Sets the isClicked bool
            if(itb.isClicked()){
                switch(itb.getText()){ // Figure out what button was pressed
                    case "Video Settings"-> game.enterState(2);
                    case "Sound Settings"-> game.enterState(3);
                    case "Control Settings"-> game.enterState(4);
                    case "Back"->game.enterState(0);
                }
            }
        }
    }

    private void drawBackground(GameContainer container) {
        // Get screen dimensions
        int screenWidth = container.getWidth();
        int screenHeight = container.getHeight();

        // Get image dimensions
        float imageWidth = backgroundImage.getWidth();
        float imageHeight = backgroundImage.getHeight();

        // Calculate the scaling factor and center position in one step
        float scaleFactor = Math.max(screenWidth / imageWidth, screenHeight / imageHeight);
        float scaledWidth = imageWidth * scaleFactor;
        float scaledHeight = imageHeight * scaleFactor;
        float x = (screenWidth - scaledWidth) / 2;
        float y = (screenHeight - scaledHeight) / 2;

        // Render the scaled image
        backgroundImage.draw(x, y, scaledWidth, scaledHeight);
    }

}
