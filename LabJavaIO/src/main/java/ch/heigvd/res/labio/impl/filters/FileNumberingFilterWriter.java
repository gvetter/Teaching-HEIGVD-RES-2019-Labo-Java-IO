package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }
  private int count = 0;
  private boolean isRN = false;

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(count == 0){
      count++;
      this.out.write(count + "\t");
    }
    if(isRN){
      if(c != '\n') {
        count++;
        isRN = false;
        this.out.write("\r");
        this.out.write(count + "\t");
        this.out.write(c);
      } else {
        this.out.write("\r" + "\n");
        count++;
        isRN = false;
        this.out.write(count + "\t");
      }
    } else {
      if (c == '\r') {
        isRN = true;
      } else {
        if (c == '\n') {
          count++;
          this.out.write(c);
          this.out.write(count + "\t");
        } else {
          this.out.write(c);
        }
      }
    }
  }


}
