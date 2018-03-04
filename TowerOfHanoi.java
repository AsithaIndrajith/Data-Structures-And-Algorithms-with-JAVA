package javaProgramms;

public class TowerOfHanoi {

	public static void towerOfHanoi(int disks,String source,String dest,String spare) {
		if(disks==1) {
			move_disk(source,dest);//if there is only one disk move it from source to destination
		}
		else {
			towerOfHanoi(disks-1,source,spare,dest);//we move remaining n-1 disks to source to spare peg and again call function
			move_disk(source,dest);
			towerOfHanoi(disks-1,spare,dest,source);//then from spare to destination
		}
	}
	public static void move_disk(String source,String dest) {
		System.out.println("Move From "+source+" to "+dest);//we print at each time disks go from where to where
	}
	public static void main(String[] args) {
		System.out.println("1: <Source peg>\n2: <Spare peg>\n3: <Destination peg>");
		towerOfHanoi(1,"1","3","2");
	}

}
