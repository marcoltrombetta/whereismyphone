package model;

import java.util.Collection;

/**
 *
 * @author marco
 */
public class MenuModel {
    private String name;
    private String url;
    private String style;
    private Collection<MenuModel> childItems;
    private MenuModel parent;
    
    public MenuModel(String name, String url, String style,MenuModel parent) {
        this.name = name;
        this.url = url;
        this.style = style;
        this.parent=parent;
    }

    public MenuModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Collection<MenuModel> getChildItems() {
        return childItems;
    }

    public void setChildItems(Collection<MenuModel> parentItems) {
        this.childItems = parentItems;
    }

    public MenuModel getParent() {
        return parent;
    }

    public void setParent(MenuModel parent) {
        this.parent = parent;
    }
    
}
