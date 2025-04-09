class Point{
    int x,y;
    Point(int x,int y){
        this.x=x;this.y=y;
    }
    public boolean equals(Object o){
        if(o==null)return false;
        if(o==this)return true;
        Point op=(Point)o;
        if(op.x==this.x&&op.y==this.y)return true;
        return false;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}

// board contains point
// snake, direction(enum)
// game
// no class for food, list of points in board