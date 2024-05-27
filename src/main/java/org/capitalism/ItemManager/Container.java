package org.capitalism.ItemManager;

import org.bukkit.entity.Interaction;

public class Container {

    protected Interaction interaction;

    Container(Interaction interaction) {
        this.interaction = interaction;
    }

    public Interaction getInteraction() {
        return this.interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

}
