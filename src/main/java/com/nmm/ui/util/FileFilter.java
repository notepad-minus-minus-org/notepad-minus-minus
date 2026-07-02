package com.nmm.ui.util;

import java.io.File;

public class FileFilter extends javax.swing.filechooser.FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String name = f.getName().toLowerCase();

		if (name.endsWith(".pdf") || name.endsWith(".epub") || name.endsWith(".txt")) {
			return true;
		}

		int lastDot = name.lastIndexOf('.');
		if (lastDot <= 0) {
			return true;
		}

		return false;
	}

	@Override
	public String getDescription() {
		return "Supported Books & Documents (*.pdf, *.epub, *.txt or no extension)";
	}
}
