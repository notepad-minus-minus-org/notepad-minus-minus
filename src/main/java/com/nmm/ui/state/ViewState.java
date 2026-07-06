package com.nmm.ui.state;

import com.nmm.core.model.DocumentSession;

public class ViewState {

    private boolean sidebarVisible = true;
    private boolean editorVisible = true;
    private boolean renderVisible = true;
    private int sidebarDividerLocation = 280;
    private int contentDividerLocation = 350;

    private final DocumentSession session;

    public ViewState(DocumentSession session) {
        this.session = session;
    }

    public DocumentSession getSession() {
        return session;
    }

    public boolean isFileOpen() {
        return session.isFileOpen();
    }

    public boolean isSidebarVisible() {
        return sidebarVisible;
    }

    public void setSidebarVisible(boolean sidebarVisible) {
        this.sidebarVisible = sidebarVisible;
    }

    public boolean isEditorVisible() {
        return editorVisible;
    }

    public void setEditorVisible(boolean editorVisible) {
        this.editorVisible = editorVisible;
    }

    public boolean isRenderVisible() {
        return renderVisible;
    }

    public void setRenderVisible(boolean renderVisible) {
        this.renderVisible = renderVisible;
    }

    public int getSidebarDividerLocation() {
        return sidebarDividerLocation;
    }

    public void setSidebarDividerLocation(int sidebarDividerLocation) {
        this.sidebarDividerLocation = sidebarDividerLocation;
    }

    public int getContentDividerLocation() {
        return contentDividerLocation;
    }

    public void setContentDividerLocation(int contentDividerLocation) {
        this.contentDividerLocation = contentDividerLocation;
    }
}
