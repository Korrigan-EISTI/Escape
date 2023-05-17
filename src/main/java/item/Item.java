package main.java.item;

import main.java.Environment;
import main.java.entity.Entity;
import main.java.entity.Player;

/**
 * The Item class represents an item in the game.
 * It extends the Entity class and provides additional functionality specific to items.
 */
public class Item extends Entity {

    /**
     * The status of the item.
     */
    protected boolean status;

    /**
     * Constructs a new Item object with the specified position and dimensions.
     *
     * @param x The x-coordinate of the item's position.
     * @param y The y-coordinate of the item's position.
     * @param w The width of the item.
     * @param h The height of the item.
     */
    public Item(double x, double y, double w, double h) {
        super(x, y, 0, 0);
        status = true;
    }

    /**
     * Updates the item's state during each game tick.
     * If a player intersects with the item, the item's status is set to false and it is destroyed.
     *
     * @param e The game environment.
     */
    @Override
    public void tick(Environment e) {
        super.tick(e);
        for (int i = 0; i < e.getEntityCount(); i++) {
            if (e.getEntity(i) instanceof Player) {
                Player player = (Player) e.getEntity(i);
                if (x - player.getWidth() <= player.getX() && player.getX() <= x + 1 && player.getY() >= y && player.getY() <= y + 1) {
                    this.status = false;
                    destroy();
                }
            }
        }
    }

    /**
     * Returns the status of the item.
     *
     * @return The status of the item.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the status of the item.
     *
     * @param status The new status of the item.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
