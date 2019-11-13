package net.comtor.ocelot.forms.regular;

import net.comtor.ocelot.html.HtmlEscapedText;
import net.comtor.ocelot.html.forms.HtmlFormElement;
import net.comtor.ocelot.html.forms.HtmlLabel;
import net.comtor.ocelot.html.forms.IHtmlCheckbox;
import net.comtor.ocelot.html.forms.inputs.HtmlCheckBox;
import net.comtor.ocelot.html.styles.HtmlDiv;
import net.comtor.ocelot.html.styles.HtmlSpan;

/**
 * Checkbox del tema Comtor
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Nov 13, 2019
 */
public class ComtorCheckBox extends HtmlDiv implements IHtmlCheckbox {

    public static final String FORM_CHECK_CLASS = "form-check";
    public static final String FORM_CHECK_INPUT_CLASS = "form-check-input";
    public static final String FORM_CHECK_LABEL_CLASS = "form-check-label";

    private HtmlLabel label;
    private HtmlCheckBox checkbox;
    private HtmlSpan sign;

    public ComtorCheckBox(String labelText, String value, String name, String style, boolean checked) {
        addClass(FORM_CHECK_CLASS);

        label = new HtmlLabel(name, "");
        label.setStyle(style);

        checkbox = new HtmlCheckBox(checked);
        checkbox.addClass(FORM_CHECK_INPUT_CLASS);
        checkbox.setId(name);
        checkbox.setName(name);
        checkbox.setValue(value);
        label.add(checkbox);

        sign = new HtmlSpan();
        sign.addClass("form-check-sign");
        label.add(sign);

        label.addEscapedText(labelText);

        add(label);
    }

    public ComtorCheckBox(String labelText, String value, String name, boolean checked) {
        this(labelText, value, name, FORM_CHECK_LABEL_CLASS, checked);
    }

    public ComtorCheckBox(String labelText, String value, String name) {
        this(labelText, value, name, false);
    }

    @Override
    public HtmlFormElement required() {
        checkbox.required();

        return this;
    }

    @Override
    public HtmlFormElement setValue(String value) {
        checkbox.setValue(value);

        return this;
    }

    @Override
    public String getValue() {
        return checkbox.getValue();
    }

    @Override
    public HtmlFormElement removeRequired() {
        checkbox.removeRequired();

        return this;
    }

    @Override
    public HtmlFormElement disable() {
        checkbox.disable();

        return this;
    }

    @Override
    public HtmlFormElement enable() {
        checkbox.enable();

        return this;
    }

    @Override
    public HtmlFormElement readOnly() {
        checkbox.readOnly();

        return this;
    }

    @Override
    public HtmlFormElement removeReadOnly() {
        checkbox.removeReadOnly();

        return this;
    }

    @Override
    public HtmlFormElement setName(String name) {
        checkbox.setName(name);

        return this;
    }

    @Override
    public String getName() {
        return checkbox.getName();
    }

}
