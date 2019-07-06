package it.unibo.oop.bbgmm.Entity;

public enum CollisionLabel {

    PLAYER {
        public boolean canCollideWith(final CollisionLabel other) {
            return other == ALIEN || other == WALL || other == POWER;
        }
    },

    ALIEN {
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER || other == WALL || other == POWER;
        }
    },

    SHOT {
        public boolean canCollideWith(final CollisionLabel other){
            return other == ALIEN || other == WALL;
        }
    },

    POWER {
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER || other == ALIEN;
        }
    },

    WALL{
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER || other == ALIEN;
        }
    };

    public boolean canCollideWith(final CollisionLabel other){
        return true;
    }

}
