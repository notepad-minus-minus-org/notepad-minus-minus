package com.nmm.ui.menu;

import com.nmm.ui.layout.ViewLayoutController;
import com.nmm.ui.util.IconUtil;

import javax.swing.*;
import java.awt.event.KeyEvent;

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

    private JMenuItem createMenuItem(String text, ImageIcon icon) {
        JMenuItem item = new JMenuItem(text, icon);
        if (icon != null) {
            item.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return item;
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
        menu.setMnemonic(KeyEvent.VK_F);

        menu.add(createMenuItem("New Project", IconUtil.getNewFile()));
        menu.add(createMenuItem("Open...", IconUtil.getOpen()));

        menu.addSeparator();

        menu.add(createMenuItem("Save", IconUtil.getSave()));
        menu.add(createMenuItem("Save As...", IconUtil.getSave()));

        menu.addSeparator();

        menu.add(createMenuItem("Exit", IconUtil.getPlaceholder()));

        return menu;
    }

    private JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);

        JMenuItem undoItem = new JMenuItem("Undo", IconUtil.getPlaceholder());
        undoItem.setToolTipText("Undo (Ctrl+Z)");
        menu.add(undoItem);

        JMenuItem redoItem = new JMenuItem("Redo", IconUtil.getPlaceholder());
        redoItem.setToolTipText("Redo (Ctrl+Y)");
        menu.add(redoItem);

        menu.addSeparator();

        menu.add(createMenuItem("Cut", IconUtil.getPlaceholder()));
        menu.add(createMenuItem("Copy", IconUtil.getPlaceholder()));
        menu.add(createMenuItem("Paste", IconUtil.getPlaceholder()));

        return menu;
    }

    private JMenu createSettingsMenu() {
        JMenu menu = new JMenu("Settings");
        menu.setMnemonic(KeyEvent.VK_S);

        menu.add(createMenuItem("Preferences", IconUtil.getPlaceholder()));
        menu.add(createMenuItem("Keymaps", IconUtil.getPlaceholder()));

        return menu;
    }

    private JMenu createViewMenu() {
        JMenu menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);

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
