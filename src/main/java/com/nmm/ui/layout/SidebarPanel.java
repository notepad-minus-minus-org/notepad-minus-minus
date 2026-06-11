package com.nmm.ui.layout;

import com.nmm.ui.FileTreePane;

import javax.swing.*;

public class SidebarPanel extends JSplitPane {

	public SidebarPanel() {

		super(
				JSplitPane.VERTICAL_SPLIT,
				new FileTreePane(),
				new TableOfContentsPanel());

		setDividerLocation(400);
		setResizeWeight(0.5);
	}
}
