/**
 * Copyright (c) Martin Andrew Habich, 2014
 */
package com.martinandrewhabich.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * DynamicBody.java
 * 
 * @author Martin
 */
public class RectangularPhysicsBody {

  public Body body;
  private float width;
  private float height;

  public RectangularPhysicsBody(World world, float xPosCenter, float yPosCenter, float width,
      float height, BodyType bodyType) {

    this.width = width;
    this.height = height;

    BodyDef bodyDef = new BodyDef();
    bodyDef.type = bodyType;
    bodyDef.position.set(xPosCenter, yPosCenter);
    body = world.createBody(bodyDef);

    // Create a polygon shape.
    PolygonShape rectangle = new PolygonShape();
    // SetAsBox takes half-width and half-height as arguments.
    rectangle.setAsBox(width / 2, height / 2);

    // Create a fixture definition to apply our shape to.
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = rectangle;
    fixtureDef.density = 0.5F;
    if (bodyType == BodyType.DynamicBody) {
      fixtureDef.friction = 0.96F;
      fixtureDef.restitution = 0.2F;
    }

    body.createFixture(fixtureDef);

    rectangle.dispose();
  }

  public float getXLeft() {
    return body.getPosition().x - width / 2;
  }

  public float getYBottom() {
    return body.getPosition().y - height / 2;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

}
