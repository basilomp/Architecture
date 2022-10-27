package bridge.webPage;

import bridge.theme.Theme;

public class Carreers implements WebPage{
    protected Theme theme;

    public Carreers(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "Carreers page in " + this.theme.getColour();
    }
}
