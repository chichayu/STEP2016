package sixthclass;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TravellingSalesmanProblem {
	
	static class Combination {
	    int[] c;
	    int n, r, count;

	    Combination(int n, int r){
	      this.n = n;
	      this.r = r;
	      c = new int[20];
	      count = 0;
	      for ( int i=0; i<20; i++ ) 
	        c[i] = 0;
	    }
	    
	    int[] combine(int m) {
	        if (m <= r) {
	          for ( int i=c[m-1]+1; i<=n-r+m; i++ ){
	             c[m] = i;
	             combine(m+1);
	          }
	        }
			return c;
	    }
	}
	
	static class Pair {
		double x;
		double y;
		
		Pair(double _x, double _y) {
			x = _x;
			y = _y;
		}
	}

	static Map<Integer, Pair> setData(BufferedReader br) throws IOException {
		String line; 
		Map<Integer, Pair> cities = new HashMap<Integer, Pair>();
         int i = 1;
         br.readLine();
         while ((line = br.readLine()) != null) {
        	 String[] strAry = line.split(",");
        	 double x = Double.parseDouble(strAry[0]);
        	 double y = Double.parseDouble(strAry[1]);
        	 Pair pair = new Pair(x, y);
             cities.put(i, pair);
             i++;
         }
         return cities;
	}
	
	static int[] getMinRoute(Map<Integer, Pair> cities) {
		int[] route = new int[cities.size()];
		for (int i = 0; i < cities.size(); i++) {
			route[i] = i + 1;
		}
		//select four cities and get the minimam length
		route = getMinOfFour(cities, route);
		return route;
	}
	
	static int[] getMinOfFour(Map<Integer, Pair> cities, int[] route) {
		int[] answer = route;
		double length1 = 0;
		double length2 = 0;
		Combination cb = new Combination(cities.size(), 4);	
		int[] cbs = cb.combine(1); 
		for (int i = 0; i < cbs.length; i++) {
			System.out.println(cbs[i]);
		}
		length1 = getLength(cities, cbs[0], cbs[1]) + getLength(cities, cbs[2], cbs[3]);
		length2 = getLength(cities, cbs[0], cbs[2]) + getLength(cities, cbs[1], cbs[3]);
		if (length1 > length2) {
			int k = answer[cbs[1]]; 
			answer[cbs[1]] = answer[cbs[2]];
			answer[cbs[2]] = k;
		}
		return answer;
	}
	
	 static double getLength(Map<Integer, Pair> cities, int a, int b) {
		double length = Math.sqrt(Math.pow((cities.get(a).x - cities.get(b).x), 2) 
				+ Math.pow((cities.get(a).y - cities.get(b).y), 2));
		return length;
	}
	
	public static void main(String args[]) throws IOException {
		//ファイルを読み込む
        FileReader fr = new FileReader("/Users/xixi/google-step-tsp/input_1.csv");
        BufferedReader br = new BufferedReader(fr);
        //データをセットする
        Map<Integer, Pair> cities = new HashMap<Integer, Pair>();
        cities = setData(br);
        br.close();
        //最短ルートを求める
        int[] route = new int[cities.size()];
        route = getMinRoute(cities);
        for (int i = 0; i < route.length; i++) {
        	System.out.println(route[i]);
        }
        double length = 0;
        for (int j = 0; j < route.length - 1 ;j++) {
        	length += getLength(cities, route[j], route[j + 1]);
        }
        length += getLength(cities, route[route.length-1], route[0]);
        System.out.println(length);
	}
}
