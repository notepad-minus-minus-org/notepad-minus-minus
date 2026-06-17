package com.nmm.ui.toolbar;

import com.nmm.ui.util.IconUtil;

import javax.swing.*;
import java.awt.*;

public class FormattingToolBar extends JToolBar {

	public FormattingToolBar() {
		setFloatable(false);
		setRollover(true);

		JButton undoButton = new JButton(IconUtil.getUndo());
		undoButton.setToolTipText("Undo (Ctrl+Z)");
		add(undoButton);

		JButton redoButton = new JButton(IconUtil.getRedo());
		redoButton.setToolTipText("Redo (Ctrl+Y)");
		add(redoButton);

		addSeparator();

		JComboBox<String> fontCombo = new JComboBox<>(
				new String[] { "Arial", "Courier New", "Times New Roman", "Verdana" });
		fontCombo.setMaximumSize(new Dimension(120, 26));
		fontCombo.setToolTipText("Font Family");
		add(fontCombo);

		addSeparator();

		JComboBox<String> sizeCombo = new JComboBox<>(
				new String[] { "8", "9", "10", "11", "12", "14", "16", "18", "24", "36" });
		sizeCombo.setSelectedItem("12");
		sizeCombo.setMaximumSize(new Dimension(55, 26));
		sizeCombo.setToolTipText("Font Size");
		add(sizeCombo);

		addSeparator();

		JToggleButton boldButton = new JToggleButton(IconUtil.getBold());
		boldButton.setToolTipText("Bold (Ctrl+B)");
		add(boldButton);

		JToggleButton italicButton = new JToggleButton(IconUtil.getItalic());
		italicButton.setToolTipText("Italic (Ctrl+I)");
		add(italicButton);

		JToggleButton underlineButton = new JToggleButton(IconUtil.getUnderline());
		underlineButton.setToolTipText("Underline (Ctrl+U)");
		add(underlineButton);

		JToggleButton strikeThroughButton = new JToggleButton(IconUtil.getStrikeThrough());
		strikeThroughButton.setToolTipText("Strikethrough");
		add(strikeThroughButton);

		addSeparator();

		ButtonGroup alignGroup = new ButtonGroup();

		JButton leftAlign = createIconButton(IconUtil.getPlaceholder(), "Left Align (Ctrl+L)");
		alignGroup.add(leftAlign);

		JButton centerAlign = createIconButton(IconUtil.getPlaceholder(), "Center Align (Ctrl+E)");
		alignGroup.add(centerAlign);

		JButton rightAlign = createIconButton(IconUtil.getPlaceholder(), "Right Align (Ctrl+R)");
		alignGroup.add(rightAlign);

		JButton justifyAlign = createIconButton(IconUtil.getPlaceholder(), "Justify (Ctrl+J)");
		alignGroup.add(justifyAlign);

		leftAlign.setSelected(true);

		add(leftAlign);
		add(centerAlign);
		add(rightAlign);
		add(justifyAlign);

		addSeparator();

		JButton ulButton = new JButton(IconUtil.getUnorderedList());
		ulButton.setToolTipText("Bullet List");
		add(ulButton);

		JButton olButton = new JButton(IconUtil.getOrderedListItem());
		olButton.setToolTipText("Numbered List");
		add(olButton);

		JButton quoteButton = new JButton(IconUtil.getQuote());
		quoteButton.setToolTipText("Block Quote");
		add(quoteButton);

		JButton codeButton = new JButton(IconUtil.getCodeBlock());
		codeButton.setToolTipText("Code Block");
		add(codeButton);

		JButton hyperlinkButton = new JButton(IconUtil.getHyperlink());
		hyperlinkButton.setToolTipText("Insert Hyperlink");
		add(hyperlinkButton);
	}

	private JButton createIconButton(ImageIcon icon, String tooltip) {
		JButton button = new JButton(icon);
		button.setToolTipText(tooltip);
		button.setFocusPainted(false);
		button.setContentAreaFilled(true);
		return button;
	}
}
