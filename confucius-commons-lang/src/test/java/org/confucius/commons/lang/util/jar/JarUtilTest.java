/**
 * AliExpress.com. Copyright (c) 2010-2015 All Rights Reserved.
 */
package org.confucius.commons.lang.util.jar;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.lang3.SystemUtils;
import org.confucius.commons.lang.ClassLoaderUtil;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.jar.JarFile;

/**
 * {@link JarUtil} {@link TestCase}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @version 1.0.0
 * @see JarUtilTest
 * @since 1.0.0
 */
public class JarUtilTest {

    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


    @Test
    public void testResolveRelativePath() {
        URL resourceURL = ClassLoaderUtil.getClassResource(classLoader, String.class);
        String relativePath = JarUtil.resolveRelativePath(resourceURL);
        String expectedPath = "java/lang/String.class";
        Assert.assertEquals(expectedPath, relativePath);
    }

    @Test
    public void testResolveJarAbsolutePath() throws Exception {
        URL resourceURL = ClassLoaderUtil.getClassResource(classLoader, String.class);
        String jarAbsolutePath = JarUtil.resolveJarAbsolutePath(resourceURL);
        File rtJarFile = new File(SystemUtils.JAVA_HOME, "/lib/rt.jar");
        Assert.assertNotNull(jarAbsolutePath);
        Assert.assertEquals(rtJarFile.getAbsolutePath(), jarAbsolutePath);

        URL url = new URL("http://www.google.com");
        jarAbsolutePath = JarUtil.resolveJarAbsolutePath(url);
        Assert.assertNull(jarAbsolutePath);
    }


    @Test
    public void testToJarFile() throws Exception {
        URL resourceURL = ClassLoaderUtil.getClassResource(classLoader, String.class);
        JarFile jarFile = JarUtil.toJarFile(resourceURL);
        JarFile rtJarFile = new JarFile(new File(SystemUtils.JAVA_HOME, "/lib/rt.jar"));
        Assert.assertNotNull(jarFile);
        Assert.assertEquals(rtJarFile.getName(), jarFile.getName());

        URL url = new URL("http://www.google.com");
        jarFile = JarUtil.toJarFile(url);
        Assert.assertNull(jarFile);
    }
}