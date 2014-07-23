package ua.cetelem.gwt.server.flow.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Container {
    private Collection<View> views = new ArrayList<>();
    private Collection<Point> points = new ArrayList<>();

    public Container() {
    }

    public Container(Collection<View> views, Collection<Point> points) {
        this.views = views;
        this.points = points;
    }

    public Collection<View> getViews() {
        return views;
    }

    public Collection<Point> getPoints() {
        return points;
    }
}
