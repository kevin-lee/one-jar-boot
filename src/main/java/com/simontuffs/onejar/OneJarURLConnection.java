/*
 * Copyright (c) 2004-2010, P. Simon Tuffs (simon@simontuffs.com)
 * All rights reserved.
 * 
 * See the full license at http://one-jar.sourceforge.net/one-jar-license.html
 * This license is also included in the distributions of this software
 * under doc/one-jar-license.txt
 * 
 * Contributor: sebastian : http://code.google.com/u/@WBZRRlBYBxZHXQl9/ 
 *   Original creator of the OneJarFile/OneJarUrlConnecion solution to resource location
 *   using jar protocols.
 *                   
 */

package com.simontuffs.onejar;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;

public class OneJarURLConnection extends JarURLConnection {

  private static final String EXCLAMATION_DOUBLE_SLASH = "!//";
  public static final String EXCLAMATION_SLASH = "!/";

  private JarFile jarFile;

  private static URL fromBangDoubleSlashToBangSlash(URL url) throws MalformedURLException {
    if (url.getFile().contains(EXCLAMATION_DOUBLE_SLASH)) {
      return new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile().replaceAll(EXCLAMATION_DOUBLE_SLASH, EXCLAMATION_SLASH));
    }
    return url;
  }

  public OneJarURLConnection(URL url) throws MalformedURLException {
    super(fromBangDoubleSlashToBangSlash(url));
//    super(url);
  }

  @Override
  public JarFile getJarFile() throws IOException {
    return jarFile;
  }

  @Override
  public void connect() throws IOException {
    String jarWithContent = getEntryName();
    int separator = jarWithContent.indexOf(EXCLAMATION_SLASH);
    // Handle the case where a URL points to the top-level jar file, i.e. no '!/' separator.
    if (separator >= 0) {
      final String jarFilename = jarWithContent.substring(0, separator++);
      final String filename = jarWithContent.substring(++separator);
      jarFile = new OneJarFile(Boot.getMyJarPath(), jarFilename, filename);
    } else {
      // Entry in the top-level One-JAR.
      jarFile = new JarFile(Boot.getMyJarPath());
    }
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return jarFile.getInputStream(jarFile.getJarEntry(getEntryName()));
  }

}
