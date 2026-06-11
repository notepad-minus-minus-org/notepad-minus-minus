package com.nmm;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.nmm.ui.MainFrame;

import javax.swing.*;

public class Main {
    static void main() {
        FlatIntelliJLaf.setup();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
