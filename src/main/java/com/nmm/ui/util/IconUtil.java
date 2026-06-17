package com.nmm.ui.util;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

/**
 * Utility class for loading icons from resources.
 */
public class IconUtil {

    private static final String ICON_PATH = "/icons/";

    /**
     * Loads an icon by its base name (without extension). With the default size of 16px.
     * Supports .png and .jpg files.
     *
     * @param name the base name of the icon (e.g., "open", "save")
     * @return an ImageIcon, or null if not found
     */
    public static ImageIcon loadImageIcon(String name) {
        return loadImageIcon(name, 16);
    }

	/**
     * Loads an icon by name with specific size, handling spaces in filename.
     *
     * @param name the base name of the icon (can contain spaces like "empty file")
     * @param size the desired width (height is proportional)
     * @return an ImageIcon, or null if not found
     */
    private static ImageIcon loadImageIcon(String name, int size) {
        String[] extensions = {".png", ".jpg", ".jpeg"};

        for (String ext : extensions) {
            try {
                String path = ICON_PATH + name + ext;
                URL url = IconUtil.class.getResource(path);
                if (url != null) {
                    BufferedImage image = ImageIO.read(url);
                    if (image != null) {
                        return new ImageIcon(image.getScaledInstance(size, -1, java.awt.Image.SCALE_SMOOTH));
                    }
                }
            } catch (IOException e) {
                // Continue to next extension
            }
        }

        System.err.println("Icon not found: " + name);
        return null;
    }

	/**
	 * Gets a placeholder icon. For development only.
	 */
	public static ImageIcon getPlaceholder() {
		return loadImageIcon("placeholder");
	}

    /**
     * Gets a bold icon.
     */
    public static ImageIcon getBold() {
        return loadImageIcon("b");
    }

    /**
     * Gets an italic icon.
     */
    public static ImageIcon getItalic() {
        return loadImageIcon("i");
    }

    /**
     * Gets an underline icon.
     */
    public static ImageIcon getUnderline() {
        return loadImageIcon("underline");
    }

    /**
     * Gets a strikethrough icon.
     */
    public static ImageIcon getStrikeThrough() {
        return loadImageIcon("strike");
    }

    /**
     * Gets an unordered list icon.
     */
    public static ImageIcon getUnorderedList() {
        return loadImageIcon("ul");
    }

    /**
     * Gets an ordered list icon.
     */
    public static ImageIcon getOrderedListItem() {
        return loadImageIcon("ol");
    }

    /**
     * Gets a code block icon.
     */
    public static ImageIcon getCodeBlock() {
        return loadImageIcon("code");
    }

    /**
     * Gets a quote icon.
     */
    public static ImageIcon getQuote() {
        return loadImageIcon("quote");
    }

    /**
     * Gets a hyperlink icon.
     */
    public static ImageIcon getHyperlink() {
        return loadImageIcon("hyperlink");
    }

    /**
     * Gets an open file icon.
     */
    public static ImageIcon getOpen() {
        return loadImageIcon("open");
    }

    /**
     * Gets a save icon.
     */
    public static ImageIcon getSave() {
        return loadImageIcon("save");
    }

    /**
     * Gets an empty/new file icon.
     */
    public static ImageIcon getNewFile() {
        return loadImageIcon("empty file", 16);
    }

    /**
     * Gets a document with unsaved changes icon.
     */
    public static ImageIcon getUnsaved() {
        return loadImageIcon("unsaved", 16);
    }

	/**
	 * Gets an undo icon.
	 */
	public static ImageIcon getUndo() {
		// TODO: replace the placeholder with an icon
		return getPlaceholder();
	}

	/**
	 * Gets a redo icon.
	 */
	public static ImageIcon getRedo() {
		// TODO: replace the placeholder with an icon
		return getPlaceholder();
	}
}