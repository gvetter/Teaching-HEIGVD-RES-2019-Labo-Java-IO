package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    char[] cbuf = str.toCharArray();
    for(int i = off; i < off+len; i++){
      if(cbuf[i] > 96 && cbuf[i] < 123){
        this.out.write(cbuf[i]-32);
      } else {
        this.out.write(cbuf[i]);
      }
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off+len; i++){
      if(cbuf[i] > 96 && cbuf[i] < 123){
        this.out.write(cbuf[i]-32);
      } else {
        this.out.write(cbuf[i]);
      }
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(c > 96 && c < 123){
      this.out.write(c-32);
    } else {
      this.out.write(c);
    }
  }

}
