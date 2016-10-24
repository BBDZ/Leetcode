package amazon_oa1;

public class longestpralindr {

    //solve this longest palindrome problem with Manacher algorithm
    //time complexity o(n)
    //space complexity o(n)
    
    public static String longestPalindrome(String s) {
        //corner case
        if(s == null || s.length() == 0) {
            return s;
        }
        
        String newString = preprocess(s);
        
        int newLength = newString.length();
        int[] radius = new int[newLength];
        //System.out.println(radius[13]);
        //center is the middle of palindrome
        //right is the longest postion that can touch for current index 
        int center = 0;
        int right = 0;
        

        for(int i = 1; i < newLength - 1; i++) {
            
            
            // the index symmetric to i based on center
            int mirror = 2 * center - i;
            
            // the position of will be larger than center but on either left or right side of right
            //if i in left of right tha two cases:
            //i is min value of the lenght at j or the length bewtween i and j
            
            radius[i] = right > i ? Math.min(radius[mirror], right - i) : 0;
            
            
            //extend from i and update if it is palindrome and the radius exceed  privous right bound
            while(newString.charAt(i - 1 - radius[i]) == newString.charAt(i + 1 + radius[i])) {
                radius[i]++;
            }
            if(i + radius[i] > right) {
                right = center + radius[i];
                center = i;
            }
        }
        //find the maxlength palindrome and the center from original string 
        int centerIndex = 0;
        int maxLength = 0;
        for(int i = 1; i < newLength - 1; i++) {
            if(radius[i] > maxLength) {
                maxLength = radius[i];
                centerIndex = i;
            }
        }
        
        int start = (centerIndex - maxLength - 1) / 2; 
        int end = start + maxLength;
        return s.substring(start, end);
    }
    
    
    //
    private static String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        if(s.length() == 0) {
            sb.append("^$");
            return sb.toString();
        }
        //start with "^", ends with "$"
        //sperate char with "#"
        sb.append("^");
        for(int i = 0 ; i < s.length(); i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        System.out.println(sb);
        return sb.toString();
        
    }	

    
		public static void main(String[] args){
			
			System.out.println(longestpralindr.longestPalindrome("aecbcdd"));
			
		}
	}

