package com.nmm.ui.layout;

import com.nmm.ui.MainFrame;
import com.nmm.ui.panes.EditPane;
import com.nmm.ui.panes.RenderPane;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

	private final EditPane editPane;
	private final RenderPane renderPane;
	private final JSplitPane splitPane;

	public ContentPanel(MainFrame frame) {
		setLayout(new BorderLayout());

		editPane = new EditPane(frame);
		renderPane = new RenderPane(frame);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editPane, renderPane);

		splitPane.setResizeWeight(0.5);

		add(splitPane, BorderLayout.CENTER);
	}

	public void updateLayout(boolean editorVisible, boolean renderVisible, int dividerLocation) {
		removeAll();

		if (editorVisible && renderVisible) {
			splitPane.setLeftComponent(editPane);
			splitPane.setRightComponent(renderPane);
			splitPane.setDividerLocation(dividerLocation);

			add(splitPane, BorderLayout.CENTER);
		} else if (editorVisible) {
			add(editPane, BorderLayout.CENTER);
		} else if (renderVisible) {
			add(renderPane, BorderLayout.CENTER);
		}

		revalidate();
		repaint();
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public EditPane getEditPane() {
		return editPane;
	}

	public RenderPane getRenderPane() {
		return renderPane;
	}
}
