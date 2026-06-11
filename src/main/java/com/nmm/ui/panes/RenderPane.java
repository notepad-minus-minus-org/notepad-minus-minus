package com.nmm.ui.panes;

import com.nmm.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RenderPane extends JPanel {

    public RenderPane(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor")));

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 6));

        JLabel title = new JLabel("Render Preview");

        JButton closeButton = new JButton("×");
        closeButton.setFocusPainted(false);

        header.add(title, BorderLayout.CENTER);
        header.add(closeButton, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        JLabel placeholder = new JLabel("Render Preview Placeholder", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }
}
