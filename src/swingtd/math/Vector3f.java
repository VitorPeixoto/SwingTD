package swingtd.math;

/**
 *
 * @author peixoto
 */
public class Vector3f {
    private float x, y, z;
    private static final float EPSILON = .0001f;
    
    public static final Vector3f 
        VECTOR_X = new Vector3f(1, 0, 0),
        VECTOR_Y = new Vector3f(0, 1, 0),
        VECTOR_Z = new Vector3f(0, 0, 1);
    
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() { x = y = z = 0; }
    
    //public float dotProduct(Vector3f axis) { return (x * axis.x) + (y * axis.y); }
    public float getNorm() { return (float) Math.sqrt(x * x + y * y + z * z); }

    public static Vector3f normalized(Vector3f v) { return v.divided(v.getNorm()); }

    //public Vector3f tripleProduct(Vectorf v1, Vector3f v2) { return (v1.scaled(this.dotProduct(v2)).subtracted(this.scaled(v1.dotProduct(v2)))); }

    public boolean equals(Vector3f v) {
        if(equals(x, v.getX()) && equals(y, v.getY()) && equals(z, v.getZ())) {
            this.setX(v.getX());
            this.setY(v.getY());
            this.setZ(v.getZ());
            return true;
        }
        return false;
    }

    private boolean equals(float f1, float f2) {
        return Math.abs(f1 - f2) < EPSILON;
    }
    
    public Vector3f added(Vector3f v) {
        return new Vector3f(x+v.x, y+v.y, z+v.z);
    }
    
    public Vector3f subtracted(Vector3f v) {
        return new Vector3f(x-v.x, y-v.y, z-v.z);
    }
    
    public Vector3f subtracted(float number) {
        return new Vector3f(x-number, y-number, z-number);
    }
    
    public Vector3f negated() {
        return new Vector3f(-x, -y, -z);
    }
    
    public Vector3f scaled(float number) {
        return new Vector3f(x*number, y*number, z*number);
    }

    public Vector3f divided(float number) {
        return new Vector3f(x/number, y/number, z/number);
    }

    public void add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
    }
    
    public void subtract(Vector3f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
    }
    
    public void subtract(float number) {
        this.x -= number;
        this.y -= number;
        this.z -= number;
    }
    
    public void scale(float number) {
        this.x *= number;
        this.y *= number;
        this.z *= number;
    }
    
    public void divide(float number) {
        this.x /= number;
        this.y /= number;
        this.z /= number;
    }

    public void multiply(Vector3f v) {
        this.x *= v.x;
        this.y *= v.y;
        this.z *= v.z;
    }

    @Override
    public String toString() {
        return "Vector3f{" + "x=" + x + ", y=" + y + ", z=" + z +'}';
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    public Vector3f copyOf() {
        return new Vector3f(this.getX(), this.getY(), this.getZ());
    }
    
    
}
