package it.unibo.oop.bbgmm.Entity.Collision;

public enum CollisionLabel {

    PLAYER {
        public boolean canCollideWith(final CollisionLabel other) {
            return other == PUNCH || other == COIN || other == POWER || other == WALL;
        }
    },

    ALIEN {
        public boolean canCollideWith(final CollisionLabel other){
            return other == WALL || other == SHOT;
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

    PUNCH{
        public boolean canCollideWith(final CollisionLabel other) { return other == PLAYER;}
    },

    WALL{
        public boolean canCollideWith(final CollisionLabel other) {
            return other == PLAYER || other == ALIEN || other == SHOT;}
    };

    public boolean canCollideWith(final CollisionLabel other){
        return true;
    }

}
