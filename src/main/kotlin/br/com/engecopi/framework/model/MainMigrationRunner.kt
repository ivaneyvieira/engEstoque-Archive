package br.com.engecopi.framework.model

import io.ebean.migration.MigrationConfig
import io.ebean.migration.MigrationRunner
import java.io.File
import java.io.IOException
import java.util.*

object MainMigrationRunner {
  @Throws(IOException::class)
  @JvmStatic
  fun main(args: Array<String>) {
    // System.setProperty("ddl.migration.generate", "true")
    val home = System.getenv("HOME")
    val fileName = System.getenv("EBEAN_PROPS") ?: "$home/ebean.properties"
    val properties = Properties()
    properties.load(File(fileName).reader())
    val config = MigrationConfig()
    config.load(properties)
    val runner = MigrationRunner(config)
    runner.run()
  }
}
