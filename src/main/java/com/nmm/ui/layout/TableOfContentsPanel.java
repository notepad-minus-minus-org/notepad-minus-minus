package com.nmm.ui.layout;

import java.awt.*;

import javax.swing.*;

public class TableOfContentsPanel extends JPanel {

    public TableOfContentsPanel() {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder("Table of Contents"));

        add(new JLabel("TOC Placeholder", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
