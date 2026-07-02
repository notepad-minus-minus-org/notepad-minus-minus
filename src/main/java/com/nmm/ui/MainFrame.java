package com.nmm.ui;

import com.nmm.ui.layout.ContentPanel;
import com.nmm.ui.layout.SidebarPanel;
import com.nmm.ui.layout.ViewLayoutController;
import com.nmm.ui.menu.MainMenuBar;
import com.nmm.ui.panes.EditPane;
import com.nmm.ui.panes.RenderPane;
import com.nmm.ui.state.ViewState;
import com.nmm.ui.toolbar.FormattingToolBar;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Notepad--");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ViewState state = new ViewState();
        SidebarPanel sidebarPanel = new SidebarPanel();
        ContentPanel contentPanel = new ContentPanel(this);
        ViewLayoutController controller =
                new ViewLayoutController(this, state, sidebarPanel, contentPanel);

        EditPane editPane = new EditPane(this, controller);
        RenderPane renderPane = new RenderPane(this, controller);
        contentPanel.initializePanes(editPane, renderPane);

        MainMenuBar menuBar = new MainMenuBar(controller);
        controller.setMenuBar(menuBar);
        setJMenuBar(menuBar);

        add(new FormattingToolBar(), BorderLayout.NORTH);
        add(controller.getMainSplitPane(), BorderLayout.CENTER);
    }
}
