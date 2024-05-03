class Cannon
{

  private PVector dimension;
  private PVector position;

  private Projectile projectile;
  private Forces forces;

  private float angle;
  private float deltaAngle;

  private boolean shoot;

  /* Constructor definition */
  public Cannon(PVector dimension, PVector position)
  {
    this.dimension = dimension;
    this.position = position;

    this.checkMag();

    this.angle = -QUARTER_PI;
    this.deltaAngle = 0.09f;
  }

  /* Function definition */
  public void fire()
  {
    if (this.shoot)
    {
      this.projectile.applyForce(forces.getGravity(true));

      this.forces.update();
      this.projectile.move();
    }
  }

  public void checkMag()
  {
    this.resetMagazine();
    this.forces = new Forces(this.projectile);
  }

  private void resetMagazine()
  {
    var radius = this.dimension.y / 2;
    var posX = this.position.x;
    var posY = this.position.y + this.dimension.y + radius;
    var position = new PVector(posX, posY);

    this.projectile = new Projectile(radius, position);
  }

  public void show()
  {
    fill(150, 45, 81, 180);
    stroke(0);
    strokeWeight(2);

    pushMatrix();
    translate(this.position.x, this.position.y + this.dimension.y);
    rotate(this.angle);

    rect(0, 0, this.dimension.x, this.dimension.y);
    popMatrix();
  }
}
