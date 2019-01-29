package br.com.engecopi.trayServer

import com.nitgen.SDK.BSP.NBioBSPJNI

class Nitgen {
  private val errors = mapOf(0 ->"Nenhum erro",
                              1 -> "Invalid handle")
  var statusMessage: String? = null
    private set
  var fingerkey: String? = null
    private set
  private var digitalCapturadaStatusDispositivo: Int = 0
  private val UserID: Int
  private val bsp: NBioBSPJNI
  private var deviceEnumInfo: NBioBSPJNI.DEVICE_ENUM_INFO? = null
  private val IndexSearchEngine: NBioBSPJNI.IndexSearch? = null
  private var hasError = false
  private var hardwareInicializado = false
  private var dispositivoAberto = false
  val deviceNameID: Short
    get() = if (this.hardwareInicializado)
      this.deviceEnumInfo!!.DeviceInfo[0].NameID
    else
      0
  val deviceName: String?
    get() = if (this.hardwareInicializado)
      this.deviceEnumInfo!!.DeviceInfo[0].Name
    else
      null
  val lastErrorCode: Int
    get() = if (this.hasError)
      this.bsp.GetErrorCode()
    else
      0
  val lastErrorMessage: String
    get() = this.errors[this.bsp.GetErrorCode()]
  val version: String
    get() = this.bsp.GetVersion()

  init {
    this.deviceEnumInfo = null
    this.objIndex = null
    this.operacaoExecutada = false
    this.statusMessage = ""
    this.digitalCapturadaStatusDispositivo = 0
    this.UserID = 0
    this.fingerkey = ""

    this.bsp = NBioBSPJNI()
    if (checkError()) {
      expection(Nitgen.DEBUG_PREFIX + "Erro ao criar objeto NBioBSPJNI")
    }

    this.deviceEnumInfo = this.bsp.DEVICE_ENUM_INFO7777()
    this.bsp.EnumerateDevice(this.deviceEnumInfo)

    if (checkError()) {
      println(Nitgen.DEBUG_PREFIX + "Erro ao enumerar device")
      throw Exception()
    }
    val n = this.deviceEnumInfo!!.DeviceCount

    if (n == 0) {
      println(Nitgen.DEBUG_PREFIX + "Componente não iniciado")
      throw Exception()
    } else {
      println(Nitgen.DEBUG_PREFIX + "Dispositivo Liberado")
    }

    this.hardwareInicializado = true
    println(Nitgen.DEBUG_PREFIX + "Dispositivo Iniciado com Sucesso")
  }

  fun openDevice(): Boolean {
    return if (this.hardwareInicializado) {
      this.bsp.OpenDevice(this.deviceEnumInfo!!.DeviceInfo[0].NameID, this.deviceEnumInfo!!.DeviceInfo[0].Instance)
      this.dispositivoAberto = true
      println(Nitgen.DEBUG_PREFIX + "Dispositivo Liberado")
      true
    } else {
      println(Nitgen.DEBUG_PREFIX + "Dispositivo Desconectado")
      false
    }
  }

  fun closeDevice() {
    this.bsp.CloseDevice(this.deviceEnumInfo!!.DeviceInfo[0].NameID, this.deviceEnumInfo!!.DeviceInfo[0].Instance)
    this.dispositivoAberto = false
  }

  fun enrollStringKey(): Boolean {
    //Inicialização e instanciações
    this.statusMessage = ""
    this.operacaoExecutada = false
    this.fingerkey = null

    if (!this.hardwareInicializado || !this.dispositivoAberto) {
      this.statusMessage = "Dispositivo não foi inicializado ou não está conectado"
    }
    val fir_handle = this.bsp.FIR_HANDLE()
    this.objIndex = this.bsp.IndexSearch()
    var textSavedFIR: NBioBSPJNI.FIR_TEXTENCODE? = null
    var fingerPlaced: Boolean = false
    this.bsp.CheckFinger(fingerPlaced)

    if (fingerPlaced) {
      //Captura a digital
      this.bsp.Capture(fir_handle)
      //Obtem a digital capturada em modo texto
      if (!this.bsp.IsErrorOccured()) {
        textSavedFIR = this.bsp.FIR_TEXTENCODE()
        this.digitalCapturadaStatusDispositivo = this.bsp.GetTextFIRFromHandle(fir_handle, textSavedFIR)
        this.fingerkey = textSavedFIR.TextFIR
        this.operacaoExecutada = true
      } else {
        this.operacaoExecutada = false
        println("Erro na captura e/ou dispositivo")
        this.fingerkey = null
        //setUserID(0);
      }
    } else {
      // setUserID(0);
      if (this.hardwareInicializado) {
        this.statusMessage = "Não foi detectada presença de digitais no dispositivo"
        println("Nenhum dedo no dispositivo")
      } else {
        this.statusMessage = "Dispositivo não foi inicializado ou não está conectado"
        println("Dispositivo Desconectado")
      }
    }

    return this.operacaoExecutada
  }

  fun checkError(): Boolean {
    this.hasError = bsp.IsErrorOccured()

    if (this.hasError) {
      System.err.println(Nitgen.DEBUG_PREFIX + this.lastErrorMessage)
    }

    return this.hasError
  }

  companion object {
    private val DEBUG_PREFIX = "WINPONTA NITGEN - DEBUG :: "
  }
}
