package net.comtor.ocelot.menu;

import java.util.List;
import net.comtor.ocelot.engine.components.menu.MenuItem;
import net.comtor.ocelot.engine.components.menu.OcelotMenu;
import net.comtor.ocelot.html.HtmlContainer;
import net.comtor.ocelot.html.HtmlTag;
import net.comtor.ocelot.html.basic.HtmlP;
import net.comtor.ocelot.html.formatting.HtmlB;
import net.comtor.ocelot.html.formatting.HtmlIcon;
import net.comtor.ocelot.html.links.HtmlA;
import net.comtor.ocelot.html.links.HtmlLi;
import net.comtor.ocelot.html.links.HtmlUl;
import net.comtor.ocelot.html.styles.HtmlDiv;
import net.comtor.ocelot.html.styles.HtmlSpan;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Guido Cafiel
 */
public class ComtorMenu extends HtmlContainer implements OcelotMenu {

    @Override
    public void buildMenu(MenuItem root) {
        HtmlUl rootTag = new HtmlUl();
        rootTag.addClass("nav");

        root.getMenuItems().forEach((father) -> rootTag.add(getFather(father)));

        add(rootTag);
    }

    @Override
    public HtmlTag getFather(MenuItem parent) {
        HtmlLi parentItem = new HtmlLi();
        parentItem.addClass("nav-item").addClass("parent-item");

        String divId = System.currentTimeMillis() + StringUtils.replace(parent.getLabel(), " ", "_");

        HtmlA navLink = new HtmlA().addHref("#" + divId);
        navLink.addClass("nav-link").addAttribute("data-toggle", "collapse");
        navLink.add(getIcon(parent.getIcon()));

        HtmlB caret = new HtmlB();
        caret.addClass("caret");

        HtmlP parentLabel = (HtmlP) new HtmlP().addEscapedText(parent.getLabel());
        parentLabel.add(caret);

        navLink.add(parentLabel);

        parentItem.add(navLink);

        HtmlDiv collapse = new HtmlDiv(divId);
        collapse.addClass("collapse");
        collapse.add(getChildren(parent.getMenuItems()));

        parentItem.add(collapse);

        return parentItem;
    }

    @Override
    public HtmlTag getChildren(List<MenuItem> childs) {
        HtmlUl nav = new HtmlUl();
        nav.addClass("nav");

        for (MenuItem child : childs) {
            HtmlLi navItem = new HtmlLi();
            navItem.addClass("nav-item").addClass("menu-item");
            navItem.addAttribute("endpoint", child.getPath());

            HtmlA navLink = new HtmlA();
            navLink.addClass("nav-link");

            HtmlSpan sidebarMini = new HtmlSpan(child.getLabel().substring(0, 1));
            sidebarMini.addClass("sidebar-mini");

            HtmlSpan sidebarNormal = new HtmlSpan(child.getLabel());
            sidebarNormal.addClass("sidebar-normal");

            navLink.add(sidebarMini).add(sidebarNormal);
            navItem.add(navLink);
            nav.add(navItem);
        }

        return nav;
    }

    private HtmlIcon getIcon(String iconClass) {
        HtmlIcon iTag = new HtmlIcon();
        iTag.addClass(iconClass);

        return iTag;
    }
}
