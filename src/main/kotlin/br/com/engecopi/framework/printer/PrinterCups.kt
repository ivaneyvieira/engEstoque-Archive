package br.com.engecopi.framework.printer

import org.cups4j.CupsClient
import org.cups4j.CupsPrinter
import org.cups4j.PrintJob

class PrinterCups(host: String) {
  val cupsClient = CupsClient(host, 631)
  
  val printers
    get() = cupsClient.printers
}

fun CupsPrinter.print(text: String) {
  val job = PrintJob.Builder(text.toByteArray()).build()
  print(job)
}
