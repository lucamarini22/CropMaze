package it.unibo.oop.bbgmm;


public class AttackImpl implements Attack {

    private Weapons weapon;
    private Direction direction;
    private List<Bullet> bulletList;

    AttackImpl(final Direction startingDirection, final Weapons startingWeapon) {
        this.weapon = startingWeapon;
        this.direction = startingDirection;
        this.bulletList = new ArrayList<Bullet>();
    }

    @Override
    public int getWeaponRange() {
        return this.weapon.range;
    }

    @Override
    public int getWeaponDamage() {
        return this.weapon.damage;
    }

    @Override
    public void setWeaponRange(final int range) {
        this.weapon.setRange(value);
    }

    @Override
    public void setWeaponDamage(final int damage) {
        this.weapon.setDamage(value);
    }

    @Override
    public void setDirection(final Direction newDirection) {
        this.direction = newDirection;
    }

    @Override
    public List<Bullet> getBulletList() {
        return this.bulletList;
    }

    @Override
    public void shotBullet() {
        Bullet bullet = new Bullet();
        bulletList.add(bullet);
    }
}
