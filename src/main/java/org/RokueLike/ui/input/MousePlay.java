package org.RokueLike.ui.input;

import org.RokueLike.domain.GameManager;
import org.RokueLike.ui.screen.PlayModeScreen;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Classes like MouseBuild, MousePlay, and Keyboard bridge input events from the UI to the GameManager

public class MousePlay implements MouseListener {

    // Same as PlayModeRenderer
    private static final int TILE_SIZE = 36;  // Tile size
    private static final int GRID_OFFSET_X = 70;  // Grid X başlangıcı (100'den 70'e kaydırıldı)
    private static final int GRID_OFFSET_Y = 50;

    private final Rectangle pauseButtonBounds;
    private final Rectangle exitButtonBounds;
    private final PlayModeScreen playModeScreen;

    public MousePlay(Rectangle pauseButtonBounds, Rectangle exitButtonBounds, PlayModeScreen playModeScreen) {
        this.pauseButtonBounds = pauseButtonBounds;
        this.exitButtonBounds = exitButtonBounds;
        this.playModeScreen = playModeScreen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();

        if (pauseButtonBounds.contains(clickPoint)) {
            GameManager.togglePauseResume();
            return;
        }

        if (exitButtonBounds.contains(clickPoint)) {
            playModeScreen.returnToLaunchScreen();
            return;
        }

        int gridX = getGridCoordinate(e.getX(), GRID_OFFSET_X);
        int gridY = getGridCoordinate(e.getY(), GRID_OFFSET_Y);

        if (e.getButton() == MouseEvent.BUTTON1) {
            GameManager.handleLeftClick(gridX, gridY);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            GameManager.handleRightClick(gridX, gridY);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Converts screen coordinates to grid coordinates.
     * This assumes that each grid cell has a fixed pixel size.
     * Adjust `CELL_SIZE` based on your game's implementation.
     */
    private int getGridCoordinate(int pixelCoordinate, int offset) {
        return (pixelCoordinate - offset) / TILE_SIZE;
    }

}
