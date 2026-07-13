package com.nmm.ui.panes;

import com.nmm.ui.MainFrame;
import com.nmm.ui.layout.ViewLayoutController;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class RenderPane extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel cardContainer;
    private final PdfPreviewPanel pdfPreviewPanel;

    public RenderPane(MainFrame mainFrame, ViewLayoutController controller) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor")));

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 6));

        JLabel title = new JLabel("Render Preview");

        JButton closeButton = new JButton("×");
        closeButton.setFocusPainted(false);
        closeButton.setToolTipText("Close Render Preview");
        closeButton.addActionListener(_ -> controller.setRenderVisible(false));

        header.add(title, BorderLayout.CENTER);
        header.add(closeButton, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardContainer = new JPanel(cardLayout);

        JLabel placeholder = new JLabel("Render Preview Placeholder", SwingConstants.CENTER);
        pdfPreviewPanel = new PdfPreviewPanel();

        cardContainer.add(placeholder, "EMPTY");
        cardContainer.add(pdfPreviewPanel, "PDF");
        add(cardContainer, BorderLayout.CENTER);
    }

    public void showPlaceholder() {
        cardLayout.show(cardContainer, "EMPTY");
    }

    public void showPdf(File pdfFile) {
        pdfPreviewPanel.loadPdf(pdfFile);
        cardLayout.show(cardContainer, "PDF");
    }
}
