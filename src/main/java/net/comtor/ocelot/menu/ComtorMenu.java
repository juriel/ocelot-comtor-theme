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

        root.getMenuItems().forEach((father) -> {
            rootTag.add(getFather(father));
        });

        add(rootTag);
    }

    @Override
    public HtmlTag getFather(MenuItem father) {
        HtmlLi mainNode = new HtmlLi();
        mainNode.addClass("nav-item").addClass("item-father");

        String divId = System.currentTimeMillis() + StringUtils.replace(father.getLabel(), " ", "_");

        HtmlSpan navLink = new HtmlSpan();
        navLink.addAttribute("href", "#" + divId);
        navLink.addClass("nav-link")
                .addAttribute("data-toggle", "collapse");
        navLink.add(getIcon(father.getIcon()));

        HtmlB caret = new HtmlB();
        caret.addClass("caret");

        HtmlP fatherLabel = (HtmlP) new HtmlP().addEscapedText(father.getLabel());
        fatherLabel.add(caret);

        navLink.add(fatherLabel);

        mainNode.add(navLink);

        HtmlDiv collapse = new HtmlDiv(divId);
        collapse.addClass("collapse");
        collapse.add(getChildren(father.getMenuItems()));

        mainNode.add(collapse);

        return mainNode;
    }

    private HtmlIcon getIcon(String iconClass) {
        HtmlIcon iTag = new HtmlIcon();
        iTag.addClass(iconClass);

        return iTag;
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

}
