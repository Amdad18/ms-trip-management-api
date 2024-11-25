/**
 * 
 */
package org.devp.trip.api.util;

import java.nio.ByteBuffer;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 *
 */
@Slf4j
public class StringUtil {
	
	private static final int MAXIMUM_WORD_COUNT = 10000;
	private static final int MAXIMUM_SUBSTRING_COUNT = 100000;
	
	private StringUtil() {
		log.info("Private Constructor"); 
	}
	
	public static String getOrDefaultEmpty(String stringContent) {
		return isNotBlank(stringContent) ? stringContent : "";
	}
	
	public static boolean isNotBlank(String string) {
		return null!=string && !string.trim().isEmpty();
	}
	
	public static boolean isBlank(String string) {
		return null==string || string.trim().isEmpty();
	}
	
	public static String[] toStringArray(List<String> stringlist) {
		if(null == stringlist) {
			return new String[0]; 
		}
		List<String> tempStringlist = new ArrayList<>(stringlist);
		String[] strArray = new String[tempStringlist.size()]; 
		int idx = 0;
		for(String strg : tempStringlist) {
			strArray[idx] = strg;
			idx ++;
		} 
		tempStringlist.clear(); 
		return strArray;
	}
	
	public static String removeStringFromStringArray(String arrayString, String tobeRemoveString) {
		
		List<String> contactList = new ArrayList<>();
		
		if(isNotBlank(arrayString)) {
			
			arrayString = StringUtils.strip(arrayString,"\"");
			arrayString = arrayString.replaceAll("\\[", ""); 
			arrayString = arrayString.replaceAll("\\]", "");
			String [] splitArray = arrayString.split(",");
			
			for(String string:splitArray) {
				contactList.add(string.trim()); 
			}
			
		}
		
		log.debug("contactList: "+contactList);
		log.debug("contactList size: "+contactList.size());
		contactList.remove(tobeRemoveString); 
		log.debug("contactList: "+contactList);
		contactList = contactList.stream().distinct().collect(Collectors.toList());
		arrayString = contactList.toString(); 
		 
		return arrayString;
		
		
	}
	
	public static String concat(String strng[]) {
		StringBuilder sbBuilder = new StringBuilder();
		for(String st:strng) {
			sbBuilder.append(st);
		}
	    return sbBuilder.toString().trim();
	}
	
	public static String concat(String delimiter, String strng[]) {
	    return String.join(delimiter, strng).trim();
 	}
	
	public static String concat(String s1, String s2) {
	    return new StringBuilder(s1).append(s2).toString().trim();
	}
	
	public static String concatWithDelimiter(String s1, String s2, String delimiter) { 
	    return new StringBuilder(s1).append(delimiter).append(s2).toString().trim();
	}

	/**
	 * 
	 * @param body
	 * @param keyword
	 * @return
	 */
	public static  boolean isKeyWordMatchExactCase(String body, String keyword) {
		
		boolean match = false;
		  
		if(body ==null || keyword == null) {
			return match;
		}
		
		keyword = keyword.trim();
		
		body = body.trim();
		
        List<String> words = calculatePossibleWords(body);
        
        List<String> subStrings = calculatePossibleSubstrings(words);
		
        match = subStrings.stream().anyMatch(keyword::equals);
        
        words.clear(); 
        subStrings.clear();

       return match;
	}
	
	/**
	 * 
	 * @param body
	 * @param keyword
	 * @return
	 */
	public static  boolean isKeyWordMatchIgnoreCase(String body, String keyword) {
		
		boolean match = false;
		  
		if(body ==null || keyword == null) {
			return match;
		}
		
		keyword = keyword.trim();
		
		body = body.trim();
        
        List<String> words = calculatePossibleWords(body);
        
        List<String> subStrings = calculatePossibleSubstrings(words);
        
        match = subStrings.stream().anyMatch(keyword::equalsIgnoreCase); 
        
        words.clear();
        subStrings.clear();

       return match;
	}
	
	/**
	 * 
	 * @param sentence
	 * @return
	 */
	public static  List<String> calculatePossibleSubStringAndWords(String sentence){
	        return calculatePossibleSubstrings(calculatePossibleWords(sentence));
	}
	
