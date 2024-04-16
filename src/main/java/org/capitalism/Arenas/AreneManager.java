package org.capitalism.Arenas;

import org.capitalism.Capitalism;

public class AreneManager {

    private Capitalism capitalism;

    public Arena actualGame;

    public AreneManager(Capitalism capitalism) {
        this.capitalism = capitalism;
    }

    public Arena getActualGame() {
        return actualGame;
    }

    public void addArena(Arena arena) {
        this.actualGame = arena;
    }

}
