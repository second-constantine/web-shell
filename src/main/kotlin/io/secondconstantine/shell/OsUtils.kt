package io.secondconstantine.shell

object OsUtils {

    @JvmStatic
    fun isWindows() = System.getProperty("os.name").startsWith("Windows")
}