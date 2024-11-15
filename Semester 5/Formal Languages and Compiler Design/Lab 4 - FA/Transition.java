package FA;

public class Transition {
    private String from;
    private String to;
    private String label;

    public Transition() {}

    public Transition(String from, String label, String to) {
        this.from = from;
        this.label = label;
        this.to = to;
    }

    public String getFromState() {
        return from;
    }

    public void setFromState(String from) {
        this.from = from;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getToState() {
        return to;
    }

    public void setToState(String to) {
        this.to = to;
    }
}
