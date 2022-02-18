package notch.launcher.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

import java.util.Optional;

public class DiscordRP {

    public boolean running = true;
    public long runtime = 0;

    public void update(String linea, String lineb, String imga, Optional<String> imgb) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(lineb);
        b.setBigImage(imga, imga);
        b.setStartTimestamps(runtime);
        b.setDetails(linea);
        if (imgb.isPresent()) {
            b.setSmallImage(imgb.get(), imgb.get());
        }
        DiscordRPC.discordUpdatePresence(b.build());
    }

    public void start() {
        this.runtime = System.currentTimeMillis();
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {

            @Override
            public void apply(DiscordUser discordUser) {
                System.out.println("Discord ready!");
            }
        }).build();

        DiscordRPC.discordInitialize("804355313460969523", handlers, true);

        new Thread("Discord") {
            @Override
            public void run() {
                while (running) {
                    DiscordRPC.discordRunCallbacks();
                }
            }
        }.start();
    }

    public void stop() {
        running = false;
        DiscordRPC.discordShutdown();
    }

}
