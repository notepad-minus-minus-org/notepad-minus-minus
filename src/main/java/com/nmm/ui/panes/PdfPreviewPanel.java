package com.nmm.ui.panes;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PdfPreviewPanel extends JPanel {

    private final JPanel pagesContainer;

    public PdfPreviewPanel() {
        setLayout(new BorderLayout());

        pagesContainer = new JPanel();
        pagesContainer.setLayout(new BoxLayout(pagesContainer, BoxLayout.Y_AXIS));
        pagesContainer.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane scrollPane = new JScrollPane(pagesContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadPdf(File pdfFile) {
        pagesContainer.removeAll();

        if (pdfFile == null || !pdfFile.isFile()) {
            pagesContainer.add(createMessageLabel("Nie mozna wczytac pliku PDF."));
            refresh();
            return;
        }

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFRenderer renderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                BufferedImage image = renderer.renderImageWithDPI(pageIndex, 144, ImageType.RGB);
                JLabel pageNumber = new JLabel("Strona " + (pageIndex + 1));
                pageNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
                pageNumber.setBorder(new EmptyBorder(0, 0, 6, 0));

                JLabel pageLabel = new JLabel(new ImageIcon(image));
                pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                pageLabel.setBorder(new EmptyBorder(0, 0, 16, 0));

                pagesContainer.add(pageNumber);
                pagesContainer.add(pageLabel);
            }
        } catch (IOException e) {
            pagesContainer.add(createMessageLabel("Blad podczas wczytywania PDF."));
        }

        refresh();
    }

    private JLabel createMessageLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(24, 12, 24, 12));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void refresh() {
        pagesContainer.revalidate();
        pagesContainer.repaint();
    }
}