	/**
	 * 
	 * @param sentence
	 * @return
	 */
	public static  List<String> calculatePossibleWords(String sentence){
		 List<String> words = new ArrayList<>();
		 if(null==sentence || sentence.trim().isEmpty()) {
			 return words;
		 }
		 
		 sentence = sentence.trim();
			
		 // Gets an instance of BreakIterator for word break for the
		 // given locale. We can instantiate a BreakIterator without
		 // specifying the locale. The locale is important when we
		 // are working with languages like Japanese or Chinese where
		 // the breaks standard may be different compared to English.
		 BreakIterator bi = BreakIterator.getWordInstance(Locale.US);

		 // Set the text string to be scanned.
		 bi.setText(sentence);

		 // Iterates the boundary / breaks
       
		 int lastIndex = bi.first();
		 while (lastIndex != BreakIterator.DONE && words.size() < MAXIMUM_WORD_COUNT) {
            int firstIndex = lastIndex;
            lastIndex = bi.next();

            if (lastIndex != BreakIterator.DONE
                && Character.isLetterOrDigit(sentence.charAt(firstIndex))) {
                String word = sentence.substring(firstIndex, lastIndex);
                if(null==word) {
                	continue;
                }
            	word = word.trim();
            	words.add(word);
            }
		 }
		 
		return words;
	}
	
	/**
	 * 
	 * @param words
	 * @return
	 */
	public static  List<String> calculatePossibleSubstrings(List<String> words){
		
		 List<String> subStrings = new ArrayList<>();
		 
		 if(null==words || words.isEmpty()) {
			 return subStrings;
		 }
		 
		 subStrings.addAll(words);
		 
		 for(int idx = 1; idx<words.size() && subStrings.size()<MAXIMUM_SUBSTRING_COUNT; idx++) {
			   int tempIdx = idx+1;
			   for(int index = 0; index<words.size() && subStrings.size()<MAXIMUM_SUBSTRING_COUNT; index++) {
				   
				   StringBuilder subString = new StringBuilder();
				   int startIndex = index;
				   
				   int lengthCount = 0;
				   
				   while(lengthCount<tempIdx 
						   && startIndex<words.size() 
						   && MAXIMUM_WORD_COUNT > lengthCount ) {
					   subString.append(words.get(startIndex)); 
					   subString.append(" "); 
					   startIndex ++;
					   lengthCount++;
				   }
				   if(lengthCount == tempIdx) {
					   subStrings.add(subString.toString().trim());
				   }
			   }
		 }
		 
		return subStrings;
		
	}
	
	public static String cleanJSONString(String jsonString) {
		if(null == jsonString) {
			return null;
		}
	    // Remove double quotes at the start and end
		return jsonString.replaceAll("^\"|\"$", "").replaceAll("\\\\", "");
	}
	
	public static String getDottedString(String s, int maxLength, int length) {
		if (s == null || s.length() == 0) {
			return s;
		}
		if (s.length() > maxLength) {
			String[] results = s.split("(?<=\\G.{" + length + "})");
			return results[0] + ".....";
		}
		return s;
	}
	
	public static String removeDashFromUUIDString(String uuidString ) {
		if(null == uuidString) {return null;}
		return uuidString.replace("-", "");
	}
	
	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String get16byteUUID() {
		return UUID.nameUUIDFromBytes(convertByteFromUUId(UUID.randomUUID())).toString();
	}
	
	public static byte[] convertByteFromUUId(UUID uuid){
	    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
	    return bb.array();
	}
	
	public static String capitalize(String str) {
	    if (str == null || str.isBlank()) {
	        return str;
	    }
	    
	    String[] words = str.split("\\s+");
	    StringBuilder capitalized = new StringBuilder();

	    for (String word : words) {
	        if (word.length() > 0) {
	        	capitalized.append(Character.toUpperCase(word.charAt(0)))
	                                .append(word.substring(1).toLowerCase())
	                                .append(" ");
	        }
	    }
	    
	    return capitalized.toString().trim();
	}
	
	public static void main(String[] args) {
		
		String arc = "[ 123,234, , 546 ] ";
		String src = removeStringFromStringArray(arc, "123");
		System.out.println(src); 
		System.out.println("UUID.randomUUID().toString()"+UUID.randomUUID().toString());
		System.out.println(get16byteUUID());
		System.out.println(removeDashFromUUIDString(UUID.randomUUID().toString())); 
	        
	}

}
