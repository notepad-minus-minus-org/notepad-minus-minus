package com.nmm.ui.menu;

import com.nmm.ui.layout.ViewLayoutController;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar(ViewLayoutController controller) {
        add(createFileMenu());
        add(createEditMenu());
        add(createViewMenu(controller));
        add(createSettingsMenu());
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu("File");

        menu.add(new JMenuItem("New Project"));
        menu.add(new JMenuItem("Open..."));

        menu.addSeparator();

        menu.add(new JMenuItem("Save"));
        menu.add(new JMenuItem("Save As..."));

        menu.addSeparator();

        menu.add(new JMenuItem("Exit"));

        return menu;
    }

    private JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");

        menu.add(new JMenuItem("Undo"));
        menu.add(new JMenuItem("Redo"));

        menu.addSeparator();

        menu.add(new JMenuItem("Cut"));
        menu.add(new JMenuItem("Copy"));
        menu.add(new JMenuItem("Paste"));

        return menu;
    }

    private JMenu createSettingsMenu() {
        JMenu menu = new JMenu("Settings");

        menu.add(new JMenuItem("Preferences"));
        menu.add(new JMenuItem("Keymaps"));

        return menu;
    }

    private JMenu createViewMenu(ViewLayoutController controller) {
        JMenu menu = new JMenu("View");

        JCheckBoxMenuItem sidebar = new JCheckBoxMenuItem("Show Sidebar", true);

        JCheckBoxMenuItem editor = new JCheckBoxMenuItem("Show Editor", true);

        JCheckBoxMenuItem render = new JCheckBoxMenuItem("Show Render Preview", true);

        sidebar.addItemListener(e -> controller.setSidebarVisible(sidebar.isSelected()));

        editor.addItemListener(e -> controller.setEditorVisible(editor.isSelected()));

        render.addItemListener(e -> controller.setRenderVisible(render.isSelected()));

        menu.add(sidebar);
        menu.add(editor);
        menu.add(render);

        return menu;
    }
}
