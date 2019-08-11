package it.unibo.oop.bbgmm.entity.collision;

/**
 * It is responsible to decide if an object type can collide with another.
 */
public enum CollisionLabel {

    /**
     * Collision tag for the player object.
     */
    PLAYER {
        @Override
        public boolean canCollideWith(final CollisionLabel other) {
            return other == ALIEN || other == COIN || other == POWER;
        }
    },

    /**
     * Collision tag for the aline object.
     */
    ALIEN {
        @Override
        public boolean canCollideWith(final CollisionLabel other) {
            return other == SHOT;
        }
    },

    /**
     * Collision tag for the shot object.
     */
    SHOT {
        @Override
        public boolean canCollideWith(final CollisionLabel other) {
            return other == ALIEN || other == WALL;
        }
    },

    /**
     * Collision tag for the power object.
     */
    POWER {
        @Override
        public boolean canCollideWith(final CollisionLabel other) {
            return other == PLAYER; }
    },

    /**
     * Collision tag for the coin object.
     */
    COIN {
        @Override
        public boolean canCollideWith(final CollisionLabel other) {
            return other == PLAYER;
        }
    },

    /**
     * Collision tag for the wall object.
     */
    WALL {
        @Override
        public boolean canCollideWith(final CollisionLabel other) {
            return other == SHOT; }
    };

    /**
     * Returns true if objects can collide with objects of the given one type.
     * @param other
     *      the other type.
     * @return
     *      true if the objects can collide.
     */
    public boolean canCollideWith(final CollisionLabel other) {
        return true;
    }

}
