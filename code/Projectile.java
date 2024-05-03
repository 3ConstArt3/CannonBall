class Projectile
{

  private float mass;
  private float radius;

  private PVector position;
  private PVector velocity;
  private PVector acceleration;

  /* Constructor definition */
  public Projectile(float radius, PVector position)
  {
    this.radius = radius * 3;
    this.mass = HALF_PI * (float)Math.sqrt(this.radius);

    this.position = position;
    this.velocity = new PVector(0, 0);
    this.acceleration = new PVector(0, 0);
  }

  /* Function definition */
  public void applyForce(PVector outerForce)
  {
    var force = PVector.div(outerForce, this.mass);
    this.acceleration.add(force);
  }

  public void move()
  {
    this.velocity.add(this.acceleration);
    this.velocity.limit(24);
    this.position.add(this.velocity);

    this.acceleration.mult(0);
  }

  public boolean isOutOfScope()
  {
    return (this.position.x >= width - this.radius || 
            this.position.y >= height - this.radius);
  }

  public void show()
  {
    noStroke();
    fill(0, 180);

    ellipse(this.position.x, this.position.y,
            this.radius, this.radius);
  }
}
