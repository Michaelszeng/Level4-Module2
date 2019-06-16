package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		}
		return s2;
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if (s.contains("underscores")) {
			//System.out.println(s);
			s = s.replace(' ', '_');
			//System.out.println(s);
		}
		return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		int s1Space = s1.indexOf(' ');
		int s2Space = s2.indexOf(' ');
		int s3Space = s3.indexOf(' ');
		if (s1.substring(s1Space+1, s1Space+2).compareTo(s2.substring(s2Space+1, s2Space+2)) < 0) {
			if (s1.substring(s1Space+1, s1Space+2).compareTo(s3.substring(s3Space+1, s3Space+2)) < 0) {
				return s1;
			}
		}
		else if (s1.substring(s1Space+1, s1Space+2).compareTo(s2.substring(s2Space+1, s2Space+2)) > 0) {
			if (s3.substring(s3Space+1, s3Space+2).compareTo(s2.substring(s2Space+1, s2Space+2)) > 0) {
				return s2;
			}
		}
		return s3;
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int total = 0;
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				int val = Character.getNumericValue(c);
				//System.out.println(val);
				total += val;
			}
		}
		//System.out.println("total:" + total);
		return total;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int num = 0;
		int start = 0;
		while (s.indexOf(substring, start) != -1) {
			num++;
			start = s.indexOf(substring, start) + substring.length();
		}
		return num;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte)(key));
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) (key));
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int num = 0;
		String[] strings = s.split(" ");
		for (String str : strings) {
			if (str.endsWith(substring)) {
				num++;
			}
		}
		return num;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int i1 = s.indexOf(substring);
		int i2 = s.lastIndexOf(substring);
		//System.out.println(i2-i1-substring.length());
		return i2-i1-substring.length();
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		s = s.replace(" ", "");
		s = s.replace(".", "");
		s = s.replace("?", "");
		s = s.replace(",", "");
		s = s.replace("-", "");
		s = s.replace(":", "");
		System.out.println(s);
		for (int i=0; i<s.length(); i++) {
			System.out.println(s.substring(i, i+1) + s.substring(s.length()-i-1, s.length()-i));
			if (!(s.substring(i, i+1).toLowerCase().equals(s.substring(s.length()-i-1, s.length()-i).toLowerCase()))) {
				return false;
			}
		}
		return true;
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
			//System.out.println(plaintext[i]);
		}
		//System.out.println(Base64.getEncoder().encodeToString(plaintext));
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
			//System.out.println(b[i]);
		}
		//System.out.println(new String(b));
		return new String(b);
	}
}
