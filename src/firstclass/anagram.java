package firstclass;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class anagram {

	public static void main(String[] args) throws IOException {
		// sort 16 characters in alphabetical order
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = null;
		try {
			String str = br.readLine();
			chars = str.toCharArray();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Arrays.sort(chars);
		String sorted = new String(chars);

		// sort every words in dictionary in alphabetical order
		BufferedReader br2 = new BufferedReader(new FileReader(
				"/usr/share/dict/words"));
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for (;;) {
			String s = br2.readLine();
			if (s == null) {
				break;
			}
			if (s.length() < 17) {
				char[] chars1 = s.toCharArray();
				Arrays.sort(chars1);
				String sw = new String(chars1);
				hashmap.put(s, sw);
			}
		}
		br2.close();

		int i = 0;
		// len[i] referrs to number of matched characters
		String longestmatch = null;
		String ans;
		for (String entry : hashmap.keySet()) {
			int[] len = new int[hashmap.size()];
			String[] chara = new String[16];
			for (int j = 0; j < 16; j++) {
				// i番目のkeyの文字列を取得
				String keyi = entry;
				// 同じだった文字を収納するための文字列を準備
				String match = null;
				// ソート済みの入力した文字列のj番目の数を取得する
				chara[j] = Character.toString(sorted.charAt(j));
				// chara[j]が文字列keyiの中に存在していたら
				if (keyi.indexOf(chara[j]) != -1) {
					int num = keyi.indexOf(chara[j]);
					// char[j]を文字列keyiから削除する
					keyi.replace(keyi.charAt(num), '\0');
					// 一致した文字をmatchに格納する
					match = match + chara[j];
				}
				// それぞれのmatchについて文字列の長さを得、一番長いものを選ぶ
				len[i++] = match.length();
				if (i == 0) {
					longestmatch = match;
				} else {
					if (len[i] > len[i - 1]) {
						longestmatch = match;
					}
				}
			}
			ans = longestmatch;
			// get value from hashmap
			System.out.println(hashmap.get(ans));
		}
	}
}
