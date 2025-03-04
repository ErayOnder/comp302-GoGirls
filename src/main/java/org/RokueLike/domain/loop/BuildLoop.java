package org.RokueLike.domain.loop;

import org.RokueLike.domain.manager.BuildManager;
import org.RokueLike.ui.render.BuildModeRenderer;
import org.RokueLike.ui.input.MouseBuild;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildLoop implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MouseBuild.isMouseClicked()) {
            // Get the last click coordinates
            int[] coords = MouseBuild.getLastClickCoordinates();
            int selectedObject = BuildModeRenderer.getSelectedObject();
            int x = coords[0];
            int y = coords[1];
            String hallName = MouseBuild.getLastClickedHall();

            // Place the object in the BuildManager
            BuildManager.placeObjectManually(hallName, x, y,selectedObject);

            // Reset the mouse click state to prevent repeated processing
            MouseBuild.resetMouseClick();
        }
    }

}