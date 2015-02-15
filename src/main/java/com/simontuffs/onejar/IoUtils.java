package com.simontuffs.onejar;

import java.io.*;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (15/02/2015)
 */
public final class IoUtils {
  private final static Logger LOGGER = Logger.getLogger("IoUtils");

  private IoUtils() throws IllegalAccessException {
    throw new IllegalAccessException(IoUtils.class.getName() + " cannot be instantiated.");
  }

  /**
   * Closes the given closeable without throwing any exception if it is not null.
   * If it throws any exception, logs it but doesn't re-throw it.
   *
   * @param closeable Closeable to be closed.
   */
  public static void closeQuietly(final Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (Exception e) {
        final StringWriter out = new StringWriter();
        final PrintWriter writer = new PrintWriter(out);
        e.printStackTrace(writer);
        LOGGER.warning(out.toString());
      }
    }
  }

  /**
   * Utility to assist with copying InputStream to OutputStream.  All
   * bytes are copied, but both streams are left open.
   *
   * @param in  Source of bytes to copy.
   * @param out Destination of bytes to copy.
   * @throws IOException
   */
  public static void copy(final InputStream in, OutputStream out) throws IOException {
    final byte[] buf = new byte[1024];
    int len = in.read(buf);
    while (len >= 0) {
      out.write(buf, 0, len);
      len = in.read(buf);
    }
  }
}
