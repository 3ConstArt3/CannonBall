class Forces
{

  private float pushAcceleration;
  private PVector gravityAcceleration;

  private Projectile projectile;

  /* Constructor definition */
  public Forces(Projectile projectile)
  {
    this.pushAcceleration = 12.4 * HALF_PI;
    this.gravityAcceleration = new PVector(0, 0.003);

    this.projectile = new Projectile(projectile.radius, projectile.position);
  }

  /* Function definition */
  public PVector getGravity(boolean apply)
  {
    if (!apply)
      return new PVector(0, 0);

    return PVector.mult(this.gravityAcceleration, this.projectile.mass);
  }

  public PVector getPush(float cannonAngle)
  {
    var push = PVector.fromAngle(cannonAngle);

    var pushStrength = push.mag();
    pushStrength *= pushAcceleration * PI;
    push.setMag(pushStrength);
      
    return push;
  }

  public void update()
  {
    var deltaGravity = new PVector(0, 0.0027);
    this.gravityAcceleration.add(deltaGravity);

    var gravityStrength = this.gravityAcceleration.mag();
    gravityStrength = constrain(gravityStrength, 0, 1);
    this.gravityAcceleration.setMag(gravityStrength);
  }
}
