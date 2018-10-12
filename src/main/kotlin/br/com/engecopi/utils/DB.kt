package br.com.engecopi.utils

import java.io.StringReader
import java.util.*

class DB(banco: String) {
  
  private val prop = properties()
  val driver = prop?.getProperty("datasource.$banco.databaseDriver") ?: ""
  val url = prop?.getProperty("datasource.$banco.databaseUrl") ?: ""
  val username = prop?.getProperty("datasource.$banco.username") ?: ""
  val password = prop?.getProperty("datasource.$banco.password") ?: ""

  companion object {
    private const val propertieFile = "/ebean.properties"
    
    private fun properties(): Properties? {
      val properties = Properties()
      val configFile = SystemUtils.readFile(propertieFile)
      properties.load(StringReader(configFile))
      return properties
    }
  }
}