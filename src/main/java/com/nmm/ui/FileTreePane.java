package com.nmm.ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;

public class FileTreePane extends JPanel {

    public FileTreePane() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("File Explorer"));

        File rootDir = new File(System.getProperty("user.dir"));
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootDir.getName());
        createFileTree(rootNode, rootDir);

        JTree fileTree = new JTree(new DefaultTreeModel(rootNode));

        JScrollPane scrollPane = new JScrollPane(fileTree);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createFileTree(DefaultMutableTreeNode node, File file) {
        File[] files = file.listFiles();
        if (files == null) return;

        for (File f : files) {
            if (f.getName().startsWith(".")) continue;

            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(f.getName());
            node.add(childNode);

            if (f.isDirectory()) {
                createFileTree(childNode, f);
            }
        }
    }
}
