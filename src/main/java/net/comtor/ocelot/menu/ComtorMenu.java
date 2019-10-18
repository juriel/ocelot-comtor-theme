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

        HtmlSpan span = new HtmlSpan();
        span.addAttribute("href", "#" + divId);
        span.addClass("nav-link").addAttribute("data-toggle", "collapse");
        span.add(getIcon(father.getIcon()));

        HtmlB b = new HtmlB();
        b.addClass("caret");
        HtmlP p = (HtmlP) new HtmlP().addData(father.getLabel());
        p.add(b);
        span.add(p);

        mainNode.add(span);

        HtmlDiv div = new HtmlDiv(divId);
        div.addClass("collapse");

        div.add(getChildren(father.getMenuItems()));
        mainNode.add(div);
        return mainNode;
    }

    private HtmlIcon getIcon(String iconClass) {
        HtmlIcon iTag = new HtmlIcon();
        iTag.addClass(iconClass);
        return iTag;
    }

    @Override
    public HtmlTag getChildren(List<MenuItem> childs) {
        HtmlUl ul = new HtmlUl();
        ul.addClass("nav");
        for (MenuItem child : childs) {
            HtmlLi navItem = new HtmlLi();
            navItem.addClass("nav-item").addClass("menu-item");
            navItem.addAttribute("endpoint", child.getPath());

            HtmlA wrapper = new HtmlA();
            wrapper.addClass("nav-link");

            HtmlSpan initial = new HtmlSpan(child.getLabel().substring(0, 1));
            initial.addClass("sidebar-mini");

            HtmlSpan label = new HtmlSpan(child.getLabel());
            label.addClass("sidebar-normal");

            wrapper.add(initial).add(label);
            navItem.add(wrapper);
            ul.add(navItem);
        }
        return ul;
    }

}
