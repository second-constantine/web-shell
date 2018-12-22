package io.secondconstantine.shell

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


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
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val errorReader = BufferedReader(InputStreamReader(process.errorStream))
            reader.lines().limit(LIMIT).forEach {
                output.append(it).append("\n")
            }
            errorReader.lines().limit(LIMIT).forEach {
                output.append(it).append("\n")
            }
        } catch (ie: InterruptedException) {
            output.append(ie.toString())
        } catch (io: IOException) {
            output.append(io.toString())
        }
        return output.toString()
    }

    private const val LIMIT = 200L
}