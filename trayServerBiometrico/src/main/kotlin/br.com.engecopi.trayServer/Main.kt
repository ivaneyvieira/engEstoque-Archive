package br.com.engecopi.trayServer

import com.nitgen.SDK.BSP.NBioBSPJNI
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
import javax.swing.ImageIcon

fun main(args: Array<String>) {
  val main = Main()
  main.start()
}

class Main {
  var tray: SystemTray? = null
  var trayIcon: TrayIcon? = null
  private val PORT = 20999
  private var serverSocket: ServerSocket? = null
  val nitgen = Nitgen()

  init {
    if (!SystemTray.isSuppo555rted()) {
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
          println("Accept ....")
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
    if (bsp.IsErrorOccured())
      "NBioBSP Error Occured [" + bsp.GetErrorCode() + "]"
    val deviceEnumInfo = bsp.DEVICE_ENUM_INFO()
    bsp.EnumerateDevice(deviceEnumInfo)
    // Device Open
    val nameId = deviceEnumInfo.DeviceInfo[0].NameID
    val instance = deviceEnumInfo.DeviceInfo[0].Instance
    bsp.OpenDevice(nameId, instance)
    /*
      val inputFIR = bsp.INPUT_FIR()
      val bResult = false
      val payload = bsp.FIR_PAYLOAD()
      val result = payload.GetText()
      */

    /*
   val  hSavedFIR = bsp.FIR_HANDLE()

    bsp.Enroll(hSavedFIR, null)
    bsp.Capture(hSavedFIR)

    var textSavedFIR: NBioBSPJNI.FIR_TEXTENCODE? = null
    var result : String? = ""
    if (bsp.IsErrorOccured() == false) {
      val textSavedFIR = bsp.FIR_TEXTENCODE ()
      bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR)
      result = textSavedFIR.TextFIR
    }*/
/*
    var result : String? = null
    val textSavedFIR = bsp.FIR_TEXTENCODE ()
    val inputFIR = bsp.INPUT_FIR()
    val bResult = false
    val payload = bsp.FIR_PAYLOAD()
    // Set stored textFIR data.
    inputFIR.SetTextFIR(textSavedFIR)
    bsp.Verify(inputFIR, bResult, payload)

    if (bsp.IsErrorOccured() == false) {

        result = "Verify OK - Payload: " + payload.GetText()

    }
*/

    var result : String? = ""

    val fir_handle = bsp.FIR_HANDLE()
    var textSavedFIR: NBioBSPJNI.FIR_TEXTENCODE? = null
    var fingerPlaced: Boolean = true
    bsp.CheckFinger(fingerPlaced)
    if (fingerPlaced) {
      //Captura a digital
      bsp.Capture(fir_handle)
      //Obtem a digital capturada em modo texto
      if (!bsp.IsErrorOccured()) {
        textSavedFIR = bsp.FIR_TEXTENCODE()
        val digitalCapturadaStatusDispositivo = bsp.GetTextFIRFromHandle(fir_handle, textSavedFIR)
        result = textSavedFIR.TextFIR
        //                }
      } else {
        result = "Erro na captura e/ou dispositivo"
      }
    } else {
      result = "Erro na captura e/ou dispositivo"
    }


    // Device Close
    bsp.CloseDevice(nameId, instance)

    println("Name $nameId")
    println("Instance $instance")

    bsp.dispose()
    return result
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