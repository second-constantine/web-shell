package by.next.way.shell

import java.io.IOException


object Shell {

    fun exec(command: String): String {
        val output = StringBuilder()
        try {
            val process = Runtime.getRuntime().exec(
                    if (OsUtils.isWindows()) {
                        "cmd /C ${command.replace("cmd", "", true)}"
                    } else {
                        command
                    }
            )
            output.append(process.inputStream.bufferedReader().use { it.readText() })
            output.append(process.errorStream.bufferedReader().use { it.readText() })
        } catch (ie: InterruptedException) {
            output.append(ie.toString())
        } catch (io: IOException) {
            output.append(io.toString())
        }
        return output.toString()
    }
}