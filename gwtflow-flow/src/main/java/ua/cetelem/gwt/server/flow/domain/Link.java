package ua.cetelem.gwt.server.flow.domain;

public class Link {
    private String id;
    private String source;
    private String sourcePort;
    private String target;
    private String targetPort;

    public Link(String id, String source, String sourcePort, String target, String targetPort) {
        this.id = id;
        this.source = source;
        this.sourcePort = sourcePort;
        this.target = target;
        this.targetPort = targetPort;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public String getTarget() {
        return target;
    }

    public String getTargetPort() {
        return targetPort;
    }

    @Override
    public String toString() {
        return String.format("id=%s,source=%s,sourcePort=%s,target=%s,targetPort=%s", id, source, sourcePort, target, targetPort);
    }
}
