package main;

public class Coordinate {
	 int x;
     int y;  
     
     public Coordinate(int x, int y) {
         this.x = x;
         this.y = y;
     }
     
     @Override
     public int hashCode()
     {
         return x;
     }
     
     @Override
     public boolean equals(Object o) {
    	 Coordinate c = (Coordinate) o;
         return c.x == x && c.y == y;
     }


}
