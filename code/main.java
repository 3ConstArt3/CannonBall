Cannon cannon;

float deltaTheta = 0.01;

void setup()
{
  surface.setTitle("CannonBall");
  surface.setResizable(false);
  surface.setLocation(displayWidth / 3, floor(0.1 * displayHeight));

  createCannon();

  size(600, 420);
}

void createCannon()
{
  var dimension = new PVector(66, 18);
  var position = new PVector(dimension.x / 2, height - 3 * dimension.y);

  cannon = new Cannon(dimension, position);
}

void draw()
{
  background(60, 81, 120, 180);

  cannon.show();
  cannon.fire();
  cannon.projectile.show();

  if (cannon.projectile.isOutOfScope())
  {
    cannon.shoot = false;
    cannon.checkMag();
  }
}

void keyPressed()
{
  var specialKeyPressed = (key == CODED);
  if (specialKeyPressed)
  {
    if (keyCode == UP)
      cannon.angle -= cannon.deltaAngle;

    if (keyCode == DOWN)
      cannon.angle += cannon.deltaAngle;

    cannon.angle = constrain(cannon.angle, -HALF_PI, 0);
  }

  var cannonIsShooting = (key == ' ');
  if (cannonIsShooting)
  {
    cannon.shoot = true;
    var push = cannon.forces.getPush(cannon.angle);
    cannon.projectile.applyForce(push);
  }
}
