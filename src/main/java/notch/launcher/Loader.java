package notch.launcher;

import net.arikia.dev.drpc.DiscordRPC;
import notch.launcher.discord.DiscordRP;

public class Loader {

    public static void main(String[] args) {
        DiscordRP dcrp = new DiscordRP();
        dcrp.start();
        dcrp.update("test a", "test b", "notch", java.util.Optional.of("space"));
    }
}
