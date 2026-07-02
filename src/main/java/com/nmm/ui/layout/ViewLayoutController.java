package com.nmm.ui.layout;

import com.nmm.ui.menu.MainMenuBar;
import com.nmm.ui.state.ViewState;
import com.nmm.ui.MainFrame;

import java.io.File;

import javax.swing.*;

public class ViewLayoutController {

	private final ViewState state;
	private final SidebarPanel sidebarPanel;
	private final ContentPanel contentPanel;
	private final JSplitPane mainSplitPane;

	private final MainFrame mainFrame;
	private final JFileChooser fileChooser = new JFileChooser();

	private MainMenuBar mainMenuBar;

	public ViewLayoutController(MainFrame mainFrame, ViewState state, SidebarPanel sidebarPanel,
			ContentPanel contentPanel) {

		this.mainFrame = mainFrame;
		this.state = state;
		this.sidebarPanel = sidebarPanel;
		this.contentPanel = contentPanel;

		this.mainSplitPane =
				new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanel, contentPanel);

		mainSplitPane.setDividerLocation(state.getSidebarDividerLocation());
	}

	public void openFileAction() {
		int returnValue = fileChooser.showOpenDialog(mainFrame);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Opening file: " + selectedFile.getAbsolutePath());

			// TODO: handle selected file
		}
	}

	public void setMenuBar(MainMenuBar menuBar) {
		this.mainMenuBar = menuBar;
	}

	public JSplitPane getMainSplitPane() {
		return mainSplitPane;
	}

	public ViewState getState() {
		return state;
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
		if (mainMenuBar != null) {
			mainMenuBar.refreshViewMenuStates();
		}
	}

	public void setEditorVisible(boolean visible) {
		state.setEditorVisible(visible);
		updateContentLayout();
		if (mainMenuBar != null) {
			mainMenuBar.refreshViewMenuStates();
		}
	}

	public void setRenderVisible(boolean visible) {
		state.setRenderVisible(visible);
		updateContentLayout();
		if (mainMenuBar != null) {
			mainMenuBar.refreshViewMenuStates();
		}
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
