/**
 *Class - BoundingBox
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

//======End Import Statements======\\

//******Begin Class******\\

public class BoundingBox {
	
	//------Variables------\\
	
	public Point3D maximum;
	public Point3D minimum;
	
	//------End Variables------\\
	
	//~~~~~~Methods~~~~~~\\
	
	//Constructor
	public BoundingBox(Point3D max, Point3D min){
		maximum = max;
		minimum = min;
	}
	
	//Check to see if the box contains a point
	public Boolean contains(Point3D position){
		if(position.X <= maximum.X && position.X >= minimum.X){
			if(position.Y <= maximum.Y && position.Y >= minimum.Y){
				if(position.Z <= maximum.Z && position.Z >= minimum.Z){
					return(true);
				}
			}	
		}
		return(false);
	}
	
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\
