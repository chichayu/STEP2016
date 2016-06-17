package thirdclass;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
	
	static ArrayList<String> readNum(char[] charArray, int index) {
		ArrayList<String> Num = new ArrayList<>();
		double number = 0;
		int i = index;
		while (i < charArray.length && Character.isDigit(charArray[i])) {
			number = number * 10 + Character.getNumericValue(charArray[i]);
			i += 1;
		}

		if (i < charArray.length && charArray[i] == '.') {
			i += 1;
			double keta = 0.1;
			while (i < charArray.length && Character.isDigit(charArray[i])) {
				number += Character.getNumericValue(charArray[i]) * keta;
				keta *= 0.1;
				i += 1;
			}
		}
		Num.add(Double.toString(number));
		Num.add(Integer.toString(i));
		return Num;
	}

	static ArrayList<String> tokenize(char[] charArray) {
		ArrayList<String> tokens = new ArrayList<>();
	
		for (int indexOld = 0 ; indexOld < charArray.length; indexOld++) {
			if (Character.isDigit(charArray[indexOld])) {
				tokens.add(readNum(charArray, indexOld).get(0));
				indexOld = Integer.parseInt(readNum(charArray, indexOld).get(1));
			} else if (charArray[indexOld] == '+') {
				tokens.add("+");
			} else if (charArray[indexOld] == '-') {
				tokens.add("-");
			} else if (charArray[indexOld] == '*') {
				tokens.add("*");
			} else if (charArray[indexOld] == '/') {
				tokens.add("/");
			} else {
				System.out.println("Invalid character found");
			}
		}
		return tokens;
	}

	static ArrayList<String> evaluateTD(ArrayList<String> array) {
		ArrayList<String> Array = new ArrayList<>();
		for (int i= 0; i < array.size(); i++) {
			Array.add(array.get(i));
		}
		for (int i = 0; i < Array.size();i++) {
			if (Array.get(i) == "*") {
				Array.set(i-1, Double.toString(Double.parseDouble(array.get(i - 1)) * Double.parseDouble(array.get(i + 1))));
				Array.remove(i);
				Array.remove(i);
			} else if (array.get(i) == "/") {
				Array.set(i-1, Double.toString(Double.parseDouble(array.get(i - 1)) / Double.parseDouble(array.get(i + 1))));
				Array.remove(i);
				Array.remove(i);
			} 
		}
		return Array;
	}
	
	static double evaluatePM(ArrayList<String> array) {
		if (array.size() < 3) {
			System.out.println("something went wrong");
			return 0;
		} else {
		double answer = Double.parseDouble(array.get(0));
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i) == "+") {
				answer += Double.parseDouble(array.get(i + 1));
			} else if (array.get(i) == "-") {
				answer -= Double.parseDouble(array.get(i + 1));
			} else {
				System.out.println("Invalid syntax");
			}
		}
		return answer;
	}
	}
	
	public static void main(String[] args) {
		ArrayList<String> array = new ArrayList<String>();
		System.out.println("数式を入力して下さい。");
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		char[] charArray = str.toCharArray();
		array = tokenize(charArray);
		System.out.println(evaluateTD(array));
		scan.close();
	}
}