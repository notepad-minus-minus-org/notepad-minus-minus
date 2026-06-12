package com.nmm.ui.layout;

import com.nmm.ui.MainFrame;
import com.nmm.ui.panes.EditPane;
import com.nmm.ui.panes.RenderPane;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

	private EditPane editPane;
	private RenderPane renderPane;
	private final JSplitPane splitPane;

	public ContentPanel(MainFrame frame) {
		setLayout(new BorderLayout());

		editPane = null;
		renderPane = null;
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		splitPane.setResizeWeight(0.5);

		add(splitPane, BorderLayout.CENTER);
	}

	public void initializePanes(EditPane editPane, RenderPane renderPane) {
		this.editPane = editPane;
		this.renderPane = renderPane;
		splitPane.setLeftComponent(editPane);
		splitPane.setRightComponent(renderPane);
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
