package org.dominokit.domino.ui.icons;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.Text;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.TextNode;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.span;

public class LabeledIcon extends BaseDominoElement<HTMLDivElement, LabeledIcon> {

    private HTMLDivElement element = div().css(IconsStyles.LABELED_ICON).asElement();
    private HTMLElement leftSpan = span().css(IconsStyles.LEFT_NODE).asElement();
    private HTMLElement rightSpan = span().css(IconsStyles.RIGHT_NODE).asElement();

    public LabeledIcon(BaseIcon icon, String text) {
        this(icon, text, IconPosition.LEFT);
    }

    public LabeledIcon(BaseIcon icon, String text, IconPosition position) {
        position.placeElements(leftSpan, rightSpan, icon, TextNode.of(text));
        element.appendChild(leftSpan);
        element.appendChild(rightSpan);
    }

    public static LabeledIcon create(BaseIcon icon, String text){
        return new LabeledIcon(icon, text);
    }

    public static LabeledIcon create(BaseIcon icon, String text, IconPosition position){
        return new LabeledIcon(icon, text, position);
    }

    @Override
    public HTMLDivElement asElement() {
        return element;
    }

    public enum IconPosition{
        LEFT((left, right, icon, text) -> {
            left.appendChild(icon.asElement());
            right.appendChild(text);
            left.classList.add(IconsStyles.ICON_NODE);
            right.classList.add(IconsStyles.TEXT_NODE);
        }),

        RIGHT((left, right, icon, text) -> {
            left.appendChild(text);
            right.appendChild(icon.asElement());
            right.classList.add(IconsStyles.ICON_NODE);
            left.classList.add(IconsStyles.TEXT_NODE);
        });

        private ElementsPlacement elementsPlacement;

        IconPosition(ElementsPlacement elementsPlacement) {
            this.elementsPlacement = elementsPlacement;
        }

        public void placeElements(HTMLElement left, HTMLElement right, BaseIcon icon, Text text) {
            elementsPlacement.placeElements(left, right, icon, text);
        }
    }

    @FunctionalInterface
    private interface ElementsPlacement{
        void placeElements(HTMLElement left, HTMLElement right, BaseIcon icon, Text text);
    }
}
