package ua.cetelem.gwt.server.flow.xml;

public interface Visiter {
    void pushElement(BaseElement el);

    void popElement(BaseElement el);

    void execute(BaseElement el);
}
