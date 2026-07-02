package com.nmm.ui.panes;

import com.nmm.ui.MainFrame;
import com.nmm.ui.layout.ViewLayoutController;

import javax.swing.*;
import java.awt.*;

public class EditPane extends JPanel {

	public enum EditPaneState {
		EMPTY, EDITING
	}

	private final CardLayout cardLayout;
	private JTextArea textEditorArea;
	private JPanel cardContainer;

	public EditPane(MainFrame mainFrame, ViewLayoutController controller) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor")));

		JPanel header = new JPanel(new BorderLayout());
		header.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 6));

		JLabel title = new JLabel("Editor");

		JButton closeButton = new JButton("×");
		closeButton.setFocusPainted(false);
		closeButton.setToolTipText("Close Editor");
		closeButton.addActionListener(_ -> controller.setEditorVisible(false));

		header.add(title, BorderLayout.CENTER);
		header.add(closeButton, BorderLayout.EAST);
		add(header, BorderLayout.NORTH);

		cardLayout = new CardLayout();
		cardContainer = new JPanel(cardLayout);

		JButton openFileButton = new JButton("Open File");
		openFileButton.addActionListener(_ -> controller.openFileAction());
		JPanel buttonWrapper = new JPanel(new GridBagLayout());
		buttonWrapper.add(openFileButton);

		textEditorArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textEditorArea);

		cardContainer.add(buttonWrapper, EditPaneState.EMPTY.name());
		cardContainer.add(scrollPane, EditPaneState.EDITING.name());

		add(cardContainer, BorderLayout.CENTER);

		EditPaneState initialState =
				controller.getState().isFileOpen() ? EditPaneState.EDITING : EditPaneState.EMPTY;
		updateActiveView(initialState);
	}

	public void updateActiveView(EditPaneState targetState) {
		cardLayout.show(cardContainer, targetState.name());
	}

	public void setEditorText(String text) {
		textEditorArea.setText(text);
	}
}
