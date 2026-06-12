package com.nmm.ui.menu;

import com.nmm.ui.layout.ViewLayoutController;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    private final ViewLayoutController controller;
    private JCheckBoxMenuItem sidebarItem;
    private JCheckBoxMenuItem editorItem;
    private JCheckBoxMenuItem renderItem;

    public MainMenuBar(ViewLayoutController controller) {
        this.controller = controller;
        add(createFileMenu());
        add(createEditMenu());
        add(createViewMenu());
        add(createSettingsMenu());
    }

    public void refreshViewMenuStates() {
        if (sidebarItem != null && editorItem != null && renderItem != null) {
            sidebarItem.setState(controller.getState().isSidebarVisible());
            editorItem.setState(controller.getState().isEditorVisible());
            renderItem.setState(controller.getState().isRenderVisible());
        }
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

    private JMenu createViewMenu() {
        JMenu menu = new JMenu("View");

        sidebarItem = new JCheckBoxMenuItem("Show Sidebar", true);
        editorItem = new JCheckBoxMenuItem("Show Editor", true);
        renderItem = new JCheckBoxMenuItem("Show Render Preview", true);

        sidebarItem.addItemListener(e -> controller.setSidebarVisible(sidebarItem.isSelected()));
        editorItem.addItemListener(e -> controller.setEditorVisible(editorItem.isSelected()));
        renderItem.addItemListener(e -> controller.setRenderVisible(renderItem.isSelected()));

        menu.add(sidebarItem);
        menu.add(editorItem);
        menu.add(renderItem);

        return menu;
    }
}
