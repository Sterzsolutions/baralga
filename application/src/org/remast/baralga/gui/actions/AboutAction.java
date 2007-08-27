package org.remast.baralga.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.remast.baralga.Messages;
import org.remast.baralga.gui.dialogs.AboutDialog;

@SuppressWarnings("serial") //$NON-NLS-1$
public final class AboutAction extends AbstractAction {

    public AboutAction() {
        putValue(NAME, Messages.getString("AboutAction.Name")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/resource/icons/gtk-about.png"))); //$NON-NLS-1$
        putValue(SHORT_DESCRIPTION, Messages.getString("AboutAction.ShortDescription")); //$NON-NLS-1$
    }
    
    public void actionPerformed(ActionEvent event) {
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.setVisible(true);
    }

}