package com.nmm.ui.panes;

import com.nmm.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EditPane extends JPanel {

    public EditPane(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor")));

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 6));

        JLabel title = new JLabel("Editor");

        JButton closeButton = new JButton("×");
        closeButton.setFocusPainted(false);

        header.add(title, BorderLayout.CENTER);
        header.add(closeButton, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        JLabel placeholder = new JLabel("Editor Content Placeholder test", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }
}
