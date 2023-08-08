package me.hostadam.timers.test;

import me.hostadam.timers.Timer;
import me.hostadam.timers.TimerLookup;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Optional;

public class ImmunityTimer {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player player)) {
            return;
        }

        String timerName = "immunity-" + player.getName();
        new TimerLookup(timerName)
                .ifPresent(timer -> {
                    event.setCancelled(true);
                    player.sendMessage("§cYou are currently immune.");
                })
                .run();
    }


}
