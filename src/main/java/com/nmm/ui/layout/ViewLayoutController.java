package com.nmm.ui.layout;

import com.nmm.ui.state.ViewState;

import javax.swing.*;

public class ViewLayoutController {

	private final ViewState state;
	private final SidebarPanel sidebarPanel;
	private final ContentPanel contentPanel;
	private final JSplitPane mainSplitPane;

	public ViewLayoutController(ViewState state, SidebarPanel sidebarPanel,
			ContentPanel contentPanel) {

		this.state = state;
		this.sidebarPanel = sidebarPanel;
		this.contentPanel = contentPanel;

		this.mainSplitPane =
				new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanel, contentPanel);

		mainSplitPane.setDividerLocation(state.getSidebarDividerLocation());
	}

	public JSplitPane getMainSplitPane() {
		return mainSplitPane;
	}

	public void setSidebarVisible(boolean visible) {
		state.setSidebarVisible(visible);

		if (visible) {
			mainSplitPane.setLeftComponent(sidebarPanel);
			mainSplitPane.setDividerLocation(state.getSidebarDividerLocation());
			mainSplitPane.setDividerSize(UIManager.getInt("SplitPane.dividerSize"));
		} else {
			state.setSidebarDividerLocation(mainSplitPane.getDividerLocation());

			mainSplitPane.setLeftComponent(null);
			mainSplitPane.setDividerSize(0);
		}

		mainSplitPane.revalidate();
		mainSplitPane.repaint();
	}

	public void setEditorVisible(boolean visible) {
		state.setEditorVisible(visible);
		updateContentLayout();
	}

	public void setRenderVisible(boolean visible) {
		state.setRenderVisible(visible);
		updateContentLayout();
	}

	private void updateContentLayout() {
		JSplitPane splitPane = contentPanel.getSplitPane();

		if (splitPane.getParent() != null) {
			state.setContentDividerLocation(splitPane.getDividerLocation());
		}

		contentPanel.updateLayout(state.isEditorVisible(), state.isRenderVisible(),
				state.getContentDividerLocation());
	}
}
