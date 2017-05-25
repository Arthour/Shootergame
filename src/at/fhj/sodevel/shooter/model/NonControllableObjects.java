package at.fhj.sodevel.shooter.model;

public class NonControllableObjects extends SpaceObject {
    public void moveForward() {
        position.setLocation(getX() + 1, getY());
        bounds.setLocation((int)bounds.getX() + 1, (int)bounds.getY());
    }

    public void moveBackward() {
        position.setLocation(getX() - 1, getY());
        bounds.setLocation((int)bounds.getX() -1, (int)bounds.getY());
    }

}
