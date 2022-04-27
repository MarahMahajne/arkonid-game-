import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-06
 */
public class Line {
    private Point start;
    private Point end;
    private Color color;
    /**
     * This is an instructor for class "Line".
     * <p>
     * the instructor receives a starting point and an ending point and sets them to the current line.
     * </p>
     *
     * @param start - a point - the starting point of the line.
     * @param end   - a point - the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This is another instructor for class "Line".
     * <p>
     * the instructor receives the tow different x and y values ,creates a new ending and starting points with them and
     * sets them to the current line.
     * </p>
     *
     * @param x1 - a double - the x value of the starting point of the line.
     * @param y1 - a double- the y value of the starting point of the line.
     * @param x2 - a double - the x value of the ending point of the line.
     * @param y2 - a double- the y value of the ending point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }
    /**
     * constructor.
     * @param x1 - a double - the x value of the starting point of the line.
     * @param y1 - a double- the y value of the starting point of the line.
     * @param x2 - a double - the x value of the ending point of the line.
     * @param y2 - a double- the y value of the ending point of the line.
     * @param color - the color of the line.
     * */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        this.color  = color;
    }
    /**
     * This function returns the length of the current line.
     * <p>
     * This function returns the length of the current line.using the class "Point"
     * </p>
     *
     * @return length - a double - the length of the line.
     */
    public double length() {
        double length = start.distance(end);
        return (length);
    }

    /**
     * This function returns the middle point of the line.
     * <p>
     * This function returns The middle point of the line..using the class "Point"
     * </p>
     *
     * @return middle- a point - the middle point of the line.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(midX, midY);
        return (middle);
    }

    /**
     * This function returns the start point of the line.
     *
     * @return start - a point - the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This function returns the end point of the line.
     *
     * @return end - a point - the ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This function checks if tow lines are intersecting.
     * <p>
     * This function returns the true if  the lines are intersecting else returns false.
     * </p>
     *
     * @param other - another line.
     * @return true - if the lines are intersecting.else return false.
     */
    public boolean isIntersecting(Line other) {
        if (calc(other) == null) {
            return false;
        }
        return true;
    }
    // Returns the intersection point if the lines intersect,
    // and null otherwise.

    /**
     * This function  finds the intersection point between tow lines.
     * <p>
     * This function returns the point of intersection between tow lines if it exists, if not returns null.
     * </p>
     *
     * @param other - another line.
     * @return interSection - returns the point of intersection.or null.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        Point interSection = calc(other);
        return (interSection);
    }

    /**
     * This function  checks if the lines are equal.
     * <p>
     * This function returns true if the lines are equal, false otherwise
     * </p>
     *
     * @param other - another line.
     * @return true - return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y3 = other.start.getY();
        double y4 = other.end.getY();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        if (x1 == x3 && x2 == x4 && y1 == y3 && y2 == y4) {
            return true;
        }
        return false;
    }

    /**
     * This function  returns the point of intersection between tow lines.
     * <p>
     * This function returns the point of intersection between tow lines if they are actually intersecting, otherwise it
     * returns null,I made this function as a helper to other functions in this class and did all the calculations here.
     * </p>
     *
     * @param other - another line.
     * @return p - point of intersection, or null.
     */
    public Point calc(Line other) {
     /*   boolean flag = false;
        boolean onLine = false;
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y3 = other.start.getY();
        double y4 = other.end.getY();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        double m1 = (y1 - y2) / (x1 - x2); // line 1
        double b1 = y1 - (m1 * x1);
        double m2 = (y3 - y4) / (x3 - x4); // line 2
        double b2 = y3 - (m2 * x3);
        double interSectionX = 0, interSectionY = 0;
        if (m1 == m2) { // parallel
            return null;
        }
        if (x1 == x2) {
            interSectionX = x1;
            interSectionY = m2 * interSectionX + b2;
            if (interSectionY >= Math.min(y3, y4) && interSectionY <= Math.max(y3, y4)) {
                flag = true;
            }
        }
        if (x3 == x4) {
            interSectionX = x3;
            interSectionY = m1 * interSectionX + b1;
            if (interSectionY >= Math.min(y2, y1) && interSectionY <= Math.max(y2, y1)) {
                flag = true;
            }
        }
        if (!flag) {
            interSectionX = (b2 - b1) / (m1 - m2);
            interSectionY = m1 * interSectionX + b1;
        }
        Point p = new Point(interSectionX, interSectionY);
        // if the point exists on the line
        if ((interSectionX >= Math.min(x1, x2) && interSectionX <= Math.max(x1, x2))
                && (interSectionY >= Math.min(y1, y2) && interSectionY <= Math.max(y1, y2))) {
            // We are within the bounding box of the first line segment,
            // so now check second line segment
            if (interSectionX >= Math.min(x3, x4) && interSectionX <= Math.max(x3, x4)) {
                if (interSectionY >= Math.min(y3, y4) && interSectionY <= Math.max(y3, y4)) {
                    onLine = true;
                }
            }
        }
        if (onLine) {
            return p;
        } else {
            return null;
        }*/
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y3 = other.start.getY();
        double y4 = other.end.getY();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        double f = ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        double uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / f;
        double uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / f;
        double intersectionX;
        double intersectionY;
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            intersectionX = x1 + (uA * (x2 - x1));
            intersectionY = y1 + (uA * (y2 - y1));
            Point p = new Point(intersectionX, intersectionY);
            return p;
        }
        return null;
    }

    /**
     * finds closest intersection point to the start of the given line.
     * <p>
     * This function returns the closest intersection point to the start of the given line , if this line does not
     * intersect with the rectangle the function returns null.
     * </p>
     *
     * @param rect - a rectangle.
     * @return the closest intersection to the start of the given line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int i, j, index = 0;
        double min;
        java.util.List<Point> list = rect.intersectionPoints(this);
        //if list is empty
        if (list == null || list.size() == 0) {
            return null;
        }
        double[] arrOfDistance = new double[list.size()];
        for (i = 0; i < list.size(); i++) {
            arrOfDistance[i] = this.start.distance(list.get(i));
        }
        min = arrOfDistance[0];
        for (j = 0; j < arrOfDistance.length; j++) {
            if (j > 0) {
                if (arrOfDistance[j] == min) {
                    min = arrOfDistance[j];
                    index = j;
                }
            }
            if (arrOfDistance[j] < min) {
                min = arrOfDistance[j];
                index = j;
            }
        }
        return list.get(index);
    }
    /*
    /**
     * finds closest intersection point to the start of the given line.
     * <p>
     * This function returns the closest intersection point to the start of the given line , if this line does not
     * intersect with the rectangle the function returns null.
     * </p>
     *
     * @param rect - a rectang
     * @return the closest intersection to the start of the given line.
    public boolean onLine(Point B) {
        Point A = this.start();
        Point C  = this.end();
        if (A.distance(B) + B.distance(C) == A.distance(C)) {
            System.out.print("true");
            return true; // B is on the line.
        }
        return false;
    }*/
    /**
     * this func draws the lines on a drawSurface.
     * @param d - the drawSurface.
     * */
    public void paintLine(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

}