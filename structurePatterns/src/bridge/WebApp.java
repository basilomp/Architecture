package bridge;

import bridge.theme.AquaTheme;
import bridge.theme.DarkTheme;
import bridge.theme.Theme;
import bridge.webPage.About;
import bridge.webPage.Carreers;
import bridge.webPage.WebPage;

public class WebApp {
    public static void main(String[] args) {
        Theme darkTheme = new DarkTheme();
        Theme aquaTheme = new AquaTheme();

        WebPage about = new About(darkTheme);
        WebPage carreers = new Carreers(aquaTheme);

        System.out.println(about.getContent());
        System.out.println(carreers.getContent());
    }
}
