package amazon_oa1;
import java.util.PriorityQueue;
import java.util.Comparator;
//class Point{
//	int x;
//	int y;
//	Point(int x, int y){
//		this.x = x;
//		this.y = y;
//	}
//}
public class knearest {
	public static Point[] findKNearest(Point[] array, int k, Point origin ){
//		//corner case
		if(k < 0) {
			return new Point[]{};
		}
		final Point og = origin;
		Point[] result = new Point[k];
		
		if( array == null ||array.length == 0 || k > array.length ) {
			return result;
		}

		PriorityQueue<Point> maxheap = new PriorityQueue<Point>(k, new Comparator<Point>(){
			@Override
			public int compare(Point a, Point b) {
				//return -diff;
				
				double diff = getDist(a,og) - getDist(b,og);
				//return (int)-diff;
				if(diff > 0) {
					return -1;
				}else if(diff < 0) {
					return 1;
				}else {
					return 0;
				}
				
			}
		});
		
		for(int i = 0; i < array.length; i++ ) {
			if(i < k) {
				maxheap.offer(array[i]);
			}else {
				if(getDist(array[i],og) < getDist(maxheap.peek(), og)) {
					maxheap.offer(array[i]);
					maxheap.poll();
				}
			}
		}
		
		for(int i = 0; i < k ; i++) {
			System.out.println(getDist(maxheap.peek(),og));
			result[k - i - 1] = maxheap.poll();
		}
		return result;
		
		
		
	}
	
	
	private static double getDist(Point p,Point origin) {
		
		double length = Math.sqrt((p.x  - origin.x) * (p.x  - origin.x) + (p.y - origin.y) * (p.y - origin.y));
		return length;
	}
//		final Point og = origin;
//		Point[] ret = new Point[k];
//
//		if(array == null || array.length == 0 || k <= 0 || k > array.length)
//			return null;
//		
//		PriorityQueue<Point> pq = new PriorityQueue<Point>(k, new Comparator<Point>(){
//			@Override
//			public int compare(Point a, Point b){
//				double diff = getDistance(a, og) - getDistance(b, og);
//				if(diff > 0)
//					return -1;
//				if(diff < 0)
//					return 1;
//				else
//					return 0;
//			}
//		});
//
//
//		for(int i = 0; i < array.length; i ++) {
//			if(i < k) {
//				pq.offer(array[i]);
//			}else {
//				if(getDistance(array[i],og) < getDistance(pq.peek(),og)) {
//					pq.offer(array[i]);
//					pq.poll();
//					
//				}
//			}
//				
//		}
//
//		for(int i = 0; i < k; i ++){
//			//System.out.println(pq.peek().x);
//			//System.out.println(pq.peek().y);
//			ret[k - i - 1] = pq.poll();
//		}
//		return ret;
//	}

	
	public static void main(String[] args){
		Point[] points = new Point[6];
		int k = 3;
		Point og = new Point(0,0);
		points[0] = new Point(1,1);
		points[1] = new Point(-2,1);
		points[2] = new Point(2,-4);
		points[3] = new Point(0,-1);
		points[4] = new Point(4,0);
		points[5] = new Point(-2,-3);
		Point[] ret = knearest.findKNearest(points, k,og);
		if(ret == null)
			System.out.println("No available");
		else{
			for(int i = 0; i < ret.length; i ++){
				System.out.println(ret[i].x + " " + ret[i].y + " ");
			}
		}
	}
	
}
