package br.com.engecopi.trayServer

import java.awt.AWTException
import java.awt.CheckboxMenuItem
import java.awt.Image
import java.awt.MenuItem
import java.awt.PopupMenu
import java.awt.SystemTray
import java.awt.TrayIcon
import java.io.IOException
import java.io.PrintWriter
import java.net.ServerSocket
import java.util.*
import javax.swing.ImageIcon
import com.nitgen.SDK.BSP.NBioBSPJNI

fun main(args: Array<String>) {
  val main = Main()
  main.start()
}

class Main {
  var tray: SystemTray? = null
  var trayIcon: TrayIcon? = null
  private val PORT = 20999
  private var serverSocket: ServerSocket? = null

  init {
    if (!SystemTray.isSupported()) {
      println("SystemTray is not supported")
    } else {
      tray = SystemTray.getSystemTray()
      val imagem = createImage("/img/finger-icon.png", "Tray icon")
      trayIcon = TrayIcon(imagem)
      // Create a pop-up menu components
      trayIcon?.popupMenu = createPopup()
    }
  }

  //Obtain the image URL
  private fun createImage(path: String, description: String): Image? {
    val imageURL = Main::class.java.getResource(path)

    return if (imageURL == null) {
      System.err.println("Resource not found: $path")
      null
    } else {
      tray?.let { tray ->
        val trayIconSize = tray.trayIconSize
        val trayImage = ImageIcon(imageURL, description).image
        trayImage.getScaledInstance(trayIconSize.width,
                                    trayIconSize.height,
                                    Image.SCALE_SMOOTH);
      }
    }
  }

  fun start() {
    try {
      tray?.add(trayIcon)
      startServer()
    } catch (e: AWTException) {
      println("TrayIcon could not be added.")
    }
  }

  fun startServer() {
    try {
      serverSocket = ServerSocket(PORT)
      serverSocket?.use { serverSocket ->
        System.out.println("Server is listening on port $PORT")

        while (!serverSocket.isClosed) {
          val socket = serverSocket.accept()
          println("New client connected")
          val output = socket.getOutputStream()
          val writer = PrintWriter(output, true)
          val template = lerDigital()
          writer.println(template)
          socket.close()
        }
      }
    } catch (ex: IOException) {
      println("Server exception: " + ex.message)
      ex.printStackTrace()
    }
  }

  private fun lerDigital(): String? {
    val bsp = NBioBSPJNI()
    println("bsp iniciado")
    return if (bsp.IsErrorOccured())
      "NBioBSP Error Occured [" + bsp.GetErrorCode() + "]"
    else "Tudo Ok"
  }

  fun createPopup() = PopupMenu().apply {
    val menuSair = MenuItem("Sair")
    menuSair.addActionListener {
      tray?.remove(trayIcon)
      serverSocket?.close()
    }
    val enable = CheckboxMenuItem("Habilitado")
    add(enable)
    addSeparator()
    add(menuSair)
  }
}