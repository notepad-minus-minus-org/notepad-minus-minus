package com.nmm.ui.toolbar;

import javax.swing.*;
import java.awt.*;

public class FormattingToolBar extends JToolBar {

	public FormattingToolBar() {
		setFloatable(false);

		add(new JButton("↩"));
		add(new JButton("↪"));

		addSeparator();

		JComboBox<String> fontCombo = new JComboBox<>(new String[] {"Arial", "Courier New"});

		fontCombo.setMaximumSize(
				new Dimension(120, 26));

		add(fontCombo);

		addSeparator();

		JComboBox<String> sizeCombo =
				new JComboBox<>(new String[] {"10", "11", "12", "14", "16", "18", "24"});

		sizeCombo.setSelectedItem("12");
		sizeCombo.setMaximumSize(
				new Dimension(55, 26));

		add(sizeCombo);

		addSeparator();

		add(new JToggleButton("B"));
		add(new JToggleButton("I"));
		add(new JToggleButton("U"));

		addSeparator();

		ButtonGroup group = new ButtonGroup();

		JToggleButton left = new JToggleButton("Left");
		JToggleButton center = new JToggleButton("Center");
		JToggleButton right = new JToggleButton("Right");
		JToggleButton justify = new JToggleButton("Justify");

		group.add(left);
		group.add(center);
		group.add(right);
		group.add(justify);

		left.setSelected(true);

		add(left);
		add(center);
		add(right);
		add(justify);
	}
}
