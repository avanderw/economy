package net.avdw.economy.market;

import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import net.avdw.economy.market.api.AMarket;
import net.avdw.economy.market.api.Demand;
import net.avdw.economy.market.api.Good;

import java.util.Random;
import java.util.UUID;

public class SimulateMarket {
    public static void main(String[] args) {
        new SimulateMarket().menu.display();
    }

    Menu menu;
    AMarket market;

    SimulateMarket() {
        market = new DemandMarket(new BasicStorage());
        menu = new Menu();
        menu.add(this);
    }

    @Action
    public void register() {
        Random rand = new Random();
        market.register(new Good(UUID.randomUUID().toString(), new Demand(rand.nextLong(), rand.nextInt())), rand.nextLong());
        display();
    }

    @Action
    public void display() {
        clearScreen();
        System.out.println(market);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
