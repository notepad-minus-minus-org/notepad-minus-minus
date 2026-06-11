package com.nmm.ui;

import com.nmm.ui.panes.EditPane;
import com.nmm.ui.panes.RenderPane;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final JPanel contentContainer;
    private final JSplitPane mainSplitPane;
    private final JSplitPane leftSidebarSplit;
    private JSplitPane contentSplitPane;
    private EditPane editPane;
    private RenderPane renderPane;
    private int lastSidebarDividerLocation = 280;
    private int lastContentDividerLocation = 350;

    public MainFrame() {
        setTitle("Notepad--");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(createProfessionalMenuBar());

        JPanel topToolbarContainer = new JPanel(new GridLayout(1, 1));
        topToolbarContainer.add(createFormattingToolBar());
        add(topToolbarContainer, BorderLayout.NORTH);

        JPanel fileTreePanel = new FileTreePane();
        JPanel tocPanel = createTableOfContentsPanel();

        leftSidebarSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileTreePanel, tocPanel);
        leftSidebarSplit.setDividerLocation(400);
        leftSidebarSplit.setResizeWeight(0.5);

        contentContainer = new JPanel(new BorderLayout());
        initializeContentPanes();

        mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSidebarSplit, contentContainer);
        mainSplitPane.setDividerLocation(lastSidebarDividerLocation);

        add(mainSplitPane, BorderLayout.CENTER);
    }

    private JMenuBar createProfessionalMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("New Project"));
        fileMenu.add(new JMenuItem("Open..."));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.add(new JMenuItem("Save As..."));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Exit"));

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Undo"));
        editMenu.add(new JMenuItem("Redo"));
        editMenu.addSeparator();
        editMenu.add(new JMenuItem("Cut"));
        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));

        JMenu viewMenu = getJMenu();

        JMenu settingsMenu = new JMenu("Settings");
        settingsMenu.add(new JMenuItem("Preferences"));
        settingsMenu.add(new JMenuItem("Keymaps"));

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(settingsMenu);

        return menuBar;
    }

    private JMenu getJMenu() {
        JMenu viewMenu = new JMenu("View");
        JCheckBoxMenuItem showSidebarItem = new JCheckBoxMenuItem("Show Sidebar", true);
        JCheckBoxMenuItem showEditorItem = new JCheckBoxMenuItem("Show Editor", true);
        JCheckBoxMenuItem showRenderItem = new JCheckBoxMenuItem("Show Render Preview", true);

        showSidebarItem.addItemListener(_ -> {
            boolean visible = showSidebarItem.isSelected();
            toggleSidebar(visible);
        });

        showEditorItem.addItemListener(_ -> toggleEditor());

        showRenderItem.addItemListener(_ -> toggleRenderPreview());

        viewMenu.add(showSidebarItem);
        viewMenu.add(showEditorItem);
        viewMenu.add(showRenderItem);
        return viewMenu;
    }

    private JToolBar createFormattingToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton undoButton = new JButton("↩");
        JButton redoButton = new JButton("↪");
        toolBar.add(undoButton);
        toolBar.add(redoButton);

        toolBar.addSeparator();

        String[] fonts = {"Arial", "Courier New"};
        JComboBox<String> fontCombo = new JComboBox<>(fonts);
        fontCombo.setMaximumSize(new Dimension(120, 26));
        toolBar.add(fontCombo);

        toolBar.addSeparator();

        String[] sizes = {"10", "11", "12", "14", "16", "18", "24"};
        JComboBox<String> sizeCombo = new JComboBox<>(sizes);
        sizeCombo.setSelectedIndex(2);
        sizeCombo.setMaximumSize(new Dimension(55, 26));
        toolBar.add(sizeCombo);

        toolBar.addSeparator();

        JToggleButton boldBtn = new JToggleButton("B");
        JToggleButton italicBtn = new JToggleButton("I");
        JToggleButton underlineBtn = new JToggleButton("U");

        toolBar.add(boldBtn);
        toolBar.add(italicBtn);
        toolBar.add(underlineBtn);

        toolBar.addSeparator();

        ButtonGroup alignmentGroup = new ButtonGroup();
        JToggleButton alignLeft = new JToggleButton("Left");
        JToggleButton alignCenter = new JToggleButton("Center");
        JToggleButton alignRight = new JToggleButton("Right");
        JToggleButton alignJustify = new JToggleButton("Justify");

        alignmentGroup.add(alignLeft);
        alignmentGroup.add(alignCenter);
        alignmentGroup.add(alignRight);
        alignmentGroup.add(alignJustify);
        alignLeft.setSelected(true);

        toolBar.add(alignLeft);
        toolBar.add(alignCenter);
        toolBar.add(alignRight);
        toolBar.add(alignJustify);

        return toolBar;
    }

    private void initializeContentPanes() {
        editPane = new EditPane(this);
        renderPane = new RenderPane(this);

        contentSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editPane, renderPane);
        contentSplitPane.setDividerLocation(lastContentDividerLocation);
        contentSplitPane.setResizeWeight(0.5);

        contentContainer.add(contentSplitPane, BorderLayout.CENTER);
    }

    private void toggleSidebar(boolean visible) {
        if (visible) {
            mainSplitPane.setLeftComponent(leftSidebarSplit);
            mainSplitPane.setDividerLocation(lastSidebarDividerLocation);
            mainSplitPane.setDividerSize(UIManager.getInt("SplitPane.dividerSize"));
        } else {
            lastSidebarDividerLocation = mainSplitPane.getDividerLocation();
            mainSplitPane.setLeftComponent(null);
            mainSplitPane.setDividerSize(0);
        }
        mainSplitPane.revalidate();
        mainSplitPane.repaint();
    }

    private void toggleEditor() {
        updateContentLayout();
    }

    private void toggleRenderPreview() {
        updateContentLayout();
    }

    private void updateContentLayout() {
        JMenuBar menuBar = getJMenuBar();
        if (menuBar == null) return;
        JMenu viewMenu = menuBar.getMenu(2);
        if (viewMenu == null) return;

        boolean editorVisible = viewMenu.getItem(1).isSelected();
        boolean renderVisible = viewMenu.getItem(2).isSelected();

        contentContainer.removeAll();

        if (editorVisible && renderVisible) {
            contentSplitPane.setLeftComponent(editPane);
            contentSplitPane.setRightComponent(renderPane);
            contentSplitPane.setDividerLocation(lastContentDividerLocation);
            contentSplitPane.setDividerSize(UIManager.getInt("SplitPane.dividerSize"));
            contentContainer.add(contentSplitPane, BorderLayout.CENTER);
        } else if (editorVisible) {
            if (contentSplitPane.getParent() != null) {
                lastContentDividerLocation = contentSplitPane.getDividerLocation();
            }
            contentContainer.add(editPane, BorderLayout.CENTER);
        } else if (renderVisible) {
            if (contentSplitPane.getParent() != null) {
                lastContentDividerLocation = contentSplitPane.getDividerLocation();
            }
            contentContainer.add(renderPane, BorderLayout.CENTER);
        }

        contentContainer.revalidate();
        contentContainer.repaint();
    }

    public void closePane(JPanel pane) {
        JMenuBar menuBar = getJMenuBar();
        if (menuBar == null) return;
        JMenu viewMenu = menuBar.getMenu(2);
        if (viewMenu == null) return;

        if (pane == editPane) {
            JCheckBoxMenuItem editorItem = (JCheckBoxMenuItem) viewMenu.getItem(1);
            editorItem.setSelected(false);
        } else if (pane == renderPane) {
            JCheckBoxMenuItem renderItem = (JCheckBoxMenuItem) viewMenu.getItem(2);
            renderItem.setSelected(false);
        }
    }

    private JPanel createTableOfContentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Table of Contents"));

        JLabel placeholderText = new JLabel("TOC Placeholder", SwingConstants.CENTER);
        panel.add(placeholderText, BorderLayout.CENTER);
        return panel;
    }
}
