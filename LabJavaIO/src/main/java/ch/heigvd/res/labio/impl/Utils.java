package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments.
   *
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    char[] chars = lines.toCharArray();

    String nextLine = "";
    for (int i = 0; i < chars.length; i++) {
      nextLine = nextLine + chars[i];
      if (chars[i] == '\n' || chars[i] == '\r') {
          if(i != chars.length-1 && chars[i+1] == '\n'){
            nextLine = nextLine + chars[i+1];
            String[] result = {nextLine, lines.substring(i+2, chars.length)};
            return result;
          } else {
            String [] result = {nextLine, lines.substring(i+1, chars.length)};
            return result;
          }

      }
    }
    String[] result = {"",lines};
    return result;

  }
}
