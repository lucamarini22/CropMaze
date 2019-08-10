package it.unibo.oop.bbgmm.entity.collision;

public enum CollisionLabel {

    PLAYER {
        public boolean canCollideWith(final CollisionLabel other) {
            return other == ALIEN || other == COIN || other == POWER;
        }
    },

    ALIEN {
        public boolean canCollideWith(final CollisionLabel other){
            return other == SHOT;
        }
    },

    SHOT {
        public boolean canCollideWith(final CollisionLabel other){
            return other == ALIEN || other == WALL;
        }
    },

    POWER {
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER;}
    },

    COIN{
        public boolean canCollideWith(final CollisionLabel other){
            return other == PLAYER;
        }
    },

    WALL{
        public boolean canCollideWith(final CollisionLabel other) {
            return other == SHOT;}
    };

    public boolean canCollideWith(final CollisionLabel other){
        return true;
    }

}
