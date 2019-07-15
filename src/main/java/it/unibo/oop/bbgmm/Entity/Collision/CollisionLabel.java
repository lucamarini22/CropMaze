package it.unibo.oop.bbgmm.Entity;

public enum CollisionLabel {

    PLAYER {
        public boolean canCollideWith(final CollisionLabel other) {
            return other == ALIEN || other == COIN || other == POWER;
        }
    },

    ALIEN {
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER || other == COIN || other == POWER || other == SHOT;
        }
    },

    SHOT {
        public boolean canCollideWith(final CollisionLabel other){
            return other == ALIEN || other == COIN;
        }
    },

    POWER {
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER || other == ALIEN;
        }
    },

    COIN{
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER || other == ALIEN;
        }
    };

    public boolean canCollideWith(final CollisionLabel other){
        return true;
    }

}
